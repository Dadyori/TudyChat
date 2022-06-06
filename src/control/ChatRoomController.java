package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
     * 채팅방 생성하고 멤버 연결
     */

    /**
     * 기존 메시지 가져오기
     */




}
