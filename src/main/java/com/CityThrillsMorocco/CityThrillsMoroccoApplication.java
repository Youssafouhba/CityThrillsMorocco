package com.CityThrillsMorocco;

import com.CityThrillsMorocco.activity.WebSocket.ActivityWebSocketHandler;
import com.CityThrillsMorocco.agency.WebSocket.AgenceWebSocketHandler;
import com.CityThrillsMorocco.config.ObjectMapperConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@Log4j2
@EnableWebSocket
@EnableScheduling
public class CityThrillsMoroccoApplication  implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/stocks").setAllowedOrigins("*");
		registry.addHandler(agenceWebSocketHandler(), "/agence").setAllowedOrigins("*");
		registry.addHandler(activityWebSocketHandler(), "/activity").setAllowedOrigins("*");
	}
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10); // Set the pool size as needed
		return scheduler;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public WebSocketHandler webSocketHandler(){ return new com.CityThrillsMorocco.WebSocket.WebSocketHandler(new ObjectMapperConfig()); }
	@Bean
	public AgenceWebSocketHandler agenceWebSocketHandler(){ return new com.CityThrillsMorocco.agency.WebSocket.AgenceWebSocketHandler(new ObjectMapperConfig()); }
	@Bean
	public ActivityWebSocketHandler activityWebSocketHandler(){ return new com.CityThrillsMorocco.activity.WebSocket.ActivityWebSocketHandler(new ObjectMapperConfig()); }
	public static void main(String[] args) {
		SpringApplication.run(CityThrillsMoroccoApplication.class, args);

	}
}