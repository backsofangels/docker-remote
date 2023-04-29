package com.backsofangels.dockerremote;

import com.backsofangels.dockerremote.services.ContainersInfoService;
import com.backsofangels.dockerremote.model.Container;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("containers/")
public class ContainerInquiryRestController {
    @Autowired
    private ContainersInfoService containersInfoService;

    @Operation(summary = "Get all running containers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves all running container",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Container.class)) })
    })
    @GetMapping(value = "running")
    public List<Container> getRunningContainers() throws URISyntaxException, IOException {
        return containersInfoService.getRunningContainers();
    }

    @GetMapping(value = "all")
    public List<Container> getAllContainers() throws URISyntaxException, IOException {
        return containersInfoService.getAllContainers();
    }
}
