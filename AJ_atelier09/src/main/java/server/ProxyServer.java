package server;

import domaine.Query;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    public void startServer(){
        while(true){
            System.out.println("Veuillez entrer l'url d'un site web (ex: https://www.google.com/): ");
            String url = scanner.nextLine();

            Query maQuery = new Query(url, Query.QueryMethod.GET);
            QueryHandler n = new QueryHandler(maQuery);

            n.start();
        }
    }
}
