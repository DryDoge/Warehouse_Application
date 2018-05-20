package gui.warehouses;

import db.entity.*;
import db.dao.*;


import java.awt.event.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.swing.*;

/*
Class representing window for managing all warehouses and beverages in the warehouses.
*/

public class WarehouseGUI extends JFrame {
    private JPanel whPanel;
    private JComboBox<String> warehousesCB;
    private JLabel telLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel pscLabel;
    private JLabel webLabel;
    private JLabel imageLabel;
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JPanel descriptionPanel;
    private JLabel infoNumberLabel;
    private JLabel infoWebLabel;
    private JLabel infoStreetLabel;
    private JLabel infoCityLabel;
    private JLabel infoTelLabel;
    private JLabel infoPCLabel;
    private JPanel actionsPanel;
    private JPanel containsPanel;
    private JButton addButton;
    private JButton deleteItemButton;
    private JPanel itemOptionsPanel;
    private JList<String>productsList;
    private JScrollPane containsScrollPanel;
    private JButton updateItemButton;
    private JPanel descPanel;
    static private NewWarehouse nw = null;
    static private UpdateWarehouse uw = null;
    static private UpdateAmount ua = null;
    static private AddNewItem ai = null;
    private final static Logger logr = Logger.getLogger(WarehouseGUI.class.getName());
    static Logger getLogr() {
        return logr;
    }
    static {
        try {
            FileHandler fh = new FileHandler("logs/WarehousesLogger.txt");
            fh.setLevel(Level.WARNING);
            logr.addHandler(fh);
        } catch (IOException e) {
            logr.log(Level.SEVERE, " File logger not working.", e);
        }
    }

    /**
     * Class constructor for warehouses.
     */
    public WarehouseGUI() {
        super("Warehouse");
        logr.setLevel(Level.INFO);
        setContentPane(whPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(900 , 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon backgroundIcon = new ImageIcon("icons/warehouse-back.jpg", "Background");
        telLabel.setText("");
        streetLabel.setText("");
        cityLabel.setText("");
        pscLabel.setText("");
        webLabel.setText("");
        imageLabel.setIcon(backgroundIcon);
        refreshButton.doClick();

        warehousesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                    Warehouse w = new DaoWarehouse().getWarehouseById(chosenId);
                    telLabel.setText(w.getTel());
                    streetLabel.setText(w.getStreet());
                    cityLabel.setText(w.getCity());
                    pscLabel.setText(w.getPostalcode());
                    webLabel.setText(w.getWeb());
                    List<Beverage> list = w.getBeverages();
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    if (list.isEmpty()){
                        listModel.addElement("This warehouse is empty!");
                    }else {
                        for (Beverage items : list) {
                            int amount = new DaoContain().getAmount(chosenId, items.getId());
                            String itemsForList = items.toString() + " | " + amount + "pcs";
                            listModel.addElement(itemsForList);
                        }
                    }
                    productsList.setModel(listModel);
                } catch (NumberFormatException ex) {
                    telLabel.setText("");
                    streetLabel.setText("");
                    cityLabel.setText("");
                    pscLabel.setText("");
                    webLabel.setText("");
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addElement("Choose number of warehouse first!");
                    productsList.setModel(listModel);
                    logr.info("No warehouse was selected to show.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        int chosenId = Integer.valueOf((String) warehousesCB.getSelectedItem());
                        int optionButton = JOptionPane.showConfirmDialog(null,
                                "Do you really want to delete this warehouse ?",
                                "Delete confirmation",JOptionPane.YES_NO_OPTION);
                        if (optionButton == JOptionPane.YES_OPTION) {
                        Warehouse s1 = new DaoWarehouse().getWarehouseById(chosenId);
                        if (deleteSelectedWarehouse(s1)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Succesfully deleted warehouse No. " + chosenId +"."
                            );
                            refreshButton.doClick();
                        }
                    }

                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(null,
                            "Choose warehouse first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No warehouse was selected to delete.");
                }

            }

        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nw == null || !(nw.isVisible())) {
                    nw = null;
                    nw = new NewWarehouse();
                    nw.setVisible(true);
                }else{
                    nw.setState(NORMAL);
                    nw.toFront();
                    nw.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Window for adding warehouse is already open.");
                }

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nw = null;
                uw = null;
                ua = null;
                ai = null;
                setData();

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                    Warehouse s1 = new DaoWarehouse().getWarehouseById(chosenId);

