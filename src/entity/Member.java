package entity;

import control.Server;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class Member extends Thread{
    Server server;
    Socket socket;

    Vector<Member> allMember;
    Vector<Member> waitMember;
    Vector<Room> room;

    OutputStream outputStream;
    DataOutputStream dataOutputStream;
    InputStream inputStream;
    DataInputStream dataInputStream;

    String message;
    String id;
    String name;
    Room myRoom;

    public Member (Socket mSocket, Server mServer) {
        this.socket=mSocket;
        this.server=mServer;

        allMember=server.allUser;
        waitMember=server.waitUser;
        room = server.room;
    }

    public void run() {
        try{
            System.out.println("[Server] 클라이언트 접속 > "+this.socket.toString());

            outputStream=this.socket.getOutputStream();
            dataOutputStream=new DataOutputStream(outputStream);
            inputStream=this.socket.getInputStream();
            dataInputStream=new DataInputStream(inputStream);

            while (true) {
                message = dataInputStream.readUTF(); //메시지 수신 상시 대기
                String[] m = message.split("//"); //메시지 나누어 저장
            }
       } catch (IOException e) {
            System.out.println("[Server] 입출력 오류 > "+e.toString());
        }
    }

}
