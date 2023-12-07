package com.io.polygon.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PolygonStockWebsocketQuotesDto {
    private String ev;
    private String sym;
    private int bx;
    private double bp;
    private int bs;
    private int ax;
    private double ap;
    private int as;
    private int c;
    private List<Integer> i;
    private long t;
    private int q;
    private int z;
}
