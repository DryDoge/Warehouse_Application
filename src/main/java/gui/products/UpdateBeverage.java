package gui.products;

import db.dao.daoDodavatel;
import db.dao.daoNapoj;
import db.e.Dodavatel;
import db.e.Napoj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UpdateBeverage extends JFrame {

    private JTextField priceTF;
    private JTextField flavorTF;
    private JTextField categoryTF;
    private JTextField brandTF;
    private JRadioButton alcoholicRadioButton;
    private JRadioButton nonalcoholicRadioButton;
    private JComboBox<String> suppliersCB;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel actionPanel;
    private JPanel updatePanel;
    private static final Logger logr = ProductsGui.getLogr();
    private Napoj beverageToUpdate = null;

    /**
     * Class constructor specifying which beverage is going to be updated.
     *
     * @param n Beverage to update.
     */
    UpdateBeverage(Napoj n) {
        super("Update beverage No. " + n.getIdnap());
        this.beverageToUpdate = n;
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        String flavor = beverageToUpdate.getPrichut();
        String category = beverageToUpdate.getDruh();
        String brand = beverageToUpdate.getZnacka();
        int price = beverageToUpdate.getCena();

        flavorTF.setText(flavor);
        categoryTF.setText(category);
        brandTF.setText(brand);
        priceTF.setText(String.valueOf(price));
        if (n.getTyp().equals("Alko")) {
            alcoholicRadioButton.setSelected(true);
            nonalcoholicRadioButton.setSelected(false);
        } else {
            nonalcoholicRadioButton.setSelected(true);
            alcoholicRadioButton.setSelected(false);
        }

        List<Dodavatel> l = new daoDodavatel().getAllSuppliers();
        suppliersCB.removeAllItems();
        suppliersCB.addItem("Select supplier");
        for (Dodavatel s : l) {
            String item = String.valueOf(s.getNazov());
            suppliersCB.addItem(item);
        }
        suppliersCB.setSelectedItem(n.getIdnap());

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
                        "Do you really want to update this beverage ?",
                        "Update confirmation", JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION) {
                    if (updateSelectedBeverage()) {
                        new daoNapoj().updateBeverage(beverageToUpdate);
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated beverage No. " + beverageToUpdate.getIdnap()
                        );
                        dispose();
                    }
                }
            }
        });
    }

    /**
     * Update beverage from user's inputs.
     *
     * @return True on success, false otherwise.
     */
    private boolean updateSelectedBeverage() {
        boolean ret = false;
        String f = flavorTF.getText();
        String c = categoryTF.getText();
        String b = brandTF.getText();
        String p = priceTF.getText();
        String chosenSupp;
        Dodavatel dod;
        if(!(new ProductsGui().areValidData(c, b, p)))
            return ret;
        if(!(alcoholicRadioButton.isSelected()) && !(nonalcoholicRadioButton.isSelected())){
            JOptionPane.showConfirmDialog(
                null,"Type is not selected",
                "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        try{
            chosenSupp = (String)suppliersCB.getSelectedItem();
            dod = new daoDodavatel().getSupplierByName(chosenSupp);
        }catch (javax.persistence.NoResultException | IllegalArgumentException | NullPointerException ex){
            JOptionPane.showConfirmDialog(
                    null,"Supplier is not selected",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            logr.info("Supplier was not selected while updating beverage");
            dod = null;
        }
        //Update beverage
        if(f.equals(""))
            beverageToUpdate.setPrichut("Bez prichute");
        else
            beverageToUpdate.setPrichut(f);
        beverageToUpdate.setDruh(c);
        beverageToUpdate.setZnacka(b);
        beverageToUpdate.setCena(Short.valueOf(p));
        if (alcoholicRadioButton.isSelected())
            beverageToUpdate.setTyp("Alko");
        else
            beverageToUpdate.setTyp("Nealko");
        beverageToUpdate.setDodavatel(dod);
        ret = true;

        return ret;
    }
}
