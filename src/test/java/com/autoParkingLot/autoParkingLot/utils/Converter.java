package com.autoParkingLot.autoParkingLot.utils;

import com.autoParkingLot.autoParkingLot.entity.Floor;
import com.autoParkingLot.autoParkingLot.entity.Parking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class Converter {

    public static String convertDtoToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static Floor convertMockMVCToFloorEntity(MvcResult result) throws UnsupportedEncodingException {
        return new Gson().fromJson(generateJsonObject(result), Floor.class);
    }

    public static Parking convertMockMVCToParkingEntity(MvcResult result) throws UnsupportedEncodingException {
        return new Gson().fromJson(generateJsonObject(result), Parking.class);
    }

    public static JsonObject generateJsonObject(MvcResult mvcResult) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(mvcResult.getResponse().getContentAsString(), JsonObject.class);
        return jsonObject.getAsJsonObject("data");
    }
}
