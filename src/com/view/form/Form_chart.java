
package com.view.form;

import com.controller.controller_Dashboard;
import com.model.ListChartYear;
import com.model.List_Chart;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.category.CategoryItemRenderer;



public class Form_chart extends javax.swing.JFrame {
    
    
    private CategoryDataset createDatasetMonth() throws SQLException {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        controller_Dashboard dashboard=new controller_Dashboard();
        List<List_Chart>listchart=new ArrayList<>();
        listchart=dashboard.getMonthlyrevenue();
        for(int i=0;i<listchart.size();i++){
            dataset.addValue(listchart.get(i).getIncome(),"Income Line",listchart.get(i).getMonth());
        }
        
        return dataset;
    }
    
    private CategoryDataset createDatasetYear() throws SQLException {
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        controller_Dashboard dashboard=new controller_Dashboard();
        List<ListChartYear> lischChartYears=new ArrayList<>();
        lischChartYears=dashboard.getYearRevenue();
        for(int i=0;i<lischChartYears.size();i++){
            dataset.addValue(lischChartYears.get(i).getIncome(), "Income Line",lischChartYears.get(i).getYear());
        }
        
        return dataset;
    }
    
    public void refreshGraph(int index) throws SQLException{      
        JFreeChart chart=null;
        if(index==0){
              chart = ChartFactory.createLineChart(
                "Doanh thu năm", // Tiêu đề
                "Năm", // Label trục X
                "Income(VNĐ)", // Label trục Y
                createDatasetYear()
        );
        }else{
              chart = ChartFactory.createLineChart(
                "Doanh thu tháng", // Tiêu đề
                "Tháng", // Label trục X
                "Income(VNĐ)", // Label trục Y
                createDatasetMonth()
        );
        }
        
        chart.getTitle().setPaint(Color.WHITE);
        //Màu nền của line chart 
        chart.setBackgroundPaint(Color.decode("#696969"));
        //Set màu nền của Linechart 
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        // Màu sắc nền linechart 
        plot.setBackgroundPaint(Color.decode("#5f9ea0"));
        // Tùy chỉnh màu sắc của đường trong biểu đồ  
        plot.getRenderer().setSeriesPaint(0, Color.WHITE);  
        // Màu sắc đường lưới 
         plot.setDomainGridlinePaint(Color.BLUE); // Màu sắc đường lưới trục đứng
        plot.setRangeGridlinePaint(Color.BLUE); // Màu sắc đường lưới trục ngang 
        
        //
        CategoryAxis xAxis = plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        xAxis.setLabelPaint(Color.GREEN); // Màu sắc của "Month"
        yAxis.setLabelPaint(Color.WHITE);  // Màu sắc của "Income(VNĐ)"
        xAxis.setTickLabelPaint(Color.GREEN); // Màu sắc của label cột tháng
        yAxis.setTickLabelPaint(Color.decode("#f0ffff")); // Màu sắc của label cột doanh thu 
        
        // Assuming you are working with a CategoryPlot
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // Set the line thickness for the renderer in a CategoryPlot
        CategoryItemRenderer renderer = categoryPlot.getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));

        // Hiển thị biểu đồ trong JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1200, 600));
        setContentPane(chartPanel);
        
    }
    
    public Form_chart(int index ) throws SQLException {
       initComponents();
       refreshGraph(index );     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout chartpanelLayout = new javax.swing.GroupLayout(chartpanel);
        chartpanel.setLayout(chartpanelLayout);
        chartpanelLayout.setHorizontalGroup(
            chartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 972, Short.MAX_VALUE)
        );
        chartpanelLayout.setVerticalGroup(
            chartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chartpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Form_chart(1).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartpanel;
    // End of variables declaration//GEN-END:variables
}
