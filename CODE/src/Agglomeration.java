import java.util.HashMap;
import java.util.ArrayList;


public class Agglomeration{

    private HashMap<Ville,ArrayList<Ville>> listVilles;  // on associe à chaque ville une liste qui va contenir les villes voisines 

    //constructeur
    public Agglomeration(){
        listVilles=new HashMap<Ville,ArrayList<Ville>>();
    }

    //methodes

    //ajouter une ville a l'agglomeration
    public void ajouterVille(Ville ville){

        for(Ville v : listVilles.keySet()){  //verifier que la ville qu'on veut rajouter n'est pas presente dans listVilles
            if(ville.equals(v)) return;
        }                
        listVilles.put(ville, new ArrayList<Ville>());
    }

    
    public void ajouterVilleVoisine(Ville villeDepart,Ville villeArrive){      //ajouter une arete entre deux villes 
        ArrayList<Ville> listVilleVoisinesDepart =listVilles.get(villeDepart);  //recuperer les voisins de la premiere ville
        ArrayList<Ville> listVilleVoisinesArrive=listVilles.get(villeArrive);  //recuperer les voisins de la deuxiéme ville

        listVilleVoisinesDepart.add(villeArrive);               //ajouter la villeArrive comme voisin de  villeDepart
        listVilleVoisinesArrive.add(villeDepart);              //ajouter la villeDepart comme voisin de villeArrive
    }

    //verifier si deux villes sont voisines
    public boolean estVoisin(Ville villeDepart,Ville villeArrive){
        boolean estVoisinDepart=false;
        boolean estVoisinArrive=false;

        ArrayList<Ville> listVilleVoisinesDepart =listVilles.get(villeDepart);  //recuperer les voisins de la premiere ville
        ArrayList<Ville> listVilleVoisinesArrive=listVilles.get(villeArrive);  //recuperer les voisins de la deuxiéme ville

        for(Ville ville : listVilleVoisinesDepart ){   // verivier si villeArrive est voisin de villeDepart
            if(ville.equals(villeArrive)){
                estVoisinDepart=true;
                break;
            }
        }

        for(Ville ville : listVilleVoisinesArrive ){   ///verifier si villeDepart est voisin de VilleArrive
            if(ville.equals(villeDepart)){
                estVoisinArrive=true;
                break;
            }
        }
        return  (estVoisinArrive && estVoisinDepart);
    }

    //voire le contenu de la listVilles
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(" la listes des villes et leurs voisins :  \n");
        for(Ville ville : listVilles.keySet()){
            sb.append(ville.toString());
            sb.append(" : [ ");
            ArrayList<Ville> listVilleVoisines=listVilles.get(ville);
            
            for(Ville villeVoisines : listVilleVoisines){
                sb.append(villeVoisines.toString()+" , ");
            }
            sb.append(" ] \n");
        }
        return sb.toString();
    }
}