package com.backsofangels.dockerremote.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class NetworkClient {
    private CloseableHttpClient client;
    private static NetworkClient networkClient;

    public synchronized static NetworkClient getNetworkClient() {
        if (networkClient == null) {
            return new NetworkClient();
        } else return networkClient;
    }

    private NetworkClient() {
        this.client = initClient();
    }

    private CloseableHttpClient initClient() {
        CloseableHttpClient localClient = HttpClients.createDefault();
        return localClient;
    }

    public CloseableHttpClient getClient() {
        return this.client;
    }
}
