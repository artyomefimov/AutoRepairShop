package com.artyomefimov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String timeFormatter(String timeAsString) {
        if (!timeAsString.matches("[0-9][0-9]:[0-9][0-9]:[0-9][0-9]"))
            timeAsString = timeAsString + ":00";
        return timeAsString;
    }

    public static Long resolveObjectById(ObjectMapper objectMapper, String customerJson, String jsonNodeName) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(customerJson);
        JsonNode entityId = jsonNode.get(jsonNodeName);
        if (!"undefined".equals(entityId.asText())) {
            return entityId.asLong();
        }
        return null;
    }
}
