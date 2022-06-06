package boundary;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeTodoFrame frame = new ChangeTodoFrame();
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
	public ChangeTodoFrame() {
	    setBounds(100, 100, 319, 170);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
	    changeButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//수정할 이름을 입력하지 않고 수정 버튼을 누르는 경우
	    		if(titleTextField.getText().isBlank()) {
	    			  dispose();
	    		  }
	    		//이미 있는 리스트와 같은 이름의 리스트로 수정한다.
	    		/*else if() {
	    			JOptionPane.showMessageDialog(null, "할 일 목록에 이미 존재하는 항목입니다");
	    		}*/
	    		else {
	    			//String todoTitle=titleTextField.getText();
		    		//changeTodo(todoId, todoTitle);
	    			dispose();
	    		}

	    	}
	    });
	  }

}
