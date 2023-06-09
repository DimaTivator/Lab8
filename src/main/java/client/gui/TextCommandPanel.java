/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package client.gui;

import client.CommandSender;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author dmitrii_andriianov
 */
public class TextCommandPanel extends javax.swing.JPanel {

    /**
     * Creates new form HelpPanel
     */
    public TextCommandPanel() {
        initComponents();
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doneLabel1 = new javax.swing.JLabel();
        textLabel = new javax.swing.JLabel();
        doneLabel = new javax.swing.JLabel();
        doneLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        doneLabel1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        doneLabel1.setForeground(new java.awt.Color(33, 209, 38));

        setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        textLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        textLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        textLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        doneLabel.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        doneLabel.setForeground(new java.awt.Color(33, 209, 38));

        doneLabel2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        doneLabel2.setForeground(new java.awt.Color(33, 209, 38));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 209, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(315, 315, 315)
                    .addComponent(doneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(345, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 356, Short.MAX_VALUE)
                    .addComponent(doneLabel2)
                    .addGap(0, 357, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(250, 250, 250)
                    .addComponent(doneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(299, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 290, Short.MAX_VALUE)
                    .addComponent(doneLabel2)
                    .addGap(0, 291, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel doneLabel;
    private javax.swing.JLabel doneLabel1;
    private javax.swing.JLabel doneLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel textLabel;
    // End of variables declaration//GEN-END:variables
}
