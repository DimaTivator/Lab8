package client.gui.workers;

import client.CommandResponseReceiver;
import client.CommandSender;
import commonModule.commands.commandObjects.ShowCommand;
import commonModule.commands.commandObjects.ShowDBCommand;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class ShowWorker extends SwingWorker<Void, String[]> {

    private final DefaultTableModel model;
    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;

    public ShowWorker(DefaultTableModel model, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver) {
        this.model = model;
        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
    }

    @Override
    protected Void doInBackground() throws Exception {

        commandSender.sendCommand(new ShowDBCommand());
        String[] response = commandResponseReceiver.receiveCommandResponse().split("\n");

        for (int i = 0; i < response.length; i += 13) {
            String key = response[i];
            String id = response[i + 1];
            String name = response[i + 2];
            String x = response[i + 3];
            String y = response[i + 4];
            String date = response[i + 5];
            String mood = response[i + 6];
            String weapon = response[i + 7];
            String realHero = response[i + 8];
            String impactSpeed = response[i + 9];
            String hasToothpick = response[i + 10];
            String carName = response[i + 11];
            String carCool = response[i + 12];

            publish(new String[]{key, id, name, x, y, date, mood, weapon, realHero, impactSpeed, hasToothpick, carName, carCool});
        }

        return null;
    }

    @Override
    protected void process(java.util.List<String[]> chunks) {
        for (String[] chunk : chunks) {
            model.addRow(chunk);
        }
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
