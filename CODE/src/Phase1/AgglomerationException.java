package Phase1;

public class AgglomerationException {

    public static class villeNotFoundException extends Exception { // une classe par excéption

        public villeNotFoundException() {
            super("ville introuvable ");
        }
    }

    public static class routeDuplicateException extends Exception {

        public routeDuplicateException() {
            super("route entre les deux villes existe deja");
        }
    }

    public static class routeMemeVilleException extends Exception {

        public routeMemeVilleException() {
            super("on ne peut pas créer une route vers la même ville");
        }
    }
}
