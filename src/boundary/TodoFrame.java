package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class TodoFrame extends JPanel {

	/**
	 * Create the panel.
	 */
	public TodoFrame() {
		setBounds(12, 50, 283, 502);
		setLayout(null);
		
		JList todoList = new JList();
		todoList.setBounds(12, 61, 259, 431);
		add(todoList);
		
		JLabel TodoLabel = new JLabel("할 일");
	    TodoLabel.setForeground(new Color(0, 0, 255));
	    TodoLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
	    TodoLabel.setBounds(12, 10, 115, 31);
	    add(TodoLabel);
		
		JButton addTodoButton = new JButton("추가");
	    addTodoButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
	    addTodoButton.setBackground(new Color(240, 230, 140));
	    addTodoButton.setForeground(new Color(0, 0, 255));
	    addTodoButton.setBounds(195, 22, 76, 29);
	    add(addTodoButton);
	      
	    addTodoButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		AddTodoFrame add = new AddTodoFrame();
	    		add.setVisible(true);
	    	}
	    });
	}

}
