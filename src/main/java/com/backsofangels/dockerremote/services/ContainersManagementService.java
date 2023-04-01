package com.backsofangels.dockerremote.services;

import com.backsofangels.dockerremote.constants.Endpoints;
import com.backsofangels.dockerremote.constants.LocalhostConfiguration;
import com.backsofangels.dockerremote.utils.NetworkClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ContainersManagementService {
    public Logger logger = LoggerFactory.getLogger(ContainersManagementService.class);
    private CloseableHttpClient client = NetworkClient.getNetworkClient().getClient();

    public int startContainer(String containerId) throws URISyntaxException, IOException {
        logger.info("About to start container with id %s", containerId);
        if (!containerId.isBlank() || !containerId.isEmpty()) {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(String.format(Endpoints.CONTAINER_START, containerId))
                    .build();

            HttpPost post = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(post);

            try {
                logger.debug("Container with id %s started", containerId);
                return response.getStatusLine().getStatusCode();
            } catch (Exception e) {
                logger.error("Error starting container with id %s", containerId);
                e.printStackTrace();
                return 500;
            } finally {
                response.close();
            }
        } else {
            logger.error("Empty containerId");
            return 500;
        }
    }

    public int stopContainer(String containerId) throws URISyntaxException, IOException {
        logger.info("About to stop container with id %s", containerId);
        if (!containerId.isEmpty() || !containerId.isBlank()) {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(String.format(Endpoints.CONTAINER_STOP, containerId))
                    .build();

            HttpPost post = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(post);

            try {
                logger.debug("Container with id %s stopped", containerId);
                return response.getStatusLine().getStatusCode();
            } finally {
                response.close();
            }

        } else {
            logger.error("Empty containerId");
            return 500;
        }
    }

    public int restartContainer(String containerId) throws URISyntaxException, IOException {
        if (!containerId.isEmpty() || !containerId.isBlank()) {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(String.format(Endpoints.CONTAINER_RESTART, containerId))
                    .build();

            HttpPost post = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(post);

            try {
                return response.getStatusLine().getStatusCode();
            } finally {
                response.close();
            }

        } else {
            logger.error("Empty containerId");
            return 500;
        }
    }

    public int killContainer(String containerId) throws IOException, URISyntaxException {
        if (!containerId.isEmpty() || !containerId.isBlank()) {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(String.format(Endpoints.CONTAINER_KILL, containerId))
                    .build();

            HttpPost post = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(post);

            try {
                return response.getStatusLine().getStatusCode();
            } finally {
                response.close();
            }

        } else {
            logger.error("Empty containerId");
            return 500;
        }
    }
}
