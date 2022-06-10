package boundary;

import control.TodoController;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class TodoFrame extends JPanel {
	private int chatId;
	JList todoList;
	Map<Integer, String> todoIdTitleList;
	Vector<String> todoTitleList;
	TodoController todoController;
	/**
	 * Create the panel.
	 */
	public TodoFrame(int chatId) {
		this.chatId=chatId;
		todoController = new TodoController();
		setBounds(12, 50, 283, 502);
		setLayout(null);

		todoIdTitleList = todoController.getTodoList(chatId);
		todoTitleList = new Vector<>();
		for (String t : todoIdTitleList.values()) {
			todoTitleList.add(t);
		}

		todoList = new JList(todoTitleList);
		todoList.setBounds(12, 61, 259, 480);
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
	    addTodoButton.setBounds(125, 20, 66, 29);
	    add(addTodoButton);

		JButton changeTodoBtn = new JButton("수정");
		changeTodoBtn.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		changeTodoBtn.setBackground(new Color(240, 230, 140));
		changeTodoBtn.setForeground(new Color(0, 0, 255));
		changeTodoBtn.setBounds(200, 20, 66, 29);
		add(changeTodoBtn);

		JButton finishTodoBtn = new JButton("완료");
		finishTodoBtn.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		finishTodoBtn.setBackground(new Color(240, 230, 140));
		finishTodoBtn.setForeground(new Color(0, 0, 255));
		finishTodoBtn.setBounds(205, 550, 66, 29);
		add(finishTodoBtn);

		JButton refresh = new JButton("새로고침");
		refresh.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		refresh.setBackground(new Color(240, 230, 140));
		refresh.setForeground(new Color(0, 0, 255));
		refresh.setBounds(10, 550, 100, 29);
		add(refresh);
	      
	    addTodoButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		AddTodoFrame add = new AddTodoFrame(chatId);
	    		add.setVisible(true);
	    	}
	    });

	    changeTodoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = todoList.getSelectedIndex();
				String title = todoTitleList.get(selectedIndex);
				Integer todoId = todoController.getTodoId(chatId, title);
				ChangeTodoFrame changeTodoFrame = new ChangeTodoFrame(chatId, todoId);
				changeTodoFrame.setVisible(true);
			}
		});

	    finishTodoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = todoList.getSelectedIndex();
				String title = todoTitleList.get(selectedIndex);
				Integer todoId = todoController.getTodoId(chatId, title);
				Boolean successFinish = todoController.finishTodo(todoId);
//				todoList.remove(selectedIndex);
			}
		});

	    refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				todoList.setVisible(false);

				todoIdTitleList = todoController.getTodoList(chatId);
				todoTitleList = new Vector<>();
				for (String t : todoIdTitleList.values()) {
					todoTitleList.add(t);
				}
				todoList = new JList(todoTitleList);
				todoList.setBounds(12, 61, 259, 480);
				add(todoList);
				todoList.setVisible(true);
			}
		});
	}

}
