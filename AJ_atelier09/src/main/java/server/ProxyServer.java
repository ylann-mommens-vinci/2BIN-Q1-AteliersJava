package server;

import blacklist.BlacklistServiceImpl;
import domaine.Query;
import domaine.QueryFactory;
import blacklist.BlacklistService;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    public void startServer(){
        BlacklistService blacklistService = new BlacklistServiceImpl();
        while(true){
            System.out.println("Veuillez entrer l'url d'un site web: ");
            String url = scanner.nextLine();

            // On instancie une query grâce a la factory et on lui set de valeurs
            Query maQuery = QueryFactory.getQuery();
            maQuery.setUrl(url);
            maQuery.setMethod(Query.QueryMethod.GET);
            if(!blacklistService.check(maQuery)){
                System.out.println("Blacklisted");
                return;
            }
            QueryHandler n = new QueryHandler(maQuery);

            n.start();
        }
    }
}
