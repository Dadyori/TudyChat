package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;

public class AddChattingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField chatTitleTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddChattingFrame frame = new AddChattingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddChattingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel chatMemLabel = new JLabel("대화상대추가");
		chatMemLabel.setBounds(12, 10, 147, 27);
		contentPane.add(chatMemLabel);
		
		JLabel chatLabel = new JLabel("채팅방 이름");
		chatLabel.setBounds(23, 325, 73, 34);
		contentPane.add(chatLabel);
		
		chatTitleTextField = new JTextField();
		chatTitleTextField.setBounds(12, 358, 231, 27);
		contentPane.add(chatTitleTextField);
		chatTitleTextField.setColumns(10);
		
		JButton addChatButton = new JButton("채팅방 만들기");
		addChatButton.setBounds(109, 431, 116, 27);
		contentPane.add(addChatButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 47, 202, 281);
		contentPane.add(panel);
		
		addChatButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  //친구를 추가하지 않고 채팅방 생성을 눌렀을 경우
	    		  /*if() {
	    			  JOptionPane.showMessageDialog(null, "채팅할 친구를 한 명 이상 선택해주세요.");
	    		  }*/
	    		  //채팅방의 이름을 설정하지 않고 채팅방 생성을 눌렀을 경우
	    		  /*else if() {
    			      JOptionPane.showMessageDialog(null, "채팅방 이름이 설정되지 않았습니다.");
    		  	  }*/
	    		  //이미 존재하는 채팅방의 이름을 입력한 후 채팅방 생성 버튼을 눌렀을 경우
	    		  /*else if() {
  			          JOptionPane.showMessageDialog(null, "동일한 이름의 채팅방이 존재합니다. 채팅방을 생성하시겠습니까?");
  		  	      }*/
	    		  //String title=chatTitleTextField.getText();
	    		  //addChatting(title, members)
	    		  dispose();
	    	  }
	      });
	}
}
