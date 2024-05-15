package com.CityThrillsMorocco.WebSocket;

import com.CityThrillsMorocco.activity.Model.Activity;

import com.CityThrillsMorocco.config.ObjectMapperConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.CityThrillsMorocco.activity.Controller.ActivityController.activities;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapperConfig objectMapperConfig;

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public WebSocketHandler(ObjectMapperConfig objectMapperConfig) {
        this.objectMapperConfig = objectMapperConfig;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(activities);
        TextMessage message = new TextMessage(jsonMessage);
        session.sendMessage(message);
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sendActivityUpdates(session);
    }

    public void sendUpdateToClients(List<Activity> activities) throws IOException {
        for (WebSocketSession session : sessions) {
            String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(activities);
            TextMessage message = new TextMessage(jsonMessage);
            session.sendMessage(message);
        }
    }

    private void sendActivityUpdates(WebSocketSession session) throws IOException {
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(activities);
        session.sendMessage(new TextMessage(jsonMessage));
    }
}