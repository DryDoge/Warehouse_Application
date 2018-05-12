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
                if (addData()) {
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added beverage No. "+new_id);
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null,"Error while adding. Try again");
            }
        });
    }

    private boolean addData(){
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        String f = flavorTextField.getText();
        String c = categoryTextField.getText();
        String b = brandTextField.getText();
        String p = priceTextField.getText();
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


        List<Napoj> l = new daoNapoj().getAllBeverages();
        for (Napoj  st: l) {
            ids.add(st.getIdnap());
        }

        while (ids.contains(new_id)){
            new_id++;
        }

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


        Napoj beverage = new Napoj();
        beverage.setIdnap(new_id);
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

        new daoBasics().addAnything(beverage);

        ret = true;

        return ret;
    }
}
