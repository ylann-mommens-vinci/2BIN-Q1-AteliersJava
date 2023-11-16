package server;

import domaine.Query;
import domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    public void startServer(){
        while(true){
            System.out.println("Veuillez entrer l'url d'un site web (ex: https://www.google.com/): ");
            String url = scanner.nextLine();

            // On instancie une query grâce a la factory et on lui set de valeurs
            Query maQuery = QueryFactory.getQuery();
            maQuery.setUrl(url);
            maQuery.setMethod(Query.QueryMethod.GET);
            QueryHandler n = new QueryHandler(maQuery);

            n.start();
        }
    }
}
