package gui.warehouses;

import db.dao.daoBasics;
import db.dao.daoSklad;
import db.e.Sklad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;



public class NewWarehouse extends JFrame{
    private JPanel newPanel;
    private JTextField telephoneTextfield;
    private JTextField webPageTextField;
    private JTextField streetTextField;
    private JTextField cityTextField;
    private JTextField postalCodeTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel optionPanel;
    private JLabel cityLabel;
    private JLabel telLabel;
    private JLabel webLabel;
    private JLabel postalCodeLabel;
    private JLabel infoTelLabel;
    private JLabel infoStreetLabel;
    private JLabel streetLabel;
    private JLabel infoCityLabel;
    private JLabel infoPCLabel;
    private JLabel infoWebLabel;
    private static int new_id = 1;
    private Sklad newWarehouse = null;

    /**
     * Class constructor for creating new warehouse.
     */
    NewWarehouse(){
        super("New warehouse");
        setContentPane(newPanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText();
                String street = streetTextField.getText();
                String web = webPageTextField.getText();
                String tel = telephoneTextfield.getText();
                String pcode = postalCodeTextField.getText();
                if (createNewWarehouse(city, street, web, tel, pcode)) {
                    new daoBasics().addAnything(newWarehouse);
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added warehouse No. " + new_id);
                    dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Error while adding. Try again");
            }
        });
    }

    /**
     * Create new warehouse from user's inputs.
     *
     * @param c Name of City.
     * @param s Name of street and number.
     * @param w Web page.
     * @param t Telephone number (9 digits).
     * @param p Postal code of city (5 digits).
     * @return True on success, false otherwise.
     */
    public boolean createNewWarehouse(String c, String s, String w, String t, String p){
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();

        List<Sklad> l = new daoSklad().getAllWarehouses();
        //Checks whether city, street, web page, telephone number and postal code are valid
        if(!(WarehouseGUI.areValidData(c,s,w,t,p)))
            return ret;
        // Generate the smallest possible id for new warehouse
        for (Sklad  st: l) {
            ids.add(st.getIdsklad());
        }
        while (ids.contains(new_id)){
            new_id++;
        }
        // Create new warehouse
        newWarehouse  = new Sklad();
        newWarehouse.setIdsklad(new_id);
        newWarehouse.setTel(t);
        newWarehouse.setMesto(c);
        newWarehouse.setUlica(s);
        newWarehouse.setPsc(p);
        newWarehouse.setWeb(w);

        ret = true;
        return ret;
    }
}
