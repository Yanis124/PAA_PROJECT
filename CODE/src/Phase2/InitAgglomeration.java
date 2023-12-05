package Phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Phase1.Agglomeration;
import Phase1.AgglomerationException;
import Phase1.Ville;
import Phase1.VilleException;

public class InitAgglomeration {

    // read agglomeration from file
    public static void readAgglomerationFromFile(Agglomeration agg, String fichier) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("ville")) { // ajouter la ville a l'agglomération
                    String nomVille = readVilleFromFile(line);
                    agg.ajouterVille(new Ville(nomVille));

                } else if (line.startsWith("route")) {// ajouter la route entre les villes
                    ArrayList<String> listNomVilles = readRouteFromFile(line); // la liste des noms des villes
                    try {
                        Ville villeDepart = agg.rechercherVilleParNom(listNomVilles.get(0)); // trouver les villes dans
                                                                                             // l'agglomération
                        Ville villeArrive = agg.rechercherVilleParNom(listNomVilles.get(1));

                        try {
                            agg.ajouterRoute(villeDepart, villeArrive);
                        } catch (AgglomerationException.routeDuplicateException
                                | AgglomerationException.routeMemeVilleException e) {
                            System.err.println(e.getMessage());
                        }
                    } catch (AgglomerationException.villeNotFoundException e) {
                        System.err.println(e.getMessage());
                    }

                } else { // ajouter uen zone de recharge dans une ville
                    String nomVille = readZoneRechargeFromFile(line);
                    try {
                        Ville ville = agg.rechercherVilleParNom(nomVille);
                        try {
                            ville.ajouterZoneRecharge();
                        } catch (VilleException.villeHasZoneRecharge e) {
                            System.err.println(e.getMessage());
                        }
                    } catch (AgglomerationException.villeNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(agg.toString());
    }

    // retourner le nom de la ville
    private static String readVilleFromFile(String line) {

        String[] ville = line.split("");
        String nomVille = ville[6];
        return nomVille;
    }

    // retourner la liste des noms des villes qui sont reliés par une route
    private static ArrayList<String> readRouteFromFile(String line) {

        ArrayList<String> listNomVilles = new ArrayList<>();
        String[] ville = line.split("");
        String nomVilleDepart = ville[6];
        String nomVilleArrive = ville[8];

        listNomVilles.add(nomVilleDepart);
        listNomVilles.add(nomVilleArrive);

        return listNomVilles;
    }

    // retourner le nom de la ville qui a une zone de recharge
    private static String readZoneRechargeFromFile(String line) {

        String[] ville = line.split("");
        String nomVille = ville[9];
        return nomVille;
    }

}
