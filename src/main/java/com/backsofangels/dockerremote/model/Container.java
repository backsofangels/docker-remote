package com.backsofangels.dockerremote.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {
    private String id;
    private ArrayList<String> names;
    private String image;
    private Long created;
    private ContainerState state;
    private ArrayList<Ports> ports;

    public Container() {
    }

    public Container(String id, ArrayList<String> names, String image, Long created, ContainerState state, ArrayList<Ports> ports) {
        this.id = id;
        this.names = names;
        this.image = image;
        this.created = created;
        this.state = state;
        this.ports = ports;
    }

    @JsonGetter(value = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter(value = "Names")
    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    @JsonGetter(value = "Image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonGetter(value = "Created")
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @JsonGetter(value = "State")
    public ContainerState getState() {
        return state;
    }

    public void setState(ContainerState state) {
        this.state = state;
    }

    @JsonGetter(value = "Ports")
    public ArrayList<Ports> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<Ports> ports) {
        this.ports = ports;
    }
}
