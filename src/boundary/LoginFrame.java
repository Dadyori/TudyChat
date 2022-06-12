package boundary;

import control.MemberController;
import entity.Member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Window;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventListener;

public class LoginFrame extends JFrame implements Runnable {

   private JFrame frame;
   private JTextField idTextField;
   private JPasswordField pwTextField;
   private Socket socket;
   private BufferedReader bufferedReader;
   private PrintWriter printWriter;
   StudyFrame studyFrame;
//   OutputStream os;
//   DataOutputStream dataOutputStream;
//   InputStream is;
//   DataInputStream inputStream;

   MemberController memberController = new MemberController();

   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               LoginFrame window = new LoginFrame();
////               window.frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the application.
    */
//   public LoginFrame() {
//      Login();
//      connect();
//   }

    public LoginFrame () {
       connect();

       frame = new JFrame();
       frame.setLocationRelativeTo(null);
       frame.getContentPane().setBackground(new Color(238, 232, 170));
       frame.setForeground(new Color(204, 204, 153));
       frame.setBackground(SystemColor.desktop);
       frame.setSize(400, 600);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setLayout(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(240, 230, 140));
       panel.setForeground(new Color(230, 230, 250));
       panel.setBounds(0, 10, 686, 710);
       frame.getContentPane().add(panel);
       panel.setLayout(null);

       JButton loginButton = new JButton("\uB85C\uADF8\uC778\uD558\uAE30");
       loginButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
       loginButton.setBackground(new Color(245, 245, 220));
       loginButton.setForeground(new Color(0, 0, 255));
       loginButton.setBounds(84, 400, 232, 39);
       panel.add(loginButton);

       JButton signUpButton = new JButton("회원가입");
       signUpButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
       signUpButton.setBackground(new Color(245, 245, 220));
       signUpButton.setForeground(new Color(0, 0, 255));
       signUpButton.setBounds(84, 450, 232, 39);
       panel.add(signUpButton);

       JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
       lblNewLabel.setForeground(new Color(0, 0, 255));
       lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
       lblNewLabel.setBounds(84, 194, 60, 46);
       panel.add(lblNewLabel);

       idTextField = new JTextField();
       idTextField.setBackground(new Color(245, 245, 220));
       idTextField.setBounds(84, 239, 232, 39);
       panel.add(idTextField);
       idTextField.setColumns(10);

       JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
       lblNewLabel_1.setForeground(new Color(0, 0, 255));
       lblNewLabel_1.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
       lblNewLabel_1.setBounds(84, 284, 88, 46);
       panel.add(lblNewLabel_1);

       JLabel lblNewLabel_2 = new JLabel("TUDY");
       lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
       lblNewLabel_2.setForeground(new Color(0, 0, 255));
       lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
       lblNewLabel_2.setBounds(148, 40, 78, 74);
       panel.add(lblNewLabel_2);

       JLabel lblNewLabel_2_1 = new JLabel("CHAT");
       lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
       lblNewLabel_2_1.setForeground(new Color(0, 0, 255));
       lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
       lblNewLabel_2_1.setBounds(148, 75, 78, 74);
       panel.add(lblNewLabel_2_1);

       pwTextField = new JPasswordField();
       pwTextField.setBackground(new Color(245, 245, 220));
       pwTextField.setBounds(84, 328, 232, 39);
       panel.add(pwTextField);

       frame.setVisible(true);
       frame.setResizable(false);

      //로그인 버튼 동작시
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String id = idTextField.getText();
            String password = pwTextField.getText();
            if (id.isBlank() || password.isBlank()) {
               JOptionPane.showMessageDialog(null, "아이디/비밀번호를 모두 입력해주세요.");
            }
            String userInfo=id+"%"+password;
            System.out.println("보내는 데이터 >> "+userInfo);
            try {
               printWriter.println("login%"+userInfo);
               printWriter.flush();

            } catch (Exception ioException) {
               ioException.printStackTrace();
            }
         }
      });

      //회원가입 버튼 동작시
      signUpButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  SignUpFrame sign = new SignUpFrame();
    		  sign.setVisible(true);
    	  }
      });

