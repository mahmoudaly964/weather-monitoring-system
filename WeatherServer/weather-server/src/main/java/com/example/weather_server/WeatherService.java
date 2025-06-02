package com.example.weather_server;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class WeatherService {
    private final ConcurrentHashMap<String, Map<String, String>> weatherData = new ConcurrentHashMap<>();

    public void updateWeather(String city, String temperature, String humidity) {
        Map<String, String> data = new ConcurrentHashMap<>();
        data.put("temperature", temperature);
        data.put("humidity", humidity);
        weatherData.put(city.toLowerCase(), data); // Store city name in lowercase
    }

    public Map<String, String> getWeather(String city) {
        return weatherData.getOrDefault(city.toLowerCase(), Map.of("message", "No data available"));
    }
}
