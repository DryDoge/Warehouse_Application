package gui.main;

import gui.products.*;
import gui.suppliers.SuppliersGui;
import gui.warehouses.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.LogManager;

public class GuiMain extends JFrame {
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton productsButton;
    private JButton warehouseButton;
    private JButton logoutButton;
    private JPanel menuPanel;
    private JButton suppliersButton;
    private JLabel imageLabel;
    private static WarehouseGUI wh = null;
    private static ProductsGui pd = null;
    private static SuppliersGui sp = null;

    /**
     * Class constructor for main GUI.
     */
    public GuiMain() {
        super("Application");
        LogManager.getLogManager().reset();
        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon homeIcon = new ImageIcon("icons/mainGui/home.png", "Home");
        ImageIcon wareIcon = new ImageIcon("icons/mainGui/warehouse.png", "Warehouse");
        ImageIcon bevIcon = new ImageIcon("icons/mainGui/beverages.png", "Beverages");
        //ImageIcon empIcon = new ImageIcon("icons/mainGui/emp.png", "Employers");
        ImageIcon logoutIcon = new ImageIcon("icons/mainGui/exit.png", "Exit");
        ImageIcon suppIcon = new ImageIcon("icons/mainGui/delivery.png", "Suppliers");
        ImageIcon backIcon = new ImageIcon("icons/mainGui/background1.png","Background");

        homeButton.setIcon(homeIcon);
        warehouseButton.setIcon(wareIcon);
        productsButton.setIcon(bevIcon);
        logoutButton.setIcon(logoutIcon);
        suppliersButton.setIcon(suppIcon);
        imageLabel.setIcon(backIcon);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int optionButton = JOptionPane.showConfirmDialog(
                        null, "Do you really want to logout?",
                        "Confrim logout", JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION) {
                    dispose();
                    homeButton.doClick();
                    LoginMenu l = new LoginMenu();
                    l.actionLogin();
                }
            }
        });
        warehouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wh == null || !(wh.isVisible())) {
                    wh = null;
                    wh = new WarehouseGUI();
                    wh.setVisible(true);
                    wh.setData();
                }else{
                    wh.setState(NORMAL);
                    wh.toFront();
                    wh.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Warehouses window is already open");
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wh != null)
                    wh.dispose();
                wh = null;
                if(pd != null)
                    pd.dispose();
                pd = null;
                if(sp != null)
                    sp.dispose();
                sp = null;
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pd == null || !(pd.isVisible())) {
                    pd = null;
                    pd = new ProductsGui();
                    pd.setVisible(true);
                    pd.setData();
                }else{
                    pd.setState(NORMAL);
                    pd.toFront();
                    pd.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Products window is already open");

                }

            }
        });
        suppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sp == null || !(sp.isVisible())) {
                    sp = null;
                    sp = new SuppliersGui();
                    sp.setVisible(true);
                    sp.setData();
                }else{
                    sp.setState(NORMAL);
                    sp.toFront();
                    sp.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Warehouses window is already open");
                }
            }
        });
    }
}