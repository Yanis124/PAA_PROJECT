package Phase1;
/**
 * Cette classe permet de gérer les Exceptions liées
 * à l'agglomeration
 * @author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 *
 */
public class AgglomerationException extends Exception {
	
	private static final long serialVersionUID = 6381921323930034609L;

	
	public AgglomerationException(String raison) {
		super(raison);
	}
	
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

    public static class RouteVersSoiMemeException extends Exception {

        public RouteVersSoiMemeException() {
            super("on ne peut pas créer une route vers la même ville");
        }
    }
    
    
}
