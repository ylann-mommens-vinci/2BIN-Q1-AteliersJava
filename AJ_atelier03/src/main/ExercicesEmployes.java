package main;

import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExercicesEmployes {

    public static void main(String[] args) {
        List<Employe> employes = new ArrayList<>();
        employes.add(new Employe(Genre.FEMME, 175, "Marie"));
        employes.add(new Employe(Genre.HOMME, 156, "Georges"));
        employes.add(new Employe(Genre.HOMME, 187, "Raphaël"));
        employes.add(new Employe(Genre.FEMME, 120, "Antoinette"));


        List<Employe> listDesHommes = employes
                .stream()                                   //on transforme en stream
                .filter(e -> e.getGenre() == Genre.HOMME)   //on filtre les genres pour gardé que les hommes
                .collect(Collectors.toList());              //on collecte les éléments et on les ajoutes a listeDesHommes

        //TODO done
        /**
        List<Employe> listDesHommes = new ArrayList<>();
        for (Employe e : employes) {
            if (e.getGenre() == Genre.HOMME) {
                listDesHommes.add(e);
            }
        }
        */

        System.out.println(listDesHommes);



    }
}
