package com.io.polygon.stock.service.impl;

import com.io.polygon.stock.config.CustomWebSocketConnectionManager;
import com.io.polygon.stock.dto.PolygonWebSocketMessage;
import com.io.polygon.stock.handler.StockMessageHandler;
import com.io.polygon.stock.service.PolygonActionWebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class PolygonActionWebSocketServiceImpl implements PolygonActionWebSocketService {

    private CustomWebSocketConnectionManager webSocketConnectionManager;

    @Override
    public void sendMessage(PolygonWebSocketMessage message) throws IOException, ExecutionException, InterruptedException {
        WebSocketSession session = webSocketConnectionManager.getCurrentSession();
        session.sendMessage(new TextMessage(message.toString()));
    }
}
