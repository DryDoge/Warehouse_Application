package gui.warehouses;

import db.dao.daoObsahuje;
import db.e.Obsahuje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateAmount extends JFrame {
    private JPanel updateamountPanel;
    private JTextField newAmountTextField;
    private JButton saveButton;
    private JButton cancelButton;

    UpdateAmount(Obsahuje o){
        super("Update amount of item No. " + o.getIdnap() + " in warehouse No. "+ o.getIdsklad());
        setContentPane(updateamountPanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);
        newAmountTextField.setText(String.valueOf(o.getMnozstvo()));

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
                        "Do you really want to update amount ?",
                        "Update confirmation",JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION){
                    if(updateAmount(o.getIdsklad(),o.getIdnap())){
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated amount"
                        );
                    }
                    dispose();
                }
            }
        });
        newAmountTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });
    }

    private boolean updateAmount(int storage, int item){

        String newAmount = newAmountTextField.getText();


        if((newAmount.equals("")) || (newAmount.equals("0")) || !(newAmount.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Amount is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return false;
        }

        Obsahuje c = new daoObsahuje().getContentInfo(storage, item);

        c.setMnozstvo(Integer.valueOf(newAmount));

        new daoObsahuje().updateAmount(c);

        return true;
    }
}
