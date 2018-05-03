package gui.WarehouseGui;

import db.*;


import java.awt.event.*;

import java.util.Comparator;
import java.util.List;
import javax.persistence.*;
import javax.swing.*;

public class WarehouseGUI extends JFrame {
    private JPanel whPanel;
    private JComboBox<String> numbersCB;
    private JLabel telLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel pscLabel;
    private JLabel webLabel;
    private JLabel imageLabel;
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JList productsList;
    private JList employersList;
    private JPanel descPanel;
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("AppPU");
    private EntityManager em = emf.createEntityManager();

    static private NewWarehouse nw = null;

    public WarehouseGUI() {
        super("Warehouse");
        setContentPane(whPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setSize(800, 700);
        setLocationRelativeTo(null);
        int optionButton = getDefaultCloseOperation();
        if (optionButton == WindowConstants.EXIT_ON_CLOSE) {
            dispose();
            em.close();
            emf.close();
        }

        telLabel.setText("");
        streetLabel.setText("");
        cityLabel.setText("");
        pscLabel.setText("");
        webLabel.setText("");
        refreshButton.doClick();

        ImageIcon backgroundIcon = new ImageIcon("icons/warehouse-back.jpg", "Background");
        imageLabel.setIcon(backgroundIcon);

        numbersCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sklad s1 = em.find(Sklad.class, Integer.valueOf((String) (numbersCB.getSelectedItem())));
                    telLabel.setText(s1.getTel());
                    streetLabel.setText(s1.getUlica());
                    cityLabel.setText(s1.getMesto());
                    pscLabel.setText(s1.getPsc());
                    webLabel.setText(s1.getWeb());
                } catch (NumberFormatException ex) {
                    telLabel.setText("");
                    streetLabel.setText("");
                    cityLabel.setText("");
                    pscLabel.setText("");
                    webLabel.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sklad s1 = em.find(Sklad.class, Integer.valueOf((String) (numbersCB.getSelectedItem())));
                    deleteData(s1);
                    refreshButton.doClick();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(null, "Delete is not possible now", "Warning", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nw == null) {
                    nw = new NewWarehouse();
                    nw.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Window for adding warehouse is already open");
                }

            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setData();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sklad s1 = em.find(Sklad.class, Integer.valueOf((String) (numbersCB.getSelectedItem())));
                    //TODO Create UpdateWarehouseGUI and updateData function
                    refreshButton.doClick();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                }

            }
        });

        addWindowListener(new  java.awt.event.WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                if(!(nw == null)){
                    nw.dispose();
                }
                em.close();
                emf.close();
            }
        });
    }

    public void setData(){

        numbersCB.removeAllItems();
        numbersCB.addItem("Select Number");
        TypedQuery<Sklad> q2 = em.createQuery(
                "SELECT s FROM Sklad AS s", Sklad.class
        );
        List<Sklad> l = q2.getResultList();
        l.sort(Comparator.comparing(Sklad::getIdsklad));
        for (Sklad s :l) {
            String item = String.valueOf(s.getIdsklad());
            numbersCB.addItem(item);
        }
    }

    private void deleteData(Sklad val) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(val);
        et.commit();
    }


}
