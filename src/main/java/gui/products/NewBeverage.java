package gui.products;

import db.dao.*;
import db.entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
Class representing window for creating a new beverage.
*/

public class NewBeverage extends JFrame {
    private JTextField flavorTextField;
    private JTextField categoryTextField;
    private JTextField brandTextField;
    private JTextField priceTextField;
    private JComboBox<String> suppliersCB;
    private JRadioButton alcoholicRadioButton;
    private JRadioButton nonalcoholicRadioButton;
    private JPanel newBeveragePanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel actionPanel;
    private static int new_id = 1;
    private static Logger logr = ProductsGui.getLogr();
    private static Beverage newBeverage = null;

    /**
     * Class constructor for creating new beverage.
     */
    NewBeverage(){
        super("New beverage");
        setContentPane(newBeveragePanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);
        List<Supplier> l = new DaoSupplier().getAllSuppliers();
        suppliersCB.removeAllItems();
        suppliersCB.addItem("Select supplier");
        for (Supplier s :l) {
            String item = String.valueOf(s.getName());
            suppliersCB.addItem(item);
        }

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        alcoholicRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alcoholicRadioButton.isSelected())
                    nonalcoholicRadioButton.setSelected(false);
            }
        });

        nonalcoholicRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nonalcoholicRadioButton.isSelected())
                    alcoholicRadioButton.setSelected(false);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flavor = flavorTextField.getText();
                String category = categoryTextField.getText();
                String brand = brandTextField.getText();
                String price = priceTextField.getText();
                if (createNewBeverage(flavor, category, brand, price)) {
                    new DaoBasics().addAnything(newBeverage);
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added beverage No. "+new_id+".");
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null,
                            "Error while adding. Try again!");
            }
        });
    }

    /**
     * Create new supplier from user's inputs.
     *
     * @param f Flavor of beverage.
     * @param c Category of beverage.
     * @param b Brand of beverage.
     * @param p Price of beverage.
     * @return True on success, false otherwise.
     */
    private boolean createNewBeverage(String f, String c, String b, String p){
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        String chosenSupp;
        Supplier dod;
        //Checks whether category, price, brand are valid
        if(!(new ProductsGui().areValidData(c, b, p)))
            return ret;
        //Checks whether type is selected
        if(!(alcoholicRadioButton.isSelected())
                && !(nonalcoholicRadioButton.isSelected())){
            JOptionPane.showConfirmDialog(
                    null,"Type is not selected!",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        //Set supplier
        try{
            chosenSupp = (String)suppliersCB.getSelectedItem();
            dod = new DaoSupplier().getSupplierByName(chosenSupp);
        }catch (javax.persistence.NoResultException | IllegalArgumentException | NullPointerException ex){
            JOptionPane.showConfirmDialog(
                    null,"Supplier is not selected!",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            logr.info("Supplier was not selected while creating new beverage.");
            dod = null;
        }
        // Generate the smallest possible id for new beverage
        List<Beverage> l = new DaoBeverage().getAllBeverages();
        for (Beverage bev: l) {
            ids.add(bev.getId());
        }
        while (ids.contains(new_id)){
            new_id++;
        }
        // Create new beverage
        newBeverage = new Beverage();
        newBeverage.setId(new_id);
        if(f.equals(""))
            newBeverage.setFlavor("Bez prichute");
        else
            newBeverage.setFlavor(f);
        newBeverage.setCategory(c);
        newBeverage.setBrand(b);
        newBeverage.setPrice(Short.valueOf(p));
        if (alcoholicRadioButton.isSelected())
            newBeverage.setType("Alko");
        else
            newBeverage.setType("Nealko");
        newBeverage.setSupplier(dod);

        ret = true;
        return ret;
    }
}
