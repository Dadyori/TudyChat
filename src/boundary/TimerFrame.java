package boundary;

import control.TimerController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class TimerFrame extends JFrame {
    TimerController timerController = new TimerController();
    String userId;
    Thread timeDisplay;
    int hour, min, sec, t=0;
    LocalDate oldTime;
    static String timerBuffer;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    TimerFrame window = new TimerFrame();
//                    window.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the application.
     */
    public TimerFrame(String userId, BufferedReader br, PrintWriter pw) {
        this.bufferedReader = br;
        this.printWriter = pw;
        this.userId = userId;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setBounds(100, 100, 663, 409);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.black,2));
        panel.setBackground(new Color(245, 245, 220));
        panel.setBounds(0, 0, 649, 372);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("TUDY CHAT");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD, 27));
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setBounds(12, 10, 182, 48);
        panel.add(lblNewLabel);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(Color.black,2));
        panel_2.setBackground(new Color(245, 245, 220));
        panel_2.setBounds(12, 68, 623, 294);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JButton startTimerButton = new JButton("\uACF5\uBD80 \uC2DC\uC791");
        startTimerButton.setBorder(new LineBorder(Color.black,2));
        startTimerButton.setBounds(12, 236, 190, 50);
        startTimerButton.setBackground(new Color(245, 245, 220));
        startTimerButton.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
        panel_2.add(startTimerButton);

        JButton pauseTimerButton = new JButton("일시 정지");
        pauseTimerButton.setBorder(new LineBorder(Color.black,2));
        pauseTimerButton.setBounds(216, 236, 190, 50);
        pauseTimerButton.setBackground(new Color(245, 245, 220));
        pauseTimerButton.setFont(new Font("한초롬돋움", Font.PLAIN, 25));
        panel_2.add(pauseTimerButton);

        JButton finishTimerButton = new JButton("\uC624\uB298 \uACF5\uBD80 \uB05D");
        finishTimerButton.setBorder(new LineBorder(Color.black,2));
        finishTimerButton.setBounds(421, 236, 190, 50);
        finishTimerButton.setBackground(new Color(245, 245, 220));
        finishTimerButton.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
        panel_2.add(finishTimerButton);

        JLabel hourLabel = new JLabel("00");
        hourLabel.setFont(new Font("굴림", Font.BOLD, 90));
        hourLabel.setBounds(64, 62, 125, 120);
        panel_2.add(hourLabel);

        JLabel lblNewLabel_1 = new JLabel(" : ");
        lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 90));
        lblNewLabel_1.setBounds(166, 74, 90, 97);
        panel_2.add(lblNewLabel_1);

        JLabel minLabel = new JLabel("00");
        minLabel.setFont(new Font("굴림", Font.BOLD, 90));
        minLabel.setBounds(253, 62, 118, 120);
        panel_2.add(minLabel);

        JLabel lblNewLabel_2 = new JLabel(" : ");
        lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 90));
        lblNewLabel_2.setBounds(360, 74, 90, 97);
        panel_2.add(lblNewLabel_2);

        JLabel secLabel = new JLabel("00");
        secLabel.setFont(new Font("굴림", Font.BOLD, 90));
        secLabel.setBounds(449, 62, 125, 120);
        panel_2.add(secLabel);

        JButton TimerGraphButton = new JButton("\uC9C0\uB09C \uACF5\uBD80\uC2DC\uAC04 \uD655\uC778");
        TimerGraphButton.setBorder(new LineBorder(Color.black,2));
        TimerGraphButton.setBackground(new Color(245, 245, 220));
        TimerGraphButton.setFont(new Font("함초롬돋움", Font.PLAIN, 21));
        TimerGraphButton.setForeground(new Color(0, 0, 0));
        TimerGraphButton.setBounds(442, 20, 193, 33);
        panel.add(TimerGraphButton);

        pauseTimerButton.setEnabled(false);
        finishTimerButton.setEnabled(false);

        TimerGraphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimeGraphFrame graph = new TimeGraphFrame(userId);
            }
        });

        startTimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimerButton.setEnabled(false);
                pauseTimerButton.setEnabled(true);
                finishTimerButton.setEnabled(true);

                timeDisplay = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        hour = Integer.parseInt(hourLabel.getText());
                        min = Integer.parseInt(minLabel.getText());
                        sec = Integer.parseInt(secLabel.getText());

                        //측정시작한 날짜
                        oldTime = LocalDate.now(ZoneId.of("Asia/Seoul"));

                        while (timeDisplay == Thread.currentThread()) {

                            hour =  t / 3600 ;
                            min = t / 60 ;
                            sec = t % 60;

                            try {
                                hourLabel.setText(String.format("%02d", hour));
                                minLabel.setText(String.format("%02d", min));
                                secLabel.setText(String.format("%02d", sec));

                                t++;

                                Thread.sleep(1000);

                            }catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                timeDisplay.start();
            }
        });

        pauseTimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimerButton.setEnabled(true);
                pauseTimerButton.setEnabled(false);
                finishTimerButton.setEnabled(true);

                timeDisplay = null;
            }
        });

        finishTimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimerButton.setEnabled(false);
                pauseTimerButton.setEnabled(false);
                finishTimerButton.setEnabled(false);

                timeDisplay = null;

                timerBuffer =hourLabel.getText();
                timerBuffer +=minLabel.getText();
                timerBuffer +=secLabel.getText();
                //시시분분초초 형식
                DateFormat sdFormat = new SimpleDateFormat("HHmmss");
                try {
                    //측정시간 저장
                    Date studyTime = sdFormat.parse(timerBuffer);
                    LocalDateTime todayStudy = LocalDateTime.ofInstant(studyTime.toInstant(), ZoneId.systemDefault());
                    String studyT = todayStudy.getHour()+":"+todayStudy.getMinute()+":"+todayStudy.getSecond();
                    timerController.setStudyTime(userId, oldTime.toString(), studyT);
                } catch (ParseException | SQLException e2) {
                    e2.printStackTrace();
                }
                CheckTimeFrame checktimer = new CheckTimeFrame(userId, timerBuffer, bufferedReader, printWriter);
                setVisible(false);
                checktimer.setVisible(true);
            }
        });

    }


}
