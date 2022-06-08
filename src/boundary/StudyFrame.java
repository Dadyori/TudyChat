package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class StudyFrame extends JFrame{
   String userId;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	StudyFrame window = new StudyFrame("dasol");
               window.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public StudyFrame(String userId) {
      this.userId = userId;
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      this.setForeground(new Color(240, 230, 140));
      this.getContentPane().setForeground(new Color(255, 255, 255));
      this.setSize(900, 700);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
      
      FriendFrame friendframe = new FriendFrame(userId);
      friendframe.setBounds(12, 50, 283, 603);
      panel.add(friendframe);
      friendframe.setVisible(true);
      
      ChattingListFrame chatlist = new ChattingListFrame(userId);
      chatlist.setBounds(307, 50, 567, 603);
      panel.add(chatlist);

      //friendframe.setVisible(true);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(new Color(245, 245, 220));
      panel_2.setBounds(307, 50, 567, 603);
      panel.add(panel_2);
      panel_2.setLayout(null);
      
      JLabel lblNewLabel_2 = new JLabel("\uB300\uD654\uBC29\uBAA9\uB85D");
      lblNewLabel_2.setForeground(new Color(0, 0, 255));
      lblNewLabel_2.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
      lblNewLabel_2.setBounds(0, 0, 163, 39);
      panel_2.add(lblNewLabel_2);
      
      JButton btnNewButton_1 = new JButton("\uB300\uD654\uBC29\uC0DD\uC131");
      btnNewButton_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      btnNewButton_1.setBackground(new Color(240, 230, 140));
      btnNewButton_1.setForeground(new Color(0, 0, 255));
      btnNewButton_1.setBounds(429, 10, 126, 29);
      panel_2.add(btnNewButton_1);
   }
   
}
