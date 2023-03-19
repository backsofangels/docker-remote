package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.apikey.SecretFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecretFileManagerTests {
    @AfterEach
    void fileCleanup () {
        File file = new File("src/main/resources/apikey");
        file.delete();
    }

    @Test
    void apiKeyFileExistsTest() {
        SecretFileManager secretFileManager = new SecretFileManager();
        assertFalse(secretFileManager.checkIfApiKeyExists());
    }

    @Test
    void createApiKeyFileTest() {
        SecretFileManager secretFileManager = new SecretFileManager();
        assertTrue(secretFileManager.createApiKeyFile());
    }

    @Test
    void persistApiKeyOnFileTest() {
        SecretFileManager secretFileManager = new SecretFileManager();
        secretFileManager.createApiKeyFile();
        assertTrue(secretFileManager.persistApiKeyOnFile("test"));
    }
}
