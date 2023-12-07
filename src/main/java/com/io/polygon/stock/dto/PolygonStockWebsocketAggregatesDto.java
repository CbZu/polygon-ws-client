package com.io.polygon.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolygonStockWebsocketAggregatesDto {
    private String ev;
    private String sym;
    private int v;
    private int av;
    private double op;
    private double vw;
    private double o;
    private double c;
    private double h;
    private double l;
    private double a;
    private int z;
    private long s;
    private long e;
}
