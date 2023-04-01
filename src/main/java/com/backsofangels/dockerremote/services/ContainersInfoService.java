package com.backsofangels.dockerremote.services;

import com.backsofangels.dockerremote.constants.Endpoints;
import com.backsofangels.dockerremote.constants.LocalhostConfiguration;
import com.backsofangels.dockerremote.model.Container;
import com.backsofangels.dockerremote.utils.NetworkClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContainersInfoService {
    private Logger logger = LoggerFactory.getLogger(ContainersInfoService.class);
    private CloseableHttpClient client = NetworkClient.getNetworkClient().getClient();

    public List<Container> getRunningContainers(boolean allContainers) throws URISyntaxException, IOException {
        URI uri;

        if (allContainers) {
            uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(Endpoints.CONTAINER_LIST)
                    .addParameter("all", "true")
                    .build();
        } else {
            uri = new URIBuilder()
                    .setScheme(LocalhostConfiguration.LOCALHOST_SCHEME)
                    .setHost(LocalhostConfiguration.LOCALHOST_HOST)
                    .setPort(LocalhostConfiguration.LOCALHOST_PORT)
                    .setPath(Endpoints.CONTAINER_LIST)
                    .build();
        }

        HttpGet get = new HttpGet(uri);
        get.addHeader("Accept", "application/json");
        CloseableHttpResponse response = client.execute(get);

        try {
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            List<Container> containers = mapper.readValue(entity.getContent(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Container.class));
            return containers;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        } finally {
            response.close();
        }
    }

    
}
