package com.backsofangels.dockerremote.model;

public enum ContainerState {
    Exited("Exited");

    private String state;

    ContainerState(String state) {
        this.state = state;
    }

    public String getContainerState() {
        return this.state;
    }
}
