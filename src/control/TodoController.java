package control;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TodoController {
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
    String user = "root";
    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public TodoController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
        }
    }

    /**
     * 할 일 목록 가져오기
     */
    public Map<Integer, String> getTodoList(Integer chatId) {
        String sql = "select todo_id, title from todo where chat_id='"+chatId+"';";
        PreparedStatement pstmt = null;
        Map<Integer, String> todos = new HashMap<>();
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                todos.put(resultSet.getInt("todo_id"), resultSet.getString("title"));
            }
        } catch (SQLException e){
            System.out.println("할 일 목록 가져오기 실패");
            e.printStackTrace();
        }
        System.out.println("할 일 목록 가져오기 성공");
        return todos;
    }


    /**
     * 할 일 추가
     */
    public Integer addTodo(Integer chat_id, String todoTitle){
        String sql = "insert into todo (chat_id, title) values(?, ?);";
        PreparedStatement pstmt = null;

        try{
            if (checkTodoDuplicate(chat_id, todoTitle)) {
                System.out.println("이미 등록된 할일");
                return 2;
            }
            pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, chat_id);
            pstmt.setString(2, todoTitle);
            pstmt.executeUpdate();
            System.out.println("할 일 추가 성공");
            return 1;
        } catch (SQLException e){
            e.printStackTrace();
            return 3;
        }
    }

    /**
     * 할 일 존재하는지 확인
     */
    public Boolean checkTodoDuplicate (Integer chatId, String title){
        String sql = "select EXISTS (select todo_id from todo " +
                "where chat_id='"+chatId+"' and title='"+title+"' limit 1) as success;";
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
     * 할 일 완료(삭제)
     */
    public Boolean finishTodo(Integer todoId) {
        String sql = "delete from todo where todo_id='"+todoId+"';";
        PreparedStatement pstmt = null;
        try {
            pstmt=connection.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("할 일 삭제 성공");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


}
