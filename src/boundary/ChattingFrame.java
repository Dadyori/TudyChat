package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChattingFrame extends JPanel {
	public JTextField sendTextfield;
	public JButton sendButton;
	public JList messageBlock;
	private String userId;

	/**
	 * Create the panel.
	 */
	public ChattingFrame() {
		this.userId = userId;
		setBounds(35, 66, 589, 517);
		setBounds(307, 50, 567, 603);
		setBackground(new Color(245, 245, 220));
		setLayout(null);
		
		JLabel chatLabel = new JLabel("채팅방이름");
	    chatLabel.setForeground(new Color(0, 0, 255));
	    chatLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
	    chatLabel.setBounds(0, 1, 163, 39);
	    add(chatLabel);
		
		sendTextfield = new JTextField();
	    sendTextfield.setBounds(12, 564, 453, 29);
	    add(sendTextfield);
	    sendTextfield.setColumns(10);
	    
	    sendButton = new JButton("전송");
	    sendButton.setBounds(470, 564, 97, 29);
	    add(sendButton);
		
		messageBlock = new JList();
		messageBlock.setBackground(new Color(204, 255, 255));
		messageBlock.setBounds(0, 50, 567, 511);
		add(messageBlock);
		
		//전송버튼을 눌렀을 시
		sendButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	}
}
