package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.services.ContainersInfoService;
import com.backsofangels.dockerremote.model.Container;
import com.backsofangels.dockerremote.services.ContainersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("containers")
public class ContainersRestController {
    @Autowired
    private ContainersInfoService containersInfoService;

    @Autowired
    private ContainersManagementService containersManagementService;

    @GetMapping(value = "running")
    public ResponseEntity<List<Container>> getRunningContainers() {
        List<Container> response = containersInfoService.getRunningContainers();

        if (response != null) {
            return ResponseEntity.ok(response);
        } else return ResponseEntity.internalServerError().body(null);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Container>> getAllContainers() {
        List<Container> response = containersInfoService.getAllContainers();

        if (response != null) {
            return ResponseEntity.ok(response);
        } else return ResponseEntity.internalServerError().body(null);
    }

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

    @RequestMapping(value = "/healthcheck", method = )
    public ResponseEntity<?> healthcheck() {
        return ResponseEntity.status(containersInfoService.healthcheck()).build();
    }
}
