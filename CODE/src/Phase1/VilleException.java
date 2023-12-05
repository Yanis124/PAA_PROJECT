package Phase1;

public class VilleException {

    public static class villeHasZoneRecharge extends Exception {   //si la ville a une zone de recharge on ne peut pas lui en rajouter une autre

        public villeHasZoneRecharge() {
            super("ville contient deja une zone de recharge");
        }
    }

    public static class villeHasNotZoneRecharge extends Exception { //si la ville n'a pas de zone de recharge on ne peut pas en retirer

        public villeHasNotZoneRecharge() {
            super("la ville n'a pas de zone de recharge");
        }
    }

    public static class villeVoisinesHasNotZoneRecharge extends Exception{  //si les voisins de la ville n'ont pas une zone de recharge on ne peut pas lui retirer une zone de recharge

        public villeVoisinesHasNotZoneRecharge(){
            super("les voisins de la ville n'ont pas de zone de recharge");
        }
    }
}
