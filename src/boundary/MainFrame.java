package boundary;

import control.ChatRoomController;
import control.MemberController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;

public class MainFrame extends JFrame{
   String userId;
   BufferedReader bufferedReader;
   PrintWriter printWriter;
   MemberController memberController = new MemberController();
   FriendFrame friendFrame;
   ChattingListFrame chattingListFrame;

   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               MainFrame window = new MainFrame("test");
//               window.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the application.
    */
   public MainFrame(String userId, BufferedReader br, PrintWriter pw) {
      this.userId = userId;
      this.bufferedReader = br;
      this.printWriter = pw;
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {

      this.setForeground(new Color(240, 230, 140));
      this.getContentPane().setForeground(new Color(255, 255, 255));
      this.setSize(900, 700);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.getContentPane().setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(240, 230, 140));
      panel.setForeground(new Color(0, 0, 0));
      panel.setBounds(0, 0, 886, 663);
      this.getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel mainLabel = new JLabel("TUDY CHAT");
      mainLabel.setBackground(new Color(255, 215, 0));
      mainLabel.setBounds(12, 10, 147, 30);
      mainLabel.setForeground(new Color(0, 0, 255));
      mainLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
      panel.add(mainLabel);

      friendFrame = new FriendFrame(userId);
      panel.add(friendFrame);
      friendFrame.setVisible(true);
      
      chattingListFrame = new ChattingListFrame(userId, bufferedReader, printWriter);
      chattingListFrame.setBounds(307, 50, 567, 603);
      panel.add(chattingListFrame);
      
      JButton recallButton = new JButton("\uC0C8\uB85C\uACE0\uCE68");
      recallButton.setBackground(new Color(245, 245, 220));
      recallButton.setForeground(new Color(0, 0, 255));
      recallButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      recallButton.setBounds(655, 10, 105, 30);
      panel.add(recallButton);

      JButton logoutBtn = new JButton("로그아웃");
      logoutBtn.setBackground(new Color(245, 245, 220));
      logoutBtn.setForeground(new Color(0, 0, 255));
      logoutBtn.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      logoutBtn.setBounds(769, 10, 105, 30);
      panel.add(logoutBtn);
      
      JButton timerButton = new JButton("\uACF5\uBD80\uC2DC\uAC04\uCE21\uC815");
      timerButton.setForeground(new Color(0, 0, 255));
      timerButton.setBackground(new Color(245, 245, 220));
      timerButton.setFont(new Font("함초롬돋움", Font.PLAIN, 30));
      timerButton.setBounds(12, 562, 283, 91);
      panel.add(timerButton);


      //새로고침 버튼 동작 시
      recallButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
             friendFrame.setVisible(false);
             friendFrame = new FriendFrame(userId);
             panel.add(friendFrame);
             friendFrame.setVisible(true);

             chattingListFrame.setVisible(false);
             chattingListFrame = new ChattingListFrame(userId, bufferedReader, printWriter);
             panel.add(chattingListFrame);
             chattingListFrame.setVisible(true);
          }
      });

      //로그아웃 버튼 동작시
      logoutBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            memberController.logout(userId);
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            setVisible(false);
         }
      });

      //공부시간측정버튼 동작 시
      timerButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  TimerFrame timer = new TimerFrame(userId, bufferedReader, printWriter);
    		  timer.setVisible(true);
    	  }
      });
   }
   
}
