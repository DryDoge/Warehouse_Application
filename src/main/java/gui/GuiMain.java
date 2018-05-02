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
    private JButton suppliersButton;
    private JLabel imageLabel;

    private warehouseGUI wh = new warehouseGUI();


    public GuiMain() {

        super("Application");
        setContentPane(mainPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        ImageIcon homeIcon = new ImageIcon("icons/home.png", "Home");
        homeButton.setIcon(homeIcon);
        ImageIcon wareIcon = new ImageIcon("icons/warehouse.png", "Warehouse");
        warehouseButton.setIcon(wareIcon);
        ImageIcon bevIcon = new ImageIcon("icons/beverages.png", "Beverages");
        productsButton.setIcon(bevIcon);
        ImageIcon sellsIcon = new ImageIcon("icons/sells.png", "Sells");
        sellsButton.setIcon(sellsIcon);
        ImageIcon empIcon = new ImageIcon("icons/emp.png", "Employers");
        employersButton.setIcon(empIcon);
        ImageIcon logoutIcon = new ImageIcon("icons/exit.png", "Exit");
        logoutButton.setIcon(logoutIcon);
        ImageIcon suppIcon = new ImageIcon("icons/delivery.png", "Suppliers");
        suppliersButton.setIcon(suppIcon);
        ImageIcon backIcon = new ImageIcon("icons/background1.png","Background");
        imageLabel.setIcon(backIcon);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int optionButton = JOptionPane.showConfirmDialog(
                        null, "Do you really want to logout?", "Confrim logout", JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION) {
                    dispose();
                    LoginMenu l = new LoginMenu();
                    wh.dispose();
                    l.actionLogin();
                }
            }
        });

        warehouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            wh.setVisible(true);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wh.dispose();
            }
        });
    }


}
