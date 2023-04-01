package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.services.ContainersInfoService;
import com.backsofangels.dockerremote.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("containers")
public class ContainerInquiryRestController {
    @Autowired
    private ContainersInfoService containersInfoService;

    @GetMapping(value = "running")
    public List<Container> getRunningContainers() throws URISyntaxException, IOException {
        return containersInfoService.getRunningContainers(false);
    }

    @GetMapping(value = "all")
    public List<Container> getAllContainers() throws URISyntaxException, IOException {
        return containersInfoService.getRunningContainers(true);
    }
}
