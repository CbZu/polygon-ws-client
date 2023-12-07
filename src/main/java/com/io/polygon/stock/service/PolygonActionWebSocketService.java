package com.io.polygon.stock.service;

import com.io.polygon.stock.dto.PolygonWebSocketMessage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface PolygonActionWebSocketService {

    void sendMessage(PolygonWebSocketMessage message) throws IOException, ExecutionException, InterruptedException;

}
