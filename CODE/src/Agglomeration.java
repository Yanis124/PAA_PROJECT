
import java.util.ArrayList;
import java.util.HashMap;

public class Agglomeration {

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

	// public boolean estDansVilleAvecZoneRecharge(Ville v) {
	// if (villeAvecZoneRecharge.contains(v)) {
	// return true;
	// }else {
	// return false;
	// }
	// }

	// public void retirerVilleAvecZoneRecharge(Ville v) {
	// retirerZoneRecharge(v);
	// villeAvecZoneRecharge.remove(v);
	// }

	// public void ajouterZoneRecharge(Ville v) {
	// if(peutAjouterZoneRecharge(v)) {
	// v.setZoneRecharge(true);
	// }
	// }

	// public boolean peutAjouterZoneRecharge(Ville v) {

	// if(v.getZoneRecharge()==false) {
	// return true;
	// }else {
	// return false;
	// }
	// }

	// public boolean peutRetirerZoneRecharge(Ville v) {
	// if(v.getZoneRecharge()==true) {
	// return false;
	// }else {
	// return true;
	// }
	// }

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

}
