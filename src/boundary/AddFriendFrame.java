package boundary;

import control.FriendController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddFriendFrame extends JFrame{

   String userId;
   private JTextField friendIdTextField;
   FriendController friendController = new FriendController();

   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               AddFriendFrame window = new AddFriendFrame("test");
//               window.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

  
   public AddFriendFrame(String userId) {
      this.userId = userId;
      initialize();
   }

   
   private void initialize() {
      this.setBackground(new Color(245, 245, 220));
      this.setBounds(100, 100, 319, 170);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.getContentPane().setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(245, 245, 220));
      panel.setBounds(0, 0, 303, 131);
      this.getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel friendLabel = new JLabel("\uCE5C\uAD6C\uCD94\uAC00");
      friendLabel.setForeground(new Color(0, 0, 255));
      friendLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
      friendLabel.setBounds(12, 10, 106, 29);
      panel.add(friendLabel);
      
      friendIdTextField = new JTextField();
      friendIdTextField.setForeground(new Color(0, 0, 255));
      friendIdTextField.setFont(new Font("함초롬돋움", Font.PLAIN, 17));
      friendIdTextField.setBackground(new Color(240, 230, 140));
      friendIdTextField.setBounds(12, 42, 287, 42);
      panel.add(friendIdTextField);
      friendIdTextField.setColumns(10);
      
      JButton addFriendButton = new JButton("\uCD94\uAC00");
      addFriendButton.setForeground(new Color(0, 0, 255));
      addFriendButton.setBackground(new Color(240, 230, 140));
      addFriendButton.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
      addFriendButton.setBounds(208, 87, 91, 33);
      panel.add(addFriendButton);
      
      addFriendButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  //친구 아이디를 입력하지 않고 친구 추가 버튼을 누른 경우
             String friendId = friendIdTextField.getText();
             int successAdd = friendController.addFriend(userId, friendId);
             if(friendId.isBlank()) {
    			  JOptionPane.showMessageDialog(null, "친구 추가할 회원의 아이디가 입력되지 않았습니다.");
    		  }
             //이미 친구 추가된 회원의 아이디를 입력한 후에 친구추가 버튼을 누른 경우
             else if (successAdd == 4) {
                JOptionPane.showMessageDialog(null, "본인은 친구로 추가할 수 없습니다.");
             }
             else if (successAdd == 2) {
                JOptionPane.showMessageDialog(null, "이미 친구 추가된 회원입니다.");
             }
             else if (successAdd == 3) {
                JOptionPane.showMessageDialog(null, "해당 아이디의 사용자를 찾을 수 없습니다.");
             }
    		 else if (successAdd == 1) {
    		    JOptionPane.showMessageDialog(null, "친구추가 성공!");
    		    setVisible(false);
             }
    	  }
      });
   }
   
}