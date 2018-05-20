package gui.warehouses;

import db.dao.DaoBasics;
import db.dao.DaoBeverage;
import db.entity.*;


import javax.persistence.RollbackException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

/*
Class representing window for adding a new beverage into the warehouse.
*/

public class AddNewItem extends JFrame{
    private JPanel addItemPanel;
    private JComboBox<String> beveragesComboBox;
    private JLabel idLabel;
    private JTextField amountTextField;
    private JLabel inforamtionsLabel;
    private JLabel infoLabel;
    private JLabel amountLabel;
    private JButton addButton;
    private JButton cancelButton;
    private static int warehouseID;
    private static final Logger logr = WarehouseGUI.getLogr();

    /**
     *Class constructor for adding a beverage to the warehouse.
     * @param wareID Id of the warehouse in which we want to add an item.
     */
    AddNewItem(int wareID) {
        super("Add item to warehouse");
        warehouseID = wareID;
        setContentPane(addItemPanel);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setData();

        beveragesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) beveragesComboBox.getSelectedItem());
                    Beverage n = new DaoBeverage().getBeverageByID(chosenId);
                    inforamtionsLabel.setText(n.toString());
                }catch (IllegalArgumentException | NullPointerException ex) {
                    inforamtionsLabel.setText("Please choose number!");
                    logr.info("No beverage was selected to add in warehouse.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int beverageId = Integer.valueOf((String) beveragesComboBox.getSelectedItem());
                    String amount = amountTextField.getText();
                    if (addBeverageToWarehouse(beverageId, amount)) {
                        JOptionPane.showMessageDialog(null,
                                "Succesfully added beverage No. "
                                        + beveragesComboBox.getSelectedItem() + " with "
                                        + amountTextField.getText() + " pieces.");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(null,
                                "Error while adding. Try again!");
                } catch (IllegalArgumentException | NullPointerException ex){
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No beverage was selected to add in warehouse.");
                } catch (RollbackException | org.eclipse.persistence.exceptions.DatabaseException eex){
                    JOptionPane.showConfirmDialog(
                            null,
                            "Selected beverage is already in this warehouse!\n"
                                    + "Please select other!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("Selected beverage is already in warehouse.");
                    setData();
                }
            }
        });
    }

    /**
     * Add a beverage to the warehouse.
     * @param beveId Id of added beverage.
     * @param amount Number of pieces.
     * @return true on success, false otherwise.
     */
    private boolean addBeverageToWarehouse(int beveId, String amount) {
        if(amount.equals("") || amount.equals("0") || !(amount.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Amount is not filled or wrong!",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return false;
        }
        Contain con = new Contain();
        con.setStorageid(warehouseID);
        con.setBeverageid(beveId);
        con.setAmount(Integer.valueOf(amount));
        new DaoBasics().addAnything(con);
        return true;
    }

    /**
     * Get all Beverages from database and prepare them for choosing.
     */
    void setData(){
        List<Beverage> l = new DaoBeverage().getAllBeverages();
        beveragesComboBox.removeAllItems();
        beveragesComboBox.addItem("Select Number");
        for (Beverage b :l) {
            String item = String.valueOf(b.getId());
            beveragesComboBox.addItem(item);
        }
    }
}
