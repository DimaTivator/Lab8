/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client.gui;

import client.Authenticator;
import client.CommandResponseReceiver;
import client.CommandSender;
import client.i10n.Resources;
import commonModule.commands.Command;
import commonModule.commands.commandObjects.*;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;


public class ApplicationWindow extends javax.swing.JFrame {

    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;
    private final Authenticator authenticator;

    private ResourceBundle resourceBundle = Resources.getResourceBundle();

    private boolean tableIsOpened = false;

    public boolean checkTableIsOpened() {
        return tableIsOpened;
    }

    public void setTableIsOpened(boolean tableIsOpened) {
        this.tableIsOpened = tableIsOpened;
    }

    /**
     * Creates new form ApplicationWindow
     */
    public ApplicationWindow(Authenticator authenticator, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver) {

        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
        this.authenticator = authenticator;
        this.resourceBundle = resourceBundle;

        initComponents();
        usernameLabel.setText(authenticator.getLogin());

        setLanguages();

    }

    private void setLanguages() {

        helpButton.setBackground(new java.awt.Color(246, 246, 246));
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setText(resourceBundle.getString("help"));
        helpButton.setBorder(null);

        infoButton.setBackground(new java.awt.Color(246, 246, 246));
        infoButton.setForeground(new java.awt.Color(255, 255, 255));
        infoButton.setText(resourceBundle.getString("info"));
        infoButton.setBorder(null);


        showButton.setBackground(new java.awt.Color(246, 246, 246));
        showButton.setForeground(new java.awt.Color(255, 255, 255));
        showButton.setText(resourceBundle.getString("show"));
        showButton.setBorder(null);

        insertButton.setBackground(new java.awt.Color(246, 246, 246));
        insertButton.setForeground(new java.awt.Color(255, 255, 255));
        insertButton.setText(resourceBundle.getString("insert"));
        insertButton.setBorder(null);

        updateButton.setBackground(new java.awt.Color(246, 246, 246));
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText(resourceBundle.getString("update"));
        updateButton.setBorder(null);

        removeButton.setBackground(new java.awt.Color(246, 246, 246));
        removeButton.setForeground(new java.awt.Color(255, 255, 255));
        removeButton.setText(resourceBundle.getString("remove"));
        removeButton.setBorder(null);

        clearButton.setBackground(new java.awt.Color(246, 246, 246));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText(resourceBundle.getString("clear"));
        clearButton.setBorder(null);

        executeScriptButton.setBackground(new java.awt.Color(246, 246, 246));
        executeScriptButton.setForeground(new java.awt.Color(255, 255, 255));
        executeScriptButton.setText(resourceBundle.getString("execute_script"));
        executeScriptButton.setBorder(null);

        removeLowerButton.setBackground(new java.awt.Color(246, 246, 246));
        removeLowerButton.setForeground(new java.awt.Color(255, 255, 255));
        removeLowerButton.setText(resourceBundle.getString("remove_lower"));
        removeLowerButton.setBorder(null);

        replaceIfGreaterButton.setBackground(new java.awt.Color(246, 246, 246));
        replaceIfGreaterButton.setForeground(new java.awt.Color(255, 255, 255));
        replaceIfGreaterButton.setText(resourceBundle.getString("replace_if_greater"));
        replaceIfGreaterButton.setBorder(null);

        removeGreaterButton.setBackground(new java.awt.Color(246, 246, 246));
        removeGreaterButton.setForeground(new java.awt.Color(255, 255, 255));
        removeGreaterButton.setText(resourceBundle.getString("remove_greater"));
        removeGreaterButton.setBorder(null);

        countImpactSpeedButton.setBackground(new java.awt.Color(246, 246, 246));
        countImpactSpeedButton.setForeground(new java.awt.Color(255, 255, 255));
        countImpactSpeedButton.setText(resourceBundle.getString("count_impact_speed"));
        countImpactSpeedButton.setBorder(null);

        filterCarButton.setBackground(new java.awt.Color(246, 246, 246));
        filterCarButton.setForeground(new java.awt.Color(255, 255, 255));
        filterCarButton.setText(resourceBundle.getString("filter_car"));
        filterCarButton.setBorder(null);

        uniqueMoodButton.setBackground(new java.awt.Color(246, 246, 246));
        uniqueMoodButton.setForeground(new java.awt.Color(255, 255, 255));
        uniqueMoodButton.setText(resourceBundle.getString("unique_mood"));
        uniqueMoodButton.setBorder(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        usernameLabel = new javax.swing.JLabel();
        usernameIconLabel = new javax.swing.JLabel();
        interactionPanel = new javax.swing.JPanel();
        pigImagePanel = new javax.swing.JLabel();
        commandsButtonsPanel = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        showButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        executeScriptButton = new javax.swing.JButton();
        removeLowerButton = new javax.swing.JButton();
        replaceIfGreaterButton = new javax.swing.JButton();
        removeGreaterButton = new javax.swing.JButton();
        countImpactSpeedButton = new javax.swing.JButton();
        filterCarButton = new javax.swing.JButton();
        uniqueMoodButton = new javax.swing.JButton();
        emptyResetButton = new javax.swing.JButton();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(222, 100, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        usernameLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(71, 199, 231));

        usernameIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/username_icon.png"))); // NOI18N

        pigImagePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pigImagePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/translucent-pig.png"))); // NOI18N

        javax.swing.GroupLayout interactionPanelLayout = new javax.swing.GroupLayout(interactionPanel);
        interactionPanel.setLayout(interactionPanelLayout);
        interactionPanelLayout.setHorizontalGroup(
            interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pigImagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        interactionPanelLayout.setVerticalGroup(
            interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interactionPanelLayout.createSequentialGroup()
                .addComponent(pigImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        commandsButtonsPanel.setBackground(new java.awt.Color(50, 110, 211));

        helpButton.setBackground(new java.awt.Color(246, 246, 246));
        helpButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-support-500.png"))); // NOI18N
        helpButton.setText("help");
        helpButton.setBorder(null);
        helpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        helpButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        helpButton.setIconTextGap(14);
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        infoButton.setBackground(new java.awt.Color(246, 246, 246));
        infoButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        infoButton.setForeground(new java.awt.Color(255, 255, 255));
        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-info-512.png"))); // NOI18N
        infoButton.setText("info");
        infoButton.setBorder(null);
        infoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        infoButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        infoButton.setIconTextGap(14);
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        showButton.setBackground(new java.awt.Color(246, 246, 246));
        showButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        showButton.setForeground(new java.awt.Color(255, 255, 255));
        showButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/eye-512.png"))); // NOI18N
        showButton.setText("show");
        showButton.setBorder(null);
        showButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showButton.setIconTextGap(9);
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        insertButton.setBackground(new java.awt.Color(246, 246, 246));
        insertButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        insertButton.setForeground(new java.awt.Color(255, 255, 255));
        insertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-add-100.png"))); // NOI18N
        insertButton.setText("insert");
        insertButton.setBorder(null);
        insertButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insertButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        insertButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        insertButton.setIconTextGap(14);
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(246, 246, 246));
        updateButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-refresh-480.png"))); // NOI18N
        updateButton.setText("update");
        updateButton.setBorder(null);
        updateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        updateButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        updateButton.setIconTextGap(14);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(246, 246, 246));
        removeButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        removeButton.setForeground(new java.awt.Color(255, 255, 255));
        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-delete-384.png"))); // NOI18N
        removeButton.setText("remove");
        removeButton.setBorder(null);
        removeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        removeButton.setIconTextGap(14);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(246, 246, 246));
        clearButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-erase-96.png"))); // NOI18N
        clearButton.setText("clear");
        clearButton.setBorder(null);
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clearButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        clearButton.setIconTextGap(14);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        executeScriptButton.setBackground(new java.awt.Color(246, 246, 246));
        executeScriptButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        executeScriptButton.setForeground(new java.awt.Color(255, 255, 255));
        executeScriptButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-monitor-96.png"))); // NOI18N
        executeScriptButton.setText("execute script");
        executeScriptButton.setBorder(null);
        executeScriptButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        executeScriptButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        executeScriptButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        executeScriptButton.setIconTextGap(14);
        executeScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeScriptButtonActionPerformed(evt);
            }
        });

        removeLowerButton.setBackground(new java.awt.Color(246, 246, 246));
        removeLowerButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        removeLowerButton.setForeground(new java.awt.Color(255, 255, 255));
        removeLowerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-remove-delivery-100.png"))); // NOI18N
        removeLowerButton.setText("remove lower");
        removeLowerButton.setBorder(null);
        removeLowerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeLowerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeLowerButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        removeLowerButton.setIconTextGap(14);
        removeLowerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLowerButtonActionPerformed(evt);
            }
        });

        replaceIfGreaterButton.setBackground(new java.awt.Color(246, 246, 246));
        replaceIfGreaterButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        replaceIfGreaterButton.setForeground(new java.awt.Color(255, 255, 255));
        replaceIfGreaterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-replace-100.png"))); // NOI18N
        replaceIfGreaterButton.setText("replace if greater");
        replaceIfGreaterButton.setBorder(null);
        replaceIfGreaterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        replaceIfGreaterButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        replaceIfGreaterButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        replaceIfGreaterButton.setIconTextGap(14);
        replaceIfGreaterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceIfGreaterButtonActionPerformed(evt);
            }
        });

        removeGreaterButton.setBackground(new java.awt.Color(246, 246, 246));
        removeGreaterButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        removeGreaterButton.setForeground(new java.awt.Color(255, 255, 255));
        removeGreaterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-clear-symbol-100.png"))); // NOI18N
        removeGreaterButton.setText("remove greater");
        removeGreaterButton.setBorder(null);
        removeGreaterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeGreaterButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeGreaterButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        removeGreaterButton.setIconTextGap(14);
        removeGreaterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGreaterButtonActionPerformed(evt);
            }
        });

        countImpactSpeedButton.setBackground(new java.awt.Color(246, 246, 246));
        countImpactSpeedButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        countImpactSpeedButton.setForeground(new java.awt.Color(255, 255, 255));
        countImpactSpeedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-7-100.png"))); // NOI18N
        countImpactSpeedButton.setText("count impact speed");
        countImpactSpeedButton.setBorder(null);
        countImpactSpeedButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        countImpactSpeedButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        countImpactSpeedButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        countImpactSpeedButton.setIconTextGap(14);
        countImpactSpeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countImpactSpeedButtonActionPerformed(evt);
            }
        });

        filterCarButton.setBackground(new java.awt.Color(246, 246, 246));
        filterCarButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        filterCarButton.setForeground(new java.awt.Color(255, 255, 255));
        filterCarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-car-100.png"))); // NOI18N
        filterCarButton.setText("filter car");
        filterCarButton.setBorder(null);
        filterCarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterCarButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        filterCarButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        filterCarButton.setIconTextGap(14);
        filterCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterCarButtonActionPerformed(evt);
            }
        });

        uniqueMoodButton.setBackground(new java.awt.Color(246, 246, 246));
        uniqueMoodButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        uniqueMoodButton.setForeground(new java.awt.Color(255, 255, 255));
        uniqueMoodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-depression-100.png"))); // NOI18N
        uniqueMoodButton.setText("unique mood");
        uniqueMoodButton.setBorder(null);
        uniqueMoodButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uniqueMoodButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uniqueMoodButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        uniqueMoodButton.setIconTextGap(14);
        uniqueMoodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniqueMoodButtonActionPerformed(evt);
            }
        });

        emptyResetButton.setBorder(null);
        emptyResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptyResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout commandsButtonsPanelLayout = new javax.swing.GroupLayout(commandsButtonsPanel);
        commandsButtonsPanel.setLayout(commandsButtonsPanelLayout);
        commandsButtonsPanelLayout.setHorizontalGroup(
            commandsButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emptyResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, commandsButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(commandsButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(insertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(executeScriptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeLowerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(replaceIfGreaterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeGreaterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countImpactSpeedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterCarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(uniqueMoodButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        commandsButtonsPanelLayout.setVerticalGroup(
            commandsButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commandsButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeScriptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeLowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(replaceIfGreaterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeGreaterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(countImpactSpeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uniqueMoodButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(emptyResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usernameIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(commandsButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(interactionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(commandsButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameIconLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(interactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void processTextCommand(Command command) {

        interactionPanel.removeAll();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        TextCommandPanel textCommandPanel = new TextCommandPanel();
        textCommandPanel.setVisible(true);
        interactionPanel.add(textCommandPanel);

        // sending command request
        commandSender.sendCommand(command);

        // receiving command response
        try {
            String responseText = commandResponseReceiver.receiveCommandResponse();
            textCommandPanel.setText(responseText.replaceAll("LINE_BREAK", ""));
        } catch (ServerIsDownException e) {
            JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
        }

        revalidate();
        repaint();
    }


    private void showCommandInterface(JPanel panel) {

        interactionPanel.removeAll();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));

        panel.setVisible(true);
        interactionPanel.add(panel);

        revalidate();

        repaint();
    }


    public void resetButtonColors(JButton button) {

        for (Component component : commandsButtonsPanel.getComponents()) {
            component.setForeground(new Color(246, 246, 246));
        }

        if (button != null) {
            button.setForeground(new Color(71, 199, 231));
        }
    }


    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed

        resetButtonColors(infoButton);

        processTextCommand(new InfoCommand());
    }//GEN-LAST:event_infoButtonActionPerformed


    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed

        resetButtonColors(showButton);

        if (!tableIsOpened) {
            tableIsOpened = true;
            TableFrame tableFrame = new TableFrame(this, commandSender, commandResponseReceiver);
            tableFrame.setVisible(true);
        }

    }//GEN-LAST:event_showButtonActionPerformed


    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed

        resetButtonColors(helpButton);

        processTextCommand(new HelpCommand());
    }//GEN-LAST:event_helpButtonActionPerformed


    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed

        resetButtonColors(insertButton);

        CommandWithObjectPanel commandWithObjectPanel = new CommandWithObjectPanel(commandSender, commandResponseReceiver);
        commandWithObjectPanel.setCommand(new InsertCommand());

        showCommandInterface(commandWithObjectPanel);
    }//GEN-LAST:event_insertButtonActionPerformed


    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

        resetButtonColors(updateButton);

        CommandWithObjectPanel commandWithObjectPanel = new CommandWithObjectPanel(commandSender, commandResponseReceiver);
        commandWithObjectPanel.setCommand(new UpdateCommand());

        showCommandInterface(commandWithObjectPanel);
    }//GEN-LAST:event_updateButtonActionPerformed


    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed

        resetButtonColors(removeButton);

        OneArgCommandPanel oneArgCommandPanel = new OneArgCommandPanel(commandSender, commandResponseReceiver);
        oneArgCommandPanel.getArgNameLabel().setText("key");
        oneArgCommandPanel.setCommand(new RemoveKeyCommand());

        showCommandInterface(oneArgCommandPanel);
    }//GEN-LAST:event_removeButtonActionPerformed


    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        ClearCommandPanel clearCommandPanel = new ClearCommandPanel(commandSender, commandResponseReceiver);
        showCommandInterface(clearCommandPanel);

        resetButtonColors(clearButton);
    }//GEN-LAST:event_clearButtonActionPerformed


    private void executeScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeScriptButtonActionPerformed

        resetButtonColors(executeScriptButton);

        ExecuteScriptPanel executeScriptPanel = new ExecuteScriptPanel();
        executeScriptPanel.setCommandSender(commandSender);
        executeScriptPanel.setCommandResponseReceiver(commandResponseReceiver);

        showCommandInterface(executeScriptPanel);
    }//GEN-LAST:event_executeScriptButtonActionPerformed


    private void removeLowerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLowerButtonActionPerformed

        resetButtonColors(removeLowerButton);

        CommandWithObjectPanel commandWithObjectPanel = new CommandWithObjectPanel(commandSender, commandResponseReceiver);
        commandWithObjectPanel.setCommand(new RemoveLowerCommand());

        showCommandInterface(commandWithObjectPanel);
    }//GEN-LAST:event_removeLowerButtonActionPerformed


    private void replaceIfGreaterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceIfGreaterButtonActionPerformed

        resetButtonColors(replaceIfGreaterButton);

        CommandWithObjectPanel commandWithObjectPanel = new CommandWithObjectPanel(commandSender, commandResponseReceiver);
        commandWithObjectPanel.setCommand(new ReplaceIfGreaterCommand());

        showCommandInterface(commandWithObjectPanel);
    }//GEN-LAST:event_replaceIfGreaterButtonActionPerformed


    private void removeGreaterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGreaterButtonActionPerformed

        resetButtonColors(removeGreaterButton);

        OneArgCommandPanel oneArgCommandPanel = new OneArgCommandPanel(commandSender, commandResponseReceiver);
        oneArgCommandPanel.getArgNameLabel().setText("key");

        showCommandInterface(oneArgCommandPanel);
    }//GEN-LAST:event_removeGreaterButtonActionPerformed


    private void countImpactSpeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countImpactSpeedButtonActionPerformed

        resetButtonColors(countImpactSpeedButton);

        CommandWithResultPanel commandWithResultPanel = new CommandWithResultPanel(commandSender, commandResponseReceiver);
        commandWithResultPanel.getArgNameLabel().setText("impact speed");
        commandWithResultPanel.setCommand(new CountLessThanImpactSpeedCommand());
        commandWithResultPanel.getResultLabel().setVisible(false);

        showCommandInterface(commandWithResultPanel);
    }//GEN-LAST:event_countImpactSpeedButtonActionPerformed


    private void filterCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterCarButtonActionPerformed

        resetButtonColors(filterCarButton);

        FilterCarCommandPanel filterCarCommandPanel = new FilterCarCommandPanel(commandSender, commandResponseReceiver);
        filterCarCommandPanel.setCommand(new FilterLessThanCarCommand());

        showCommandInterface(filterCarCommandPanel);

    }//GEN-LAST:event_filterCarButtonActionPerformed


    private void uniqueMoodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniqueMoodButtonActionPerformed

        processTextCommand(new PrintUniqueMoodCommand());

        resetButtonColors(uniqueMoodButton);
    }//GEN-LAST:event_uniqueMoodButtonActionPerformed


    private void emptyResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptyResetButtonActionPerformed

        interactionPanel.removeAll();

        javax.swing.GroupLayout interactionPanelLayout = new javax.swing.GroupLayout(interactionPanel);
        interactionPanel.setLayout(interactionPanelLayout);
        interactionPanelLayout.setHorizontalGroup(
                interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pigImagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        interactionPanelLayout.setVerticalGroup(
                interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interactionPanelLayout.createSequentialGroup()
                                .addComponent(pigImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        resetButtonColors(null);

        revalidate();
        repaint();
    }//GEN-LAST:event_emptyResetButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel commandsButtonsPanel;
    private javax.swing.JButton countImpactSpeedButton;
    private javax.swing.JButton emptyResetButton;
    private javax.swing.JButton executeScriptButton;
    private javax.swing.JButton filterCarButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JPanel interactionPanel;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JLabel pigImagePanel;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton removeGreaterButton;
    private javax.swing.JButton removeLowerButton;
    private javax.swing.JButton replaceIfGreaterButton;
    private javax.swing.JButton showButton;
    private javax.swing.JButton uniqueMoodButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel usernameIconLabel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
