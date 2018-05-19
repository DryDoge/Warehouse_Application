package gui.warehouses;

import db.dao.daoBasics;
import db.dao.daoSklad;
import db.e.Sklad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private Sklad warehouseToUpdate = null;

    /**
     * Class constructor specifying which warehouse is going to be updated.
     * @param w Warehouse to update.
     */
    UpdateWarehouse(Sklad w){
        super("Update Warehouse No. "+w.getIdsklad());
        this.warehouseToUpdate = w;
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        String tel = warehouseToUpdate.getTel();
        String street = warehouseToUpdate.getUlica();
        String city = warehouseToUpdate.getMesto();
        String postalCode = warehouseToUpdate.getPsc();
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
                        new daoSklad().updateWarehouse(warehouseToUpdate);
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated warehouse No. "+ warehouseToUpdate.getIdsklad()+"."
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
        warehouseToUpdate.setUlica(s);
        warehouseToUpdate.setMesto(c);
        warehouseToUpdate.setPsc(p);
        warehouseToUpdate.setWeb(w);

        ret = true;
        return ret;
    }


}