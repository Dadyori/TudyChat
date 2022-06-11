package control;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ChatRoomController {
    MemberController memberController = new MemberController();
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://211.202.97.118:3306/tudy_chat?serverTimezone=UTC";
    String user = "newuser";
    String password = "1234";
//    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
//    String user = "root";
//    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public ChatRoomController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
            e.printStackTrace();
        }
    }

    public Boolean titleDuplicate(String title) {
        String sql = "select EXISTS (select chat_name from chat_room where chat_name='"+title+"' limit 1) as success;";
        PreparedStatement pstmt = null;
        boolean success=false;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                success = resultSet.getBoolean("success");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 채팅방 생성
     */
    public Integer addChatRoom(String chatTitle, List<String> members){
        if (titleDuplicate(chatTitle)) return 2;
        String sql = "insert into chat_room (chat_name, chat_num) values(?, ?);";
        PreparedStatement pstmt = null;
        try{
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, chatTitle);
            pstmt.setInt(2, members.size());
            pstmt.executeUpdate();
            System.out.println("채팅방 추가 성공");
            if (addChatMember(chatTitle, members)) return 1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 3;
    }

    /**
     * 메시지 보내기
     */
    public Boolean sendMessage(Integer chatId, String userId, String message) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String sql = "insert into message (room_id, user_id, message) values(?, ?, ?);";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, chatId);
            pstmt.setString(2, userId);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
            System.out.println("메시지 전송 성공");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 채팅방에 멤버 추가
     */
    public Boolean addChatMember(String chatTitle, List<String> members){
        String sql = "select id from chat_room where chat_name = '"+chatTitle+"';";
        Integer chatId = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                chatId=resultSet.getInt("id");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        String sql2 = "insert into chat_member (room_id, user_id) values(?, ?);";
        try{
            pstmt=connection.prepareStatement(sql2);
            Boolean successUpdate=true;
            for (String member : members) {
                pstmt.setInt(1, chatId);
                pstmt.setString(2, member);
                pstmt.executeUpdate();
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 채팅방 목록 가져오기
     */
    public List<Integer> getChatRoomList(String userId) {
        String sql = "select room_id from chat_member where user_id='"+userId+"';";
        List<Integer> chatRoomList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                chatRoomList.add(resultSet.getInt("room_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return chatRoomList;
    }

    /**
     * 채팅방 이름으로 아이디 얻기
     */
    public Integer getRoomId (String title) {
        String sql = "select id from chat_room where chat_name='"+title+"';";
        PreparedStatement pstmt = null;
        Integer result=null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result=resultSet.getInt("id");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 채팅방 아이디, 이름 목록 얻기
     */
    public Map<String, String> getRoomInfo(Integer roomId) {
        String sql = "select id, chat_name from chat_room where id='"+roomId+"';";
        Map<String, String> roomInfo = new HashMap<>();
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                roomInfo.put("roomId", resultSet.getString("id"));
                roomInfo.put("title", resultSet.getString("chat_name"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return roomInfo;
    }



    /**
     * 채팅방 참여중인 멤버 가져오기
     */
    public List<String> getChatRoomMember(Integer chatId) {
        String sql = "select user_id from chat_member where room_id='"+chatId+"';";
        List<String> members = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                members.add(resultSet.getString("user_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return members;
    }


    /**
     * 기존 메시지 가져오기
     */
    public List<String> getChatMessage(Integer chatId) {
        String sql = "select user.user_name, message.message from message join user on message.user_id=user.user_id" +
                " where message.room_id ="+chatId+" order by createdAt;";
        List<String> messages = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String messageInfo="";
                messageInfo=messageInfo+resultSet.getString("user_name");
                messageInfo=messageInfo+" >> ";
                messageInfo=messageInfo+resultSet.getString("message");
                messages.add(messageInfo);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

}
