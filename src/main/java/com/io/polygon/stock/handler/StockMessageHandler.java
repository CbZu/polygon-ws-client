package com.io.polygon.stock.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.io.polygon.stock.dto.PolygonStockWebsocketAggregatesDto;
import com.io.polygon.stock.entity.StockEntity;
import com.io.polygon.stock.enums.MarketDataChannels;
import com.io.polygon.stock.mapper.StockMapper;
import com.io.polygon.stock.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class StockMessageHandler extends TextWebSocketHandler {

    private Gson gson = new Gson();

    private StockMapper mapper;

    private StockRepository stockRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.debug("Opened new session in instance " + this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        log.info(payload);
        String ev = gson.fromJson(payload, JsonArray.class).get(0).getAsJsonObject().get("ev").getAsString();
        List<StockEntity> stockEntityList = null;
        switch (MarketDataChannels.getByValue(ev)) {
            case AGGREGATES_PER_MINUTE:
            case AGGREGATES_PER_SECOND:
                stockEntityList = mapper.polygonStockWebsocketDtosToStockEntities(gson.fromJson(message.getPayload(),
                        new TypeToken<List<PolygonStockWebsocketAggregatesDto>>() {}.getType()));
                break;
            case TRADES:
                break;
            case QUOTES:
                break;
            case FAIR_MARKET_VALUE:
                break;
        }
        if (stockEntityList != null) {
            stockRepository.saveAll(stockEntityList);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        session.close(CloseStatus.SERVER_ERROR);
    }

}
