package gui.suppliers;

import db.dao.daoDodavatel;
import db.e.Dodavatel;
import db.e.Napoj;

import javax.persistence.RollbackException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuppliersGui extends JFrame
{
    private JPanel mainPanel;
    private JComboBox<String> suppliersCB;
    private JButton refreshButton;
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel telLabel;
    private JLabel webLabel;
    private JLabel background;
    private JList<String> supplyList;
    private JPanel descPanel;
    private JPanel actionsPanel;
    private JScrollPane supplyScrollPanel;
    private JPanel supplyPanel;
    private NewSupplier  ns = null;
    private UpdateSupplier us = null;
    private final static Logger logr = Logger.getLogger(SuppliersGui.class.getName());
    static {
        try {
            FileHandler fh = new FileHandler("SuppliersLogger.txt");
            fh.setLevel(Level.WARNING);
            logr.addHandler(fh);
        } catch (IOException e) {
            logr.log(Level.SEVERE, " File logger not working", e);
        }
    }

    /**
     * Class constructor for suppliers.
     */
    public SuppliersGui(){
        super("Supplier");
        logr.setLevel(Level.INFO);
        setContentPane(mainPanel);
        setSize(750 , 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon backgroundIcon = new ImageIcon("icons/supplier-back.jpg", "Background");
        telLabel.setText("");
        emailLabel.setText("");
        webLabel.setText("");
        nameLabel.setText("");
        webLabel.setText("");
        background.setIcon(backgroundIcon);
        refreshButton.doClick();
        logr.setLevel(Level.INFO);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ns = null;
                us = null;
                setData();
            }
        });

        suppliersCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) suppliersCB.getSelectedItem());
                    Dodavatel d = new daoDodavatel().getSupplierById(chosenId);
                    telLabel.setText(d.getTel());
                    nameLabel.setText(d.getNazov());
                    emailLabel.setText(d.getEmail());
                    webLabel.setText(d.getWeb());
                    List<Napoj> list = d.getNapoje();
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    if (list.isEmpty()){
                        listModel.addElement("This supplier does not delivery anything!");
                    }else {
                        for (Napoj items : list) {
                            listModel.addElement(items.toString());
                        }
                    }
                    supplyList.setModel(listModel);
                }catch (NullPointerException | IllegalArgumentException ex){
                    telLabel.setText("");
                    emailLabel.setText("");
                    webLabel.setText("");
                    nameLabel.setText("");
                    webLabel.setText("");
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addElement("Choose number of supplier first!");
                    supplyList.setModel(listModel);
                    logr.info("No supplier was selected");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) suppliersCB.getSelectedItem());
                    int optionButton = JOptionPane.showConfirmDialog(null,
                            "Do you really want to delete this supplier?",
                            "Delete confirmation",JOptionPane.YES_NO_OPTION);
                    if (optionButton == JOptionPane.YES_OPTION) {
                        Dodavatel d = new daoDodavatel().getSupplierById(chosenId);
                        if (deleteSelectedSupplier(d)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Succesfully deleted supplier No. " + chosenId
                            );
                            refreshButton.doClick();
                        }
                    }
                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Choose supplier first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No supplier was selected to delete");
                }
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ns == null || !(ns.isVisible())) {
                    ns = null;
                    ns = new NewSupplier();
                    ns.setVisible(true);
                }else{
                    ns.toFront();
                    ns.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Window for adding beverage is already open");

                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String)suppliersCB.getSelectedItem());
                    Dodavatel d = new daoDodavatel().getSupplierById(chosenId);

                    if(us == null || !(us.isVisible())) {
                        us = null;
                        us = new UpdateSupplier(d);
                        us.setVisible(true);
                    }else{
                        us.setState(NORMAL);
                        us.toFront();
                        us.requestFocus();
                        JOptionPane.showMessageDialog(null,
                                "Window for updating beverage is already open");
                    }
                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No supplier was selected to update");
                }
            }
        });

        addWindowListener(new  java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                if (!(ns == null)) {
                    ns.dispose();
                    ns = null;
                }
                if (!(us == null)) {
                    us.dispose();
                    us = null;
                }

                dispose();
            }
        });
    }
    /**
     * Get all suppliers and prepare them for choosing.
     */
    public void setData() {
        List<Dodavatel> l = new daoDodavatel().getAllSuppliers();
        suppliersCB.removeAllItems();
        suppliersCB.addItem("Select Number");
        for (Dodavatel s :l) {
            String item = String.valueOf(s.getIddod());
            suppliersCB.addItem(item);
        }
    }

    /**
     * Deletes chosen supplier.
     *
     * @param d The supplier who is going to be deleted.
     * @return True on success, false otherwise.
     */
    private boolean deleteSelectedSupplier(Dodavatel d){
        try {

            new daoDodavatel().deleteSupplier(d.getIddod());
            return true;
        }catch (RollbackException e){
            logr.warning("Cannot delete supplier from database");
            return false;
        }
    }

    /**
     * Checks if email is correct.
     *
     * @param email Email adress which is going to be checked.
     * @return True if it is right, false otherwise.
     */
    private static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Checks whether name, web page, telephone number and email are filled correctly
     *
     * @param name Name of the supplier.
     * @param web Web page of the supplier.
     * @param telephone Telephone number of the supplier.
     * @param email Email of supplier.
     * @return True if all are correct, false otherwise.
     */
    static boolean areValidData(String name, String web, String telephone, String email){
        boolean ret = false;

        if (name.equals("")) {
            JOptionPane.showConfirmDialog(
                    null, "Street is not filled",
                    "Warning", JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if (web.equals("") || !(web.matches(
                "^(https?://)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}/?$"))
                ) {
            JOptionPane.showConfirmDialog(
                    null, "Web page is not filled or wrong",
                    "Warning", JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if ((telephone.length() != 9) || !(telephone.matches("[0-9]+"))) {
            JOptionPane.showConfirmDialog(
                    null, "Telephone number is not filled or wrong",
                    "Warning", JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if(!(isValidEmailAddress(email)) || email.equals("")){
            JOptionPane.showConfirmDialog(
                    null, "Email is not filled or wrong",
                    "Warning", JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        ret = true;

        return ret;
    }
}
