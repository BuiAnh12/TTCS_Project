package com.view.form;

import com.controller.controller_Product;
import com.model.Product;
import com.view.modal.product.insertModal;
import com.view.modal.product.updateModal;
import com.view.swing.ScrollBar;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class productForm extends javax.swing.JPanel {

    private insertModal im = null;
    private updateModal um = null;
    private List<Product> productList;
    private int status = 1;
    private int previlege;
    private Product selectedProduct;

    public int getPrevilege() {
        return previlege;
    }

    public void setPrevilege(int previlege) {
        this.previlege = previlege;
    }

    public productForm() {
        initComponents();

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        refreshTable();
    }

    public void refreshTable() {
        try {
            controller_Product controller = new controller_Product();
            String searchTxt = this.txtSearch3.getText();
            productList = controller.getAllproduct(status, searchTxt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Product tmp : productList) {
            table.addRow(new Object[]{tmp.getProductName(), tmp.getManufacturer(), tmp.getSellPrice(), tmp.getCategory()});
        }
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void refreshDetail() {
        txtName.setText("");
        txtManufacture.setText("");
        txtCategory.setText("");
        descriptionTxt.setText("");
        txtSellPrice.setText("");
    }

    public void openUpdateForm() {
        if (um == null) {
            um = new updateModal();
            um.setVisible(true);
            um.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    um = null; //// Đặt lại thành null khi cửa sổ đóng
                    refreshTable();
//                        refreshDetail();
                }
            });
        } else {
            um.toFront();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PanelLeft3 = new javax.swing.JPanel();
        PanelButton3 = new javax.swing.JPanel();
        PanelSearch3 = new javax.swing.JPanel();
        txtSearch3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelFilter = new javax.swing.JPanel();
        sortComboBox = new javax.swing.JComboBox<>();
        PanelInsert = new javax.swing.JPanel();
        insertBtn = new javax.swing.JButton();
        PanelTable = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.view.swing.Table();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelRight = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelDetail = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtManufacture = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JScrollPane();
        descriptionTxt = new javax.swing.JTextArea();
        txtCategory = new javax.swing.JTextField();
        labelSellPrice = new javax.swing.JLabel();
        txtSellPrice = new javax.swing.JTextField();
        PanelDUBtn = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(22, 23, 23));
        setForeground(new java.awt.Color(22, 23, 23));

        jPanel7.setBackground(new java.awt.Color(22, 23, 23));
        jPanel7.setForeground(new java.awt.Color(22, 23, 23));
        jPanel7.setPreferredSize(new java.awt.Dimension(1080, 720));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(22, 23, 23));
        jPanel8.setForeground(new java.awt.Color(22, 23, 23));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        PanelLeft3.setBackground(new java.awt.Color(22, 23, 23));

        PanelButton3.setBackground(new java.awt.Color(22, 23, 23));
        PanelButton3.setLayout(new javax.swing.BoxLayout(PanelButton3, javax.swing.BoxLayout.LINE_AXIS));

        PanelSearch3.setBackground(new java.awt.Color(22, 23, 23));

        txtSearch3.setBackground(new java.awt.Color(36, 36, 36));
        txtSearch3.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch3ActionPerformed(evt);
            }
        });
        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearch3KeyTyped(evt);
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

        javax.swing.GroupLayout PanelSearch3Layout = new javax.swing.GroupLayout(PanelSearch3);
        PanelSearch3.setLayout(PanelSearch3Layout);
        PanelSearch3Layout.setHorizontalGroup(
            PanelSearch3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSearch3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelSearch3Layout.setVerticalGroup(
            PanelSearch3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(txtSearch3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        PanelButton3.add(PanelSearch3);

        PanelFilter.setBackground(new java.awt.Color(22, 23, 23));

        sortComboBox.setBackground(new java.awt.Color(36, 36, 36));
        sortComboBox.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        sortComboBox.setForeground(new java.awt.Color(255, 255, 255));
        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp theo Tên", "Sắp xếp theo Sản xuất", "Sắp xếp theo Loại", " " }));
        sortComboBox.setToolTipText("");
        sortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortComboBoxItemStateChanged(evt);
            }
        });
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
                .addComponent(sortComboBox, 0, 198, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelFilterLayout.setVerticalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton3.add(PanelFilter);

        PanelInsert.setBackground(new java.awt.Color(22, 23, 23));

        insertBtn.setBackground(new java.awt.Color(36, 36, 36));
        insertBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        insertBtn.setForeground(new java.awt.Color(255, 255, 255));
        insertBtn.setText("Thêm");
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
                .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInsertLayout.setVerticalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton3.add(PanelInsert);

        PanelTable.setForeground(new java.awt.Color(60, 63, 65));

        spTable.setBorder(null);

        table.setForeground(new java.awt.Color(22, 23, 23));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Nhà sản xuất", "Giá bán", "Loại"
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
            .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("SẢN PHẨM");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLeft3Layout = new javax.swing.GroupLayout(PanelLeft3);
        PanelLeft3.setLayout(PanelLeft3Layout);
        PanelLeft3Layout.setHorizontalGroup(
            PanelLeft3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addGroup(PanelLeft3Layout.createSequentialGroup()
                .addGroup(PanelLeft3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLeft3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        PanelLeft3Layout.setVerticalGroup(
            PanelLeft3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLeft3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(PanelLeft3);

        PanelRight.setBackground(new java.awt.Color(22, 23, 23));
        PanelRight.setLayout(new java.awt.BorderLayout());

        PanelHeader.setBackground(new java.awt.Color(22, 23, 23));

        jLabel3.setBackground(new java.awt.Color(22, 23, 23));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CHI TIẾT");

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
        PanelDetail.setForeground(new java.awt.Color(36, 36, 36));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tên:");

        txtName.setEditable(false);
        txtName.setBackground(new java.awt.Color(255, 255, 255));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nhà sản xuất:");

        txtManufacture.setEditable(false);
        txtManufacture.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Loại:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mô tả:");

        descriptionTxt.setEditable(false);
        descriptionTxt.setBackground(new java.awt.Color(255, 255, 255));
        descriptionTxt.setColumns(20);
        descriptionTxt.setRows(5);
        txtDescription.setViewportView(descriptionTxt);

        txtCategory.setEditable(false);
        txtCategory.setBackground(new java.awt.Color(255, 255, 255));

        labelSellPrice.setBackground(new java.awt.Color(255, 255, 255));
        labelSellPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSellPrice.setForeground(new java.awt.Color(255, 255, 255));
        labelSellPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSellPrice.setText("Giá bán:");

        txtSellPrice.setEditable(false);
        txtSellPrice.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelDetailLayout = new javax.swing.GroupLayout(PanelDetail);
        PanelDetail.setLayout(PanelDetailLayout);
        PanelDetailLayout.setHorizontalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDetailLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtManufacture)
                                .addComponent(txtName)
                                .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        PanelDetailLayout.setVerticalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtManufacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSellPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSellPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        PanelRight.add(PanelDetail, java.awt.BorderLayout.CENTER);

        PanelDUBtn.setBackground(new java.awt.Color(22, 23, 23));
        PanelDUBtn.setLayout(new javax.swing.BoxLayout(PanelDUBtn, javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setBackground(new java.awt.Color(22, 23, 23));

        deleteBtn.setBackground(new java.awt.Color(36, 36, 36));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Xóa");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(36, 36, 36));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Cập nhật");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        resetBtn.setBackground(new java.awt.Color(36, 36, 36));
        resetBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetBtn.setText("Hoàn tác");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(resetBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateBtn)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn)
                    .addComponent(resetBtn))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        PanelDUBtn.add(jPanel10);

        PanelRight.add(PanelDUBtn, java.awt.BorderLayout.SOUTH);

        jPanel8.add(PanelRight);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch3ActionPerformed

    private void txtSearch3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyTyped
        Timer timer = new Timer(500, (ActionEvent e) -> {
            refreshTable();
        });
        timer.setRepeats(false); // Đảm bảo rằng Timer chỉ thực hiện một lần

        // Thêm DocumentListener vào searchTextField
        txtSearch3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartTimer();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartTimer();
            }

            public void restartTimer() {
                if (timer.isRunning()) {
                    timer.restart();
                } else {
                    timer.start();
                }
            }
        });
