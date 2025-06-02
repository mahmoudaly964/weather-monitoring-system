package com.example.weather_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WeatherServerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WeatherServerApplication.class, args);
		TCPServer tcpServer = context.getBean(TCPServer.class);
		new Thread(tcpServer).start(); // Run TCP server in a separate thread
	}
}
