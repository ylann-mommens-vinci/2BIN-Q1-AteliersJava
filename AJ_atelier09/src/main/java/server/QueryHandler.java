package server;

import domaine.Query;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryHandler extends Thread{
    Query maQuery;

    public QueryHandler(Query maQuery){
        this.maQuery=maQuery;
    }

    // On utilise les threads pour pouvoir faire des requêtes réseaux sans bloqué le main
    @Override
    public void run() {
        // Créer un client HTTP
        try (CloseableHttpClient httpClient = HttpClients.createDefault();){
            // Exécuter la requête
            try(CloseableHttpResponse response = httpClient.execute(new HttpGet(maQuery.getUrl()));
            ) {
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

            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
