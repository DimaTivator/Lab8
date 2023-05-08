/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client.gui;

import client.CommandResponseReceiver;
import client.CommandSender;
import client.gui.graphics.ColorGenerator;
import client.gui.graphics.HumanPanel;
import client.gui.workers.ShowWorker;
import commonModule.collectionClasses.*;
import commonModule.commands.Command;
import commonModule.commands.commandObjects.GetOwnerCommand;
import commonModule.commands.commandObjects.UpdateCommand;
import commonModule.exceptions.InvalidCoordinatesException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public class TableFrame extends javax.swing.JFrame {

    private final DefaultTableModel model;
    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;
    private final ApplicationWindow applicationWindow;
    private final HashMap<Long, HumanPanel> humanPanels;

    private final HashMap<Long, HumanBeing> humanBeings;

    /**
     * Creates new form TableFrame
     */
    public TableFrame(ApplicationWindow applicationWindow, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver) {

        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
        this.applicationWindow = applicationWindow;
        humanBeings = new HashMap<>();
        humanPanels = new HashMap<>();

        initComponents();
        visualizationPanel.setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                applicationWindow.setTableIsOpened(false);
                applicationWindow.resetButtonColors(null);
            }
        });

        String[] columns = {
                "key",
                "id",
                "name",
                "x_coordinate",
                "y_coordinate",
                "creation date",
                "mood",
                "weapon",
                "realHero",
                "impact speed",
                "hasToothpick",
                "carName",
                "carCool"
        };
        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        JTableHeader header = table.getTableHeader();
        Font newFont = new Font(header.getFont().getName(), header.getFont().getStyle(), 12);
        header.setFont(newFont);

        setSorters();

        setEditors();

        fillTable();
    }



    private void fillTable() {

        SwingWorker<Void, Object[]> showWorker = new ShowWorker(model, commandSender, commandResponseReceiver, humanBeings, visualizationPanel, this);
        showWorker.execute();

    }


    private void setSorters() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            sorter.setComparator(i, Comparator.naturalOrder());
        }
        table.setRowSorter(sorter);
    }


    private HumanBeing getRowObject() {

        int rowIndex = table.getSelectedRow();

        int key = (int) table.getValueAt(rowIndex, 0);

        return humanBeings.get((long) key);
    }


    private void setEditors() {

        TableColumn column;
        TableCellEditor cellEditor;

        column = table.getColumn("key");

        cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                JOptionPane.showMessageDialog(null, "У вас нет доступа к изменению значения этого параметра");
                cancelCellEditing();

                return false;
            }
        };

        column.setCellEditor(cellEditor);

        column = table.getColumn("id");
        column.setCellEditor(cellEditor);

        column = table.getColumn("creation date");
        column.setCellEditor(cellEditor);

        setNameCellEditor();
        setXCoordinateCellEditor();
        setYCoordinateCellEditor();
        setMoodCellEditor();
        setWeaponCellEditor();
        setRealHeroCellEditor();
        setHasToothpickCellEditor();
        setImpactSpeedCellEditor();
        setCarNameCellEditor();
        setCarCoolCellEditor();

    }


    public void paintHumans() {

        visualizationPanel.removeAll();

        Command command = new GetOwnerCommand();
        int i = 50;

        for (Long key : humanBeings.keySet()) {

            HumanBeing humanBeing = humanBeings.get(key);

            try {
                command.setArgs(new String[]{ humanBeing.getId().toString() });
            } catch (Exception ignored) {}

            // System.out.println(humanBeing.getId().toString());

            commandSender.sendCommand(command);

            try {
                String response = commandResponseReceiver.receiveCommandResponse();

                HumanPanel humanPanel;
                if (humanPanels.containsKey(key)) {
                    humanPanel = humanPanels.get(key);
                } else {
                    humanPanel = new HumanPanel(35);
                    humanPanel.setHumanBeing(humanBeing);
                }


                humanPanel.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //
                    }

                    public void mousePressed(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                                "id: " + humanPanel.getHumanBeing().getId() + "\n" +
                                        "name: " + humanPanel.getHumanBeing().getName());
                        // System.out.println(humanPanel.getHumanBeing().getName());
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //
                    }
                });

                humanPanel.setColor(ColorGenerator.getColor(Integer.parseInt(response)));
                humanPanel.setBounds((int) humanBeing.getCoordinates().getX(), (int) Math.ceil(humanBeing.getCoordinates().getY()), 50, 50);

                visualizationPanel.add(humanPanel);
                visualizationPanel.repaint();

                //i += 50;

            } catch (ServerIsDownException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void setNameCellEditor() {

        TableColumn column = table.getColumn("name");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = (String) super.getCellEditorValue();
                HumanBeing rowObject = getRowObject();

                String previousName = rowObject.getName();
                rowObject.setName(input);

                boolean success = false;
                if (!input.isEmpty()) {

                    Command updateCommand = new UpdateCommand();
                    try {
                        updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                    } catch (Exception ignored) {}
                    updateCommand.setValue(rowObject);

                    commandSender.sendCommand(updateCommand);

                    try {
                        String response = commandResponseReceiver.receiveCommandResponse();
                        if (!response.strip().equals("Done!")) {
                            JOptionPane.showMessageDialog(null, response);
                        } else {
                            success = true;
                        }
                    } catch (ServerIsDownException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } finally {
                        cancelCellEditing();

                        if (success) {
                            model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                            paintHumans();
                            table.repaint();
                        } else {
                            rowObject.setName(previousName);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Имя не может быть пустым");
                    cancelCellEditing();
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setXCoordinateCellEditor() {

        TableColumn column = table.getColumn("x_coordinate");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject = getRowObject();

                Coordinates previousCoordinates = rowObject.getCoordinates();
                Coordinates newCoordinates = new Coordinates();

                try {
                    newCoordinates.setX(Double.parseDouble(input));
                    newCoordinates.setY(rowObject.getCoordinates().getY());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Координата должна быть числом");
                    cancelCellEditing();
                    return true;

                } catch (InvalidCoordinatesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    cancelCellEditing();
                    return true;
                }

                rowObject.setCoordinates(newCoordinates);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setCoordinates(previousCoordinates);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setYCoordinateCellEditor() {

        TableColumn column = table.getColumn("y_coordinate");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject = getRowObject();

                Coordinates previousCoordinates = rowObject.getCoordinates();
                Coordinates newCoordinates = new Coordinates();

                try {
                    newCoordinates.setY(Float.parseFloat(input));
                    newCoordinates.setX(rowObject.getCoordinates().getY());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Координата должна быть числом");
                    cancelCellEditing();
                    return true;

                } catch (InvalidCoordinatesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    cancelCellEditing();
                    return true;
                }

                rowObject.setCoordinates(newCoordinates);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setCoordinates(previousCoordinates);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setMoodCellEditor() {

        TableColumn column = table.getColumn("mood");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toUpperCase().strip();
                HumanBeing rowObject = getRowObject();

                Mood previousMood = rowObject.getMood();
                Mood newMood;

                try {
                    newMood = Mood.valueOf(input);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Некорректное значение мood");
                    cancelCellEditing();
                    return true;
                }

                rowObject.setMood(newMood);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setMood(previousMood);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setWeaponCellEditor() {

        TableColumn column = table.getColumn("weapon");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toUpperCase().strip();
                HumanBeing rowObject = getRowObject();

                WeaponType previousWeapon = rowObject.getWeaponType();
                WeaponType newWeapon;

                try {
                    newWeapon = WeaponType.valueOf(input);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Некорректное значение weapon");
                    cancelCellEditing();
                    return true;
                }

                rowObject.setWeaponType(newWeapon);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setWeaponType(previousWeapon);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setRealHeroCellEditor() {

        TableColumn column = table.getColumn("realHero");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toLowerCase().strip();
                HumanBeing rowObject = getRowObject();

                boolean previousRealHero = rowObject.isRealHero();
                boolean newRealHero;

                if (input.equals("true")) {
                    newRealHero = true;
                } else if (input.equals("false")) {
                    newRealHero = false;
                } else {
                    JOptionPane.showMessageDialog(null, "realHero должен быть true или false");
                    cancelCellEditing();
                    return true;
                }

                rowObject.setRealHero(newRealHero);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setRealHero(previousRealHero);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setHasToothpickCellEditor() {

        TableColumn column = table.getColumn("hasToothpick");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toLowerCase().strip();
                HumanBeing rowObject = getRowObject();

                boolean previousHasToothpick = rowObject.isHasToothpick();
                boolean newHasToothpick;

                if (input.equals("true")) {
                    newHasToothpick = true;
                } else if (input.equals("false")) {
                    newHasToothpick = false;
                } else {
                    JOptionPane.showMessageDialog(null, "hasToothpick должен быть true или false");
                    cancelCellEditing();
                    return true;
                }

                rowObject.setHasToothpick(newHasToothpick);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setRealHero(previousHasToothpick);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setImpactSpeedCellEditor() {

        TableColumn column = table.getColumn("impact speed");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject = getRowObject();

                double previousImpactSpeed = rowObject.getImpactSpeed();
                double newImpactSpeed;

                try {
                    newImpactSpeed = Double.parseDouble(input);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Значение impact speed должно быть числом");
                    cancelCellEditing();
                    return true;

                }

                rowObject.setImpactSpeed(newImpactSpeed);

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setImpactSpeed(previousImpactSpeed);
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setCarNameCellEditor() {

        TableColumn column = table.getColumn("carName");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String newCarName = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject = getRowObject();

                String previousCarName = rowObject.getCar().getName();


                rowObject.setCar(new Car(newCarName, rowObject.getCar().getCool()));

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(newCarName, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setCar(new Car(previousCarName, rowObject.getCar().getCool()));
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setCarCoolCellEditor() {

        TableColumn column = table.getColumn("carCool");
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject = getRowObject();

                boolean previousCarCool = rowObject.getCar().getCool();
                boolean newCarCool;

                if (input.equals("true")) {
                    newCarCool = true;
                } else if (input.equals("false")) {
                    newCarCool = false;
                } else {
                    JOptionPane.showMessageDialog(null, "carCool должно быть true или false");
                    cancelCellEditing();
                    return true;
                }

                rowObject.setCar(new Car(rowObject.getCar().getName(), newCarCool));

                boolean success = false;

                Command updateCommand = new UpdateCommand();
                try {
                    updateCommand.setArgs(new String[]{String.valueOf(rowObject.getId())});
                } catch (Exception ignored) {}
                updateCommand.setValue(rowObject);

                commandSender.sendCommand(updateCommand);

                try {
                    String response = commandResponseReceiver.receiveCommandResponse();
                    if (!response.strip().equals("Done!")) {
                        JOptionPane.showMessageDialog(null, response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(newCarCool, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans();
                        table.repaint();
                    } else {
                        rowObject.setCar(new Car(rowObject.getCar().getName(), previousCarCool));
                    }
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        visualizationPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "key", "id", "name", "x_coordinate", "y_coordinate", "realHero", "hasToothpick", "impact speed", "mood", "weapon", "car name", "carCool", "creation date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout visualizationPanelLayout = new javax.swing.GroupLayout(visualizationPanel);
        visualizationPanel.setLayout(visualizationPanelLayout);
        visualizationPanelLayout.setHorizontalGroup(
            visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1131, Short.MAX_VALUE)
        );
        visualizationPanelLayout.setVerticalGroup(
            visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visualizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visualizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JPanel visualizationPanel;
    // End of variables declaration//GEN-END:variables
}
