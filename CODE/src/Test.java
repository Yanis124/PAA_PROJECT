//exemple de test

public class Test {
    public static void main(String args[]){

        //exemple avec 3 villes
        Ville v1=new Ville("Paris");
        Ville v2=new Ville("Lyon");
        Ville v3=new Ville("Marseille");

        Agglomeration france=new Agglomeration();
        //ajouter les 3 villes
        france.ajouterVille(v1);
        france.ajouterVille(v2);
        france.ajouterVille(v3);

        //relier les villes
        france.ajouterVilleVoisine(v1, v2);   //Paris--Lyon
        france.ajouterVilleVoisine(v1, v3);   //paris--Marseille

        System.out.println(france.toString());
        
        System.out.println(france.estVoisin(v1, v2));   //verifier que v1 et v2 sont voisins
        System.out.println(france.estVoisin(v2, v3));

        //ajouter des stations 
        v1.ajouterStation();  // ajouter une station a paris
        v2.ajouterStation();  // ajouter une station a lyon

        System.out.println(france.toString());


        System.out.println();
    }
}
