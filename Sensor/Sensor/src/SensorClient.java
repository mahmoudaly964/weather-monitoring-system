import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SensorClient {
    // private static final String SERVER_HOST = "127.0.0.1";
    private static final String SERVER_HOST = "172.20.10.3"; // Change to server IP if needed
    private static final int SERVER_PORT = 5000; // TCP server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Weather Server!");

            while (true) {
                // Get user input
                System.out.print("Enter City Name: ");
                String city = scanner.nextLine().trim();

                System.out.print("Enter Temperature: ");
                String temperature = scanner.nextLine().trim();

                System.out.print("Enter Humidity: ");
                String humidity = scanner.nextLine().trim();

                // Validate input
                if (city.isEmpty() || temperature.isEmpty() || humidity.isEmpty()) {
                    System.out.println("Error: All fields are required.");
                    continue;
                }

                // Create data packet
                String weatherData = city + "," + temperature + "," + humidity;

                // Send data to server
                out.println(weatherData);

                // Receive acknowledgment
                String response = in.readLine();
                if (response != null) {
                    System.out.println("Server: " + response);
                } else {
                    System.out.println("Server did not respond.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error: Could not connect to the server.");
            e.printStackTrace();
        }
    }
}
