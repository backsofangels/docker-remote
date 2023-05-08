package com.backsofangels.dockerremote.apikey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SecretFileManager {
    private final String FILE_NAME = "src/main/resources/apikey";
    private Logger logger = LoggerFactory.getLogger("SecretFileManager");

    public SecretFileManager() {}

    public boolean createApiKeyFile() {
        try {
            Path pathToSecret = Paths.get(FILE_NAME);
            Files.createFile(pathToSecret);
            logger.debug("File created");
            return true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public boolean checkIfApiKeyExists() {
        return Files.exists(Paths.get(FILE_NAME));
    }

    public boolean persistApiKeyOnFile(String keyString) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(keyString);
            writer.close();
            return true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public String retrieveKeyFromFile() {
        if (!checkIfApiKeyExists()) {
            return null;
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                String key = reader.readLine();
                reader.close();
                return key;
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return null;
            }
        }
    }
}
