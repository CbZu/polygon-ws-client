package com.io.polygon.stock.dto;

import com.google.gson.Gson;
import com.io.polygon.stock.enums.PolygonWebSocketAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PolygonWebSocketMessage {

    private PolygonWebSocketAction action;
    private String params;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
