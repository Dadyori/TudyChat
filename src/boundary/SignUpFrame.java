package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JTextField checkPwTextField;
	private JTextField emailTextField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpFrame frame = new SignUpFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		nameLabel.setBounds(27, 37, 66, 15);
		contentPane.add(nameLabel);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		idLabel.setBounds(27, 94, 57, 15);
		contentPane.add(idLabel);
		
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		pwLabel.setBounds(27, 157, 57, 15);
		contentPane.add(pwLabel);
		
		JLabel checkPwLabel = new JLabel("비밀번호 확인");
		checkPwLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		checkPwLabel.setBounds(27, 211, 89, 15);
		contentPane.add(checkPwLabel);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		emailLabel.setBounds(27, 267, 57, 15);
		contentPane.add(emailLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(27, 62, 238, 21);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		idTextField = new JTextField();
		idTextField.setBounds(27, 119, 238, 21);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JTextField();
		pwTextField.setBounds(27, 180, 238, 21);
		contentPane.add(pwTextField);
		pwTextField.setColumns(10);
		
		checkPwTextField = new JTextField();
		checkPwTextField.setBounds(27, 236, 238, 21);
		contentPane.add(checkPwTextField);
		checkPwTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(27, 289, 238, 21);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton checkIdButton = new JButton("중복확인");
		checkIdButton.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		checkIdButton.setBounds(288, 118, 97, 23);
		contentPane.add(checkIdButton);
		
		JButton signUpButton = new JButton("회원가입");
		signUpButton.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		signUpButton.setBounds(288, 330, 108, 36);
		contentPane.add(signUpButton);		
		
		//중복확인 버튼 동작시
		checkIdButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    		  //boolean b= checkDuplicate(idTextField.getText());
	    		  
	    		  //아이디가 중복일 경우
	    		  /*if(b) {
	    			  JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
	    		  }
	   
	    		  else {
	    			  JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
	    		  }
	    		  */
	    	  }
	      });
		
		//회원가입 버튼 동작시
		signUpButton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e) {
	    
	    		  //비밀번호가 확인 란과 다를 때
	    		  
	    		  //아이디, 비밀번호 칸에 영어, 숫자 외의 다른 문자를 입력했을 경우
	    		  if(idTextField.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || pwTextField.getText().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
	    			  JOptionPane.showMessageDialog(null, "아이디/비밀번호를 사용할 수 없습니다. 영어 대소문자, 숫자로 입력해주세요.");
	    		  }
	    		  //아이디가 20자 이상일 경우
	    		  else if(idTextField.getText().length()>=20) {
	    			  JOptionPane.showMessageDialog(null, "아이디를 20자 이하로 설정해주세요.");
	    		  }
	    		  //비밀번호를 8자 이하, 20자 이상으로 입력했을 경우
	    		  else if(pwTextField.getText().length()<8 || pwTextField.getText().length()>=20) {
	    			  JOptionPane.showMessageDialog(null, "비밀번호를 8자 이상 20자 이하로 설정해주세요.");
	    		  }
	    		  //요구한 필드를 모두 채우지 않았을 경우
	    		  else if(idTextField.getText().isBlank() || pwTextField.getText().isBlank() || nameTextField.getText().isBlank() || emailTextField.getText().isBlank()) {
	    			  JOptionPane.showMessageDialog(null, "회원가입에 필요한 필수 정보를 모두 입력해주세요.");
	    		  }
	    		  //비밀번호 확인란에 앞서 작성한 비밀번호와 다른 비밀번호를 입력했을 경우
	    		  else if(!pwTextField.getText().equals(checkPwTextField.getText())) {
	    			  JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
	    		  }
	    		  else {
	    			  String userId=idTextField.getText();
		    		  String userPw=pwTextField.getText();
		    		  String name=nameTextField.getText();
		    		  String email=emailTextField.getText();
		    		  //register(userId,userPw,name,email);
		    		  JOptionPane.showMessageDialog(null, "회원가입이 완료됐습니다.");
	    			  dispose();
	    		  }
	    	  }
	      });
	
	}
	
}
