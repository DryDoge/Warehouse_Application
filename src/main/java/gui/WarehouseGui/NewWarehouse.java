package gui.WarehouseGui;

import db.Sklad;

import javax.persistence.*;
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

    public NewWarehouse(){
        super("New warehouse");
        setContentPane(newPanel);
        pack();
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            dispose();
        }
        setSize(700,450);
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
                    JOptionPane.showMessageDialog(null, "Succesfully added");
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null,"Error while adding. Try again");


            }
        });
        webPageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });
    }



    private boolean addData(){
        boolean ret = false;
        int new_id = 1;
        List<Integer> ids = new ArrayList<Integer>();
        String c = cityTextField.getText();
        String s = streetTextField.getText();
        String w = webPageTextField.getText();
        String t = telephoneTextfield.getText();
        String p = postalCodeTextField.getText();

        if(c.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"City is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
        return ret;
        }

        if(s.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"Street is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }


        if(w.equals("") || !(w.matches("^(https?://)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}/?$"))){
            JOptionPane.showConfirmDialog(
                    null,"Web page is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if((t.length()!= 9) || !(t.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Telephone number is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if((p.length()!= 5) || !(p.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Postal code is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }


        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();

        TypedQuery<Sklad> q2 = em.createQuery(
                "SELECT s FROM Sklad AS s", Sklad.class
        );

        List<Sklad> l = q2.getResultList();

        for (Sklad  st: l) {
            ids.add(st.getIdsklad());
        }

        while (ids.contains(new_id)){
            new_id++;
        }

        Sklad storage = new Sklad();
        storage.setIdsklad(new_id);
        storage.setTel(t);
        storage.setMesto(c);
        storage.setUlica(s);
        storage.setPsc(p);
        storage.setWeb(w);
        em.persist(storage);
        et.commit();
        ret = true;


        em.close();
        emf.close();
        return ret;


    }
}
