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
    private int id;
    private static final Logger logr = ProductsGui.getLogr();

    UpdateBeverage(Napoj n) {
        super("Update beverage No. " + n.getIdnap());
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        this.id = n.getIdnap();
        String flavor = n.getPrichut();
        String category = n.getDruh();
        String brand = n.getZnacka();
        int price = n.getCena();

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
                    if (updateData(id)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated beverage No. " + id
                        );
                        dispose();
                    }
                }
            }
        });
    }
    private boolean updateData(int id) {
        boolean ret = false;
        String f = flavorTF.getText();
        String c = categoryTF.getText();
        String b = brandTF.getText();
        String p = priceTF.getText();
        String chosenSupp;
        Dodavatel dod;

        if(!(ProductsGui.areValidData(c, b, p)))
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

        Napoj beverage = new daoNapoj().getBeverageByID(id);

        if(f.equals(""))
            beverage.setPrichut("Bez prichute");
        else
            beverage.setPrichut(f);
        beverage.setDruh(c);
        beverage.setZnacka(b);
        beverage.setCena(Short.valueOf(p));
        if (alcoholicRadioButton.isSelected())
            beverage.setTyp("Alko");
        else
            beverage.setTyp("Nealko");
        beverage.setDodavatel(dod);

        new daoNapoj().updateBeverage(beverage);
        ret = true;

        return ret;
    }
}
