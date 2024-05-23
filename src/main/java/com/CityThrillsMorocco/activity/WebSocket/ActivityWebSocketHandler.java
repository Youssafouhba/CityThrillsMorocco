package com.CityThrillsMorocco.activity.WebSocket;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.config.ObjectMapperConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.CityThrillsMorocco.activity.Controller.ActivityController.activities$;

@Component
public class ActivityWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapperConfig objectMapperConfig;

    public static Long userid;

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public ActivityWebSocketHandler(ObjectMapperConfig objectMapperConfig) {
        this.objectMapperConfig = objectMapperConfig;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        userid = extractUserIdFromUri(session.getUri());
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(activities$);
        TextMessage message = new TextMessage(jsonMessage);
        session.sendMessage(message);
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        userid = extractUserIdFromUri(session.getUri());
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
        userid = extractUserIdFromUri(session.getUri());
        String jsonMessage = objectMapperConfig.getObjectMapper().writeValueAsString(activities$);
        session.sendMessage(new TextMessage(jsonMessage));
    }

    private Long extractUserIdFromUri(URI uri) {
        // Obtenez la chaîne de requête de l'URI
        String query = uri.getQuery();
        if (query != null) {
            // Divisez la chaîne de requête en paires clé-valeur
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                // Pour chaque paire clé-valeur, séparez la clé et la valeur
                String[] keyValue = param.split("=");
                // Vérifiez si la clé est "userId"
                if (keyValue.length == 2 && keyValue[0].equals("userId")) {
                    // Si c'est le cas, renvoyez la valeur (l'ID de l'utilisateur)
                    try {
                        return Long.parseLong(keyValue[1]);
                    } catch (NumberFormatException e) {
                        // Gérer les cas où l'ID de l'utilisateur n'est pas un nombre valide
                        e.printStackTrace();
                    }
                }
            }
        }
        // Si aucun ID d'utilisateur n'est trouvé dans l'URI, renvoyez null
        return null;
    }

}
