package client;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;

public class ConsoleClient {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Välj ett alternativ:");
            System.out.println("1. Visa alla Items");
            System.out.println("2. Skapa nytt Item");
            System.out.println("3. Uppdatera ett Item");
            System.out.println("4. Ta bort ett Item");
            System.out.println("0. Avsluta");

            int action = scanner.nextInt();
            scanner.nextLine(); // För att hantera radslutet efter nummerinmatning

            switch (action) {
                case 1: // Visa alla Items
                    HttpRequest getRequest = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/items/all"))
                            .GET()
                            .build();

                    HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Svar från servern: " + getResponse.body());
                    break;

                case 2: // Skapa nytt Item
                    System.out.println("Skriv in namnet på Item:");
                    String itemName = scanner.nextLine();

                    HttpRequest postRequest = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/items/create"))
                            .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + itemName + "\"}")) // Förutsätter att servern förväntar sig ett JSON-objekt
                            .header("Content-Type", "application/json")
                            .build();

                    HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Svar från servern: " + postResponse.body());
                    break;

                case 3: // Uppdatera ett Item
                    System.out.println("Ange ID för Item att uppdatera:");
                    String updateId = scanner.nextLine();
                    System.out.println("Ange nytt namn för Item:");
                    String updateName = scanner.nextLine();

                    HttpRequest updateRequest = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/items/" + updateId))
                            .PUT(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + updateName + "\"}")) // Förutsätter att servern förväntar sig ett JSON-objekt
                            .header("Content-Type", "application/json")
                            .build();

                    HttpResponse<String> updateResponse = client.send(updateRequest, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Svar från servern: " + updateResponse.body());
                    break;

                case 4: // Ta bort ett Item
                    System.out.println("Ange ID för Item att ta bort:");
                    String deleteId = scanner.nextLine();

                    HttpRequest deleteRequest = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/items/" + deleteId))
                            .DELETE()
                            .build();

                    HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Svar från servern: " + deleteResponse.body());
                    break;

                case 0: // Avsluta programmet
                    System.out.println("Avslutar programmet...");
                    return; // Använd return för att avsluta main-metoden på ett snyggt sätt

                default:
                    System.out.println("Ogiltigt alternativ!");
            }
        }
    }
}
