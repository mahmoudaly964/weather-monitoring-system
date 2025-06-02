package com.example.weather_server;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public Map<String, String> getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }
}
