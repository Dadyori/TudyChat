package boundary;

import control.TodoController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangeTodoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField titleTextField;
	Integer todoId;
	Integer chatId;

	TodoController todoController;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangeTodoFrame frame = new ChangeTodoFrame();
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
	public ChangeTodoFrame(Integer chatId, Integer todoId) {
		this.chatId = chatId;
		this.todoId = todoId;
		todoController = new TodoController();
	    setBounds(100, 100, 319, 170);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(245, 245, 220));

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(245, 245, 220));
	    panel.setBounds(0, 0, 461, 133);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	      
	    JLabel changeTodoLabel = new JLabel("할 일 수정");
	    changeTodoLabel.setForeground(new Color(0, 0, 255));
	    changeTodoLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
	    changeTodoLabel.setBounds(12, 10, 106, 29);
	    panel.add(changeTodoLabel);
	      
	    titleTextField = new JTextField();
	    titleTextField.setForeground(new Color(0, 0, 255));
	    titleTextField.setFont(new Font("함초롬돋움", Font.PLAIN, 17));
	    titleTextField.setBackground(new Color(240, 230, 140));
	    titleTextField.setBounds(12, 42, 287, 42);
	    panel.add(titleTextField);
	    titleTextField.setColumns(10);
	      
	    JButton changeButton = new JButton("수정");
	    changeButton.setForeground(new Color(0, 0, 255));
	    changeButton.setBackground(new Color(240, 230, 140));
	    changeButton.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
	    changeButton.setBounds(208, 87, 91, 33);
	    panel.add(changeButton);
	    setResizable(false);
		
	    changeButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				String changeTitle = titleTextField.getText();
				//수정할 이름을 입력하지 않고 수정 버튼을 누르는 경우
	    		if (changeTitle.isBlank()) {
					//JOptionPane.showMessageDialog(null, "변경 내용을 입력해주세요");
					dispose();
	    		}
	    		else if (todoController.checkTodoDuplicate(chatId, changeTitle)) {
					JOptionPane.showMessageDialog(null, "할 일 목록에 이미 존재하는 항목입니다");
				}
	    		else {
					Boolean successChange = todoController.changeTodo(chatId, todoId, changeTitle);
					if (successChange){
						dispose();
					}
	    		}

	    	}
	    });
	  }

}
