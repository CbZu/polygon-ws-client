package com.io.polygon.stock.listener;

import com.io.polygon.stock.event.MessageEvent;
import com.io.polygon.stock.service.PolygonActionWebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageEventListener {

    private PolygonActionWebSocketService polygonActionWebSocketService;

    @Async
    @EventListener
    public void onMessage(MessageEvent messageEvent) throws InterruptedException {
        polygonActionWebSocketService.saveMessage(messageEvent.getMessage());
    }
}
