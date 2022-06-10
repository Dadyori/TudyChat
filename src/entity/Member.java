package entity;

import action.Server;
import control.*;
import lombok.Getter;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Member extends Thread{
    Server server;
    Socket socket;

//    Vector<Member> allMember;
//    Vector<Member> friendMember;
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
    ChatRoomController chatRoomController = new ChatRoomController();

    public Member (Socket socket, Server server) throws IOException{
        this.socket=socket;
        this.server=server;

//        allMember=server.allUserList;
//        waitMember=server.waitUserList;
        roomList = server.roomList;
        myRoom = new Room();
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
                message=bufferedReader.readLine();
                System.out.println("받아오는 메시지" + message);
                String[] m = message.split("%"); //메시지 나누어 저장
                if(m[0].equals("login")) {
                    int successLogin = memberController.login(m[1], m[2]);
                    if (successLogin == 1) {
                        Map<String, String> userInfo = memberController.getUserInfo(m[1]);
                        name=userInfo.get("name");
                        id=userInfo.get("id");
                        status=Integer.parseInt(userInfo.get("status"));
//                        allMember.add(this);
//                        waitMember.add(this);
                        printWriter.println("successLogin%OKAY%"+m[1]);
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
                else if (m[0].equals("getChatRoomList")){
                    List<Integer> chatRoomList = chatRoomController.getChatRoomList(m[1]);
                    for (Integer chatId : chatRoomList) {
                        Room room = new Room();
                        Map<String, String> roomInfo = chatRoomController.getRoomInfo(chatId);
                        room.roomId=Integer.parseInt(roomInfo.get("roomId"));
                        room.title=roomInfo.get("title");
                        roomList.add(room);
                    }
                }
                else if (m[0].equals("makeChatRoom")){
                    myRoom = new Room(Integer.parseInt(m[1]), m[2]);
                    myRoom.roomId=Integer.parseInt(m[1]);
                    myRoom.title=m[2];
                    myRoom.count++;
                    roomList.add(myRoom);
                    myRoom.member.add(this);
                    System.out.println("성공적으로 방 생성");
                    printWriter.println("successMakeRoom%OKAY");
                    printWriter.flush();
                }
                else if (m[0].equals("enterChatRoom")) {
                    for (int i=0;i<roomList.size();i++) {
                        Room r = roomList.get(i);
                        if (r.roomId == Integer.parseInt(m[1])){
                            r.member.add(this);
                            myRoom = r;
                            System.out.println("성공적으로 방 입장");
                            printWriter.println("successEnterRoom%"+this.id+"%"+this.myRoom.roomId);
                            printWriter.flush();
                            break;
                        }
                    }
                }
                else if (m[0].equals("send")){
                    System.out.println("두 개 나오나? "+roomList);
                    List<Member> chatMember = myRoom.member;
                    Set<Member> set = new HashSet<>(chatMember);
                    if (! set.isEmpty()) {
                        for (Member chatMem : set) {
                            if (chatMem.myRoom.equals(this.myRoom)){
                                System.out.println("채팅방 참여 멤버 "+chatMem.name);
                                String sendMsg = "sendToChat%"+this.name+"%"+m[2];
                                System.out.println("보낼 메시지 "+sendMsg);
                                chatMem.printWriter.println(sendMsg);
                                chatMem.printWriter.flush();
                            }
                        }
                    }
                }
                else if (m[0].equals("shareTime")) {
                    for (int i=0;i<roomList.size();i++) {
                        Room r = roomList.get(i);
                        if (r.roomId == Integer.parseInt(m[1])){
                            r.member.add(this);
                            myRoom = r;
                            System.out.println("공유할 방 member 입장 완료 >> "+this.name+" 시간 >> "+m[3]);
                            printWriter.println("successShareTime%"+m[1]+"%"+m[2]+"%"+this.name+"%"+m[3]);
                            printWriter.flush();
                            break;
                        }
                    }
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
