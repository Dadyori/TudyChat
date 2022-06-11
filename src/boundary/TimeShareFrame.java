package boundary;

import control.ChatRoomController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeShareFrame extends JFrame {
    String userId;
    String time;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    ChatRoomController chatRoomController = new ChatRoomController();
    private JPanel contentPane;
    List<JCheckBox> checkBoxes;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    TimeShareFrame frame = new TimeShareFrame();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public TimeShareFrame(String userId, String time, BufferedReader br, PrintWriter pw) {
        this.userId = userId;
        this.time = time;
        this.bufferedReader = br;
        this.printWriter = pw;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 271, 519);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel timeShareLabel = new JLabel("공부시간 공유");
        timeShareLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        timeShareLabel.setBounds(12, 10, 147, 27);
        contentPane.add(timeShareLabel);

        JPanel shareChatListPanel = new JPanel();
        shareChatListPanel.setBounds(12, 47, 230,407);
        shareChatListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        checkBoxes = new ArrayList<>();
        List<Integer> chatRoomList = chatRoomController.getChatRoomList(userId);
        List<String> titleList = new ArrayList<>();
        for (Integer integer : chatRoomList) {
            Map<String, String> roomInfo = chatRoomController.getRoomInfo(integer);
            titleList.add(roomInfo.get("title"));
        }
        for (String chatTitle : titleList) {
            JCheckBox checkBox = new JCheckBox(chatTitle);
            checkBoxes.add(checkBox);
            shareChatListPanel.add(checkBox);
        }
        contentPane.add(shareChatListPanel);

        JButton shareButton = new JButton("공유");
        shareButton.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
        shareButton.setBounds(164, 12, 79, 23);
        contentPane.add(shareButton);

        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        String chatTitle = checkBox.getText();
                        Integer roomId = chatRoomController.getRoomId(chatTitle);
                        printWriter.println("shareTime%"+roomId+"%"+userId+"%"+time);
                        printWriter.flush();
                        setVisible(false);
                    }
                }
            }
        });
    }
}
