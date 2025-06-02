package com.example.weather_server;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TCPServer implements Runnable {
    private static final int PORT = 5000;
    private final WeatherService weatherService;

    public TCPServer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP Server is running on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new SensorHandler(clientSocket, weatherService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
