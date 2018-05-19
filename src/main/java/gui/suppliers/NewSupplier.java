package gui.suppliers;

import db.dao.daoBasics;
import db.dao.daoDodavatel;
import db.e.Dodavatel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class NewSupplier extends JFrame {
    private JPanel newSupplierPanel;
    private JTextField webTF;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField telTF;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel actionPanel;
    private JLabel infoTelLabel;
    private static int new_id = 1;
    private Dodavatel newSupplier = null;

    /**
     * Class constructor for creating new supplier.
     */
    NewSupplier() {
        super("New supplier");
        setContentPane(newSupplierPanel);
        int optionButton = getDefaultCloseOperation();
        if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
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
                String name = nameTF.getText();
                String web = webTF.getText();
                String tel = telTF.getText();
                String email = emailTF.getText();
                if (createNewSupplier(name, web, tel, email)) {
                    new daoBasics().addAnything(newSupplier);
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added Supplier No. " + new_id);
                    dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Error while adding. Try again");
            }
        });
    }

    /**
     * Create new supplier from user's inputs.
     *
     * @param n Name of supplier
     * @param e Email address of supplier.
     * @param w Web page.
     * @param t Telephone number (9 digits).
     * @return True on success, false otherwise.
     */
    private boolean createNewSupplier(String n, String w, String t, String e) {
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        List<Dodavatel> l = new daoDodavatel().getAllSuppliers();
        //Checks whether name, web page, telephone number and email are valid
        if (!(SuppliersGui.areValidData(n, w, t, e)))
            return ret;
        // Generate the smallest possible id for new beverage
        for (Dodavatel d : l) {
            ids.add(d.getIddod());
        }
        while (ids.contains(new_id)) {
            new_id++;
        }
        // Create new supplier
        newSupplier = new Dodavatel();
        newSupplier.setIddod(new_id);
        newSupplier.setNazov(n);
        newSupplier.setEmail(e);
        newSupplier.setWeb(w);
        newSupplier.setTel(t);

        ret = true;
        return ret;
    }
}






