package control;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MemberController {
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://211.202.97.118/tudy_chat?serverTimezone=UTC";
    String user = "newuser";
    String password = "1234";
//    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
//    String user = "root";
//    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public MemberController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
        }
    }

    /**
     * 회원가입
     */
    public boolean signup(String id, String password, String name, String email){
        String sql = "insert into user (user_id, user_pw, user_name, email) values(?, ?, ?, ?);";
        String sql2 = "insert into connect_state (user_id, is_connect) values(?, ?);";
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            pstmt=connection.prepareStatement(sql2);
            pstmt.setString(1, id);
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();
            System.out.println("회원가입성공!");
            return true;
        } catch (Exception e){
            System.out.println("회원가입 실패..");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 아이디 중복확인
     */
    public boolean checkDuplicate(String id){
        String sql = "select EXISTS (select user_id from user where user_id='"+id+"' limit 1) as success;";
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
     * 로그인
     */
    public int login(String id, String pw){
        String sql = "select user_id, user_pw from user where user_id='"+id+"';";
        String sql2 = "update connect_state set is_connect=1 where user_id='"+id+"';";
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                String password = resultSet.getString("user_pw");
                if (pw.equals(password)){
                    pstmt=connection.prepareStatement(sql2);
                    pstmt.executeUpdate();
                    System.out.println("로그인 성공!");
                    return 1;
                }
                else {
                    System.out.println("비밀번호 틀림");
                    return 2;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("아이디 존재하지 않음");
        return 3;
    }

    /**
     * 로그아웃
     */
    public boolean logout(String id){
        String sql = "update connect_state set is_connect=0 where user_id='"+id+"';";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("로그아웃 성공!");
        } catch (Exception e){
            System.out.println("로그아웃 실패");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 상태 확인
     */
    public boolean checkStatus(String id) {
        String sql = "select is_connect from connect_state where user_id='"+id+"';";
        PreparedStatement pstmt = null;
        Boolean isConnect=false;
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                isConnect = resultSet.getBoolean("is_connect");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return isConnect;
    }

    /**
     * 아이디, 이름, 상태 가져오기
     */
    public Map<String, String> getUserInfo(String id) {
        String sql = "select user.user_id, user.user_name, connect_state.is_connect " +
                "from user join connect_state on user.user_id=connect_state.user_id " +
                "where user.user_id='"+id+"';";
        PreparedStatement pstmt = null;
        Map<String, String> userInfo = new HashMap<>();
        try {
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                userInfo.put("id", resultSet.getString("user_id"));
                userInfo.put("name", resultSet.getString("user_name"));
                userInfo.put("status", resultSet.getString("is_connect")); //1,0
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userInfo;
    }
}
