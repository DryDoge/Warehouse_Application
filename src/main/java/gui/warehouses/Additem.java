package gui.warehouses;

import db.dao.daoBasics;
import db.dao.daoNapoj;
import db.e.Napoj;
import db.e.Obsahuje;
import org.eclipse.persistence.exceptions.EclipseLinkException;

import javax.persistence.RollbackException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Additem extends JFrame{
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

    Additem(int wareID) {
        super("Add item to warehouse");
        warehouseID = wareID;
        setContentPane(addItemPanel);
        setSize(400, 170);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setData();


        beveragesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) beveragesComboBox.getSelectedItem());
                    Napoj n = new daoNapoj().getBeverageByID(chosenId);
                    inforamtionsLabel.setText(n.toString());
                }catch (NumberFormatException ex) {
                    inforamtionsLabel.setText("Please choose number!");
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
                    if (addBeverageToWarehouse()) {
                        JOptionPane.showMessageDialog(null,
                                "Succesfully added beverage No. "
                                        + beveragesComboBox.getSelectedItem() + " with "
                                        + amountTextField.getText() + " pieces");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(null,
                                "Error while adding. Try again");
                } catch (NumberFormatException ex){
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                } catch (RollbackException | org.eclipse.persistence.exceptions.DatabaseException eex){
                    JOptionPane.showConfirmDialog(
                            null,
                            "Selected beverage is already in this warehouse!\n"
                                    + "Please select other!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    setData();
                }


            }
        });

    }

    private boolean addBeverageToWarehouse() {

        int beveId = Integer.valueOf((String) beveragesComboBox.getSelectedItem());
        String amount = amountTextField.getText();


        if(amount.equals("") || amount.equals("0") || !(amount.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Amount is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return false;
        }

        Obsahuje con = new Obsahuje();
        con.setIdsklad(warehouseID);
        con.setIdnap(beveId);
        con.setMnozstvo(Integer.valueOf(amount));
        new daoBasics().addAnything(con);

        return true;
    }

    void setData(){
        List<Napoj> l = new daoNapoj().getAllBeverages();
        beveragesComboBox.removeAllItems();
        beveragesComboBox.addItem("Select Number");
        for (Napoj n :l) {
            String item = String.valueOf(n.getIdnap());
            beveragesComboBox.addItem(item);
        }
    }
}
