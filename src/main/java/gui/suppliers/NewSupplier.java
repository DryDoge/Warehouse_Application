package gui.suppliers;

import db.dao.daoBasics;
import db.dao.daoDodavatel;
import db.e.Dodavatel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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


    public NewSupplier() {
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
                if (addData()) {
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added Supplier No. " + new_id);
                    dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Error while adding. Try again");
            }
        });
    }

    private boolean addData() {
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        String n = nameTF.getText();
        String w = webTF.getText();
        String t = telTF.getText();
        String e = emailTF.getText();

        if (!(SuppliersGui.areValidData(n, w, t, e)))
            return ret;

        List<Dodavatel> l = new daoDodavatel().getAllSuppliers();

        for (Dodavatel d : l) {
            ids.add(d.getIddod());
        }

        while (ids.contains(new_id)) {
            new_id++;
        }

        Dodavatel supplier = new Dodavatel();
        supplier.setIddod(new_id);
        supplier.setNazov(n);
        supplier.setEmail(e);
        supplier.setWeb(w);
        supplier.setTel(t);
        new daoBasics().addAnything(supplier);
        ret = true;

        return ret;
    }
}






