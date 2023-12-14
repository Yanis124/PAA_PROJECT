package Phase2;

/** Représente une Agglomération 
 * @author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 */
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Vector;

public class Agglomeration {
	
	/** Représente les routes reliant les villes dans l'agglomération
	 * avec une Liste d'adjacence
	 */
	private Map<Ville, List<Ville>> routes ;
	
	/** Construit une agglomeration */
	public Agglomeration() {
		
		routes = new HashMap<Ville,List<Ville>>();
	}
	
	/** 
	 * Vérifie si les villes voisines d'une ville sont bien relier
	 * à une zone de recharge 
	 * @param villeAVerifier la ville dont on veut vérifier les voisins
	 * @return Un booléen qui indique si les voisins sont relier à une zone 
	 * de recharge ou non
	 */
	public boolean voisinsRelierAUneZoneRecharge(Ville villeAVerifier) {
		
		List<Ville> villeVoisineDeAVerifier = routes.get(villeAVerifier);
		for(Ville voisinsDeAVerifier : villeVoisineDeAVerifier){
			if(!estRelierAUneZoneRecharge(voisinsDeAVerifier)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Vérifie si une ville est bien relier à une Zone de recharge
	 * @param villeAVerifier la ville dont on veux vérifier si elle 
	 * est bien relier à une zone de recharge
	 * @return un booléen qui indique si la ville est bien relier ou non
	 */
	public boolean estRelierAUneZoneRecharge(Ville villeAVerifier) {
		
		List<Ville> villeVoisineDeAVerifier = routes.get(villeAVerifier);
		for(Ville voisinsDeAVerifier : villeVoisineDeAVerifier){
			if(voisinsDeAVerifier.getZoneRecharge()==true) {
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * Permet de rechercher une ville dans une Agglomeration
	 * @param nom le nom de la ville que l'on veut rechercher
	 * @return La ville que l'on recherche ou null s'il ne l'a pas trouver
	 */
	public Ville rechercherVille(String nom) {
		for(Ville v : routes.keySet()) {
			if(nom.equals(v.getNom())){
				return v;
			}
		}
		return null;
	}
	/**
	 * Permet de retirer une Zone de recharge à une Ville
	 * @param v la ville dont on veux retirer la zone de recharge
	 */
	public void retirerZoneRecharge(Ville v)throws AgglomerationException {
		v.setZoneRecharge(false);
		if(!voisinsRelierAUneZoneRecharge(v)) {
			
			v.setZoneRecharge(true);
			throw new ContrainteAccessibiliteException();
		}
	}
	
	/**
	 * Intègre une ville dans l'agglomération
	 * @param v la ville qu'on intègre
	 */
	public void ajouterVille(Ville v) {
			routes.put(v, new Vector<Ville>());
		
	}
	/** Créer une route entre deux ville de l'agglomeration
	 * 
	 * @param depart La ville de départ de la route
	 * @param arrivee La ville d'arrivée de la route
	 */
	public void ajouterRoute(Ville depart, Ville arrivee) {
		if(!depart.equals(arrivee)){
			routes.get(depart).add(arrivee);
			routes.get(arrivee).add(depart);
		}
	}
	
	/** Permet d'ajouter une Zone de recharge à une Ville
	 * @param v la ville dont on souhaite ajouter une zone de recharge
	 */
	public void ajouterZoneRecharge(Ville v) {
		v.setZoneRecharge(true);
	}
	
	@Override
	public String toString() {
		String s ="";
		for(Ville v : routes.keySet()) {
			s+= v + " " + routes.get(v) + "\n";
		}
		return s;
	}
	
	public void afficherListeAdjacence() {
		System.out.println(this);
	}

	public void afficherVilleAvecZoneRecharge() {
		System.out.print("Ville avec une Zone de Recharge : ");
		for(Ville v : routes.keySet()) {
			if(v.getZoneRecharge()) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
	}
	
	/*
	private HashMap<Ville, ArrayList<Ville>> listVilles;

	public Agglomeration() {

		listVilles = new HashMap<>();
	}

	// retourner la liste des villes avec une zone de recharge
	public ArrayList<Ville> getVilleAvecZoneRecharge() {
		ArrayList<Ville> listVillesAvecZoneRecharge = new ArrayList<>();

		for (Ville ville : listVilles.keySet()) {
			listVillesAvecZoneRecharge.add(ville);
		}
		return listVillesAvecZoneRecharge;
	}

	// ajouter une route entre deux ville
	public void ajouterRoute(Ville villeDepart, Ville villeArrive)
			throws AgglomerationException.routeDuplicateException, AgglomerationException.routeMemeVilleException {

		if (villeDepart.equals(villeArrive)) {
			throw new AgglomerationException.routeMemeVilleException();
		}

		ArrayList<Ville> listVilleVoisinesDepart = listVilles.get(villeDepart); // recuperer les voisins de la premiere
																				// ville
		ArrayList<Ville> listVilleVoisinesArrive = listVilles.get(villeArrive); // recuperer les voisins de la deuxiéme
																				// ville

		// verifier qu'il n'y a pas de route entre les deux villes
		for (Ville v : listVilleVoisinesDepart) {
			if (v.equals(villeArrive)) {
				throw new AgglomerationException.routeDuplicateException();
			}
		}

		for (Ville v : listVilleVoisinesArrive) {
			if (v.equals(villeDepart)) {
				throw new AgglomerationException.routeDuplicateException();
			}
		}

		listVilleVoisinesDepart.add(villeArrive); // ajouter la villeArrive comme voisin de villeDepart
		listVilleVoisinesArrive.add(villeDepart); // ajouter la villeDepart comme voisin de villeArrive
	}

	// retouner la ville avec le nom passé en paramétre
	public Ville rechercherVilleParNom(String nom) throws AgglomerationException.villeNotFoundException {
		for (Ville v : listVilles.keySet()) {

			if (v.getNom().equals(nom)) {
				return v;
			}
		}
		throw new AgglomerationException.villeNotFoundException(); // si la ville n'éxsite pas
	}

	// ajouter une ville si on l'a pas encore ajouter a l'agglomeration
	public void ajouterVille(Ville ville) {

		for (Ville v : listVilles.keySet()) {
			if (ville.equals(v)) {

				return;
			}
		}
		listVilles.put(ville, new ArrayList<Ville>());
	}

	public void retirerVille(Ville ville) {
		for (Ville v : listVilles.keySet()) {
			if (ville.equals(v)) {
				listVilles.remove(ville);
				return;
			}
		}
	}

	// retirer une zone de recharge
	public void retirerZoneRecharge(Ville ville)
			throws VilleException.villeHasNotZoneRecharge, VilleException.villeVoisinesHasNotZoneRecharge {

		if (ville.getZoneRecharge() == false) {
			throw new VilleException.villeHasNotZoneRecharge(); // si la ville ne contient pas de ville de recharge
		}
		// si les voisnis de la ville n'ont pas de station de recharge accéssible
		if (contrainteZoneRecharge(ville) == false) {
			throw new VilleException.villeVoisinesHasNotZoneRecharge(); // si supprimer la zone de recharge ne respecte
																		// pas la contrainte d'accecibilitée
		}
		// si toutes les contraintes sont respectées on retirer la zone de recharge
		ville.retirerZoneRecharge();
	}

	// afficher les villes avec une zone de recharge
	public void afficherVilleAvecZoneRecharge() {
		System.out.print("Villes avec une Zone de Recharge : ");
		for (Ville v : listVilles.keySet()) {
			if (v.getZoneRecharge()) {
				System.out.print(v.getNom() + ",");
			}
		}
		System.out.println("\n");
	}

	// verifier que la contraite d'accecibilité est respectée
	private boolean contrainteZoneRecharge(Ville ville) {

		boolean contrainteZoneRecharge = true;
		// retiter la zone de recharge de ville
		try {
			ville.retirerZoneRecharge();
		} catch (VilleException.villeHasNotZoneRecharge e) {
			System.err.println(e.getMessage());
		}
		// verifier que retirer la zone de recherche respecte la contrainte
		// d'accecibilité
		for (Ville v : listVilles.keySet()) {
			if (v.getZoneRecharge() == false) { // si la ville n'a pas de zone de recharge
				ArrayList<Ville> listVillesVoisines = listVilles.get(v); // on verifie si il y a une zone de recharge
																			// dans ses voisins
				if (villeVoisinesZoneRecharge(listVillesVoisines) == false) {
					contrainteZoneRecharge = false; // on a trouver une ville qui n'a pas de zone de recharge avec ses
													// voisines qui n'ont pas de zone de recharge
					break;
				}
			}
		}

		try {
			ville.ajouterZoneRecharge();
		} catch (VilleException.villeHasZoneRecharge e) {
			System.err.println(e.getMessage());
		}
		return contrainteZoneRecharge;
	}

	// verifier si les voisines d'une ville ont une zone de recharge
	private boolean villeVoisinesZoneRecharge(ArrayList<Ville> listVillesVoisines) {

		boolean zoneRecharge = false;

		for (Ville vVoisins : listVillesVoisines) {
			if (vVoisins.getZoneRecharge() == true) { // verifier qu'au moin une des villes voisins a une zone de //
														// recharge
				zoneRecharge = true;
				break;
			}
		}
		return zoneRecharge;

	}

	// afficher le grahe de l'agglomeration
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Ville v : listVilles.keySet()) {
			sb.append(v.getNom() + " : [");
			ArrayList<Ville> listVilleVoisines = listVilles.get(v);

			for (Ville villeVoisines : listVilleVoisines) {
				sb.append(villeVoisines.toString() + ",");
			}
			sb.append(" ] \n");
		}
		return sb.toString();
	}
	*/
	
}

