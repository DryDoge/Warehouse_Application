package gui.products;

import db.dao.DaoSupplier;
import db.dao.DaoBeverage;
import db.entity.Supplier;
import db.entity.Beverage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

/*
Class representing window for updating a beverage.
*/

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
    private Beverage beverageToUpdate = null;

    /**
     * Class constructor specifying which beverage is going to be updated.     *
     * @param b Beverage to update.
     */
    UpdateBeverage(Beverage b) {
        super("Update beverage No. " + b.getId());
        this.beverageToUpdate = b;
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        String flavor = beverageToUpdate.getFlavor();
        String category = beverageToUpdate.getCategory();
        String brand = beverageToUpdate.getBrand();
        int price = beverageToUpdate.getPrice();

        flavorTF.setText(flavor);
        categoryTF.setText(category);
        brandTF.setText(brand);
        priceTF.setText(String.valueOf(price));
        if (b.getType().equals("Alko")) {
            alcoholicRadioButton.setSelected(true);
            nonalcoholicRadioButton.setSelected(false);
        } else {
            nonalcoholicRadioButton.setSelected(true);
            alcoholicRadioButton.setSelected(false);
        }

        List<Supplier> l = new DaoSupplier().getAllSuppliers();
        suppliersCB.removeAllItems();
        suppliersCB.addItem("Select supplier");
        for (Supplier s : l) {
            String item = String.valueOf(s.getName());
            suppliersCB.addItem(item);
        }
        suppliersCB.setSelectedItem(b.getId());

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
                        new DaoBeverage().updateBeverage(beverageToUpdate);
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated beverage No. " + beverageToUpdate.getId() + "."
                        );
                        dispose();
                    }
                }
            }
        });
    }

    /**
     * Update beverage from user's inputs.
     * @return True on success, false otherwise.
     */
    private boolean updateSelectedBeverage() {
        boolean ret = false;
        String f = flavorTF.getText();
        String c = categoryTF.getText();
        String b = brandTF.getText();
        String p = priceTF.getText();
        String chosenSupp;
        Supplier dod;
        if(!(new ProductsGui().areValidData(c, b, p)))
            return ret;
        if(!(alcoholicRadioButton.isSelected()) && !(nonalcoholicRadioButton.isSelected())){
            JOptionPane.showConfirmDialog(
                null,"Type is not selected!",
                "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        try{
            chosenSupp = (String)suppliersCB.getSelectedItem();
            dod = new DaoSupplier().getSupplierByName(chosenSupp);
        }catch (javax.persistence.NoResultException | IllegalArgumentException | NullPointerException ex){
            JOptionPane.showConfirmDialog(
                    null,"Supplier is not selected!",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            logr.info("Supplier was not selected while updating beverage.");
            dod = null;
        }
        //Update beverage
        if(f.equals(""))
            beverageToUpdate.setFlavor("Bez prichute");
        else
            beverageToUpdate.setFlavor(f);
        beverageToUpdate.setCategory(c);
        beverageToUpdate.setBrand(b);
        beverageToUpdate.setPrice(Short.valueOf(p));
        if (alcoholicRadioButton.isSelected())
            beverageToUpdate.setType("Alko");
        else
            beverageToUpdate.setType("Nealko");
        beverageToUpdate.setSupplier(dod);
        ret = true;

        return ret;
    }
}
