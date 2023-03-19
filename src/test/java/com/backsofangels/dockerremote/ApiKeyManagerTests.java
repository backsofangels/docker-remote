package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.apikey.ApiKeyManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApiKeyManagerTests {

    @Test
    void isApiKeyGenerated() {
        assertNotNull(ApiKeyManager.getManagerInstance().getApiKey());
    }

    @Test
    void validateApiKeyTestWithNotValidKey() {
        assertFalse(ApiKeyManager.getManagerInstance().validateApiKey("randomKey"));
    }
}
