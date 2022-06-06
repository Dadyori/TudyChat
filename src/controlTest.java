import control.FriendController;
import control.MemberController;
import control.TimerController;
import control.TodoController;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class controlTest {
    public static void main(String[] args) throws SQLException {
        TodoController controller=new TodoController();
        Boolean finish = controller.finishTodo(2);
        System.out.println("finish = " + finish);
    }

}
