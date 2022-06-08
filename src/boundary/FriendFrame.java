package boundary;

import control.FriendController;
import control.MemberController;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.util.*;
import java.util.List;

public class FriendFrame extends JPanel {

	MemberController memberController;
	FriendController friendController;

	/**
	 * Create the panel.
	 */
	public FriendFrame(String userId) {
		memberController = new MemberController();
		friendController = new FriendController();
		Vector<String> fruits=new Vector<>(Arrays.asList("apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"));
		setBounds(12, 50, 283, 502);
		setLayout(null);

		List<String> friends = friendController.getFriends(userId);
		Vector<String> status = new Vector<>();
		Vector<String> friendInfo = new Vector<>();
		for (String friend : friends) {
			Map<String, String> userInfo = memberController.getUserInfo(friend);
			status.add(userInfo.get("status"));
			String temp = userInfo.get("name")+" ("+userInfo.get("id")+")";
			friendInfo.add(temp);
		}
		System.out.println("status"+status);
		System.out.println("friendInfo"+friendInfo);
		
		JList friendStatusList = new JList(status);
		friendStatusList.setBounds(12, 61, 49, 431);
		add(friendStatusList);

		JList friendList = new JList(friendInfo);
		friendList.setBounds(73, 61, 198, 431);
		add(friendList);
		
		JLabel friendLabel = new JLabel("\uCE5C\uAD6C\uBAA9\uB85D");
	    friendLabel.setForeground(new Color(0, 0, 255));
	    friendLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
	    friendLabel.setBounds(12, 10, 115, 31);
	    add(friendLabel);
		
		JButton addFriendButton = new JButton("추가");
	      addFriendButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
	      addFriendButton.setBackground(new Color(240, 230, 140));
	      addFriendButton.setForeground(new Color(0, 0, 255));
	      addFriendButton.setBounds(195, 22, 76, 29);
	      add(addFriendButton);
	      
	      addFriendButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  AddFriendFrame add = new AddFriendFrame();
	    		  add.setVisible(true);
	    	  }
	      });
	}
	
}
