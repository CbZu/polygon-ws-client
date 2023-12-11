package com.io.polygon.stock.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.io.polygon.stock.config.CustomWebSocketConnectionManager;
import com.io.polygon.stock.dto.PolygonStockWebsocketAggregatesDto;
import com.io.polygon.stock.dto.PolygonWebSocketMessage;
import com.io.polygon.stock.entity.StockEntity;
import com.io.polygon.stock.enums.MarketDataChannels;
import com.io.polygon.stock.mapper.StockMapper;
import com.io.polygon.stock.repository.StockRepository;
import com.io.polygon.stock.service.PolygonActionWebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PolygonActionWebSocketServiceImpl implements PolygonActionWebSocketService {

    private final List<StockEntity> stockEntityList = new ArrayList<>();

    private Gson gson = new Gson();

    private StockMapper mapper;

    private CustomWebSocketConnectionManager webSocketConnectionManager;

    private StockRepository stockRepository;

    @Override
    public void sendMessage(PolygonWebSocketMessage message) throws IOException {
        WebSocketSession session = webSocketConnectionManager.getCurrentSession();
        session.sendMessage(new TextMessage(message.toString()));
    }

    @Override
    public void connectWebSocket() {
        webSocketConnectionManager.openConnection();
    }

    @Override
    public void disconnectWebSocket() throws Exception {
        webSocketConnectionManager.closeConnection();
    }

    @Override
    @Transactional
    public void saveMessage(String message) {
        String ev = gson.fromJson(message, JsonArray.class).get(0).getAsJsonObject().get("ev").getAsString();
        switch (MarketDataChannels.getByValue(ev)) {
            case AGGREGATES_PER_MINUTE:
            case AGGREGATES_PER_SECOND:
                stockEntityList.addAll(mapper.polygonStockWebsocketDtosToStockEntities(gson.fromJson(message,
                        new TypeToken<List<PolygonStockWebsocketAggregatesDto>>() {
                        }.getType())));
                break;
            case TRADES:
                break;
            case QUOTES:
                break;
            case FAIR_MARKET_VALUE:
                break;
        }
        if (stockEntityList.size() > 500) {
            stockRepository.saveAll(stockEntityList);
            stockEntityList.clear();
        }
    }

}
