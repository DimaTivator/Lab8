package client.gui.workers;

import client.CommandResponseReceiver;
import client.CommandSender;
import client.gui.TableFrame;
import commonModule.collectionClasses.*;
import commonModule.commands.commandObjects.ShowDBCommand;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



public class Painter extends SwingWorker<Void, Object[]> {

    private final DefaultTableModel model;
    private final CommandSender commandSender;
    private final CommandResponseReceiver commandResponseReceiver;
    private final Map<Long, HumanBeing> humanBeings;
    private final JPanel visualizationPanel;
    private final TableFrame tableFrame;

    public Painter(DefaultTableModel model, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver,
                      Map<Long, HumanBeing> humanBeings, JPanel visualizationPanel, TableFrame tableFrame) {

        this.model = model;
        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;
        this.humanBeings = humanBeings;
        this.visualizationPanel = visualizationPanel;
        this.tableFrame = tableFrame;
    }

    @Override
    protected Void doInBackground() throws Exception {

        while (true) {

            Thread.sleep(5000);

            HashMap<Long, HumanBeing> previousHumanBeings = new HashMap<>(humanBeings);

            humanBeings.clear();

            commandSender.sendCommand(new ShowDBCommand());
            String[] response = commandResponseReceiver.receiveCommandResponse().split("\n");

            for (int i = 0; i < response.length; i += 13) {

                int key = Integer.parseInt(response[i]);
                int id = Integer.parseInt(response[i + 1]);
                String name = response[i + 2];
                double x = Double.parseDouble(response[i + 3]);
                double y = Double.parseDouble(response[i + 4]);
                LocalDate date = LocalDate.parse(response[i + 5]);
                String mood = response[i + 6];
                String weapon = response[i + 7];
                boolean realHero = Boolean.parseBoolean(response[i + 8]);
                double impactSpeed = Double.parseDouble(response[i + 9]);
                boolean hasToothpick = Boolean.parseBoolean(response[i + 10]);
                String carName = response[i + 11];
                boolean carCool = Boolean.parseBoolean(response[i + 12]);

                HumanBeing humanBeing = new HumanBeing();
                humanBeing.setId((long) id);
                humanBeing.setName(name);
                humanBeing.setCoordinates(new Coordinates(x, (float) y));
                humanBeing.setCreationDate(date);
                humanBeing.setMood(Mood.valueOf(mood));
                humanBeing.setWeaponType(WeaponType.valueOf(weapon));
                humanBeing.setImpactSpeed(impactSpeed);
                humanBeing.setHasToothpick(hasToothpick);
                humanBeing.setRealHero(realHero);
                humanBeing.setCar(new Car(carName, carCool));

                humanBeings.put((long) key, humanBeing);
            }

//            if (humanBeings.size() == previousHumanBeings.size()) {
//                continue;
//            }

            for (Long key : humanBeings.keySet()) {

                if (previousHumanBeings.containsKey(key) && previousHumanBeings.get(key).equals(humanBeings.get(key))) {
                    continue;
                }

                HumanBeing humanBeing = humanBeings.get(key);

                publish(new Object[]{
                        key,
                        humanBeing.getId(),
                        humanBeing.getName(),
                        humanBeing.getCoordinates().getX(),
                        humanBeing.getCoordinates().getY(),
                        humanBeing.getCreationDate(),
                        humanBeing.getMood().toString(),
                        humanBeing.getWeaponType().toString(),
                        humanBeing.isRealHero(),
                        humanBeing.getImpactSpeed(),
                        humanBeing.isHasToothpick(),
                        humanBeing.getCar().getName(),
                        humanBeing.getCar().getName()
                });

            }

        }

        // return null;
    }

    @Override
    protected void process(java.util.List<Object[]> chunks) {

        for (Object[] chunk : chunks) {
            tableFrame.paintHumans(true, (Long) chunk[1]);
        }

        // tableFrame.fillTable();
    }

    @Override
    protected void done() {
        try {
            get();
            tableFrame.paintHumans(false, -1L);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
