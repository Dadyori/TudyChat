package action;

import control.FriendController;
import control.MemberController;
import control.TimerController;
import control.TodoController;
import entity.Member;
import entity.Room;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class MainController extends Thread{
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;
    private Member member;

    private List<MainController> allUserList;
    private List<MainController> waitUserList;
    private List<Room> roomList;

    MemberController memberController;
    FriendController friendController;
    TimerController timerController;
    TodoController todoController;

    private Room userRoom;

    public MainController(Socket socket, List<MainController> allUserList,
                          List<MainController> waitUserList, List<Room> roomList) throws Exception {
//        this.member = new Member(socket, );
        this.userRoom = new Room();
        this.socket = socket;
        this.allUserList=allUserList;
        this.waitUserList=waitUserList;
        this.roomList=roomList;

        this.memberController = new MemberController();
        this.friendController = new FriendController();
        this.timerController = new TimerController();
        this.todoController = new TodoController();

        bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            String[] command = null;
            while (true) {
                if (bufferedReader.ready()){
                    command = bufferedReader.readLine().split("\\|");
                }
                if (command == null) {
                    break;
                }
                if (command[0].compareTo("signup")==0) {
                    String[] userInfo = command[1].split("%");
                    memberController.signup(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                }
                else if (command[0].compareTo("idDuplicate")==0){
                    boolean idDuplicate = memberController.checkDuplicate(command[1]);
                }
                else if (command[0].compareTo("login")==0){
                    String[] userInfo = command[1].split("%");
                    int success = memberController.login(userInfo[0], userInfo[1]);
                    if (success == 1){
                        printWriter.println("successLogin|"+success);
                        printWriter.flush();
                        waitUserList.add(this);
                        String roomListMessage="";
                        for (int i=0;i<roomList.size();i++){
                            roomListMessage+=(roomList.get(i).getTitle())+"%";
                        }
                        roomListMessage+="-";
                        if (roomListMessage.length()!=0){
                            for (int i=0;i<waitUserList.size();i++){
                                waitUserList.get(i).printWriter.println("login|"+roomListMessage);
                                waitUserList.get(i).printWriter.flush();
                            }
                        }
                    }
                }
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
