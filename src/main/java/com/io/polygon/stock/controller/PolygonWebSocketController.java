package com.io.polygon.stock.controller;

import com.io.polygon.stock.dto.PolygonAuthRequest;
import com.io.polygon.stock.dto.PolygonWebSocketMessage;
import com.io.polygon.stock.enums.PolygonWebSocketAction;
import com.io.polygon.stock.service.PolygonActionWebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/polygon/websocket")
@AllArgsConstructor
public class PolygonWebSocketController {

    private PolygonActionWebSocketService polygonActionWebSocketService;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody PolygonAuthRequest request) throws IOException, ExecutionException, InterruptedException {
        polygonActionWebSocketService.sendMessage(new PolygonWebSocketMessage(PolygonWebSocketAction.auth, request.getApiKey()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribe")
    public void subscribe(@RequestParam String params) throws IOException, ExecutionException, InterruptedException {
        polygonActionWebSocketService.sendMessage(new PolygonWebSocketMessage(PolygonWebSocketAction.subscribe, params));
    }

    @GetMapping("/unsubscribe")
    public void unsubscribe(@RequestParam String params) throws IOException, ExecutionException, InterruptedException {
        polygonActionWebSocketService.sendMessage(new PolygonWebSocketMessage(PolygonWebSocketAction.unsubscribe, params));
    }
}
