package control;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimerController {
    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://localhost/tudy_chat?serverTimezone=UTC";
    String user = "root";
    String password = "dasol";
    String driverName = "com.mysql.cj.jdbc.Driver";

    public TimerController() {
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
        } catch (Exception e){
            System.out.println("MySQL 연동 실패");
        }
    }

    /**
     * 저장된 시간 불러오기 (한달)
     */
    public Map<String, String> getStudyTimeForMonth(String id){
        String sql="select * from study_time where (today between DATE_ADD(NOW(),INTERVAL -1 MONTH ) AND NOW() and user_id='"+id+"') order by today;";
        PreparedStatement pstmt = null;
        Map<String, String> result = new HashMap<>();
        try{
            pstmt=connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result.put(resultSet.getString("today"), resultSet.getString("study_time"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 시간 저장
     */
    public Boolean setStudyTime(String id, String today, String studyTime) throws SQLException {
        String sql = "insert into study_time (user_id, today, study_time) values(?, ?, ?);";
        PreparedStatement pstmt = null;
        try{
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, today);
            pstmt.setString(3,studyTime);
            pstmt.executeUpdate();
            System.out.println("공부시간 추가 성공!");
            return true;
        } catch (Exception e){
            System.out.println("공부시간 추가 실패..");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 시간 측정 다음날로 초과
     */
    public String getOverTime(String now, String studyTime){
        String result=null;
//        LocalDateTime current = LocalDateTime(now)-LocalDateTime() //현재시간-자정
//        LocalDateTime overTime = LocalDateTime(studyTime)- current;
        return result;
    }

    
    /**
     * 당일 측정시간 공유
     * 채팅방 아이디 받아와서 보내기
     */
    

}
