package boundary;

import control.ChatRoomController;
import entity.Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class ChattingFrame extends JPanel {
	Integer roomId;
	String userId;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	public JTextField sendTextfield;
	public JButton sendButton;
	public JTextArea messageBlock;
	ChatRoomController chatRoomController;

	/**
	 * Create the panel.
	 */
	public ChattingFrame(Integer roomId, String userId, BufferedReader br, PrintWriter pw) {
		chatRoomController = new ChatRoomController();
		this.roomId = roomId;
		this.userId = userId;
		this.bufferedReader = br;
		this.printWriter = pw;
		printWriter.println("entryChatRoom%"+roomId+"%"+userId);
		printWriter.flush();

		setBounds(35, 66, 589, 517);
		setBounds(307, 50, 567, 603);
		setBackground(new Color(245, 245, 220));
		setLayout(null);

		Map<String, String> roomInfo = chatRoomController.getRoomInfo(roomId);
		JLabel chatLabel = new JLabel(roomInfo.get("title"));
		chatLabel.setForeground(new Color(0, 0, 255));
		chatLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		chatLabel.setBounds(5, 1, 163, 39);
		add(chatLabel);

		sendTextfield = new JTextField();
		sendTextfield.setBounds(12, 564, 453, 29);
		add(sendTextfield);
		sendTextfield.setColumns(10);

		sendButton = new JButton("전송");
		sendButton.setBounds(470, 564, 97, 29);
		add(sendButton);

		messageBlock = new JTextArea();
		messageBlock.setEditable(false);
		add(messageBlock);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0,50,567,511);
		add(scrollPane);
		scrollPane.setViewportView(messageBlock);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

		List<String> chatMessage = chatRoomController.getChatMessage(roomId);
		for (String msg : chatMessage) {
			messageBlock.append(msg+"\n");
		}

		//전송버튼을 눌렀을 시
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = sendTextfield.getText();
				sendTextfield.setText("");
				Boolean successSend = chatRoomController.sendMessage(roomId, userId, message);
				if (successSend){
					printWriter.println("send%" + roomId + "%" + message);
					printWriter.flush();
				}
			}
		});
	}

//	@Override
//	public void run() {
//		try {
//			while (true) {
//				String command = bufferedReader.readLine();
//				System.out.println("채팅 커멘드 잘 읽어오나? " + command);
//				String[] m = command.split("%");
//				if (m[0].equals("successMakeRoom")){
//					if (m[1].equals("OKAY")) {
//						JOptionPane.showMessageDialog(null, "방생성성공");
//						System.out.println("방 성공적으로 생성");
//					}
//				}
//				if (m[0].equals("successEnterRoom")) {
//					if (m[1].equals("OKAY")){
//						messageBlock.append("방 입장 성공");
//						JOptionPane.showMessageDialog(null, "방 입장 성공");
//						System.out.println("방 성공적으로 입장");
//					}
//				}
//				if (m[0].equals("sendToChat")) {
//					messageBlock.append(m[1] + " >> " + m[2]+"\n");
//				}
//			}
//		} catch (IOException e) {
//			System.out.println("[Server] 입출력 오류 > " + e.toString());
//		}
//	}
}
