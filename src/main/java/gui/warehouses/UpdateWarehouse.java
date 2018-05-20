package gui.warehouses;

import db.dao.DaoWarehouse;
import db.entity.Warehouse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Class representing window for updating a warehouse.
*/

public class UpdateWarehouse extends JFrame {
    private JPanel updatePanel;
    private JPanel optionPanel;

    private JTextField telTextField;
    private JTextField webTextField;
    private JTextField pcTextField;
    private JTextField streetTextField;
    private JTextField cityTextField;

    private JButton saveButton;
    private JButton cancelButton;
    private JLabel webLabel;
    private JLabel postalCodeLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel infoTelLabel;
    private JLabel infoPCLabel;
    private JLabel telLabel;
    private Warehouse warehouseToUpdate = null;

    /**
     * Class constructor specifying which warehouse is going to be updated.
     * @param w Warehouse to update.
     */
    UpdateWarehouse(Warehouse w){
        super("Update Warehouse No. "+w.getId());
        this.warehouseToUpdate = w;
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        String tel = warehouseToUpdate.getTel();
        String street = warehouseToUpdate.getStreet();
        String city = warehouseToUpdate.getCity();
        String postalCode = warehouseToUpdate.getPostalcode();
        String web = warehouseToUpdate.getWeb();

        telTextField.setText(tel);
        streetTextField.setText(street);
        cityTextField.setText(city);
        pcTextField.setText(postalCode);
        webTextField.setText(web);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int optionButton = JOptionPane.showConfirmDialog(null,
                        "Do you really want to update this warehouse ?",
                        "Update confirmation",JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION){
                    if(updateSelectedWarehouse()){
                        new DaoWarehouse().updateWarehouse(warehouseToUpdate);
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated warehouse No. "+ warehouseToUpdate.getId() + "."
                        );
                        dispose();
                    }

                }
            }
        });
    }

    /**
     * Update selected warehouse from user's inputs.
     * @return True on success, false otherwise.
     */
    private boolean updateSelectedWarehouse(){
        boolean ret = false;
        String c = cityTextField.getText();
        String s = streetTextField.getText();
        String w = webTextField.getText();
        String t = telTextField.getText();
        String p = pcTextField.getText();

        if(!(WarehouseGUI.areValidData(c,s,w,t,p)))
            return ret;
        warehouseToUpdate.setTel(t);
        warehouseToUpdate.setStreet(s);
        warehouseToUpdate.setCity(c);
        warehouseToUpdate.setPostalcode(p);
        warehouseToUpdate.setWeb(w);

        ret = true;
        return ret;
    }


}