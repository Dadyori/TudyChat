package control;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChatRoomController {
    MemberController memberController = new MemberController();
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
    String user = "root";
    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public ChatRoomController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
        }
    }

    /**
     * 채팅방 생성
     */
    public Boolean addChatRoom(String chatTitle, List<String> members){
        String sql = "insert into chat_room (chat_name, chat_num) values(?, ?);";
        PreparedStatement pstmt = null;
        try{
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, chatTitle);
            pstmt.setInt(2, members.size());
            pstmt.executeUpdate();
            System.out.println("채팅방 추가 성공");
            if (addChatMember(chatTitle, members)) return true;
        } catch (SQLException e){
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
    public LinkedHashMap<String, String> getChatMessage(Integer chatId) {
        String sql = "select user.user_name, message.message from message join user on message.user_id=user.user_id" +
                " where message.room_id ="+chatId+" order by createdAt;";
        LinkedHashMap<String, String> messages = new LinkedHashMap<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                messages.put(resultSet.getString("user_name"), resultSet.getString("message"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

}
