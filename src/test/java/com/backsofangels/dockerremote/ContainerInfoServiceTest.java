package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.model.Container;
import com.backsofangels.dockerremote.services.ContainersInfoService;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WireMockTest(httpPort = 2375)
public class ContainerInfoServiceTest {

    @Autowired
    ContainersInfoService infoService;

    @Test
    void healthcheckTest() {
        //Arrange
        givenThat(
                head(urlPathEqualTo("/v1.41/_ping"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withLogNormalRandomDelay(3000, 0.9))

        );

        //Act
        int healthCheckReturn = infoService.healthcheck();

        //Assert
        assertEquals(200, healthCheckReturn);
    }

    @Test
    void getRunningContainersTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json").willReturn(
                        aResponse()
                                .withBodyFile("containers-json-all-false-OK.json")
                                .withLogNormalRandomDelay(3000, 0.9)
                )
        );

        //Act
        List<Container> containers = infoService.getRunningContainers();

        //Assert
        assertEquals(1, containers.size());
    }

    @Test
    void getRunningContainers_BadParameterTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json").willReturn(
                        aResponse()
                                .withStatus(400)
                                .withBody("{\"message\": \"Something went wrong.\"}")
                                .withLogNormalRandomDelay(3000, 0.9)
                )
        );

        //Act
        List<Container> containers = infoService.getRunningContainers();

        //Assert
        assertEquals(0, containers.size());
    }

    @Test
    void getRunningContainers_ServerErrorTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json").willReturn(
                        aResponse()
                                .withStatus(500)
                                .withBody("{\"message\": \"Something went wrong.\"}")
                                .withLogNormalRandomDelay(3000, 0.9)
                )
        );

        //Act
        List<Container> containers = infoService.getRunningContainers();

        //Assert
        assertEquals(0, containers.size());
    }

    @Test
    void getAllContainersTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json?all=true")
                        .willReturn(
                            aResponse()
                                    .withBodyFile("containers-json-OK.json")
                                    .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        List<Container> containers = infoService.getAllContainers();

        //assert
        assertEquals(4, containers.size());
    }

    @Test
    void getAllContainers_BadParameterTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json?all=true").willReturn(
                        aResponse()
                                .withStatus(400)
                                .withBody("{\"message\": \"Something went wrong.\"}")
                                .withLogNormalRandomDelay(3000, 0.9)
                )
        );

        //Act
        List<Container> containers = infoService.getRunningContainers();

        //Assert
        assertEquals(0, containers.size());
    }

    @Test
    void getAllContainers_ServerErrorTest() {
        //Arrange
        givenThat(
                get("/v1.41/containers/json?all=true").willReturn(
                        aResponse()
                                .withStatus(500)
                                .withBody("{\"message\": \"Something went wrong.\"}")
                                .withLogNormalRandomDelay(3000, 0.9)
                )
        );

        //Act
        List<Container> containers = infoService.getRunningContainers();

        //Assert
        assertEquals(0, containers.size());
    }
}
