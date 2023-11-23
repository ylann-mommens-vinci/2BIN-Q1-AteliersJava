package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BlacklistServiceImpl implements BlacklistService {
    public static Set<String> BlacklistedDomains;

    //initialistion avec static clinit
    static {
        //On récupère les donnés dans le fichier .properties et on les load dans prop
        try(FileInputStream file = new FileInputStream("blacklist.properties")) {
            Properties prop = new Properties();
            prop.load(file);

            //On sotck les domaines dans un Set
            BlacklistedDomains = Set.of(prop.getProperty("blacklistedDomains").split(";"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean check(Query query){
        return BlacklistedDomains.stream().noneMatch(domain -> query.getUrl().contains(domain));
    }
}
