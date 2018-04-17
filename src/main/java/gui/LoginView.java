package gui;
import javax.swing.*;
import java.awt.*;

public class LoginView{

    private static JFrame jf;
    private static JPanel jp;

    public LoginView(){
        jf = new JFrame("LOGIN MENU");
        jf.setSize(300, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        jf.add(jp);
        placeComponents(jp);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Login:");
        userLabel.setBounds(30, 20, 100, 40);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 30, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 60, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(40, 110, 90, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(160, 110, 90, 25);
        panel.add(registerButton);

    }


}