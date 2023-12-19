package Phase1;

public class Route {
    
    private Ville villeDepart;
    private Ville villeArrive;

    public Route(Ville villeDepart, Ville villeArrive) {
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public Ville getVilleArrive() {
        return villeArrive;
    }

    public boolean equal(Route route){

        if(this.villeDepart.getNom().equals(route.getVilleDepart().getNom()) && this.villeArrive.getNom().equals(route.getVilleArrive().getNom())){
            
            return true;
        }

        else if(this.villeDepart.getNom().equals(route.getVilleArrive().getNom()) && this.villeArrive.getNom().equals(route.getVilleDepart().getNom())){
            return true;
        }

        return false;
    }
}
