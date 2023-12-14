package Phase2;

/** Cette classe permet de gérer l'exception de la 
 * contrainte d'accessibilité
 * @author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 *
 */
public class ContrainteAccessibiliteException extends AgglomerationException{
	
	
	private static final long serialVersionUID = -7216021069771571491L;

	public ContrainteAccessibiliteException() {
		super("Retirer cette ville enfreint la contrainte d'accessibilité");
	}
}
