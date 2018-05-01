package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView{

    private static JFrame jf;
    private final JTextField errtext;

    private final JPasswordField passwordText;
    private final JTextField userText;
    private final JButton loginButton;


    public LoginView(){
        this.errtext = new JTextField("Incorrect login or password");
        jf = new JFrame("Login Menu");

        JLabel userLabel = new JLabel("User:");
        JLabel passwordLabel = new JLabel("Password:");
        JButton resetButton = new JButton("Clear");
        this.loginButton = new JButton("Login");
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
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
        

        jf.add(userLabel);
        jf.add(userText);
        jf.add(passwordLabel);
        jf.add(passwordText);
        jf.add(loginButton);
        jf.add(resetButton);

        
        
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
                char[] upass = passwordText.getPassword();
                if(uname.equals("Admin") && upass.equals("admin")){
//                    InventorySystem saa = new InventorySystem();
//                    saa.setExtendedState(InventorySystem.MAXIMIZED_BOTH);
//                    saa.setVisible(true);
                    jf.dispose();
                }else{
                    JOptionPane.showMessageDialog(jf, errtext.getText());
                    userText.setText("");
                    passwordText.setText("");
                    userText.requestFocus();
                }
            } 
        });
    }
    
    
}
