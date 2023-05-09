package client;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigSaver {

    private static Gson gson = new Gson();

    public static void saveConfig(String jsonString) {
        try {
            Files.write(Paths.get("config.json"), jsonString.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to save string to `config.json`!");
        }
    }

}
