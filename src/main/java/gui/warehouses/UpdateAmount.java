package gui.warehouses;

import db.dao.DaoContain;
import db.entity.Contain;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
Class representing window for updating amount of a beverage in the warehouse.
*/

public class UpdateAmount extends JFrame {
    private JPanel updateamountPanel;
    private JTextField newAmountTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private Contain ContainsToUpdate;
    /**
     * Class constructor for updating amount of a beverage in the selected warehouse.
     *
     * @param c Content to update.
     */
    UpdateAmount(Contain c){
        super("Update amount of item No. " + c.getBeverageid() + " in warehouse No. "+ c.getStorageid());
        this.ContainsToUpdate = c;
        setContentPane(updateamountPanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);
        newAmountTextField.setText(String.valueOf(ContainsToUpdate.getAmount()));

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
                    if(updateAmount()){
                        new DaoContain().updateAmount(ContainsToUpdate);
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated amount."
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


    /**
     * Update amount of item from user's input.
     * @return True on success, false otherwise.
     */
    private boolean updateAmount(){

        String newAmount = newAmountTextField.getText();
        if((newAmount.equals("")) || (newAmount.equals("0")) || !(newAmount.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Amount is not filled or wrong!",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return false;
        }
        ContainsToUpdate.setAmount(Integer.valueOf(newAmount));
        return true;
    }
}
