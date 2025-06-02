import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherHttpClient {
    private static final String SERVER_URL = "http://172.20.10.3:8080/weather/"; // Change if needed

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Get city input
            System.out.print("Enter City Name (or type 'exit' to quit): ");
            String city = scanner.nextLine().trim();

            if (city.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            if (city.isEmpty()) {
                System.out.println("Error: City name cannot be empty.");
                continue;
            }

            // Send GET request
            fetchWeather(city);
        }

        scanner.close();
    }

    private static void fetchWeather(String city) {
        try {
            URL url = new URL(SERVER_URL + city);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print response
                System.out.println("Weather Data: " + response.toString());

            } else { // Handle error responses
                System.out.println("Error: Server returned " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Error: Could not connect to the server.");
            e.printStackTrace();
        }
    }
}
