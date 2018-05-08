package gui.warehouses;

import db.e.*;
import db.dao.*;


import java.awt.event.*;

import java.util.*;
import javax.swing.*;

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
    static private Additem ai = null;

    public WarehouseGUI() {
        super("Warehouse");
        setContentPane(whPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(850, 710);
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
                    Sklad s = new daoSklad().getWarehouseById(chosenId);
                    telLabel.setText(s.getTel());
                    streetLabel.setText(s.getUlica());
                    cityLabel.setText(s.getMesto());
                    pscLabel.setText(s.getPsc());
                    webLabel.setText(s.getWeb());
                    List<Napoj> list = s.getNapoje();
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    if (list.isEmpty()){
                        listModel.addElement("This warehouse is empty!");
                    }else {
                        for (Napoj items : list) {
                            int amount = new daoObsahuje().getAmount(chosenId, items.getIdnap());
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
                        Sklad s1 = new daoSklad().getWarehouseById(chosenId);
                        if (deleteSelectedWarehouse(s1)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Succesfully deleted warehouse No. " + chosenId
                            );
                            refreshButton.doClick();
                        }
                    }

                } catch (IllegalArgumentException ex) {
                            JOptionPane.showConfirmDialog(null,
                                     "Delete is not possible now",
                                    "Warning", JOptionPane.DEFAULT_OPTION);
                } catch (NullPointerException exp){
                    JOptionPane.showConfirmDialog(
                            null, "Choose warehouse first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
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
                    JOptionPane.showMessageDialog(null,
                            "Window for adding warehouse is already open");
                    nw.requestFocus();
                }

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nw = null;
                uw = null;
                setData();

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                    Sklad s1 = new daoSklad().getWarehouseById(chosenId);

                    if(uw == null || !(uw.isVisible())) {
                        uw = null;
                        uw = new UpdateWarehouse(s1);
                        uw.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Window for updating warehouse is already open");
                        uw.requestFocus();
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
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
                                            + " from warehouse No. " + warehouseId
                            );
                            refreshButton.doClick();
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Delete is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                } catch (NullPointerException exp){
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
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

                    Obsahuje c = new daoObsahuje().getContentInfo(warehouseId, beverageID);

                    if(ua == null || !(ua.isVisible())) {
                        ua = null;
                        ua = new UpdateAmount(c);
                        ua.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Window for updating amount is already open");
                        ua.requestFocus();
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                } catch (NullPointerException exp){
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
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
                        ai = new Additem(chosenId);
                        ai.setVisible(true);
                        ai.setData();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Window for adding item is already open");
                        ai.requestFocus();
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showConfirmDialog(
                            null, "Choose warehouse first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
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
            }
        });
    }



    public void setData(){
        List<Sklad> l = new daoSklad().getAllWarehouses();
        warehousesCB.removeAllItems();
        warehousesCB.addItem("Select Number");
        for (Sklad s :l) {
            String item = String.valueOf(s.getIdsklad());
            warehousesCB.addItem(item);
        }
    }

    private boolean deleteSelectedWarehouse(Sklad s) {
        new daoSklad().deleteWarehouse(s.getIdsklad());
        return true;
    }

    private boolean deleteSelectedContent(int warehouse, int beverage){
        new daoObsahuje().deleteContentOfItem(warehouse, beverage);
        return true;
    }

}
