package gui.suppliers;

import db.dao.daoDodavatel;
import db.e.Dodavatel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSupplier extends JFrame {

    private JPanel updateSupplierPanel;
    private JTextField webTF;
    private JButton cancelButton;
    private JButton saveButton;
    private JLabel infoTelLabel;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField telTF;
    private int id;

    public UpdateSupplier(Dodavatel d){
    super("Update supplier No. "+d.getIddod());
    setContentPane(updateSupplierPanel);
    int optionButton = getDefaultCloseOperation();
    if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
        setVisible(false);
    }
    pack();
    setLocationRelativeTo(null);

    this.id = d.getIddod();
    String name = d.getNazov();
    String email = d.getEmail();
    String tel = d.getTel();
    String web = d.getWeb();

    nameTF.setText(name);
    emailTF.setText(email);
    telTF.setText(tel);
    webTF.setText(web);

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
                        "Do you really want to update this supplier ?",
                        "Update confirmation", JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION) {
                    if (updateData(id)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated Supplier No. " + id
                        );
                        dispose();
                    }
                }
            }
        });
    }

    private boolean updateData(int id){
        boolean ret = false;
        String n = nameTF.getText();
        String w = webTF.getText();
        String t = telTF.getText();
        String e = emailTF.getText();

        if(!(SuppliersGui.areValidData(n, w, t, e)))
            return ret;

        Dodavatel supp = new daoDodavatel().getSupplierById(id);

        supp.setNazov(n);
        supp.setTel(t);
        supp.setEmail(e);
        supp.setWeb(w);

        new daoDodavatel().updateSupplier(supp);
        ret = true;

        return ret;
    }

}
