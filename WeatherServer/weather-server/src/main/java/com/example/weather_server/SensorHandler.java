package com.example.weather_server;

import java.io.*;
import java.net.Socket;

public class SensorHandler implements Runnable {
    private final Socket socket;
    private final WeatherService weatherService;

    public SensorHandler(Socket socket, WeatherService weatherService) {
        this.socket = socket;
        this.weatherService = weatherService;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input;
            while ((input = in.readLine()) != null) {
                String[] parts = input.split(",");
                if (parts.length == 3) { // Expected: City,Temperature,Humidity
                    String city = parts[0].trim();
                    String temperature = parts[1].trim();
                    String humidity = parts[2].trim();

                    weatherService.updateWeather(city, temperature, humidity);
                    out.println("Data received successfully");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
