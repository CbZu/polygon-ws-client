package com.io.polygon.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PolygonStockWebsocketTradesDto {
    private String ev;
    private String sym;
    private int x;
    private String i;
    private int z;
    private double p;
    private int s;
    private List<Integer> c;
    private long t;
    private int q;
}
