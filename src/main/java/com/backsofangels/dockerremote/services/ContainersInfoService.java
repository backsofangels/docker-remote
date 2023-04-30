package com.backsofangels.dockerremote.services;

import com.backsofangels.dockerremote.constants.Endpoints;
import com.backsofangels.dockerremote.constants.LocalhostConfiguration;
import com.backsofangels.dockerremote.model.Container;
import com.backsofangels.dockerremote.utils.NetworkClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContainersInfoService {
    private Logger logger = LoggerFactory.getLogger(ContainersInfoService.class);
    private CloseableHttpClient client = NetworkClient.getNetworkClient().getClient();

    public List<Container> getRunningContainers() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(Endpoints.CONTAINER_LIST)
                    .build();


            CloseableHttpResponse response = null;
            HttpGet get = new HttpGet(uri);
            get.addHeader("Accept", "application/json");

            try {
                response = client.execute(get);
                HttpEntity entity = response.getEntity();

                ObjectMapper mapper = new ObjectMapper();

                return mapper
                        .readValue(entity.getContent(), mapper
                                .getTypeFactory()
                                .constructCollectionType(ArrayList.class, Container.class));
            } catch (ConnectException exception) {
                logger.error("Could not connect to docker backend - message=%s", exception.toString());
                return null;
            } catch (IOException exception) {
                exception.printStackTrace();
                return null;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            return null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public List<Container> getAllContainers() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(Endpoints.CONTAINER_LIST)
                    .addParameter("all", "true")
                    .build();


            CloseableHttpResponse response = null;
            HttpGet get = new HttpGet(uri);
            get.addHeader("Accept", "application/json");

            try {
                response = client.execute(get);

                HttpEntity entity = response.getEntity();

                ObjectMapper mapper = new ObjectMapper();

                return mapper
                        .readValue(entity.getContent(), mapper
                                .getTypeFactory()
                                .constructCollectionType(ArrayList.class, Container.class));
            } catch (ConnectException connectException) {
                connectException.printStackTrace();
                return null;
            } catch (IOException exception) {
                exception.printStackTrace();
                return null;
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            return null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public int healthcheck() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(Endpoints.SYSTEM_PING)
                    .build();

            CloseableHttpResponse response = null;
            HttpHead head = new HttpHead(uri);

            try {
                response = client.execute(head);
                return response.getStatusLine().getStatusCode();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
