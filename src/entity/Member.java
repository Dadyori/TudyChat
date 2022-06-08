package entity;

import action.Server;
import control.*;
import lombok.Getter;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Vector;

public class Member extends Thread{
    Server server;
    Socket socket;

    Vector<Member> allMember;
    Vector<Member> friendMember;
    Vector<Room> roomList;

//    OutputStream outputStream;
//    DataOutputStream dataOutputStream;
//    InputStream inputStream;
//    DataInputStream dataInputStream;
    BufferedReader bufferedReader;
    PrintWriter printWriter;


    String message;
    String id;
    String name;
    int status;
    Room myRoom;

    MemberController memberController = new MemberController();
    FriendController friendController = new FriendController();
    TodoController todoController = new TodoController();
    TimerController timerController = new TimerController();
    ChatRoomController chatRoomController = new ChatRoomController();

    public Member (Socket socket, Server server) throws IOException{
        this.socket=socket;
        this.server=server;

        allMember=server.allUserList;
//        waitMember=server.waitUserList;
        roomList = server.roomList;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void run() {
        try{
            System.out.println("[Server] 클라이언트 접속 > "+this.socket.toString());

//            outputStream=this.socket.getOutputStream();
//            dataOutputStream=new DataOutputStream(outputStream);
//            inputStream=this.socket.getInputStream();
//            dataInputStream=new DataInputStream(inputStream);

            while (true) {
//                message = dataInputStream.readUTF(); //메시지 수신 상시 대기
                message=bufferedReader.readLine();
                String[] m = message.split("%"); //메시지 나누어 저장
                System.out.println("frame->member"+m);
                System.out.println(m[0]);
                if(m[0].equals("login")) {
                    System.out.println("member에서 로그인 진입");
                    int successLogin = memberController.login(m[1], m[2]);
                    if (successLogin == 1) {
                        Map<String, String> userInfo = memberController.getUserInfo(m[1]);
                        name=userInfo.get("name");
                        id=userInfo.get("id");
                        status=Integer.parseInt(userInfo.get("status"));
                        allMember.add(this);
//                        waitMember.add(this);
                        printWriter.println("successLogin%OKAY%"+id);
                        printWriter.flush();
                    }
                    else if (successLogin == 2) {
                        printWriter.println("successLogin%FailPassword");
                        printWriter.flush();
                    }
                    else {
                        printWriter.println("successLogin%FailId");
                        printWriter.flush();
                    }

//                    dataOutputStream.writeUTF("successLogin\\|"+successLogin);
                }
                else if (m[0].equals("signup")) {
                    boolean signup = memberController.signup(m[1], m[2], m[3], m[4]);
//                    dataOutputStream.writeUTF("successSignUp\\|"+signup);
                    printWriter.println("successSignUp%"+signup);
                    printWriter.flush();
                }
                else if (m[0].equals("idDuplicate")) {
                    boolean duplicate = memberController.checkDuplicate(m[1]);
//                    dataOutputStream.writeUTF("isDuplicate\\|"+duplicate);
                    printWriter.println("isDuplicate%"+duplicate);
                    printWriter.flush();
                }
                else if (m[0].equals("makeRoom")) {
                    myRoom = new Room();
                    myRoom.title = m[1];
                    myRoom.count++;
                    roomList.add(myRoom);
                    myRoom.member.add(this);
//                    waitMember.remove(this);
//                    dataOutputStream.writeUTF("successMakeRoom\\|true");
                    System.out.println("[Server] "+name+" : 방 "+m[1]+" 생성 완료");
                }
            }
       } catch (IOException e) {
            System.out.println("[Server] 입출력 오류 > "+e.toString());
        }
    }

    void sendMessage (String message) {
//        for (int i=0; i<friendMember.size();i++){
//            try {
//                friendMember.get(i).dataOutputStream.writeUTF(message);
//            } catch (IOException e) {
//                friendMember.remove(i--);
//            }
//        }
    }

}
