package gui;

import db.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.*;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class warehouseGUI extends JFrame {
    private JPanel whPanel;
    private JList list1;
    private JComboBox numbersCB;
    private JLabel telLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel pscLabel;
    private JLabel webLabel;

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("AppPU");
    private final EntityManager em = emf.createEntityManager();



    public warehouseGUI(){
        super("Warehouse");
        setContentPane(whPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setSize(800, 700);
        setLocationRelativeTo(null);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            dispose();
        }
        telLabel.setText("");
        streetLabel.setText("");
        cityLabel.setText("");
        pscLabel.setText("");
        webLabel.setText("");
        numbersCB.addItem("Select Number");

        TypedQuery<Sklad> q2 = em.createQuery(
        "SELECT s FROM Sklad AS s", Sklad.class
        );

        List<Sklad> l = q2.getResultList();
        for (Sklad s :l) {
            String item = String.valueOf(s.getIdsklad());
            numbersCB.addItem(item);
        }

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
                }catch (NumberFormatException ex){
                    telLabel.setText("");
                    streetLabel.setText("");
                    cityLabel.setText("");
                    pscLabel.setText("");
                    webLabel.setText("");
                }
            }
        });
    }
}
