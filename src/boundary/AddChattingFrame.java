package boundary;

import control.ChatRoomController;
import control.FriendController;
import control.MemberController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddChattingFrame extends JFrame {
	String userId;
	private JPanel contentPane;
	private JTextField chatTitleTextField;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	FriendController friendController = new FriendController();
	MemberController memberController = new MemberController();
	ChatRoomController chatRoomController = new ChatRoomController();
	List<JCheckBox> checkBoxes;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddChattingFrame frame = new AddChattingFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddChattingFrame(String userId, BufferedReader br, PrintWriter pw) {
		this.userId = userId;
		this.bufferedReader = br;
		this.printWriter = pw;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 271, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel chatMemLabel = new JLabel("대화상대추가");
		chatMemLabel.setBounds(12, 10, 147, 27);
		contentPane.add(chatMemLabel);

		List<String> friends = friendController.getFriends(userId);
		Vector<String> friendInfo = new Vector<>();
		for (String friend : friends) {
			Map<String, String> userInfo = memberController.getUserInfo(friend);
			String temp = userInfo.get("name")+" ("+userInfo.get("id")+")";
			friendInfo.add(temp);
		}


		JPanel friendPanel = new JPanel();
		friendPanel.setBounds(12, 30, 230,400);
		friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkBoxes = new ArrayList<>();
		for (String s : friendInfo) {
			JCheckBox checkBox = new JCheckBox(s);
			checkBoxes.add(checkBox);
			friendPanel.add(checkBox);
		}
		contentPane.add(friendPanel);
		JLabel chatLabel = new JLabel("채팅방 이름");
		chatLabel.setBounds(12, 325, 73, 34);
		contentPane.add(chatLabel);
		
		chatTitleTextField = new JTextField();
		chatTitleTextField.setBounds(12, 358, 231, 27);
		contentPane.add(chatTitleTextField);
		chatTitleTextField.setColumns(10);
		
		JButton addChatButton = new JButton("채팅방 만들기");
		addChatButton.setBounds(112, 431, 116, 27);
		contentPane.add(addChatButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 47, 202, 281);
		contentPane.add(panel);
		
		addChatButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    	  	  List<String> chatMember = new ArrayList<>();
				  String chatTitle = chatTitleTextField.getText();
				  if (chatTitle.isBlank()) {
					  JOptionPane.showMessageDialog(null, "채팅방 이름을 설정해주세요!");
				  }
				  else {
					  for (JCheckBox checkBox : checkBoxes) {
						  if (checkBox.isSelected()) {
							  String text = checkBox.getText();
							  String[] split = text.split("\\s");
							  String friendId = split[1].substring(1, split[1].length() - 1);
							  chatMember.add(friendId);
						  }
					  }
					  if (chatMember.isEmpty()){
						  JOptionPane.showMessageDialog(null, "초대할 친구를 한 명 이상 선택해주세요");
					  }
					  else {
					  	  chatMember.add(userId);
						  Integer addChat = chatRoomController.addChatRoom(chatTitle, chatMember);
						  if (addChat == 2) {
						  	  JOptionPane.showMessageDialog(null, "이미 존재하는 채팅방 이름입니다!");
						  }
						  else if (addChat ==1) {
							  Integer roomId = chatRoomController.getRoomId(chatTitle);
							  String makeRoom = "makeChatRoom%"+roomId+"%"+chatTitle;
							  printWriter.println(makeRoom);
							  printWriter.flush();
							  ChattingFrame chattingFrame = new ChattingFrame(roomId, userId, bufferedReader, printWriter);
							  chattingFrame.setVisible(true);
							  setVisible(false);
						  }

					  }
				  }
	    	  }
	      });
	}
}
