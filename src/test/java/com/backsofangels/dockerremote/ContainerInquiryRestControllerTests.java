package com.backsofangels.dockerremote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerInquiryRestControllerTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getRunningContainersTest() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/"
        + "containers/running", ArrayList.class)).isNotNull();
    }

    @Test
    void getAllContainersTest() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/" + "containers/all", ArrayList.class)).isNotNull();
    }
}
