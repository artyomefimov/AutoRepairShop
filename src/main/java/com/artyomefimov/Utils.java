package com.artyomefimov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static String timeFormatter(String timeAsString) {
        if (!timeAsString.matches("[0-9][0-9]:[0-9][0-9]:[0-9][0-9]"))
            timeAsString = timeAsString + ":00";
        return timeAsString;
    }

    public static Long resolveJsonNodeValue(ObjectMapper objectMapper, String json, String jsonNodeName) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode entityId = jsonNode.get(jsonNodeName);
        if (!"undefined".equals(entityId.asText())) {
            return entityId.asLong();
        }
        return null;
    }
}
