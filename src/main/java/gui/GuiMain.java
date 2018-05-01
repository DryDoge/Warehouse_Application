package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMain extends JFrame {
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton employersButton;
    private JButton productsButton;
    private JButton warehouseButton;
    private JButton sellsButton;
    private JButton logoutButton;
    private JPanel menuPanel;
    private JTextPane titleTextPane;
    private JPanel workingPanel;


    public GuiMain() {

        super("Application");
        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon homeIcon = new ImageIcon("icons/home.png","Home");
        ImageIcon wareIcon = new ImageIcon("icons/warehouse.png","Warehouse");
        ImageIcon empIcon = new ImageIcon("icons/emp.png","Employers");
        ImageIcon sellsIcon = new ImageIcon("icons/sells.png", "Sells");
        ImageIcon bevIcon = new ImageIcon("icons/beverages.png", "Beverages");
        ImageIcon logoutIcon = new ImageIcon("icons/exit.png", "Exit");
        homeButton.setIcon(homeIcon);
        warehouseButton.setIcon(wareIcon);
        productsButton.setIcon(bevIcon);
        sellsButton.setIcon(sellsIcon);
        employersButton.setIcon(empIcon);
        logoutButton.setIcon(logoutIcon);


    }
}
