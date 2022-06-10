package boundary;

import control.ChatRoomController;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;

public class StudyFrame extends JFrame{
   String userId;
   Integer roomId;
   BufferedReader bufferedReader;
   PrintWriter printWriter;
   ChatRoomController chatRoomController;
   TodoFrame todoFrame;
   ChattingFrame chattingFrame;
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               StudyFrame window = new StudyFrame("dasol");
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
   public StudyFrame(String userId, Integer roomId, BufferedReader br, PrintWriter pw) {
      this.bufferedReader = br;
      this.printWriter = pw;
      this.userId = userId;
      this.roomId = roomId;
      chatRoomController = new ChatRoomController();
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      this.setForeground(new Color(240, 230, 140));
      this.getContentPane().setForeground(new Color(255, 255, 255));
      this.setSize(900, 700);
      this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      this.getContentPane().setLayout(null);

      JPanel panel = new JPanel();
      panel.setBackground(new Color(240, 230, 140));
      panel.setForeground(new Color(0, 0, 0));
      panel.setBounds(0, 0, 886, 663);
      this.getContentPane().add(panel);
      panel.setLayout(null);

      JLabel lblNewLabel = new JLabel("TUDY CHAT");
      lblNewLabel.setBackground(new Color(255, 215, 0));
      lblNewLabel.setBounds(12, 10, 147, 30);
      lblNewLabel.setForeground(new Color(0, 0, 255));
      lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
      panel.add(lblNewLabel);

      todoFrame = new TodoFrame(roomId);
      todoFrame.setBounds(12, 50, 283, 603);
      panel.add(todoFrame);
      //todoframe.setVisible(true);

      chattingFrame = new ChattingFrame(roomId, userId, bufferedReader, printWriter);
      chattingFrame.setBounds(307, 50, 567, 603);
      panel.add(chattingFrame);
      setVisible(true);
      setResizable(false);
   }
}
