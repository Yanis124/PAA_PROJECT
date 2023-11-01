
public class Ville {
    
    private String nom;  //le nom de la ville
    private boolean station; // true si elle contient une station 
    
    //constructeurs
    public Ville(String nom){
        this.nom=nom;
        this.station=false;
    }

    public Ville(String nom,boolean station){
        this(nom);
        this.station=station;
    }

    public boolean equals(Ville ville){    //comparer deux ville (on considere que deux ville sont les meme si elles sont le meme nom)
         if(this.nom.compareTo(ville.getNom())==0){
            return true;
         }
         return false;
    }

    //methodes

    //accesseurs 
    public String getNom(){
        return this.nom;
    }

    public boolean getStation(){
        return this.station;
    }

    //seteurs
    public void ajouterStation(){
        this.station=true;
    }

    public String toString(){

        return nom+" a "+(station ? 1:0)+" station";
    }
}