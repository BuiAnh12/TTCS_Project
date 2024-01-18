package com.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.decode("2368548"));
        setFont(new Font("sansserif", Font.BOLD, 16));
        setForeground(new Color(255, 255, 255));
        // Set a compound border to create a thicker line at the bottom
        Border line = new LineBorder(new Color(255, 255, 255), 2); // Regular border line
        Border emptyBorder = new EmptyBorder(10, 5, 10, 5); // Empty border to provide spacing
        setBorder(new CompoundBorder(line, emptyBorder));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(255, 255, 255));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//        int separatorThickness = 3; // Adjust the thickness of the separator line
//        for (int i = 0; i < separatorThickness; i++) {
//            grphcs.drawLine(0, getHeight() - i - 1, getWidth(), getHeight() - i - 1);
//        }
    }
}
