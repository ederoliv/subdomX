package io.github.ederoliv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Main {

    //at_WeQUOMyxeWSqHMskVkODCobqegrYc uniftec.com.br
    public static void main(String[] args) throws IOException, InterruptedException {
        // Replace "API_KEY" with your actual Whois XML API key
        String apiKey = "at_WeQUOMyxeWSqHMskVkODCobqegrYc";
        String domainName = "uniftec.com.br";

        String url = "https://subdomains.whoisxmlapi.com/api/v1?apiKey=" + apiKey + "&domainName=" + domainName;

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
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