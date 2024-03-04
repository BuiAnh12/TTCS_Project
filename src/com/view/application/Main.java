package com.view.application;

//import com.model.Login_account;
import com.model.Staff;
import com.view.event.EventMenuSelected;
import com.view.form.customerForm;
import com.view.form.dashboardForm;
import com.view.form.importForm;
import com.view.form.orderForm;
import com.view.form.productForm;
import com.view.form.staffForm;
import com.view.login.Login;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;


public class Main extends javax.swing.JFrame {
    
    private Staff account;
    
    private dashboardForm home;
    private orderForm ordForm;
    private importForm impForm;
    private productForm proForm;
    private customerForm cusForm;
    private staffForm staForm;

    public Staff getAccount() {
        return account;
    }

    public void setAccount(Staff account) {
        this.account = account;
    }
    
    
    
    public void setMenuPrevilege(){
        this.menu.setPrevilege(this.account.getPrevilege());
        this.menu.reset();
    }
    
    public void reset(){
        
        header2.setAccount(account);
        header2.UpdateAccountName();
        menu.addEventMenuSelected(new EventMenuSelected() {
                @Override
                public void selected(int index) {
                    if (account.getPrevilege() == 3){
                        if (index == 0) {
                            home = new dashboardForm();
                            setForm(home);
                        } else if (index == 1) {
                            ordForm = new orderForm();
                            setForm(ordForm);
                            ordForm.setUser(account);

                        } else if (index == 2) {
                            impForm = new importForm();
                            setForm(impForm);
                        } else if (index == 3) {
                            proForm = new productForm();
                            setForm(proForm);

                        } else if (index == 4){
                            cusForm = new customerForm();
                            setForm(cusForm);

                        } else if (index == 5){
                            staForm = new staffForm();
                            setForm(staForm);

                        }
                    }
                    else if (account.getPrevilege() == 2){
                        if (index == 0) {
                            proForm = new productForm();
                            setForm(proForm);
                        } else if (index == 1) {
                            impForm = new importForm();
                            setForm(impForm);
                        }
                    } else if (account.getPrevilege() == 1){
                       
                        
                        if (index == 0) {
                            ordForm = new orderForm();
                            setForm(ordForm);
                            ordForm.setUser(account);
                        } else if (index == 1) {
                            proForm = new productForm();
                            setForm(proForm);
                            proForm.setPrevilege(account.getPrevilege());
                        } else if (index == 2) {
                            impForm = new importForm();
                            setForm(impForm);
                            impForm.setPrevilege(account.getPrevilege());
                        } else if (index == 3) {
                            cusForm = new customerForm();
                            setForm(cusForm);
                        }
                        
                    }
                    
                }
            });
            
        
    }
    
    public Main() throws SQLException {
        initComponents();
        
        setBackground(new Color(0, 0, 0, 0));
        
        home = new dashboardForm();
        ordForm = new orderForm();
        impForm = new importForm();
        proForm = new productForm();
        cusForm = new customerForm();
        staForm = new staffForm();
//        this.setMenuPrevilege(3);
        menu.initMoving(Main.this);
        
    }
    

    

    
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.view.swing.PanelBorder();
        menu = new com.view.component.Menu();
        header2 = new com.view.component.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(36, 36, 36));

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.view.component.Header header2;
    private javax.swing.JPanel mainPanel;
    private com.view.component.Menu menu;
    private com.view.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
