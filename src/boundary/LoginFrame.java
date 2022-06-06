package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Window;

public class LoginFrame {

   private JFrame frame;
   private JTextField idTextField;
   private JPasswordField pwTextField;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LoginFrame window = new LoginFrame();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public LoginFrame() {
      Login();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void Login() {
      frame = new JFrame();
      frame.setLocationRelativeTo(null);
      frame.getContentPane().setBackground(new Color(238, 232, 170));
      frame.setForeground(new Color(204, 204, 153));
      frame.setBackground(SystemColor.desktop);
      frame.setSize(400, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(240, 230, 140));
      panel.setForeground(new Color(230, 230, 250));
      panel.setBounds(0, 10, 686, 710);
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JButton loginButton = new JButton("\uB85C\uADF8\uC778\uD558\uAE30");
      loginButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      loginButton.setBackground(new Color(245, 245, 220));
      loginButton.setForeground(new Color(0, 0, 255));
      loginButton.setBounds(135, 400, 126, 46);
      panel.add(loginButton);
      
      JButton signUpButton = new JButton("회원가입");
      signUpButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      signUpButton.setBackground(new Color(245, 245, 220));
      signUpButton.setForeground(new Color(0, 0, 255));
      signUpButton.setBounds(135, 456, 126, 46);
      panel.add(signUpButton);
      
      JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
      lblNewLabel.setForeground(new Color(0, 0, 255));
      lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
      lblNewLabel.setBounds(160, 194, 53, 46);
      panel.add(lblNewLabel);
      
      idTextField = new JTextField();
      idTextField.setBackground(new Color(245, 245, 220));
      idTextField.setBounds(84, 239, 232, 39);
      panel.add(idTextField);
      idTextField.setColumns(10);
      
      JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
      lblNewLabel_1.setForeground(new Color(0, 0, 255));
      lblNewLabel_1.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
      lblNewLabel_1.setBounds(160, 288, 88, 46);
      panel.add(lblNewLabel_1);
      
      JLabel lblNewLabel_2 = new JLabel("TUDY");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setForeground(new Color(0, 0, 255));
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      lblNewLabel_2.setBounds(148, 35, 78, 74);
      panel.add(lblNewLabel_2);
      
      JLabel lblNewLabel_2_1 = new JLabel("CHAT");
      lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2_1.setForeground(new Color(0, 0, 255));
      lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      lblNewLabel_2_1.setBounds(148, 96, 78, 74);
      panel.add(lblNewLabel_2_1);
      
      pwTextField = new JPasswordField();
      pwTextField.setBackground(new Color(245, 245, 220));
      pwTextField.setBounds(84, 328, 232, 46);
      panel.add(pwTextField);
      
      //로그인 버튼 동작시
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String userId = "dan";	//아이디
            String userPw = "1234";	//비밀번호
            
            //로그인 성공시
            if(userId.equals(idTextField.getText())&&userPw.equals(pwTextField.getText())) {
               JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
               frame.dispose();	//LoginFrame 닫음
               MainFrame main = new MainFrame();
               main.setVisible(true); //MainFrame 켬
            }
            /*
            //가입하지 않은 아이디로 로그인 버튼을 누른 경우
            else if(){
            	JOptionPane.showMessageDialog(null, "가입된 아이디가 아닙니다. 회원가입을 진행해주세요.");
            }
            //가입한 비밀번호와 다른 비밀번호로 로그인 버튼을 누른 경우
            else if(){
            	JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
            }
            //아이디, 비밀번호 중 하나라도 입력하지 않고 로그인 버튼을 누른경우
             else if(idTextField.getText().isBlank() || pwTextField.getText().isBlank()){
            	JOptionPane.showMessageDialog(null, "아이디/비밀번호를 모두 입력해주세요.");
            }
            */
            else {
               JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 틀립니다");
            }
         }
      });
      //회원가입 버튼 동작시
      signUpButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  SignUpFrame sign = new SignUpFrame();
    		  sign.setVisible(true);
    	  }
      });
      
   }
   
}