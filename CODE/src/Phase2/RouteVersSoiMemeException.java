package Phase2;

/** Cette classe permet de gérer l'exception du cas
 * où une ville serait relier avec elle même
 * 
 *@author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 *
 */
public class RouteVersSoiMemeException extends AgglomerationException{
	
	private static final long serialVersionUID = 3217189599226714623L;

	public RouteVersSoiMemeException() {
		super("Une ville ne peut être voisine avec elle même");
	}
}