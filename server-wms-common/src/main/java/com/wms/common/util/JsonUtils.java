package com.wms.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.common.constant.SymbolConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by ThomasYu on 2019-08-05
 */
@Slf4j
public class JsonUtils {

    public static ObjectMapper json;

    static {
        json = new ObjectMapper();
        json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        json.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String write(Object obj) {
        String s = SymbolConstant.NONE;
        try {
            s = json.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("", e);
        }
        return s;
    }

    public static <T> T read(String s, Class<T> clazz) {
        T t = null;
        try {
            t = json.readValue(s, clazz);
        } catch (IOException e) {
            log.error("", e);
        }
        return t;
    }

}
