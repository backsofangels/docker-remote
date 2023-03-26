package com.backsofangels.dockerremote.deserializers;

import com.backsofangels.dockerremote.model.Ports;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class PortsDeserializer extends StdDeserializer<Ports> {
    public PortsDeserializer() {
        this(null);
    }

    public PortsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Ports deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        int publicPort = (int) jsonNode.get("PublicPort").numberValue();
        int privatePort = (int) jsonNode.get("PrivatePort").numberValue();
        String type = jsonNode.get("Type").asText();

        return new Ports(publicPort, privatePort, type);
    }
}
