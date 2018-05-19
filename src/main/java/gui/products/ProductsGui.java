package gui.products;

import db.dao.daoNapoj;
import db.e.*;
import org.eclipse.persistence.exceptions.*;

import javax.persistence.RollbackException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsGui extends JFrame {

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
    private JRadioButton nonalcoRadioButton;
    private JRadioButton alcoRadioButton;
    private JRadioButton allRadioButton;
    private JLabel idLabel;
    private static NewBeverage nb = null;
    private static UpdateBeverage ub = null;
    private final static Logger logr = Logger.getLogger(ProductsGui.class.getName());
    static Logger getLogr() {
        return logr;
    }
    static {
        try {
            FileHandler fh = new FileHandler("logs/BeveragesLogger.txt");
            fh.setLevel(Level.WARNING);
            logr.addHandler(fh);
        } catch (IOException e) {
            logr.log(Level.SEVERE, " File logger not working", e);
        }
    }

    /**
     * Class constructor for beverages.
     *
     */

    public ProductsGui(){
        super("Beverage");
        logr.setLevel(Level.INFO);
        setContentPane(mainPanel);
        setSize(850 , 750);
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
                    if(n.getDodavatel() == null)
                        supplierInfoLabel.setText("No supplier");
                    else
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
                    logr.info("No beverage was chosen to show");
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

                } catch (IllegalArgumentException | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(null,
                            "Choose beverage first",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No beverage was selected to delete");
                }
            }


        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nb = null;
                ub = null;
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
                    nb.setState(NORMAL);
                    nb.toFront();
                    nb.requestFocus();
                    JOptionPane.showMessageDialog(null,
                            "Window for adding beverage is already open");

                }
            }
        });

        allRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allRadioButton.isSelected()){
                    alcoRadioButton.setSelected(false);
                    nonalcoRadioButton.setSelected(false);
                }
                setData();
            }
        });

        alcoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alcoRadioButton.isSelected()){
                    allRadioButton.setSelected(false);
                    nonalcoRadioButton.setSelected(false);
                }
                setData();
            }
        });

        nonalcoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nonalcoRadioButton.isSelected()){
                    allRadioButton.setSelected(false);
                    alcoRadioButton.setSelected(false);
                }
                setData();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String) beveragesCB.getSelectedItem());
                    Napoj n = new daoNapoj().getBeverageByID(chosenId);

                    if (ub == null || !(ub.isVisible())) {
                        ub = null;
                        ub = new UpdateBeverage(n);
                        ub.setVisible(true);
                    } else {
                        ub.setState(NORMAL);
                        ub.toFront();
                        ub.requestFocus();
                        JOptionPane.showMessageDialog(null,
                                "Window for updating beverage is already open");

                    }
                } catch (IllegalArgumentException  | NullPointerException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Choose beverage first",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                    logr.warning("No beverage was selected to update");
                }
            }
        });

        addWindowListener(new  java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                if (!(nb == null)) {
                    nb.dispose();
                    nb = null;
                }
                if (!(ub == null)) {
                    ub.dispose();
                    ub = null;
                }

                dispose();
            }
        });

    }

    /**
     * Get selected type of beverages and prepare them for choosing.
     */
    public void setData() {
        List<Napoj> l;
        if (allRadioButton.isSelected()){
             l = new daoNapoj().getAllBeverages();
        }else if (alcoRadioButton.isSelected()){
             l = new daoNapoj().getAllAlcoBeverages();
        }else {
             l = new daoNapoj().getAllNonAlcoBeverages();
        }
        beveragesCB.removeAllItems();
        beveragesCB.addItem("Select Number");
        for (Napoj s :l) {
            String item = String.valueOf(s.getIdnap());
            beveragesCB.addItem(item);
        }
    }

    /**
     * Removes a beverage and all information about its.
     * @param n Beverage
     * @return true on success, false otherwise
     */
    private boolean deleteSelectedBeverage(Napoj n) {
        try {

            new daoNapoj().deleteBeverage(n.getIdnap());
            return true;
        }catch (RollbackException e){
            logr.warning("Selected beverage cannot be deleted");
            return false;
        }
    }

    /**
     * Checks whether category, brand and price are filled correctly.
     * @param c Category of a beverage.
     * @param b Brand of a beverage.
     * @param p Price of a beverage.
     * @return True on success, false otherwise.
     */
    public boolean areValidData(String c, String b, String p){
        boolean ret = false;
        if(c.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"Category is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if(b.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"Brand is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }


        if(Integer.valueOf(p) == null || Integer.valueOf(p) <= 0){
            JOptionPane.showConfirmDialog(
                    null,"Price is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        ret = true;

        return ret;
    }
}
