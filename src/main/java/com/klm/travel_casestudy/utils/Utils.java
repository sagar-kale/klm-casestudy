package com.klm.travel_casestudy.utils;

import com.google.gson.Gson;
import com.klm.travel_casestudy.domain.Airport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class Utils {
    @Autowired
    private Gson gson;

    public <T> T convertTOEntity(Map map, Class<T> resultType) {
        if (resultType.getCanonicalName().equalsIgnoreCase(Airport.class.getCanonicalName())) {
            String embedded = gson.toJson(map.get("_embedded"));
            return gson.fromJson(embedded, resultType);

        } else return gson.fromJson((String) map.get("airport"), resultType);
    }
}
