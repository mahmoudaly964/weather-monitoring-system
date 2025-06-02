# Weather Monitoring System

A distributed system for collecting, storing, and retrieving weather data across multiple cities.

## System Architecture

This project consists of three main components:

1. **Weather Server** - Central server that stores weather data and provides access through REST API and TCP
2. **Sensor Client** - Simulates IoT sensors sending weather data to the server
3. **Weather HTTP Client** - Retrieves weather information through the REST API

## Components

### Weather Server

The server component is a Spring Boot application that:

- Exposes REST endpoints for retrieving weather data
- Runs a TCP server for receiving sensor data
- Stores weather information in memory using concurrent collections

#### Key Files:
- `WeatherServerApplication.java` - Main application entry point
- `WeatherService.java` - Service for storing and retrieving weather data
- `WeatherController.java` - REST controller exposing HTTP endpoints
- `TCPServer.java` - TCP server implementation
- `SensorHandler.java` - Handles incoming sensor connections

### Sensor Client

A Java application that simulates weather sensors sending data to the server:

- Connects to the server via TCP socket
- Allows user to enter city name, temperature, and humidity
- Sends the data to the server and receives acknowledgment

#### Key Files:
- `SensorClient.java` - Client implementation for sending sensor data

### Weather HTTP Client

A Java application that allows users to retrieve weather data:

- Connects to the server via HTTP
- Retrieves weather information for a specified city
- Displays the data to the user

#### Key Files:
- `WeatherHttpClient.java` - Client implementation for retrieving weather data

## How to Run

### Weather Server

1. Navigate to the server directory:
    ```sh
    cd "weather monitoring system/WeatherServer/weather-server"
    ```
2. Build and run the Spring Boot application:
    ```sh
    ./mvnw spring-boot:run
    ```
    The server will start on port 8080 for HTTP requests and port 5000 for TCP connections.

### Sensor Client

1. Navigate to the sensor directory:
    ```sh
    cd "weather monitoring system/Sensor/Sensor"
    ```
2. Compile and run the application:
    ```sh
    javac src/SensorClient.java -d bin
    java -cp bin SensorClient
    ```
3. Enter the requested information:
    - City name (e.g., "London")
    - Temperature (e.g., "22.5")
    - Humidity (e.g., "65")

### Weather HTTP Client

1. Navigate to the HTTP client directory:
    ```sh
    cd "weather monitoring system/HttpClient/WeatherHttpClient"
    ```
2. Compile and run the application:
    ```sh
    javac src/WeatherHttpClient.java -d bin
    java -cp bin WeatherHttpClient
    ```
3. Enter the name of the city to get weather information.

## Configuration

The default configuration uses IP address 172.20.10.3 for the server. To use a different address:

1. Update the `SERVER_HOST` constant in `SensorClient.java`
2. Update the `SERVER_URL` constant in `WeatherHttpClient.java`

## Technical Details

- **Server**: Spring Boot 3.4.4, Java 17
- **Clients**: Java SE
- **Communication**: REST API (HTTP) and TCP sockets
- **Data Format**: JSON for HTTP, comma-separated values for TCP

## Data Flow

1. Sensor Client sends weather data (city, temperature, humidity) to the server via TCP
2. Server stores the data in memory using a thread-safe ConcurrentHashMap
3. HTTP Client requests weather data for a specific city via REST API
4. Server retrieves the requested data and returns it as JSON

## License

This project is created for educational purposes.
