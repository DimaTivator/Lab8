package client;

import commonModule.auxiliaryClasses.ConsoleColors;
import commonModule.dataStructures.network.CommandResponse;
import commonModule.dataStructures.network.Response;
import commonModule.dataStructures.network.SizeResponse;
import commonModule.exceptions.InvalidInputException;
import commonModule.exceptions.serverExceptions.ServerIsDownException;

public class CommandResponseReceiver {

    private final NetworkProvider networkProvider;

    public CommandResponseReceiver(NetworkProvider networkProvider) {
        this.networkProvider = networkProvider;
    }

    public String receiveCommandResponse() throws ServerIsDownException {

        Response response = networkProvider.receive();

        StringBuilder stringBuilder = new StringBuilder();

        if (response == null) {
            throw new ServerIsDownException();
        }

        if (response.getClass() == SizeResponse.class) {

            SizeResponse sizeResponse = (SizeResponse) response;
            int numberOfPackages = Integer.parseInt(sizeResponse.getSize());

            for (int i = 0; i < numberOfPackages; i++) {
                response = networkProvider.receive();
                if (response == null) {
                    throw new ServerIsDownException();
                } else {
                    stringBuilder.append(((CommandResponse)response).getOutput());
                }
            }

        } else {
            stringBuilder.append(((CommandResponse)response).getOutput());
        }

        return stringBuilder.toString();
    }
}
