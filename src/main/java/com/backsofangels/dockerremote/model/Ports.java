package com.backsofangels.dockerremote.model;

import com.backsofangels.dockerremote.deserializers.PortsDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PortsDeserializer.class)
public class Ports {
    private int privatePort;
    private int publicPort;
    public String type;

    public Ports() {
        privatePort = 8080;
        publicPort = 8080;
        type = "Tcp";
    }

    public Ports(int privatePort, int publicPort, String type) {
        this.privatePort = privatePort;
        this.publicPort = publicPort;
        this.type = type;
    }

    public int getPrivatePort() {
        return privatePort;
    }

    @JsonSetter(value = "PrivatePort")
    public void setPrivatePort(int privatePort) {
        this.privatePort = privatePort;
    }

    public int getPublicPort() {
        return publicPort;
    }

    @JsonSetter(value = "PublicPort")
    public void setPublicPort(int publicPort) {
        this.publicPort = publicPort;
    }

    public String getType() {
        return type;
    }

    @JsonSetter(value = "Type")
    public void setType(String type) {
        this.type = type;
    }
}


