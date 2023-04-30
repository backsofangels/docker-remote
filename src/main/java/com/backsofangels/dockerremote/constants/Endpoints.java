package com.backsofangels.dockerremote.constants;

public class Endpoints {
    private static final String BASE = "/v1.41";

    // Container inquiry endpoints
    public static final String CONTAINER_LIST = BASE + "/containers/json";

    // Container management endpoints
    public static final String CONTAINER_START = BASE + "/containers/%s/start";
    public static final String CONTAINER_STOP =  BASE + "/containers/%s/stop";
    public static final String CONTAINER_RESTART =  BASE + "/containers/%s/restart";
    public static final String CONTAINER_KILL =  BASE + "/containers/%s/kill";

    // Helpers
    public static final String SYSTEM_PING = BASE + "/_ping";
}
