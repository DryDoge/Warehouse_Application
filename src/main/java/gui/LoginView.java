package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView{

    private static JFrame jf;
    private final JTextField text;
    private final JTextField errtext;
    private JLabel userLabel, passwordLabel;
    private JPasswordField passwordText;
    private JTextField userText;
    private JButton loginButton, resetButton;


    public LoginView(){
        this.text = new JTextField("Succesfully logged");
        this.errtext = new JTextField("Incorrect login or password");
        jf = new JFrame("LOGIN MENU");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.userLabel = new JLabel("User:");
        this.passwordLabel = new JLabel("Password:");
        this.loginButton = new JButton("Login");
        this.resetButton = new JButton("Clear");
        this.userText = new JTextField(20);
        this.passwordText = new JPasswordField(20);

        userLabel.setBounds(30, 20, 100, 40);
        passwordLabel.setBounds(30, 60, 80, 25);
        userText.setBounds(100, 30, 160, 25);
        passwordText.setBounds(100, 60, 160, 25);
        loginButton.setBounds(50, 100, 90, 35);
        resetButton.setBounds(160, 100, 90, 35);

        jf.setSize(300, 180);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        jf.setLayout(null);


        jf.add(userLabel);
        jf.add(userText);
        jf.add(passwordLabel);
        jf.add(passwordText);
        jf.add(loginButton);
        jf.add(resetButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = userText.getText();
                String pass = new String(passwordText.getPassword());
                if (uname.equals("Admin") && (pass.equals("admin"))) {
                    JOptionPane.showMessageDialog(jf, text.getText());
                } else {
                    JOptionPane.showMessageDialog(jf, errtext.getText(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passwordText.setText("");
            }
        });
    }


}