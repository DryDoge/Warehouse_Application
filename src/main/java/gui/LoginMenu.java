package gui;

import javax.swing.*;
import java.awt.event.*;

public class LoginMenu extends JFrame{

    private final JTextField errtext = new JTextField("Incorrect login or password");
    private final JPasswordField passwordText;
    private final JTextField userText;
    private final JButton loginButton;

    public LoginMenu(){
        super("Login Menu");
        JLabel userLabel = new JLabel("User:");
        JLabel passwordLabel = new JLabel("Password:");
        JButton resetButton = new JButton("Clear");
        this.loginButton = new JButton("Login");
        this.userText = new JTextField(20);
        this.passwordText = new JPasswordField(20);

        userLabel.setBounds(30, 20, 100, 40);
        passwordLabel.setBounds(30, 60, 80, 25);
        resetButton.setBounds(160, 100, 90, 35);
        userText.setBounds(100, 30, 160, 25);
        passwordText.setBounds(100, 60, 160, 25);
        loginButton.setBounds(50, 100, 90, 35);

        setSize(300, 180);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        add(userLabel);
        add(userText);
        add(passwordLabel);
        add(passwordText);
        add(loginButton);
        add(resetButton);
        
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
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

    public void actionLogin(){
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                String uname = userText.getText();
                //noinspection deprecation
                String upass = passwordText.getText();
                if(uname.equals("admin") && upass.equals("admin")){
                    dispose();
                    new GuiMain();
                }else{
                    JOptionPane.showMessageDialog(null, errtext.getText());
                    userText.setText("");
                    passwordText.setText("");
                    userText.requestFocus();
                }
            }
        });
    }
}