                    if(uw == null || !(uw.isVisible())) {
                        uw = null;
                        uw = new UpdateWarehouse(s1);
                        uw.setVisible(true);
                    }else{
                        uw.setState(NORMAL);
                        uw.toFront();
                        uw.requestFocus();
                        JOptionPane.showMessageDialog(null,
                                "Window for updating warehouse is already open.");

                    }

                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No warehouse was selected to update.");
                }

            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int warehouseId = Integer.valueOf((String) warehousesCB.getSelectedItem());
                    String fromPanel = productsList.getSelectedValue();
                    String parts[] = fromPanel.split(" ");
                    int beverageID = Integer.valueOf(parts[1]);
                    int optionButton = JOptionPane.showConfirmDialog(null,
                            "Do you really want to delete this item from warehouse ?",
                            "Delete confirmation",JOptionPane.YES_NO_OPTION);
                    if (optionButton == JOptionPane.YES_OPTION) {
                        if (deleteSelectedContent(warehouseId, beverageID)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Succesfully deleted beverage No. " + beverageID
                                            + " from warehouse No. " + warehouseId+"."
                            );
                            refreshButton.doClick();
                        }
                    }
                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No beverage was selected to delete from warehouse.");
                }
            }
        });

        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int warehouseId = Integer.valueOf((String) warehousesCB.getSelectedItem());
                    String fromPanel = productsList.getSelectedValue();
                    String parts[] = fromPanel.split(" ");
                    int beverageID = Integer.valueOf(parts[1]);

                    Contain c = new DaoContain().getContentInfo(warehouseId, beverageID);

                    if(ua == null || !(ua.isVisible())) {
                        ua = null;
                        ua = new UpdateAmount(c);
                        ua.setVisible(true);
                    }else{
                        ua.setState(NORMAL);
                        ua.toFront();
                        ua.requestFocus();
                        JOptionPane.showMessageDialog(null,
                                "Window for updating amount is already open.");
                    }

                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No beverage was selected to update in warehouse.");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ai == null || !(ai.isVisible())) {
                        ai = null;
                        int chosenId = Integer.valueOf((String) warehousesCB.getSelectedItem());
                        ai = new AddNewItem(chosenId);
                        ai.setVisible(true);
                        ai.setData();
                    } else {
                        ai.setState(NORMAL);
                        ai.toFront();
                        ai.requestFocus();
                        JOptionPane.showMessageDialog(null,
                                "Window for adding item is already open.");

                    }
                }catch (IllegalArgumentException | NullPointerException ex){
                    JOptionPane.showConfirmDialog(
                            null, "Choose warehouse first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No warehouse was selected to add beverage.");
                }
            }
        });

        addWindowListener(new  java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                if (!(nw == null)) {
                    nw.dispose();
                    nw = null;
                }
                if (!(uw == null)) {
                    uw.dispose();
                    uw = null;
                }
                if (!(ua == null)) {
                    ua.dispose();
                    ua = null;
                }
                if (!(ai == null)) {
                    ai.dispose();
                    ai = null;
                }
                dispose();
                dispose();
            }
        });
    }

    /**
     * Get all warehouses and prepare them for choosing.
     */
    public void setData(){
        List<Warehouse> l = new DaoWarehouse().getAllWarehouses();
        warehousesCB.removeAllItems();
        warehousesCB.addItem("Select Number");
        for (Warehouse w :l) {
            String item = String.valueOf(w.getId());
            warehousesCB.addItem(item);
        }
    }

    /**
     * Deletes chosen warehouse.
     * @param w The warehouse who is going to be deleted.
     * @return True on success, false otherwise.
     */
    private boolean deleteSelectedWarehouse(Warehouse w) {
        try{
            new DaoWarehouse().deleteWarehouse(w.getId());
            return true;
        }catch (RollbackException e){
            logr.warning("Selected warehouse cannot be deleted.");
            return false;
        }
    }

    /**
     * Removes the chosen beverage from selected warehouse.
     * @param warehouse The warehouse which from we are going to remove the beverage.
     * @param beverage  The beverage which is going to be deleted from the warehouse.
     * @return True on success, false otherwise.
     */
    private boolean deleteSelectedContent(int warehouse, int beverage){
        try{
            new DaoContain().deleteContentOfItem(warehouse, beverage);
            return true;
        }catch (RollbackException e){
            logr.warning("Selected item cannot be deleted from warehouse.");
            return false;
        }
    }

    /**
     * Checks whether city, street, web page, telephone number and postal code are correct.
     * @param city City where the warehouse is located.
     * @param street Street on which the warehouse is located.
     * @param web Web page of the warehouse.
     * @param telephone Telephone number of the warehouse.
     * @param poctalCode Postal code of the warehouse.
     * @return True if all are correct, false otherwise.
     */
    public static boolean areValidData(String city, String street, String web, String telephone, String poctalCode){
        boolean ret = false;
        if(city.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"City is not filled","Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        if(street.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"Street is not filled","Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        if(web.equals("") || !(web.matches(
                "^(https?://)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}/?$"))
                ){
            JOptionPane.showConfirmDialog(
                    null,"Web page is not filled or wrong","Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        if((telephone.length()!= 9) || !(telephone.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Telephone number is not filled or wrong","Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        if((poctalCode.length()!= 5) || !(poctalCode.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Postal code is not filled or wrong","Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        ret = true;

        return ret;
    }

}
