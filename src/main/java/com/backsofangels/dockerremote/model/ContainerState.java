package com.backsofangels.dockerremote.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContainerState {
    EXITED("exited"),
    RUNNING("running");

    private String state;

    ContainerState(String state) {
        this.state = state;
    }

    public String getContainerState() {
        return this.state;
    }

    //The creator allows for all possible states to be parsed in whatever case they are,
    //given that the entry in the enum exists
    @JsonCreator
    public static ContainerState fromValue(String state) {
        for (ContainerState containerState: values()) {
            if (containerState.state.equalsIgnoreCase(state)) {
                return containerState;
            }
        }
        throw new IllegalArgumentException("Invalid value for ContainerState: " + state);
    }
}
