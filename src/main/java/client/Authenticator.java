package client;

import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.dataStructures.network.AuthenticationRequest;
import commonModule.dataStructures.network.AuthenticationResponse;
import commonModule.dataStructures.network.Request;
import commonModule.exceptions.InvalidInputException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Authenticator {

    private final NetworkProvider networkProvider;
    private String login;
    private String password;

    public Authenticator(NetworkProvider networkProvider) {
        this.networkProvider = networkProvider;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Method encodes password using the SHA-256 algorithm
     */
    public String encodePassword(String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256 hashing to the password
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            // Convert the hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void logIn(String login, String password) {

        this.login = login;
        this.password = password;

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(false, login, password);
        networkProvider.send(authenticationRequest);
    }

    public void register(String login, String password) {

        this.login = login;
        this.password = password;

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(true, login, password);
        networkProvider.send(authenticationRequest);
    }

    public boolean processResponse() throws ServerIsDownException, InvalidInputException {

        AuthenticationResponse response = (AuthenticationResponse) networkProvider.receive();

        if (response == null) {
            throw new ServerIsDownException();
        }

        if (response.getException()!= null) {
            throw (InvalidInputException) response.getException();
        }

        return response.isAuthenticated();
    }
}
