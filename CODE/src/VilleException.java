public class VilleException {

    public static class villeHasZoneRecharge extends Exception {

        public villeHasZoneRecharge() {
            super("ville contient deja une zone de recharge");
        }
    }

    public static class villeHasNotZoneRecharge extends Exception {

        public villeHasNotZoneRecharge() {
            super("la ville n'a pas de zone de recharge");
        }
    }
}
