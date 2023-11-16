package main;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        // Créer un client HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Spécifier l'URL de la requête GET
        String url = "https://duckduckgo.com/";

        // Créer une requête GET
        HttpGet httpGet = new HttpGet(url);

        try {
            // Exécuter la requête
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // Afficher le statut de la réponse
            System.out.println("Status Code: " + response.getCode() + " " + response.getReasonPhrase());

            // Lire le contenu HTML de la réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            // Afficher le contenu HTML
            System.out.println("HTML Content:\n" + content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
