package action;

import entity.Member;
import entity.Room;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Server {
    ServerSocket serverSocket = null;
    public Vector<Member> allUserList;
    public Vector<Member> waitUserList;
    public Vector<Room> roomList;

    public Server() {
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("[Server] 서버소켓 준비완료");

            allUserList = new Vector<>();
            waitUserList = new Vector<>();
            roomList = new Vector<>();

            while (true){
                //클라이언트 요청 상시 대기
                Socket socket = serverSocket.accept();
                Member member = new Member(socket, this);
                member.start();
            }
        } catch (SocketException e){
            System.out.println("[Server] 서버 소켓 오류 > "+e.toString());
        } catch (IOException e){
            System.out.println("[Server] 입출력 오류 > "+e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
    
}
