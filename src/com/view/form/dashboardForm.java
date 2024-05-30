
package com.view.form;

import com.controller.controller_Dashboard;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import com.view.model.Model_Card;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

public class dashboardForm extends javax.swing.JPanel {
    
    private Form_chart formchart;
    
    public void resetChart(int index) throws SQLException{
            this.Chart_panel.removeAll();
            this.Chart_panel.revalidate();
            this.Chart_panel.repaint();
            this.Chart_panel.add(new Form_chart(index).getContentPane());
            
        }
    
    public dashboardForm() {
        initComponents();
        controller_Dashboard controller_dashDashboard =new controller_Dashboard();
        List<BigDecimal>money=controller_dashDashboard.getRevenue();
        
        try {
            formchart=new Form_chart(1);
            resetChart(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn khi có sự kiện thay đổi
                String selectedValue = sortComboBox.getSelectedItem().toString(); 
                
                if (selectedValue.equals("Năm")){
                    try {
                        resetChart(0);
                        System.out.println("0");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(selectedValue.equals("Tháng")){           
                    try {
                        resetChart(1);
                        System.out.println(1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
  
            }
        });
        try {
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/stock.png")), "Số lượng",String.valueOf(controller_dashDashboard.getSoldQuanity())));
                Object moneyValue = money.get(2);
                card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/3.png")), "Lợi nhuận", "0"));
                if (moneyValue instanceof Number) {
                    card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/3.png")), "Lợi nhuận", String.valueOf(decimalFormat.format((Number) moneyValue)) + " VNĐ"));
                } 
                card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/Customer1.png")), "Khách hàng", String.valueOf(controller_dashDashboard.getTotalCustomer())));
            //  add row table
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        Chart_panel.add(formchart.getContentPane());
        setVisible(true);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel = new javax.swing.JLayeredPane();
        card1 = new com.view.component.Card();
        card2 = new com.view.component.Card();
        card3 = new com.view.component.Card();
        Chart_panel = new javax.swing.JPanel();
        sortComboBox = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        Chart_panel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Chart_panelLayout = new javax.swing.GroupLayout(Chart_panel);
        Chart_panel.setLayout(Chart_panelLayout);
        Chart_panelLayout.setHorizontalGroup(
            Chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Chart_panelLayout.setVerticalGroup(
            Chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        sortComboBox.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Năm", " " }));
        sortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Chart_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Chart_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortComboBoxActionPerformed

    }//GEN-LAST:event_sortComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart_panel;
    private com.view.component.Card card1;
    private com.view.component.Card card2;
    private com.view.component.Card card3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JComboBox<String> sortComboBox;
    // End of variables declaration//GEN-END:variables
}
