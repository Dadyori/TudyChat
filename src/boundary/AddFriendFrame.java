package boundary;

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

   private JTextField friendIdTextField;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               AddFriendFrame window = new AddFriendFrame();
               window.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

  
   public AddFriendFrame() {
      initialize();
   }

   
   private void initialize() {
      this.setBackground(new Color(245, 245, 220));
      this.setBounds(100, 100, 319, 170);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    		  if(friendIdTextField.getText().isBlank()) {
    			  JOptionPane.showMessageDialog(null, "친구 추가할 회원의 아이디가 입력되지 않았습니다.");
    		  }
    		  //이미 친구 추가된 회원의 아이디를 입력한 후에 친구추가 버튼을 누른 경우
    		  /*else if() {
    			  JOptionPane.showMessageDialog(null, "이미 친구 추가된 회원입니다.");
    		  }*/
    		  //가입되지 않은 아이디를 입력한 후 친구 추가 버튼을 누른 경우
    		  /*else if() {
			  	  JOptionPane.showMessageDialog(null, "해당 아이디의 사용자를 찾을 수 없습니다.");
		  	  }*/
    		  else {
    			  String friendId=friendIdTextField.getText();
        		  //addFriend(memberId, friendId);
        		  dispose();
    		  }
    	  }
      });
   }
   
}