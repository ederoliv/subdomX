package io.github.ederoliv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Substitua "API_KEY" pela sua chave de API real
        String apiKey = "API_KEY";
        String domainName = "google.com";

        String url = "https://www.whoisxmlapi.com/whoisserver/WhoisService?apiKey=" + apiKey + "&domainName=" + domainName;

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method("GET")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                String body = response.body();
                System.out.println("Requisição bem-sucedida!");
                System.out.println("Resposta XML: " + body);
            } else {
                System.out.println("Falha na requisição: " + statusCode);
                System.out.println("Corpo da resposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro durante a requisição: " + e.getMessage());
        }
    }
}