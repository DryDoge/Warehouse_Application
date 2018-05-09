package gui.products;

import db.dao.daoNapoj;
import db.e.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class ProductGui extends JFrame {

    private JPanel mainPanel;
    private JPanel storedPanel;
    private JPanel infoPanel;
    private JPanel actionPanel;
    private JScrollPane storedScrollPanel;
    private JComboBox<String> beveragesCB;
    private JList<String> warehousesList;
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JLabel flavorInfoLabel;
    private JLabel typeInfoLabel;
    private JLabel brandInfoLabel;
    private JLabel priceInfoLabel;
    private JLabel imageLabel;
    private JLabel supplierInfoLabel;
    private static NewBeverage nb = null;




    public ProductGui(){
        super("Beverage");
        setContentPane(mainPanel);
        setSize(850, 710);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon backgroundIcon = new ImageIcon("icons/product-back.jpg", "Background");
        flavorInfoLabel.setText("");
        typeInfoLabel.setText("");
        brandInfoLabel.setText("");
        priceInfoLabel.setText("");
        supplierInfoLabel.setText("");
        imageLabel.setIcon(backgroundIcon);
        refreshButton.doClick();


        beveragesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String)beveragesCB.getSelectedItem());
                    Napoj n = new daoNapoj().getBeverageByID(chosenId);
                    flavorInfoLabel.setText(n.getPrichut());
                    typeInfoLabel.setText(n.getDruh());
                    brandInfoLabel.setText(n.getZnacka());
                    priceInfoLabel.setText(String.valueOf(n.getCena())+"€");
                    supplierInfoLabel.setText(n.getDodavatel().getNazov());

                    List<Sklad> list = n.getSklady();
                    list.sort(Comparator.comparing(Sklad::getIdsklad));
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    if (list.isEmpty()){
                        listModel.addElement("This beverage is not stored yet!");
                    }else {
                        for (Sklad storage : list) {
                            String itemsForList = String.valueOf(storage.getIdsklad()) +
                                    " | " +storage.getMesto()+ "| " + storage.getWeb() +
                                    " | " + storage.getTel();
                            listModel.addElement(itemsForList);
                        }
                    }
                    warehousesList.setModel(listModel);
                } catch (NullPointerException | IllegalArgumentException ex) {
                    flavorInfoLabel.setText("");
                    typeInfoLabel.setText("");
                    brandInfoLabel.setText("");
                    priceInfoLabel.setText("");
                    supplierInfoLabel.setText("");
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addElement("Choose number of beverage first!");
                    warehousesList.setModel(listModel);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) beveragesCB.getSelectedItem());
                    int optionButton = JOptionPane.showConfirmDialog(null,
                            "Do you really want to delete this warehouse ?",
                            "Delete confirmation",JOptionPane.YES_NO_OPTION);
                    if (optionButton == JOptionPane.YES_OPTION) {
                        Napoj n1 = new daoNapoj().getBeverageByID(chosenId);
                        if (deleteSelectedBeverage(n1)) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Succesfully deleted beverage No. " + chosenId
                            );
                            refreshButton.doClick();
                        }
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(null,
                            "Delete is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                } catch (NullPointerException exp){
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first!",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                }
            }


        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nb = null;
                setData();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nb == null || !(nb.isVisible())) {
                    nb = null;
                    nb = new NewBeverage();
                    nb.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Window for adding warehouse is already open");
                    nb.requestFocus();
                }
            }
        });
    }

    public void setData() {
        List<Napoj> l = new daoNapoj().getAllBeverages();
        beveragesCB.removeAllItems();
        beveragesCB.addItem("Select Number");
        for (Napoj s :l) {
            String item = String.valueOf(s.getIdnap());
            beveragesCB.addItem(item);
        }
    }



    private boolean deleteSelectedBeverage(Napoj n) {
        try {

            new daoNapoj().deleteBeverage(n.getIdnap());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
