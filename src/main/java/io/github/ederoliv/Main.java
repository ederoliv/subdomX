package io.github.ederoliv;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;
import java.util.List;


public class Main {

    //at_WeQUOMyxeWSqHMskVkODCobqegrYc
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o dominio que deseja pesquisar: ");
        String dominio = scanner.nextLine();

        System.out.print("Digite a chave da API do XmlAPI (desenvolvi utilizando: at_WeQUOMyxeWSqHMskVkODCobqegrYc ) \n mas quando acabar as requisições ela estará inválida :( \n API KEY: ");
        String apiKey = scanner.nextLine();

        String url = "https://subdomains.whoisxmlapi.com/api/v1?apiKey=" + apiKey + "&domainName=" + dominio;

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {

                System.out.println("Requisição bem-sucedida!");
                System.out.println("Domínio: " + dominio);
                System.out.println("Subdomínios:");

                ObjectMapper mapper = new ObjectMapper();

                try {

                    Map<String, Object> responseMap = mapper.readValue(response.body(), Map.class);


                    if (responseMap.containsKey("result")) {
                        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");


                        if (result.containsKey("records") && result.get("records") != null) {
                            List<Map<String, Object>> records = (List<Map<String, Object>>) result.get("records");


                            for (Map<String, Object> record : records) {
                                String domain = (String) record.get("domain");
                                System.out.println(domain);
                            }
                        } else {
                            System.out.println("Eu estava esperando a lista 'records' da API, mas não o encontrei :( ");
                        }
                    } else {
                        System.out.println("Eu estava esperando o objeto 'records' da API, mas não o encontrei :( ");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao processar resposta: " + e.getMessage());
                }

            } else {
                System.out.println("Falha na requisição: " + statusCode);
                System.out.println("JSON da resposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro durante a requisição: " + e.getMessage());
        }
    }
}