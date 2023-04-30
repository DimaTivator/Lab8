package client;

import commonModule.commands.Command;
import commonModule.dataStructures.network.CommandRequest;

public class CommandSender {

    private final NetworkProvider networkProvider;
    private final Authenticator authenticator;

    public CommandSender(NetworkProvider networkProvider, Authenticator authenticator) {
        this.networkProvider = networkProvider;
        this.authenticator = authenticator;
    }

    public void sendCommand(Command command) {

        CommandRequest request = new CommandRequest(command);

        // TODO REMOVE THIS CODE
        if (authenticator.getLogin() == null || authenticator.getPassword() == null) {
            request.setLogin("admin");
            request.setPassword("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
        } else {
            request.setLogin(authenticator.getLogin());
            request.setPassword(authenticator.getPassword());
        }

        networkProvider.send(request);
    }
}
