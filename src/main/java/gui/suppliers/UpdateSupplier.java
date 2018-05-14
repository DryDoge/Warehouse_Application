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
    private Dodavatel supplierToUpdate = null;

    /**
     * Class constructor specifying which supplier is going to be updated.
     *
     * @param d Supplier to update.
     */
    UpdateSupplier(Dodavatel d){
    super("Update supplier No. "+d.getIddod());
    this.supplierToUpdate = d;
    setContentPane(updateSupplierPanel);
    int optionButton = getDefaultCloseOperation();
    if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
        setVisible(false);
    }
    pack();
    setLocationRelativeTo(null);

    this.id = supplierToUpdate.getIddod();
    String name = supplierToUpdate.getNazov();
    String email = supplierToUpdate.getEmail();
    String tel = supplierToUpdate.getTel();
    String web = supplierToUpdate.getWeb();

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
                    if (updateSelectedSupplier()) {
                        new daoDodavatel().updateSupplier(supplierToUpdate);
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

    /**
     * Update selected supplier from user's inputs.
     *
     * @return True on success, false otherwise.
     */
    private boolean updateSelectedSupplier(){
        boolean ret = false;
        String n = nameTF.getText();
        String w = webTF.getText();
        String t = telTF.getText();
        String e = emailTF.getText();

        if(!(SuppliersGui.areValidData(n, w, t, e)))
            return ret;
        supplierToUpdate.setNazov(n);
        supplierToUpdate.setTel(t);
        supplierToUpdate.setEmail(e);
        supplierToUpdate.setWeb(w);
        ret = true;

        return ret;
    }

}
