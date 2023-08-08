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

    public int startContainer(String containerId) {
        //Add non-null check
        if (containerId == null) {
            return 500;
        }

        if (containerId.isBlank() || containerId.isEmpty()) {
            return 500;
        }

        logger.info("About to start container with id %s", containerId);

        try {
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
            } finally {
                response.close();
            }
        } catch (URISyntaxException exception) {
            logger.error("Error in creating URI");
            exception.printStackTrace();
            return 500;
        } catch (IOException ioException) {
            logger.error("Error in response unmarshalling");
            ioException.printStackTrace();
            return 500;
        }
    }

    public int stopContainer(String containerId) {
        //Add non-null check
        if (containerId == null) {
            return 500;
        }

        logger.info("About to stop container with id %s", containerId);

        if (containerId.isEmpty() || containerId.isBlank()) {
            return 500;
        }

        try {
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

        } catch (URISyntaxException exception) {
            logger.error("Error in creating URI");
            exception.printStackTrace();
            return 500;
        } catch (IOException ioException) {
            logger.error("Error in response unmarshalling");
            ioException.printStackTrace();
            return 500;
        }
    }

    public int restartContainer(String containerId) {
        //Add non-null check
        if (containerId == null) {
            return 500;
        }

        if (containerId.isEmpty() || containerId.isBlank()) {
            return 500;
        }

        try {
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
        } catch (URISyntaxException exception) {
            logger.error("Error in creating URI");
            exception.printStackTrace();
            return 500;
        } catch (IOException ioException) {
            logger.error("Error in response unmarshalling");
            ioException.printStackTrace();
            return 500;
        }
    }

    public int killContainer(String containerId) {
        //Add non-null check
        if (containerId == null) {
            return 500;
        }

        if (containerId.isEmpty() || containerId.isBlank()) {
            return 500;
        }

        try {
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
        } catch (URISyntaxException exception) {
            logger.error("Error in creating URI");
            exception.printStackTrace();
            return 500;
        } catch (IOException ioException) {
            logger.error("Error in response unmarshalling");
            ioException.printStackTrace();
            return 500;
        }
    }
}
