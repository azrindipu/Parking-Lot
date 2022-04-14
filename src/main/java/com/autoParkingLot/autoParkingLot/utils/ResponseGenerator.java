package com.autoParkingLot.autoParkingLot.utils;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseGenerator {
    private static Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);

    public static JSONObject generateResponse(Object response){
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, response);
        logger.info("azlog: response: "+responseBody.toJSONString());
        return responseBody;
    }
}
