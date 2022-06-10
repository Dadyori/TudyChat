package boundary;

import control.ChatRoomController;
import lombok.Getter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Getter
public class ChattingListFrame extends JPanel {
	String userId;
	Integer roomId;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	List<Integer> chatIdList;
	JList chattingList;
	JButton enterButton;
	ChatRoomController chatRoomController = new ChatRoomController();

	/**
	 * Create the panel.
	 */
	public ChattingListFrame(String id, BufferedReader br, PrintWriter pw) {
		this.userId=id;
		this.bufferedReader = br;
		this.printWriter = pw;
		setBounds(35, 66, 589, 517);
		setBounds(307, 50, 567, 603);
		setLayout(null);

		Vector<String> roomInfoList = new Vector<>();
		printWriter.println("getChatRoomList%"+userId);
		printWriter.flush();
		chatIdList = chatRoomController.getChatRoomList(id);
		for (Integer roomId : chatIdList) {
			Map<String, String> roomInfo = chatRoomController.getRoomInfo(roomId);
//			String temp = roomInfo.get("title")+" ("+roomInfo.get("roomId")+")";
			roomInfoList.add(roomInfo.get("title"));
		}

		chattingList = new JList(roomInfoList);
		chattingList.setBounds(20, 54, 530, 526);
//		add(chattingList);
//		chattingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		JListHandler handler = new JListHandler();
//		chattingList.addListSelectionListener(handler);
		add(chattingList);

		JLabel chatLabel = new JLabel("\uB300\uD654\uBC29\uBAA9\uB85D");
	    chatLabel.setForeground(new Color(0, 0, 255));
	    chatLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
	    chatLabel.setBounds(10, 1, 163, 39);
	    add(chatLabel);
	      
	    JButton addChatButton = new JButton("\uB300\uD654\uBC29\uC0DD\uC131");
	    addChatButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
	    addChatButton.setBackground(new Color(240, 230, 140));
	    addChatButton.setForeground(new Color(0, 0, 255));
	    addChatButton.setBounds(429, 10, 126, 29);
	    add(addChatButton);

		enterButton = new JButton("대화방 접속");
		enterButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		enterButton.setBackground(new Color(240, 230, 140));
		enterButton.setForeground(new Color(0, 0, 255));
		enterButton.setBounds(300, 10, 126, 29);
		add(enterButton);

	    addChatButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  AddChattingFrame add = new AddChattingFrame(userId, bufferedReader, printWriter);
	    		  add.setVisible(true);
	    	  }
	      });

	    enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = chattingList.getSelectedIndex();
				Integer chatRoomId = chatIdList.get(selectedIndex);
				printWriter.println("enterChatRoom%"+chatRoomId);
				printWriter.flush();
//				StudyFrame studyFrame = new StudyFrame(userId, chatRoomId, bufferedReader, printWriter);
//				studyFrame.setVisible(true);
			}
		});
	}

//	private class JListHandler implements ListSelectionListener {
//		@Override
//		public void valueChanged(ListSelectionEvent e) {
//			int selectedRoom = chattingList.getSelectedIndex();
//			StudyFrame studyFrame = new StudyFrame(userId, chatRoomList.get(selectedRoom), bufferedReader, printWriter);
////			studyFrame.setVisible(true);
////			printWriter.println("EnterChatting%"+selectedRoom);
////			printWriter.flush();
//		}
//	}
}
