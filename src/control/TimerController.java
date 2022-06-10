package control;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
     * 저장된 시간 불러오기 (3주)
     */
    public Map<String, String> getStudyTimeForMonth(String id){
        String sql = "select today, study_time from study_time " +
                "where (today between date_add(now(), interval -20 day) and now() " +
                "and user_id='"+id+"') order by today;";
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
        String sql2 = "select study_time from study_time where user_id='"+id+"' and today='"+today+"';";
        PreparedStatement pstmt = null;
        String sql;
        String time = null;
        try {
            pstmt = connection.prepareStatement(sql2);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                time = resultSet.getString("study_time");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (time!=null) {
            String[] timeSplit = time.split(":");
            String[] addTime = studyTime.split(":");
            List<String> result = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int temp = Integer.parseInt(timeSplit[i]) + Integer.parseInt(addTime[i]);
                if (temp >= 60 && i != 0) {
                    temp -= 60;
                    int t2 = Integer.parseInt(result.get(i - 1)) + 1;
                    result.set(i - 1, String.valueOf(t2));
                }
                result.add(String.valueOf(temp));
            }
            String timeResult = result.get(0) + ":" + result.get(1) + ":" + result.get(2);
            try {
                if (time != null) {
                    sql = "update study_time set study_time='" + timeResult +
                            "' where user_id='" + id + "' and today='" + today + "';";
                    pstmt = connection.prepareStatement(sql);
                    pstmt.executeUpdate();
                    System.out.println("공부시간 추가 성공");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            sql = "insert into study_time (user_id, today, study_time) values(?, ?, ?);";
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, today);
            pstmt.setString(3, studyTime);
            pstmt.executeUpdate();
            System.out.println("공부시간 추가 성공!");
            return true;
        }
        return false;
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
