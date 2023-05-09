/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client.gui;

import client.CommandResponseReceiver;
import client.CommandSender;
import client.gui.graphics.ColorGenerator;
import client.gui.graphics.HumanPanel;
import client.gui.workers.Painter;
import client.gui.workers.ShowWorker;
import client.i10n.Resources;
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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TableFrame extends javax.swing.JFrame {

    private final DefaultTableModel model;
    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;
    private final ApplicationWindow applicationWindow;
    private final Map<Long, HumanPanel> humanPanels;

    private final Map<Long, HumanBeing> humanBeings;

    private ResourceBundle resourceBundle = Resources.getResourceBundle();

    /**
     * Creates new form TableFrame
     */
    public TableFrame(ApplicationWindow applicationWindow, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver) {

        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
        this.applicationWindow = applicationWindow;

        humanBeings = Collections.synchronizedMap(new HashMap<Long, HumanBeing>());
        humanPanels = Collections.synchronizedMap(new HashMap<Long, HumanPanel>());

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
                resourceBundle.getString("key"),
                resourceBundle.getString("id"),
                resourceBundle.getString("name"),
                resourceBundle.getString("x_coordinate"),
                resourceBundle.getString("y_coordinate"),
                resourceBundle.getString("creation_date"),
                resourceBundle.getString("mood"),
                resourceBundle.getString("weapon"),
                resourceBundle.getString("realHero"),
                resourceBundle.getString("impact_speed"),
                resourceBundle.getString("hasToothpick"),
                resourceBundle.getString("carName"),
                resourceBundle.getString("carCool")
        };

        model = new DefaultTableModel(columns, 0);
        table.setModel(model);

        JTableHeader header = table.getTableHeader();
        Font newFont = new Font(header.getFont().getName(), header.getFont().getStyle(), 12);
        header.setFont(newFont);

        setSorters();

        setEditors();

        fillTable();

        SwingWorker<Void, Object[]> painter = new Painter(model, commandSender, commandResponseReceiver, humanBeings, visualizationPanel, this);
        painter.execute();
    }


    public void fillTable() {

        SwingWorker<Void, Object[]> showWorker = new ShowWorker(model, commandSender, commandResponseReceiver, humanBeings, visualizationPanel, this);
        showWorker.execute();

    }


    private void setSorters() {
//        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            sorter.setComparator(i, Comparator.naturalOrder());
//        }
//        table.setRowSorter(sorter);

        NumberFormat numberFormat = NumberFormat.getInstance(Resources.getCurrentLocale());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Resources.getCurrentLocale());

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

        // компаратор для key, id
        Comparator<String> intComparator = (s1, s2) -> {
            try {
                Integer v1 = numberFormat.parse(s1).intValue();
                Integer v2 = numberFormat.parse(s2).intValue();
                return v1.compareTo(v2);
            } catch (ParseException e) {
                //System.out.println(e.getMessage());
            }
            return 0;
        };

        sorter.setComparator(0, intComparator);
        sorter.setComparator(1, intComparator);

        // компаратор для координат и скорости

        Comparator<String> doubleComparator = (s1, s2) -> {
            try {
                Double v1 = numberFormat.parse(s1).doubleValue();
                Double v2 = numberFormat.parse(s2).doubleValue();
                return v1.compareTo(v2);
            } catch (ParseException e) {
                // System.out.println(e.getMessage());
            }
            return 0;
        };

        sorter.setComparator(3, doubleComparator);
        sorter.setComparator(4, doubleComparator);
        sorter.setComparator(9, doubleComparator);


        Comparator<String> dateComparator = (s1, s2) -> {
            LocalDate d1 = LocalDate.parse(s1, dateFormatter);
            LocalDate d2 = LocalDate.parse(s2, dateFormatter);
            return d1.compareTo(d2);
        };

        sorter.setComparator(5, dateComparator);

        sorter.setComparator(2, Comparator.naturalOrder());
        sorter.setComparator(6, Comparator.naturalOrder());
        sorter.setComparator(7, Comparator.naturalOrder());
        sorter.setComparator(8, Comparator.naturalOrder());
        sorter.setComparator(10, Comparator.naturalOrder());
        sorter.setComparator(11, Comparator.naturalOrder());
        sorter.setComparator(12, Comparator.naturalOrder());

        table.setRowSorter(sorter);
    }


    private HumanBeing getRowObject() throws ParseException {

        int rowIndex = table.getSelectedRow();

        // int key = Integer.parseInt(table.getValueAt(rowIndex, 0).toString());
        // int key = NumberFormat.getInstance(Resources.getCurrentLocale()).parse(table.getValueAt(rowIndex, 0).toString()).intValue();
        int key = Integer.parseInt(NumberFormat.getInstance(Resources.getCurrentLocale()).parse(table.getValueAt(rowIndex, 0).toString()).toString());


        return humanBeings.get((long) key);
    }


    private void setEditors() {

        TableColumn column;
        TableCellEditor cellEditor;

        column = table.getColumn(resourceBundle.getString("key"));

        cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.AccessError"));
                cancelCellEditing();

                return false;
            }
        };

        column.setCellEditor(cellEditor);

        column = table.getColumn(resourceBundle.getString("id"));
        column.setCellEditor(cellEditor);

        column = table.getColumn(resourceBundle.getString("creation_date"));
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


    public void paintHumans(boolean repaint, Long id) {

        if (repaint) {
            for (Component component : visualizationPanel.getComponents()) {
                if (component instanceof HumanPanel) {
                    HumanBeing humanBeing = ((HumanPanel) component).getHumanBeing();
                    if (humanBeing.getId().equals(id)) {
                        visualizationPanel.remove(component);
                        repaint();
                        break;
                    }
                }
            }
        } else {
            visualizationPanel.removeAll();
        }


        Command command = new GetOwnerCommand();

        for (Long key : humanBeings.keySet()) {

            HumanBeing humanBeing = humanBeings.get(key);

            if (repaint && !humanBeing.getId().equals(id)) {
                continue;
            }

            try {
                command.setArgs(new String[]{ humanBeing.getId().toString() });
            } catch (Exception ignored) {}

            commandSender.sendCommand(command);

            try {
                String response = commandResponseReceiver.receiveCommandResponse();

                HumanPanel humanPanel;
                if (humanPanels.containsKey(key)) {
                    humanPanel = humanPanels.get(key);
                } else {
                    humanPanel = new HumanPanel(35, humanBeing);
                }


                humanPanel.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //
                    }

                    public void mousePressed(MouseEvent e) {
                        JOptionPane.showMessageDialog(null,
                            resourceBundle.getString("id") + ": " + humanPanel.getHumanBeing().getId() + "\n" +
                                    resourceBundle.getString("name") + ": " + humanPanel.getHumanBeing().getName());
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
                //System.out.println(ColorGenerator.getColor(Integer.parseInt(response)));
                //humanPanel.setBounds((int) humanBeing.getCoordinates().getX() + 500, (int) Math.ceil(humanBeing.getCoordinates().getY()) + 100, 60, 65);
                //humanPanel.setBounds((int) humanBeing.getCoordinates().getX() + 500, 0, 60, 300);
                humanPanel.setBounds((int) humanBeing.getCoordinates().getX() + 500, 0, 60, 65);

                if (id == -1L) {
                    humanPanel.startAnimation();
                    visualizationPanel.add(humanPanel);
                }

                if (repaint && humanBeings.get(key).getId().equals(id) && id != -1) {
                    humanPanel.startAnimation();
                    visualizationPanel.add(humanPanel);
                    break;
                }

                visualizationPanel.repaint();

            } catch (ServerIsDownException e) {
                JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void setNameCellEditor() {

        TableColumn column = table.getColumn(resourceBundle.getString("name"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = (String) super.getCellEditorValue();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

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
                            JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                        } else {
                            success = true;
                        }
                    } catch (ServerIsDownException e) {
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                    } finally {
                        cancelCellEditing();

                        if (success) {
                            model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                            paintHumans(true, rowObject.getId());
                            table.repaint();
                        } else {
                            rowObject.setName(previousName);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.EmptyName"));
                    cancelCellEditing();
                }

                return true;
            }
        };

        column.setCellEditor(cellEditor);
    }


    private void setXCoordinateCellEditor() {

        TableColumn column = table.getColumn(resourceBundle.getString("x_coordinate"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip().replace(",", ".");
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                Coordinates previousCoordinates = rowObject.getCoordinates();
                Coordinates newCoordinates = new Coordinates();

                try {
                    newCoordinates.setX(Double.parseDouble(input));
                    newCoordinates.setY(rowObject.getCoordinates().getY());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.CoordinateFormat"));
                    cancelCellEditing();
                    return true;

                } catch (InvalidCoordinatesException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.InvalidInputException") + ":\n" + e.getMessage());
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("y_coordinate"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip().replace(",", ".");
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                Coordinates previousCoordinates = rowObject.getCoordinates();
                Coordinates newCoordinates = new Coordinates();

                try {
                    newCoordinates.setY(Float.parseFloat(input));
                    newCoordinates.setX(rowObject.getCoordinates().getX());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.CoordinateFormat"));
                    cancelCellEditing();
                    return true;

                } catch (InvalidCoordinatesException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.InvalidInputException") + ":\n" + e.getMessage());
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("mood"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toUpperCase().strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                Mood previousMood = rowObject.getMood();
                Mood newMood;

                try {
                    newMood = Mood.valueOf(resourceBundle.getString(input.toLowerCase()).toUpperCase());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.MoodError"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("weapon"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toUpperCase().strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                WeaponType previousWeapon = rowObject.getWeaponType();
                WeaponType newWeapon;

                try {
                    newWeapon = WeaponType.valueOf(resourceBundle.getString(input.toLowerCase()).toUpperCase());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.WeaponType"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("realHero"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toLowerCase().strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                boolean previousRealHero = rowObject.isRealHero();
                boolean newRealHero;

                if (input.equals("true") || input.equals("1")) {
                    newRealHero = true;
                } else if (input.equals("false") || input.equals("0")) {
                    newRealHero = false;
                } else {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.RealHero"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("hasToothpick"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).toLowerCase().strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                boolean previousHasToothpick = rowObject.isHasToothpick();
                boolean newHasToothpick;

                if (input.equals("true")) {
                    newHasToothpick = true;
                } else if (input.equals("false")) {
                    newHasToothpick = false;
                } else {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.HasToothpick"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("impact_speed"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                double previousImpactSpeed = rowObject.getImpactSpeed();
                double newImpactSpeed;

                try {
                    newImpactSpeed = Double.parseDouble(input);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ImpactSpeed"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(input, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("carName"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String newCarName = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(newCarName, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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

        TableColumn column = table.getColumn(resourceBundle.getString("carCool"));
        TableCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {

            @Override
            public boolean stopCellEditing() {

                String input = ((String) super.getCellEditorValue()).strip();
                HumanBeing rowObject;

                try {
                    rowObject = getRowObject();
                } catch (ParseException e) {
                    cancelCellEditing();
                    return false;
                }

                boolean previousCarCool = rowObject.getCar().getCool();
                boolean newCarCool;

                if (input.equals("true")) {
                    newCarCool = true;
                } else if (input.equals("false")) {
                    newCarCool = false;
                } else {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.CarCoolError"));
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
                        JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.ResponseError") + ": \n" + response);
                    } else {
                        success = true;
                    }
                } catch (ServerIsDownException e) {
                    JOptionPane.showMessageDialog(null, Resources.getResourceBundle().getString("error.serverIsDown"));
                } finally {
                    cancelCellEditing();

                    if (success) {
                        model.setValueAt(newCarCool, table.getSelectedRow(), table.getSelectedColumn());
                        paintHumans(true, rowObject.getId());
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
