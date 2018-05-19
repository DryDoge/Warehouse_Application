package gui.products;

import db.dao.*;
import db.e.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    private static Napoj newBeverage = null;

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
        List<Dodavatel> l = new daoDodavatel().getAllSuppliers();
        suppliersCB.removeAllItems();
        suppliersCB.addItem("Select supplier");
        for (Dodavatel s :l) {
            String item = String.valueOf(s.getNazov());
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
                short price = Short.valueOf(priceTextField.getText());
                if (createNewBeverage(flavor, category, brand, price)) {
                    new daoBasics().addAnything(newBeverage);
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added beverage No. "+new_id);
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null,"Error while adding. Try again");
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
    boolean createNewBeverage(String f, String c, String b, short p){
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        String chosenSupp;
        Dodavatel dod;
        String type = "Nealko";
        //Checks whether category, price, brand are valid
        if(!(new ProductsGui().areValidData(c, b, String.valueOf(p))))
            return ret;
        //Checks whether type is selected
        if(!(alcoholicRadioButton.isSelected()) && !(nonalcoholicRadioButton.isSelected())){
            JOptionPane.showConfirmDialog(
                    null,"Type is not selected",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }
        //Set supplier
        try{
            chosenSupp = (String)suppliersCB.getSelectedItem();
            dod = new daoDodavatel().getSupplierByName(chosenSupp);
        }catch (javax.persistence.NoResultException | IllegalArgumentException | NullPointerException ex){
            JOptionPane.showConfirmDialog(
                    null,"Supplier is not selected",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            logr.info("Supplier was not selected while creating new beverage");
            dod = null;
        }
        // Generate the smallest possible id for new beverage
        List<Napoj> l = new daoNapoj().getAllBeverages();
        for (Napoj  st: l) {
            ids.add(st.getIdnap());
        }
        while (ids.contains(new_id)){
            new_id++;
        }
        // Create new beverage
        newBeverage = new Napoj();
        newBeverage.setIdnap(new_id);
        if(f.equals(""))
            newBeverage.setPrichut("Bez prichute");
        else
            newBeverage.setPrichut(f);
        newBeverage.setDruh(c);
        newBeverage.setZnacka(b);
        newBeverage.setCena(p);
        if (alcoholicRadioButton.isSelected()) {
            type = "Alko";
            newBeverage.setTyp(type);
        }else
            newBeverage.setTyp(type);
        newBeverage.setDodavatel(dod);

        ret = true;
        return ret;
    }
}
