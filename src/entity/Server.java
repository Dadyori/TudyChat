package entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class Server {
    ServerSocket serverSocket = null;

    public Vector<entity.Member> allUser;
    public Vector<entity.Member> waitUser;
    public Vector<Room> room;

    public Server() {
        Server server = new Server();
        
        server.allUser = new Vector<entity.Member>();
        server.waitUser = new Vector<entity.Member>();
        server.room = new Vector<>();
        
        try {
            server.serverSocket = new ServerSocket(9999);
            System.out.println("[Server] 서버소켓 준비완료");

            while (true){
                //클라이언트 요청 상시 대기
                Socket socket = server.serverSocket.accept();
                Member member = new Member(socket, server);
                member.start();
            }
        } catch (SocketException e){
            System.out.println("[Server] 서버 소켓 오류 > "+e.toString());
        } catch (IOException e){
            System.out.println("[Server] 입출력 오류 > "+e.toString());
        }
    }
    
}
