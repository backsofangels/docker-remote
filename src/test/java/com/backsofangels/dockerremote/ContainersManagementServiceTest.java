package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.services.ContainersManagementService;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WireMockTest(httpPort = 2375)
public class ContainersManagementServiceTest {
    @Autowired
    ContainersManagementService containersManagementService;

    @Test
    void startContainerTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/start")
                        .willReturn(
                                aResponse().withStatus(204)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.startContainer(containerUUID);

        //Assert
        assertEquals(204, startContainerResult);
    }

    @Test
    void startContainer_AlreadyStartedTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/start")
                        .willReturn(
                                aResponse().withStatus(304)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.startContainer(containerUUID);

        //Assert
        assertEquals(304, startContainerResult);
    }

    @Test
    void startContainerTest_ContainerNotFound() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/start")
                        .willReturn(
                                aResponse()
                                        .withStatus(404)
                                        .withBody("{\"message\": \"No such container: " + containerUUID + "\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.startContainer(containerUUID);

        //Assert
        assertEquals(404, startContainerResult);
    }

    @Test
    void startContainerTest_ServerError() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/start")
                        .willReturn(
                                aResponse()
                                        .withStatus(500)
                                        .withBody("{\"message\": \"Something went wrong.\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.startContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void startContainerTest_NullContainerId() {
        //Arrange

        //Act
        int startContainerResult = containersManagementService.startContainer(null);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void stopContainerTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/stop")
                        .willReturn(
                                aResponse().withStatus(204)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.stopContainer(containerUUID);

        //Assert
        assertEquals(204, startContainerResult);
    }

    @Test
    void stopContainer_NullContainerIdTest() {
        //Arrange
        String containerUUID = null;

        //Act
        int startContainerResult = containersManagementService.stopContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void stopContainer_AlreadyStoppedTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/stop")
                        .willReturn(
                                aResponse().withStatus(304)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.stopContainer(containerUUID);

        //Assert
        assertEquals(304, startContainerResult);
    }

    @Test
    void stopContainer_ContainerNotFoundTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/stop")
                        .willReturn(
                                aResponse()
                                        .withStatus(404)
                                        .withBody("{\"message\": \"No such container: " + containerUUID + "\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.stopContainer(containerUUID);

        //Assert
        assertEquals(404, startContainerResult);
    }

    @Test
    void stopContainer_ServerErrorTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/stop")
                        .willReturn(
                                aResponse()
                                        .withStatus(500)
                                        .withBody("{\"message\": \"Something went wrong.\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.stopContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void restartContainerTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/restart")
                        .willReturn(
                                aResponse().withStatus(204)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.restartContainer(containerUUID);

        //Assert
        assertEquals(204, startContainerResult);
    }

    @Test
    void restartContainer_NullContainerIdTest() {
        //Arrange
        String containerUUID = null;

        //Act
        int startContainerResult = containersManagementService.restartContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void restartContainer_ContainerNotFoundTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/restart")
                        .willReturn(
                                aResponse()
                                        .withStatus(404)
                                        .withBody("{\"message\": \"No such container: " + containerUUID + "\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.restartContainer(containerUUID);

        //Assert
        assertEquals(404, startContainerResult);
    }

    @Test
    void restartContainer_ServerErrorTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/restart")
                        .willReturn(
                                aResponse()
                                        .withStatus(500)
                                        .withBody("{\"message\": \"Something went wrong.\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.restartContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void killContainerTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/kill")
                        .willReturn(
                                aResponse().withStatus(204)
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.killContainer(containerUUID);

        //Assert
        assertEquals(204, startContainerResult);
    }

    @Test
    void killContainer_ContainerNotFoundTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/kill")
                        .willReturn(
                                aResponse()
                                        .withStatus(404)
                                        .withBody("{\"message\": \"No such container: " + containerUUID + "\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.killContainer(containerUUID);

        //Assert
        assertEquals(404, startContainerResult);
    }

    @Test
    void killContainer_ServerErrorTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/kill")
                        .willReturn(
                                aResponse()
                                        .withStatus(500)
                                        .withBody("{\"message\": \"Something went wrong.\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.killContainer(containerUUID);

        //Assert
        assertEquals(500, startContainerResult);
    }

    @Test
    void killContainer_ContainerNotRunningTest() {
        //Arrange
        String containerUUID = UUID.randomUUID().toString();

        givenThat(
                post("/v1.41/containers/" + containerUUID + "/kill")
                        .willReturn(
                                aResponse()
                                        .withStatus(409)
                                        .withBody("{\"message\": \"Container" + containerUUID + "is not running\"}")
                                        .withLogNormalRandomDelay(3000, 0.9)
                        )
        );

        //Act
        int startContainerResult = containersManagementService.killContainer(containerUUID);

        //Assert
        assertEquals(409, startContainerResult);
    }
}
