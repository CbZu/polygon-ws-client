package com.io.polygon.stock.config;

import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import java.util.concurrent.CompletableFuture;

public class CustomWebSocketConnectionManager extends WebSocketConnectionManager {

    private final WebSocketClient client;
    private final WebSocketHandler webSocketHandler;
    @Nullable
    private WebSocketSession webSocketSession;
    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    public CustomWebSocketConnectionManager(WebSocketClient client, WebSocketHandler webSocketHandler, String uriTemplate) {
        super(client, webSocketHandler, uriTemplate);
        this.client = client;
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void openConnection() {
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Connecting to WebSocket at " + this.getUri());
        }

        CompletableFuture<WebSocketSession> future = this.client.execute(this.webSocketHandler, this.headers, this.getUri());
        future.whenComplete((result, ex) -> {
            if (result != null) {
                this.webSocketSession = result;
                this.webSocketSession.setTextMessageSizeLimit(1024 * 1024);
                this.logger.info("Successfully connected");
            } else if (ex != null) {
                this.logger.error("Failed to connect", ex);
            }

        });
    }

    @Override
    public void closeConnection() throws Exception {
        if (this.webSocketSession != null) {
            this.webSocketSession.close();
        }
    }

    public WebSocketSession getCurrentSession() {
        return webSocketSession;
    }
}
