package com.CityThrillsMorocco.agency.WebSocket;

import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.config.ObjectMapperConfig;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;




public class AgenceWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapperConfig objectMapperConfig;

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public AgenceWebSocketHandler(ObjectMapperConfig objectMapperConfig) {
        this.objectMapperConfig = objectMapperConfig;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString("agence");
        TextMessage message = new TextMessage(jsonMessage);
        session.sendMessage(message);
        sessions.add(session);
    }
    public void sendUpdateToClients(Agence agence) throws IOException {
        for (WebSocketSession session : sessions) {
            String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(agence);
            TextMessage message = new TextMessage(jsonMessage);
            session.sendMessage(message);
        }
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sendAgencyUpdates(session);
    }

    private void sendAgencyUpdates(WebSocketSession session) throws IOException {
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString("agence");
        session.sendMessage(new TextMessage(jsonMessage));
    }
}
