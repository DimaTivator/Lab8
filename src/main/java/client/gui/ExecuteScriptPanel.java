/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package client.gui;

import client.CommandResponseReceiver;
import client.CommandSender;
import client.gui.workers.ScriptExecutor;

import javax.swing.*;


public class ExecuteScriptPanel extends javax.swing.JPanel {

    // private ScriptExecutor scriptExecutor = new ScriptExecutor();

    private CommandSender commandSender;
    private CommandResponseReceiver commandResponseReceiver;

    public void setCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void setCommandResponseReceiver(CommandResponseReceiver commandResponseReceiver) {
        this.commandResponseReceiver = commandResponseReceiver;
    }

    /**
     * Creates new form ExecuteScriptPanel
     */
    public ExecuteScriptPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doneLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        argNameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextPanel = new javax.swing.JTextArea();

        doneLabel.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        doneLabel.setForeground(new java.awt.Color(33, 209, 38));

        jPanel1.setPreferredSize(new java.awt.Dimension(684, 520));

        argNameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        argNameLabel.setForeground(new java.awt.Color(50, 110, 211));
        argNameLabel.setText("script path");
        argNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jSeparator1.setForeground(new java.awt.Color(50, 110, 211));

        textField.setBackground(new java.awt.Color(238, 238, 238));
        textField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        textField.setForeground(new java.awt.Color(50, 110, 211));
        textField.setBorder(null);
        textField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        sendButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        sendButton.setForeground(new java.awt.Color(50, 110, 211));
        sendButton.setText("Send");
        sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(238, 238, 238));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(50, 110, 211));

        resultTextPanel.setBackground(new java.awt.Color(238, 238, 238));
        resultTextPanel.setColumns(20);
        resultTextPanel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        resultTextPanel.setForeground(new java.awt.Color(50, 110, 211));
        resultTextPanel.setRows(5);
        resultTextPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        resultTextPanel.setFocusable(false);
        jScrollPane1.setViewportView(resultTextPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(argNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1))
                        .addGap(378, 378, 378)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(argNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(doneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(doneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        doneLabel.setVisible(false);

        SwingWorker<Void, String> executeScriptWorker = new ScriptExecutor(
                textField.getText(),
                commandSender,
                commandResponseReceiver,
                resultTextPanel,
                doneLabel
                );

        executeScriptWorker.execute();

    }//GEN-LAST:event_sendButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel argNameLabel;
    private javax.swing.JLabel doneLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea resultTextPanel;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