//      event();
   }

//
//   public void event() {
//      ChattingListFrame chatListFrame = mainFrame.chattingListFrame;
//      chatListFrame.enterBtn.addActionListener(this);
//      studyFrame.chattingFrame.sendButton.addActionListener(this);
//   }
//
//   @Override
//   public void actionPerformed(ActionEvent e) {
//      ChattingListFrame chatListFrame = mainFrame.chattingListFrame;
//      ChattingFrame chattingFrame = studyFrame.chattingFrame;
//      if (e.getSource() == chattingFrame.sendButton) {
//          String message = chattingFrame.sendTextfield.getText();
//          if (message.isBlank()) {
//             chattingFrame.sendTextfield.setText("");
//          }
//          try {
//             printWriter.println("send%"+message);
//             printWriter.flush();
//          } catch (Exception exception){
//             exception.printStackTrace();
//          }
//       }
//       else if (e.getSource() == mainFrame.chattingListFrame.enterBtn) {
//          int selectedIndex = mainFrame.chattingListFrame.chattingList.getSelectedIndex();
//          StudyFrame studyFrame = new StudyFrame(mainFrame.userId, mainFrame.chattingListFrame.chatIdList.get(selectedIndex), bufferedReader, printWriter);
//          studyFrame.setVisible(true);
//       }
//   }

   @Override
   public void run() {
      //받아오기
      while (true) {
         try {
            String message = bufferedReader.readLine();
            String[] command=message.split("%");
            System.out.println("member->frame"+message);
            if (command == null){
               bufferedReader.close();
               printWriter.close();
               socket.close();
               System.exit(0);
            }
            else if (command[0].equals("successLogin")){
               //로그인 성공시 정보 받아와서 Frame 생성
               for (String s : command) {
                  System.out.println("다시받아온 데이터 "+s);
               }
               if (command[1].equals("OKAY")){
                  MainFrame mainFrame = new MainFrame(command[2], bufferedReader, printWriter);
                  frame.setVisible(false);
                  mainFrame.setVisible(true);
               }
               else if (command[1].equals("FailId")){
                  JOptionPane.showMessageDialog(null, "가입된 아이디가 아닙니다. 회원가입을 진행해주세요.");
               }
               else if (command[1].equals("FailPassword")){
                  JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
               }
            }
            else if (command[0].equals("successEnterRoom")){
               System.out.println("방 입장 성공 >> id : "+ command[1]+" 방 아이디 : "+command[2]);
               studyFrame = new StudyFrame(command[1], Integer.parseInt(command[2]), bufferedReader, printWriter);
               studyFrame.setVisible(true);
            }
            else if (command[0].equals("sendToChat")) {
               String sendMsg = command[1]+" >> "+command[2];
               studyFrame.chattingFrame.messageBlock.append(sendMsg+"\n");
            }
            else if (command[0].equals("successShareTime")) {
               studyFrame = new StudyFrame(command[2], Integer.parseInt(command[1]), bufferedReader, printWriter);
               String hour=command[4].substring(0, 2);
               String min=command[4].substring(3, 5);
               String sec=command[4].substring(6, 8);
               String time = hour+":"+min+":"+sec;
               String shareTimeMsg = command[3]+"님의 오늘 공부 시간은 "+time+"입니다!\n";
               printWriter.println("send%"+command[1]+"%"+shareTimeMsg);
               printWriter.flush();
            }
         } catch (IOException io) {
            System.out.println("정보받아오는데 동작안함");
            io.printStackTrace();
         }
      }
   }

   public void connect() {
      try {
         socket = new Socket("211.202.97.118", 9999);
//         inputStream = new DataInputStream(this.socket.getInputStream());
//         dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
         bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      } catch (UnknownHostException e){
         System.out.println("서버를 찾을 수 없습니다");
         e.printStackTrace();
         System.exit(0);
      } catch (IOException e) {
         System.out.println("서버와 연결이 안되었습니다");
         e.printStackTrace();
         System.exit(0);
      }

      Thread thread = new Thread(this);
      thread.start();
   }
}