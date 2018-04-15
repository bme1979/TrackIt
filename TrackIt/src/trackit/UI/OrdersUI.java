package trackit.UI;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import trackit.DAL.AnOrder;

/**
 * UI Layer: Handles all aspects of the Order panel.
 *
 * @author Douglas
 */
public class OrdersUI
        extends JPanel {
    // <editor-fold defaultstate="collapsed" desc="Constants">

    public static final String TAB_NAME = "Orders";

    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Private Fields">
    private final ArrayList<AnOrder> orders;
    private final AnOrder bll;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Components">
    JButton btnCreate, btnRemove, btnEdit;
    String[] ordersLabel = {"Order Date", "Order Number", "Supplier", "Status", "Total"};
    JTable ordersTable;
    OrderItemsUI details;
    int selectedRow;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * orders ui
     */
    public OrdersUI() {
        this.bll = new AnOrder();
        this.orders = new ArrayList<>();
        setLayout(new BorderLayout());

        //add data to suppliers arraylist 
        Object[][] testData = {{"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"}, {"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"}, {"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"}};
        ordersTable = new JTable(testData, ordersLabel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        ordersTable.setFillsViewportHeight(true);
        ordersTable.setDefaultEditor(Object.class, null);

        add(scrollPane, BorderLayout.CENTER);

        JPanel btmSup = new JPanel();

        btnCreate = new JButton("Create");
        btnCreate.addActionListener((ActionEvent e) -> {
            System.out.print("create order");
            details = new OrderItemsUI();
        });

        btnEdit = new JButton("Edit");
        btnEdit.addActionListener((ActionEvent e) -> {
            System.out.print("Edit order");
            //if list item selected edit item else select item
            selectedRow = ordersTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Select item to edit");
            } else {
                details = new OrderItemsUI();
                //TODO: enter item info of selected item
            }
        });

        btnRemove = new JButton("Remove");
        btnRemove.addActionListener((ActionEvent e) -> {
            System.out.print("remove order");
            selectedRow = ordersTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null, "Select item to remove");
            } else {
                //TODO: remove item from db
                JOptionPane.showMessageDialog(null, "Item successfully removed");
            }
        });

        btmSup.add(btnCreate);
        btmSup.add(btnEdit);
        btmSup.add(btnRemove);

        add(btmSup, BorderLayout.SOUTH);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private void getValues() {
        /* if (bll.load()) {
            //this.orders.addAll(bll.getItems());
        }*/
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     * Displays the frame.
     */
    public void display() {
        setVisible(true);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SubClasses">
   
    // </editor-fold>
}
