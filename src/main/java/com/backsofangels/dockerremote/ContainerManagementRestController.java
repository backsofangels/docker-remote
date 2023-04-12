package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.services.ContainersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management")
public class ContainerManagementRestController {
    @Autowired
    private ContainersManagementService containersManagementService;

    @PostMapping(value = "/start/{containerId}")
    public ResponseEntity<String> startContainer(@PathVariable String containerId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(containersManagementService.startContainer(containerId)));
    }

    @PostMapping(value = "/stop/{containerId}")
    public ResponseEntity<String> stopContainer(@PathVariable String containerId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(containersManagementService.stopContainer(containerId)));
    }

    @PostMapping(value = "/restart/{containerId}")
    public ResponseEntity<String> restartContainer(@PathVariable String containerId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(containersManagementService.restartContainer(containerId)));
    }

    @PostMapping(value = "/kill/{containerId}")
    public ResponseEntity<String> killContainer(@PathVariable String containerId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(containersManagementService.killContainer(containerId)));
    }
}
