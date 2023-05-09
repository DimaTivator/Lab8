package client;

import client.gui.AuthenticationWindow;
import client.gui.ApplicationWindow;
import client.i10n.Resources;
import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.commands.Command;
import commonModule.dataStructures.network.*;
import commonModule.dataStructures.Triplet;
import commonModule.exceptions.commandExceptions.InvalidArgumentsException;
import commonModule.io.consoleIO.CommandParser;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;

public class Client {

    public static void main(String[] args) {

        InetAddress address = null;
        int port = 0;

        try {
            address = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println(ConsoleColors.RED + "Unknown host. Please try again" + ConsoleColors.RESET);
            System.exit(0);
        }

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "Port must be a number. Please try again" + ConsoleColors.RESET);
            System.exit(0);
        }

        try {

            NetworkProvider networkProvider = new NetworkProvider(address.getHostAddress(), port);
            Authenticator authenticator = new Authenticator(networkProvider);
            CommandSender commandSender = new CommandSender(networkProvider, authenticator);
            CommandResponseReceiver commandResponseReceiver = new CommandResponseReceiver(networkProvider);


            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get("config.json")));
            HashMap<String, Object> configJSON = gson.fromJson(json, HashMap.class);

            ResourceBundle resourceBundle;
            Locale locale;

            switch (configJSON.get("language").toString()) {

                case "ukrainian" -> {
                    resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_UA");
                    locale = new Locale("uk", "UA");
                }

                case "spanish" -> {
                    resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_DO");
                    locale = new Locale("es", "DO");
                }

                case "icelandic" -> {
                    resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_IS");
                    locale = new Locale("is", "IS");
                }

                default -> {
                    resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_RU");
                    locale = new Locale("ru", "RU");
                }
            }

            Resources.setResourceBundle(resourceBundle);
            Resources.setCurrentLocale(locale);

            // Creating windows
            SwingUtilities.invokeLater(() -> {
                java.awt.EventQueue.invokeLater(() -> {
                    new AuthenticationWindow(authenticator, commandSender, commandResponseReceiver).setVisible(true);
                    // new ApplicationWindow(authenticator, commandSender, commandResponseReceiver).setVisible(true);
                });
            });

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
