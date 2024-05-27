
package com.view.form;


import com.controller.controller_Import;
import com.controller.controller_Invoice;
import com.controller.controller_InvoiceItem;
import com.controller.controller_cartElement;
import com.model.CartElement;
import com.model.Invoice;
import com.model.InvoiceItem;
import com.model.Product;
import com.model.Staff;
import com.view.modal.order.customerMoney;
import com.view.modal.order.insertModal;
import com.view.modal.order.updateModal;
import com.view.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class orderForm extends javax.swing.JPanel {
    private insertModal im = null;
    private updateModal um = null;
    private customerMoney cm = null;
    private Invoice invoice;
    private List<Invoice> invoiceList;
    private int status = 1;
    private Staff user;

    public Staff getUser() {
        return user;
    }

    public void setUser(Staff user) {
        this.user = user;
    }
    
    public orderForm() {
        initComponents();
        
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        refreshtable();
        this.returnBtn.setEnabled(false);
        this.updateBtn.setEnabled(false);
        this.deleteBtn.setEnabled(false);
    }
    public void refreshtable(){
        try {
            controller_Invoice controller = new controller_Invoice();
            String searchTxt = this.txtSearch.getText();
            invoiceList=controller.getAllInvoices(status,searchTxt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (Invoice invoice : invoiceList) {
            table.addRow(new Object[]{invoice.getCustomerName(), invoice.getStaffName(), invoice.getPurchaseDate(), decimalFormat.format(invoice.getTotalAmount()) +" VNĐ" });
        }
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
    }
    
    public void updateDetail(Invoice selectedInvoice){
        int selectedRow = table.getSelectedRow();
        Invoice tmp = invoiceList.get(selectedRow);
        this.txtCustomerName.setText(tmp.getCustomerName());
        this.txtStaffName.setText(tmp.getStaffName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.txtPurchaseDate.setText(sdf.format(tmp.getPurchaseDate()));
        this.txtUpdateDate.setText(sdf.format(tmp.getUpdateDate()));
        this.totalAmountField.setValue(tmp.getTotalAmount());   
//        int invoice_id = tmp.getInvoiceId();  
        DefaultTableModel model =(DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        List<CartElement> cartList = new ArrayList<>();
        controller_cartElement controller_cart = new  controller_cartElement();
        try{
            cartList = controller_cart.getAllCartElement(selectedInvoice.getInvoiceId());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (CartElement cart : cartList) {
            tableDetail.addRow(new Object[]{cart.getProductName(), cart.getQuantity(), decimalFormat.format(cart.getTotalPrice()) + " VNĐ" });
            tableDetail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        }
         this.txtCustomerName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtStaffName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtPurchaseDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.totalAmountField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PanelLeft = new javax.swing.JPanel();
        PanelButton = new javax.swing.JPanel();
        PanelSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelFilter = new javax.swing.JPanel();
        sortComboBox = new javax.swing.JComboBox<>();
        PanelInsert = new javax.swing.JPanel();
        insertBtn = new javax.swing.JButton();
        PanelTable = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.view.swing.Table();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelRight = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelDetail = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        txtStaffName = new javax.swing.JTextField();
        txtPurchaseDate = new javax.swing.JTextField();
        txtUpdateDate = new javax.swing.JTextField();
        totalAmountField = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        detailSpTable = new javax.swing.JScrollPane();
        tableDetail = new com.view.swing.Table();
        PanelDUBtn = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        returnBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setForeground(new java.awt.Color(22, 23, 23));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));
        jPanel1.setForeground(new java.awt.Color(22, 23, 23));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));
        jPanel1.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel2.setBackground(new java.awt.Color(22, 23, 23));
        jPanel2.setForeground(new java.awt.Color(22, 23, 23));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        PanelLeft.setBackground(new java.awt.Color(22, 23, 23));

        PanelButton.setBackground(new java.awt.Color(22, 23, 23));
        PanelButton.setLayout(new javax.swing.BoxLayout(PanelButton, javax.swing.BoxLayout.LINE_AXIS));

        PanelSearch.setBackground(new java.awt.Color(22, 23, 23));

        txtSearch.setBackground(new java.awt.Color(36, 36, 36));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(22, 23, 23));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/view/icon/search.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelSearchLayout = new javax.swing.GroupLayout(PanelSearch);
        PanelSearch.setLayout(PanelSearchLayout);
        PanelSearchLayout.setHorizontalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelSearchLayout.setVerticalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        PanelButton.add(PanelSearch);

        PanelFilter.setBackground(new java.awt.Color(22, 23, 23));

        sortComboBox.setBackground(new java.awt.Color(36, 36, 36));
        sortComboBox.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        sortComboBox.setForeground(new java.awt.Color(255, 255, 255));
        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort By Customer", "Sort By Staff", "Sort By Total" }));
        sortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelFilterLayout = new javax.swing.GroupLayout(PanelFilter);
        PanelFilter.setLayout(PanelFilterLayout);
        PanelFilterLayout.setHorizontalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addComponent(sortComboBox, 0, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelFilterLayout.setVerticalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelFilter);

        PanelInsert.setBackground(new java.awt.Color(22, 23, 23));

        insertBtn.setBackground(new java.awt.Color(36, 36, 36));
        insertBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        insertBtn.setForeground(new java.awt.Color(255, 255, 255));
        insertBtn.setText("Insert");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInsertLayout = new javax.swing.GroupLayout(PanelInsert);
        PanelInsert.setLayout(PanelInsertLayout);
        PanelInsertLayout.setHorizontalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInsertLayout.setVerticalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelInsert);

        PanelTable.setForeground(new java.awt.Color(60, 63, 65));

        spTable.setBorder(null);

        table.setForeground(new java.awt.Color(22, 23, 23));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer name", "Staff name", "Date", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTableLayout.createSequentialGroup()
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ORDER");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLeftLayout = new javax.swing.GroupLayout(PanelLeft);
        PanelLeft.setLayout(PanelLeftLayout);
        PanelLeftLayout.setHorizontalGroup(
            PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(PanelLeftLayout.createSequentialGroup()
                .addGroup(PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLeftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        PanelLeftLayout.setVerticalGroup(
            PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(PanelLeft);

        PanelRight.setBackground(new java.awt.Color(22, 23, 23));
        PanelRight.setForeground(new java.awt.Color(22, 23, 23));
        PanelRight.setLayout(new java.awt.BorderLayout());

        PanelHeader.setBackground(new java.awt.Color(22, 23, 23));

        jLabel3.setBackground(new java.awt.Color(22, 23, 23));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DETAIL");

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelRight.add(PanelHeader, java.awt.BorderLayout.NORTH);

        PanelDetail.setBackground(new java.awt.Color(36, 36, 36));

        jPanel7.setBackground(new java.awt.Color(36, 36, 36));

        jPanel6.setBackground(new java.awt.Color(36, 36, 36));
        jPanel6.setForeground(new java.awt.Color(36, 36, 36));

        jPanel4.setBackground(new java.awt.Color(36, 36, 36));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Customer name: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Staff name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Purchase date");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Update date");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Amount");

        txtCustomerName.setEditable(false);
        txtCustomerName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(102, 102, 102));
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        txtStaffName.setEditable(false);
        txtStaffName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtStaffName.setForeground(new java.awt.Color(102, 102, 102));

        txtPurchaseDate.setEditable(false);
        txtPurchaseDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtPurchaseDate.setForeground(new java.awt.Color(102, 102, 102));

        txtUpdateDate.setEditable(false);
        txtUpdateDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtUpdateDate.setForeground(new java.awt.Color(102, 102, 102));

        totalAmountField.setEditable(false);
        totalAmountField.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        totalAmountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtUpdateDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(txtPurchaseDate, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStaffName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalAmountField))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtUpdateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBackground(new java.awt.Color(36, 36, 36));
        jPanel5.setForeground(new java.awt.Color(36, 36, 36));

        detailSpTable.setBorder(null);

        tableDetail.setForeground(new java.awt.Color(22, 23, 23));
        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Total Pricce"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetail.setFont(new java.awt.Font("Sitka Small", 1, 13)); // NOI18N
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
        });
        detailSpTable.setViewportView(tableDetail);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(detailSpTable, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detailSpTable, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout PanelDetailLayout = new javax.swing.GroupLayout(PanelDetail);
        PanelDetail.setLayout(PanelDetailLayout);
        PanelDetailLayout.setHorizontalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelDetailLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelDetailLayout.setVerticalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        PanelRight.add(PanelDetail, java.awt.BorderLayout.CENTER);

        PanelDUBtn.setBackground(new java.awt.Color(22, 23, 23));
        PanelDUBtn.setForeground(new java.awt.Color(22, 23, 23));
        PanelDUBtn.setLayout(new javax.swing.BoxLayout(PanelDUBtn, javax.swing.BoxLayout.LINE_AXIS));

        jPanel8.setBackground(new java.awt.Color(22, 23, 23));

        returnBtn.setBackground(new java.awt.Color(36, 36, 36));
        returnBtn.setForeground(new java.awt.Color(255, 255, 255));
        returnBtn.setText("RETURN");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(36, 36, 36));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(36, 36, 36));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(36, 36, 36));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("PRINT");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(returnBtn)
                .addComponent(deleteBtn)
                .addComponent(updateBtn)
                .addComponent(btnPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnBtn)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn)
                    .addComponent(btnPrint))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDUBtn.add(jPanel8);

        PanelRight.add(PanelDUBtn, java.awt.BorderLayout.SOUTH);

        jPanel2.add(PanelRight);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        this.refreshtable();
    }//GEN-LAST:event_txtSearchKeyTyped

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed

        if (im==null) {
            im = new insertModal();
            im.setVisible(true);
            im.setUser(user);
            im.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        im = null;
                        refreshtable();//// Đặt lại thành null khi cửa sổ đóng
                    }
                });
        } else {
            im.toFront();
        }
    
    }//GEN-LAST:event_insertBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int index = this.table.getSelectedRow() ;
        updateDetail(invoiceList.get(index));
        this.returnBtn.setEnabled(true);
        this.updateBtn.setEnabled(true);
        this.deleteBtn.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void totalAmountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountFieldActionPerformed

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked

    }//GEN-LAST:event_tableDetailMouseClicked

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn trả hàng của hóa đơn này?", "Alert",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int selectedIndex = table.getSelectedRow();
            Invoice tmp = invoiceList.get(selectedIndex);
            controller_cartElement controller_cart = new controller_cartElement();
            List<CartElement> cartList = new ArrayList<CartElement>();
            try{
                cartList = controller_cart.getAllCartElement(tmp.getInvoiceId());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            for (CartElement cart: cartList ){
                int importId = cart.getImportId();
                int quantity = cart.getQuantity();
                controller_Import controller_import = new controller_Import();
                try{
                    controller_import.editImport(importId, quantity);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            controller_Invoice controller_invoice = new controller_Invoice();
            controller_InvoiceItem controller_item = new controller_InvoiceItem();
            try{
                controller_item.deleteInvoiceItemId(tmp.getInvoiceId());
                controller_invoice.deleteInvoice(tmp.getInvoiceId());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        refreshtable();
    }//GEN-LAST:event_returnBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hóa đơn này?", "Alert",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int selectedIndex = table.getSelectedRow();
            Invoice tmp = invoiceList.get(selectedIndex);
            controller_Invoice controller = new controller_Invoice();
            controller_InvoiceItem controller_item = new controller_InvoiceItem();
            try{
                controller_item.deleteInvoiceItemId(tmp.getInvoiceId());
                controller.deleteInvoice(tmp.getInvoiceId());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            refreshtable();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (um==null) {
            int selectedIndex = table.getSelectedRow();
            
            um = new updateModal(invoiceList.get(selectedIndex));
            um.setVisible(true);
            um.setUser(user);
            um.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        um = null;
                        refreshtable();//// Đặt lại thành null khi cửa sổ đóng
                    }
                });
        } else {
            um.toFront();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed
        this.status = this.sortComboBox.getSelectedIndex() + 1;
        this.refreshtable();
    }//GEN-LAST:event_sortComboBoxActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if (table.getSelectedRow() >= 0) {
            if (cm==null) {
            invoice = invoiceList.get(table.getSelectedRow());
            cm = new customerMoney(invoice.getInvoiceId());
            cm.setVisible(true);
            cm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        cm = null;
                        refreshtable();//// Đặt lại thành null khi cửa sổ đóng
                    }
                });
        } else {
            cm.toFront();
        }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn để in", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelDUBtn;
    private javax.swing.JPanel PanelDetail;
    private javax.swing.JPanel PanelFilter;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelInsert;
    private javax.swing.JPanel PanelLeft;
    private javax.swing.JPanel PanelRight;
    private javax.swing.JPanel PanelSearch;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JScrollPane detailSpTable;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton returnBtn;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JScrollPane spTable;
    private com.view.swing.Table table;
    private com.view.swing.Table tableDetail;
    private javax.swing.JFormattedTextField totalAmountField;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtPurchaseDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStaffName;
    private javax.swing.JTextField txtUpdateDate;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
