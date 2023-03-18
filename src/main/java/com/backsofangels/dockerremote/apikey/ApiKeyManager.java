package com.backsofangels.dockerremote.apikey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Random;

@Service
public class ApiKeyManager {
    private static ApiKeyManager managerInstance = null;
    private String apiKey;

    private Logger logger = LoggerFactory.getLogger("ApiKeyManager");

    private ApiKeyManager() {
        apiKey = this.generateApiKey();
    }

    public static ApiKeyManager getManagerInstance() {
        if (managerInstance == null) {
            managerInstance = new ApiKeyManager();
        }
        return managerInstance;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    private void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private String generateApiKey() {
        Random random = new Random();
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    public boolean validateApiKey(String key) {
        return key.equals(apiKey);
    }
}
