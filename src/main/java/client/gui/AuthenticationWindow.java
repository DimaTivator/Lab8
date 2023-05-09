package client.gui;

import client.Authenticator;
import client.CommandResponseReceiver;
import client.CommandSender;
import client.ConfigSaver;
import client.i10n.Resources;
import commonModule.exceptions.InvalidInputException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthenticationWindow extends javax.swing.JFrame {

    private final Authenticator authenticator;
    private final CommandResponseReceiver commandResponseReceiver;
    private final CommandSender commandSender;

    private ResourceBundle resourceBundle = Resources.getResourceBundle();


    public AuthenticationWindow(Authenticator authenticator, CommandSender commandSender, CommandResponseReceiver commandResponseReceiver) {

        this.authenticator = authenticator;
        this.commandSender = commandSender;
        this.commandResponseReceiver = commandResponseReceiver;

        initComponents();
        txtUsername.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtPassword.setBackground(new java.awt.Color(0, 0, 0, 1));

        setLanguages();

        pack();
    }

    private void setLanguages() {

        usernameLabel.setText(resourceBundle.getString("username"));
        passwordLabel.setText(resourceBundle.getString("password"));
        txtLoginLabel.setText(resourceBundle.getString("login"));
        jLabel4.setText(resourceBundle.getString("account_question"));
        SignUpButton.setText(resourceBundle.getString("sign_up"));
        logInButton.setText(resourceBundle.getString("log_in"));
        languageMenu.setText(resourceBundle.getString("language"));

        pack();
    }

    private void initMenu() {
        // adding languages menu

        menuBar = new JMenuBar();

        Font font = new Font("Arial", Font.PLAIN, 12);

        menuBar.setFont(font);
        menuBar.setBorderPainted(false);
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.setForeground(new java.awt.Color(50, 110, 211));

        languageMenu = new JMenu("Language");


        russianMenuItem = new JCheckBoxMenuItem("русский");
        russianMenuItem.setFont(font);

        ukraineMenuItem = new JCheckBoxMenuItem("українська");
        ukraineMenuItem.setFont(font);

        spanishMenuItem = new JCheckBoxMenuItem("español");
        spanishMenuItem.setFont(font);

        icelandMenuItem = new JCheckBoxMenuItem("íslenska");
        icelandMenuItem.setFont(font);

        russianMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(true);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(false);
            icelandMenuItem.setSelected(false);

            resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_RU");
            Resources.setResourceBundle(resourceBundle);

            ConfigSaver.saveConfig("""
                    {
                      "language": "russian"
                    }""");

            setLanguages();
        });

        ukraineMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(true);
            spanishMenuItem.setSelected(false);
            icelandMenuItem.setSelected(false);

            resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_UA");
            Resources.setResourceBundle(resourceBundle);

            ConfigSaver.saveConfig("""
                    {
                      "language": "ukrainian"
                    }""");

            setLanguages();
        });

        spanishMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(true);
            icelandMenuItem.setSelected(false);

            resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_DO");
            Resources.setResourceBundle(resourceBundle);

            ConfigSaver.saveConfig("""
                    {
                      "language": "spanish"
                    }""");

            setLanguages();
        });

        icelandMenuItem.addActionListener(event -> {
            russianMenuItem.setSelected(false);
            ukraineMenuItem.setSelected(false);
            spanishMenuItem.setSelected(false);
            icelandMenuItem.setSelected(true);

            resourceBundle = ResourceBundle.getBundle("client.i10n.Resources_IS");
            Resources.setResourceBundle(resourceBundle);

            ConfigSaver.saveConfig("""
                    {
                      "language": "icelandic"
                    }""");

            setLanguages();
        });

        if (resourceBundle.getBaseBundleName().equals("client.i10n.Resources_RU")) {
            russianMenuItem.setSelected(true);
        } else if (resourceBundle.getBaseBundleName().equals("client.i10n.Resources_UA")) {
            ukraineMenuItem.setSelected(true);
        } else if (resourceBundle.getBaseBundleName().equals("client.i10n.Resources_DO")) {
            spanishMenuItem.setSelected(true);
        } else {
            icelandMenuItem.setSelected(true);
        }

        languageMenu.add(russianMenuItem);
        languageMenu.add(ukraineMenuItem);
        languageMenu.add(spanishMenuItem);
        languageMenu.add(icelandMenuItem);

        menuBar.add(languageMenu);

        setJMenuBar(menuBar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AuthenticationPictureLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtLoginLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        usernameIcon = new javax.swing.JLabel();
        passwordIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AuthenticationPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/auth_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 32, Short.MAX_VALUE)
                                .addComponent(AuthenticationPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(AuthenticationPictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(50, 110, 211));

        txtLoginLabel.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtLoginLabel.setForeground(new java.awt.Color(255, 255, 255));
        txtLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLoginLabel.setText("Login");

        usernameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(199, 226, 255));
        usernameLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(199, 226, 255));
        passwordLabel.setText("Password");

        txtUsername.setFont(txtUsername.getFont());
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setBorder(null);
        txtUsername.setCaretColor(new java.awt.Color(199, 216, 255));
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtPassword.setFont(txtPassword.getFont());
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(null);
        txtPassword.setCaretColor(new java.awt.Color(199, 226, 255));

        logInButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        logInButton.setForeground(new java.awt.Color(50, 110, 211));
        logInButton.setText("Log in");
        logInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(199, 226, 255));
        jLabel4.setText("Don't have an account?");

        SignUpButton.setBackground(new java.awt.Color(50, 110, 211));
        SignUpButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(199, 226, 255));
        SignUpButton.setText("Sign up");
        SignUpButton.setBorder(null);
        SignUpButton.setBorderPainted(false);
        SignUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignUpButton.setFocusPainted(false);
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        usernameIcon.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/pics/icons8-male-user-24.png")))); // NOI18N

        passwordIcon.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/pics/icons8-password-24.png")))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(txtLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(109, 109, 109))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                        .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                                                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(usernameIcon))
                                                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(logInButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                                                                                .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(passwordIcon)))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(SignUpButton)
                                                                                .addGap(69, 69, 69))))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(306, 306, 306)
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)))
                                                .addComponent(jLabel5)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(txtLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(usernameIcon)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(passwordIcon)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        initMenu();

        pack();
    }// </editor-fold>

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String login = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        password = authenticator.encodePassword(password);

        authenticator.logIn(login, password);
        if (checkAuthentication()) {
            this.dispose();
            new ApplicationWindow(authenticator, commandSender, commandResponseReceiver).setVisible(true);
        }
    }

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String login = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        password = authenticator.encodePassword(password);

        authenticator.register(login, password);
        if (checkAuthentication()) {
            this.dispose();
            new ApplicationWindow(authenticator, commandSender, commandResponseReceiver).setVisible(true);
        }
    }

    private boolean checkAuthentication() {

        try {
            return authenticator.processResponse();
        } catch (ServerIsDownException e) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, Resources.getResourceBundle().getString("error.serverIsDown"), "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidInputException e) {
            JFrame errorFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(errorFrame, Resources.getResourceBundle().getString("error.InvalidInputException"), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel AuthenticationPictureLabel;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton logInButton;
    private javax.swing.JLabel passwordIcon;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel txtLoginLabel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel usernameIcon;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration

    private JMenuBar menuBar;
    private JMenu languageMenu;
    private JCheckBoxMenuItem russianMenuItem;
    private JCheckBoxMenuItem ukraineMenuItem;
    private JCheckBoxMenuItem spanishMenuItem;
    private JCheckBoxMenuItem icelandMenuItem;
}
