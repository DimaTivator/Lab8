package client.gui;

import client.Authenticator;
import commonModule.exceptions.InvalidInputException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

import javax.swing.*;
import java.awt.*;

public class AuthenticationWindow extends JFrame {

    private final Authenticator authenticator;

    public AuthenticationWindow(Authenticator authenticator) {
        this.authenticator = authenticator;
        initComponents();
    }

    private void initComponents() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Authentication");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Имя пользователя:");

        JTextField usernameTextField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Пароль:");

        JPasswordField passwordTextField = new JPasswordField(20);

        JButton loginButton = new JButton("Войти");
        loginButton.setPreferredSize(new Dimension(200, 30));

        JButton registerButton = new JButton("Зарегистрироваться");
        registerButton.setPreferredSize(new Dimension(200, 30));


        // adding action listeners to the buttons to send authentication requests
        loginButton.addActionListener(event -> {
            String login = usernameTextField.getText();
            String password = String.valueOf(passwordTextField.getPassword());
            password = authenticator.encodePassword(password);

            authenticator.logIn(login, password);
            if (checkAuthentication()) {
                this.dispose();
            }
        });

        registerButton.addActionListener(event -> {
            String login = usernameTextField.getText();
            String password = String.valueOf(passwordTextField.getPassword());
            password = authenticator.encodePassword(password);

            authenticator.register(login, password);
            if (checkAuthentication()) {
                this.dispose();
            }
        });


        // adding all stuff to panel using BoxLayout
        JPanel panel = new JPanel();
        BoxLayout inputFieldsLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(inputFieldsLayout);

        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameTextField.setMaximumSize(new Dimension(200, 45));
        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextField.setMaximumSize(new Dimension(200, 45));
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(userLabel);
        panel.add(usernameTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);

        // adding "Log in", "Register" using BoxLayout
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);

        BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
        buttonsPanel.setLayout(buttonsLayout);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(buttonsPanel);

        add(panel);


        // MenuBar on the top
        JMenuBar menuBar = new JMenuBar();

        JMenu languageMenu = new JMenu("Languages");

        JCheckBoxMenuItem russianMenuItem = new JCheckBoxMenuItem("Русский");
        JCheckBoxMenuItem ukraineMenuItem = new JCheckBoxMenuItem("Украинский");
        JCheckBoxMenuItem spanishMenuItem = new JCheckBoxMenuItem("Испанский");
        JCheckBoxMenuItem islandMenuItem = new JCheckBoxMenuItem("Исландский");

        russianMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(true);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(false);
            islandMenuItem.setSelected(false);
        });

        ukraineMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(true);
            spanishMenuItem.setSelected(false);
            islandMenuItem.setSelected(false);
        });

        spanishMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(true);
            islandMenuItem.setSelected(false);
        });

        islandMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(false);
            islandMenuItem.setSelected(true);
        });

        russianMenuItem.setSelected(true);

        languageMenu.add(russianMenuItem);
        languageMenu.add(ukraineMenuItem);
        languageMenu.add(spanishMenuItem);
        languageMenu.add(islandMenuItem);

        menuBar.add(languageMenu);

        setJMenuBar(menuBar);
    }


    private boolean checkAuthentication() {

        try {
            return authenticator.processResponse();
        } catch (ServerIsDownException | InvalidInputException e) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