//        refreshTable();
    }//GEN-LAST:event_txtSearch3KeyTyped

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

//        refreshTable();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed

    }//GEN-LAST:event_sortComboBoxActionPerformed

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        if (this.previlege == 1) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền thực hiện điều này!",
                    "Xác thực", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (im == null) {
            im = new insertModal();
            im.setVisible(true);
            im.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    im = null; //// Đặt lại thành null khi cửa sổ đóng
                    refreshTable();
                }
            });
        } else {
            im.toFront();
        }

    }//GEN-LAST:event_insertBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        selectedProduct = productList.get(row);

        if (row >= 0) {
            txtName.setText(model.getValueAt(row, 0).toString());
            txtManufacture.setText(model.getValueAt(row, 1).toString());
            txtSellPrice.setText(selectedProduct.getSellPrice().toString());
            txtCategory.setText(model.getValueAt(row, 3).toString());
            descriptionTxt.setText(selectedProduct.getDescription());
        }
    }//GEN-LAST:event_tableMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (this.previlege == 1) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền thực hiện điều này!",
                    "Xác thực", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cầm muốn xóa!");
        } else {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();
            Product selectedProduct = productList.get(row);
            controller_Product controller = new controller_Product();
            int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không?", "Thông báo",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response==JOptionPane.YES_OPTION){
                 try {
                controller.deleteProduct(selectedProduct.getProductId());
                } catch (SQLException ex) {
                    Logger.getLogger(productForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
           
            refreshTable();
            JOptionPane.showMessageDialog(null, "Xóa thành công!");
            refreshDetail();
        }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (this.previlege == 1) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền thực hiện điều này!",
                    "Xác thực", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (table.getSelectedRow() > -1) {
            openUpdateForm();
            um.setDataProduct(selectedProduct);
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cầm muốn cập nhật!");
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void sortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortComboBoxItemStateChanged
        // TODO add your handling code here:
        controller_Product controller = new controller_Product();
        String searchTxt = this.txtSearch3.getText();

        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // Lấy phương thức sắp xếp được chọn
                String selectedMethod = (String) evt.getItem();
                switch (selectedMethod) {
                    case "Sắp xếp theo Tên":
                        status = 1;
                        break;
                    case "Sắp xếp theo Sản xuất":
                        status = 2;
                        break;
                    case "Sắp xếp theo Loại":
                        status = 3;
                        break;
                }
                System.out.println(status);
                productList = controller.getAllproduct(status, searchTxt);
                refreshTable();
                System.out.println("SORTED");
            }
        } catch (SQLException ex) {
            Logger.getLogger(customerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sortComboBoxItemStateChanged

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        refreshDetail();
        refreshTable();
    }//GEN-LAST:event_resetBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton3;
    private javax.swing.JPanel PanelDUBtn;
    private javax.swing.JPanel PanelDetail;
    private javax.swing.JPanel PanelFilter;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelInsert;
    private javax.swing.JPanel PanelLeft3;
    private javax.swing.JPanel PanelRight;
    private javax.swing.JPanel PanelSearch3;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextArea descriptionTxt;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel labelSellPrice;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JScrollPane spTable;
    private com.view.swing.Table table;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JScrollPane txtDescription;
    private javax.swing.JTextField txtManufacture;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSellPrice;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
