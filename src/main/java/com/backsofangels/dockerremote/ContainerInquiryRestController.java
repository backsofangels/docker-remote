package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.containersinfo.ContainersInfoService;
import com.backsofangels.dockerremote.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("containers")
public class ContainerInquiryRestController {
    @Autowired
    private ContainersInfoService containersInfoService;

    @GetMapping(value = "running")
    public List<Container> getRunningContainers() throws URISyntaxException, IOException {
        return containersInfoService.getRunningContainers();
    }

    @GetMapping(value = "all")
    public String getAllContainers() {
        return null;
    }
}
