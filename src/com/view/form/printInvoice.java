/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view.form;

import com.control.db.ConnectionDB;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnTran
 */
public class printInvoice extends javax.swing.JFrame implements Printable {

    private int id;
    private double customerMoney;

    public printInvoice(int id, double customerMoney) {
        this.id = id;
        this.customerMoney = customerMoney;
        initComponents();
        getInvoiceInfo();
        formatTable();
        displayInvoiceTable();
        printPanel();
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // In nội dung của JPanel
        invoicePanel.paint(g2d);

        return Printable.PAGE_EXISTS;
    }

    private void printPanel() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        // Lấy PageFormat mặc định
        PageFormat pageFormat = job.defaultPage();

        // Tạo Paper với kích thước tùy chỉnh (ví dụ: 5.625x8.5 inches)
        Paper paper = new Paper();
        double paperWidth = 6.8 * 72; // 1 inch = 72 points
        double paperHeight = 9 * 72;
        paper.setSize(paperWidth, paperHeight);
        paper.setImageableArea(0, 0, paperWidth, paperHeight);

        // Đặt Paper mới cho PageFormat
        pageFormat.setPaper(paper);

        // Đặt lại PageFormat cho PrinterJob
        job.setPrintable(this, pageFormat);

        if (job.printDialog()) {
            try {
                job.print();
                JOptionPane.showMessageDialog(null, "In thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Người dùng đã hủy thao tác.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void displayInvoiceTable() {

        String storedProcedure = "{call displayInvoiceTable(?)}";
        try {
            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            Connection cnn = ConnectionDB.getConnection();
            PreparedStatement pre = cnn.prepareStatement(storedProcedure);
            pre.setInt(1, id);
            
            ResultSet re = pre.executeQuery();
            // Lưu kích thước cột hiện tại
            int oldColumn0Width = invoiceTable.getColumnModel().getColumn(0).getWidth();
            int oldColumn1Width = invoiceTable.getColumnModel().getColumn(1).getWidth();
            int oldColumn2Width = invoiceTable.getColumnModel().getColumn(2).getWidth();
            
            // Đặt tên cột theo thiết kế
            String[] columnNames = {"Sản phẩm", "Đơn giá", "Số lượng"};
            tableModel.setColumnIdentifiers(columnNames);

            int rowCount = 0;
            while (re.next()) {
                if (rowCount < 7) { // Hiển thị 7 sản phẩm đầu tiên
                    Object[] row = new Object[3];
                    row[0] = re.getString("ProductName");
                    row[1] = re.getDouble("SellPrice");
                    row[2] = re.getInt("Quantity");
                    tableModel.addRow(row);
                } else if (rowCount == 7) { // Hiển thị dấu 3 chấm (...) trong hàng thứ 8
                    Object[] row = {"...", "...", "..."};
                    tableModel.addRow(row);
                } else {
                    break; // Không cần xử lý thêm hàng nào
                }
                rowCount++;
            }

            invoiceTable.setModel(tableModel);
            // Khôi phục kích thước cột
            invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(oldColumn0Width);
            invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(oldColumn1Width);
            invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(oldColumn2Width);
            this.revalidate();

        } catch (SQLException ex) {
            Logger.getLogger(printInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getInvoiceInfo() {
        String storedProcedure = "{call GetInvoiceInfo(?)}";
        Connection cnn;
        try {
            cnn = ConnectionDB.getConnection();
            PreparedStatement pre = cnn.prepareStatement(storedProcedure);
            pre.setInt(1, id);
            
            ResultSet re = pre.executeQuery();
            
            while (re.next()) {
                invoiceID.setText("Mã đơn hàng: " + id);
                time.setText(re.getString("CreatedAt"));
                customerName.setText("Khách hàng: " + re.getString("CustomerName"));
                employeeName.setText("Nhân viên bán hàng: " + re.getString("Name"));
                total.setText(re.getString("TotalPrice"));
                cusMoney.setText(String.valueOf(customerMoney));
                returnMoney.setText(String.valueOf(customerMoney - Double.valueOf(re.getString("TotalPrice"))));
            }

        } catch (SQLException ex) {
            Logger.getLogger(printInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }

    private void formatTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        invoiceTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        invoiceTable.setShowGrid(false);
        tablePane.getViewport().setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        invoicePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        invoiceID = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        customerName = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        tablePane = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        cusMoney = new javax.swing.JLabel();
        returnMoney = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel12.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("time");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        invoicePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel13.setText("---------------------------------------------------------------------");

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel1.setText("Cửa hàng phân bón Việt Nam");

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel2.setText("97 Man Thiện, Phường Hiệp Phú, TP.Thủ Đức");

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel6.setText("Hotline: 034567890");

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel14.setText("---------------------------------------------------------------------");

        invoiceID.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        invoiceID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        invoiceID.setText("id");

        time.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        time.setText("time");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        jLabel5.setText("HÓA ĐƠN MUA HÀNG");

        customerName.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        customerName.setText("Khách hàng: ");

        employeeName.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        employeeName.setText("Nhân viên bán hàng:");

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sản phẩm", "Đơn giá", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        invoiceTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        invoiceTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        invoiceTable.setGridColor(new java.awt.Color(255, 255, 255));
        invoiceTable.setRowHeight(28);
        invoiceTable.setRowSelectionAllowed(false);
        invoiceTable.setSelectionBackground(new java.awt.Color(76, 149, 108));
        invoiceTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        invoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.getTableHeader().setReorderingAllowed(false);
        tablePane.setViewportView(invoiceTable);

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel3.setText("Tổng tiền:");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel8.setText("Tiền khách đưa:");

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel7.setText("Tiền thối:");

        total.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("Tổng tiền");
        total.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cusMoney.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        cusMoney.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cusMoney.setText("Tiền khách đưa");
        cusMoney.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        returnMoney.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        returnMoney.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        returnMoney.setText("Tiền trả");
        returnMoney.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel15.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel15.setText("---------------------------------------------------------------------");

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        jLabel11.setText("Cảm ơn quý khách!");

        javax.swing.GroupLayout invoicePanelLayout = new javax.swing.GroupLayout(invoicePanel);
        invoicePanel.setLayout(invoicePanelLayout);
        invoicePanelLayout.setHorizontalGroup(
            invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, invoicePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(181, 181, 181))
            .addGroup(invoicePanelLayout.createSequentialGroup()
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablePane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(invoicePanelLayout.createSequentialGroup()
                                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(invoiceID, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(returnMoney, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(invoicePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cusMoney))))
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(invoicePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(customerName))
                            .addGroup(invoicePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(employeeName)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(invoicePanelLayout.createSequentialGroup()
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel2))
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(invoicePanelLayout.createSequentialGroup()
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel6))
                    .addGroup(invoicePanelLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        invoicePanelLayout.setVerticalGroup(
            invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invoicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceID)
                    .addComponent(time))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(customerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cusMoney))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(invoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(returnMoney))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(invoicePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(invoicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(printInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(printInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(printInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(printInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new printInvoice().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cusMoney;
    private javax.swing.JLabel customerName;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel invoiceID;
    private javax.swing.JPanel invoicePanel;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel returnMoney;
    private javax.swing.JScrollPane tablePane;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
