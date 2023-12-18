package Phase2;

import Phase1.Agglomeration;
import Phase1.AgglomerationException;
import Phase1.Ville;
import Phase1.VilleException;
import java.util.ArrayList;
import java.util.Random;




public class AlgoApproximationNaif {

    public static int algoNaif(int k,Agglomeration agglomeration){  

        int i=0;
        Random random=new Random();
        ArrayList<Ville> listVillesAgglomeration=agglomeration.getListVillesAgglomeration(); //recuperer la liste des ville de l'agglomeration

        while (i<k) {
            
            int randomInt=random.nextInt(agglomeration.getNombreVilles()); 
            Ville ville=listVillesAgglomeration.get(randomInt); //selectionner une ville aléatoire
            try{
                ville=agglomeration.rechercherVilleParNom(ville.getNom()); //recuperer la ville de l'agglomeration
            }
            catch(AgglomerationException.villeNotFoundException e){
                System.err.println(e.getMessage());
            }

            if(ville.getZoneRecharge()){ 
                try{
                    agglomeration.retirerZoneRecharge(ville);
                }
                catch( VilleException.villeHasNotZoneRecharge | VilleException.ContrainteAccessibiliteException e){
                    System.err.println(e.getMessage());
                }
            }
            else{
                try{
                    ville.ajouterZoneRecharge();
                }
                catch(VilleException.villeHasZoneRecharge e){
                    System.err.println(e.getMessage());
                }
            }
            i++;
        }
        return agglomeration.getNombreVillesAvecZoneRecharge(); //score est le nombre de station dans l'agglomeration
    }

    public static int  algoMoinNaif(int k,Agglomeration agglomeration){

        int i=0;
        int scoreCourant=agglomeration.getNombreVillesAvecZoneRecharge(); //recuperer le score actuelle de l'agglomeration
        Random random=new Random();
        ArrayList<Ville> listVillesAgglomeration=agglomeration.getListVillesAgglomeration(); //recuperer la liste des ville de l'agglomeration
        

        while(i<k){
            int randomInt=random.nextInt(agglomeration.getNombreVilles()); 
            Ville ville=listVillesAgglomeration.get(randomInt); //selectionner une ville aléatoire

            try{
                ville=agglomeration.rechercherVilleParNom(ville.getNom()); //recuperer la ville de l'agglomeration
            }
            catch(AgglomerationException.villeNotFoundException e){
                System.err.println(e.getMessage());
            }

             if(ville.getZoneRecharge()){ 
                try{
                    agglomeration.retirerZoneRecharge(ville);
                }
                catch( VilleException.villeHasNotZoneRecharge | VilleException.ContrainteAccessibiliteException e){
                    System.err.println(e.getMessage());
                }
            }
            else{
                try{
                    ville.ajouterZoneRecharge();
                }
                catch(VilleException.villeHasZoneRecharge e){
                    System.err.println(e.getMessage());
                }
            }
            
            int score=agglomeration.getNombreVillesAvecZoneRecharge(); //recuperer le score actuelle de l'agglomeration
            
            if(score<scoreCourant){
                i=0;
                scoreCourant=score;
            }

            else{
                i++;
            }
        }

        return agglomeration.getNombreVillesAvecZoneRecharge();
    }
    
}
