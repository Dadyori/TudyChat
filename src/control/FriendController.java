package control;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendController {
    MemberController memberController = new MemberController();
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
    String user = "root";
    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public FriendController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
        }
    }


    /**
     * 친구목록 가져오기
     */
    public List<String> getFriends(String id){
        String sql = "select friend_id from friend where user_id='"+id+"' or friend_id='"+id+"';";
        PreparedStatement pstmt = null;
        ArrayList<String> friends = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                friends.add(resultSet.getString("friend_id"));
            }
        }catch (SQLException e){
            System.out.println("친구목록 가져오기 실패");
            e.printStackTrace();
        }
        System.out.println("친구 목록 가져오기 성공 >> "+friends);
        return friends;
    }


    /**
     * 친구 추가
     */
    public int addFriend(String userId, String friendId){
        String sql = "insert into friend (user_id, friend_id) values(?, ?);";
        PreparedStatement pstmt = null;
        try {
            if (checkFriendDuplicate(userId, friendId)) {
                System.out.println("이미 등록된 친구");
                return 2;
            }
            if (memberController.checkDuplicate(friendId)){
                pstmt=connection.prepareStatement(sql);
                pstmt.setString(1, userId);
                pstmt.setString(2, friendId);
                pstmt.executeUpdate();
                System.out.println("친구추가 성공!");
                return 1;
            }
            else {
                System.out.println("가입되지 않은 회원 친구추가 시도");
                return 3;
            }
        } catch (Exception e){
            e.printStackTrace();
            return 4;
        }
    }


    /**
     * 이미 친구인지 확인
     */
    public boolean checkFriendDuplicate(String memberId, String friendId){
        String sql = "select EXISTS (select friend_id from friend where friend_id='"+friendId+"' limit 1) as success;";
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
     * 친구상태 확인
     */
    public Map<String, Boolean> getFriendStatus(List<String> friends){
        Map<String, Boolean> resultStatus=new HashMap<>();

        for (String friendId : friends) {
            String sql = "select is_connect from connect_state where user_id='"+friendId+"';";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
                while (resultSet.next()){
                    resultStatus.put(friendId, resultSet.getBoolean("is_connect"));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return resultStatus;
    }

}
