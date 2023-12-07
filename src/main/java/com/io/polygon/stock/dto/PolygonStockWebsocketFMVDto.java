package com.io.polygon.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PolygonStockWebsocketFMVDto {
    private String ev;
    private double val;
    private String sym;
    private long t;
}
