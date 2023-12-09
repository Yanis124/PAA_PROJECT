package Phase1;
/** 
 * Représente une ville dans l'agglomération 
 * @author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 */
public class Ville implements Comparable<Ville> {
	/** Le nom de la ville */
	private String nom;
	/** Indique si une ville possède ou non une Zone de Recharge */
	private boolean zoneRecharge;
	
	/** Construit une ville à partir de son nom
	 *  Attribut une zone de recharge à la ville
	 * 
	 * @param nom le nom de la ville
	 */
	public Ville(String nom) {
		this.nom = nom;
		zoneRecharge = true;
		
	}
	/** Permet d'obtenir le nom de la ville
	 * 
	 * @return le nom de la ville
	 */
	public String getNom() {
		return nom;
	}
	/** Permet de savoir si une ville possède une zone de recharge ou non
	 * 
	 * @return un booléen qui renseigne si la ville possède ou non une zone de recharge
	 */
	public boolean getZoneRecharge() {
		return zoneRecharge;
	}
	/** Permet d'attribuer ou de retirer une zone de recharge à une ville 
	 * 
	 * @param zoneRecharge la zone de recharge à retirer ou à ajouter
	 */
	public void setZoneRecharge(boolean zoneRecharge) {
		this.zoneRecharge = zoneRecharge;
	}
	
	
	@Override
	public String toString() {
		return  nom + " (" + zoneRecharge + ") ";
	}
	@Override
	public boolean equals(Object o) {
		if((o==null) || (!(o instanceof Ville) )) {
			return false ;
		}if(o== this) {
			return true ;
		}
		Ville v = (Ville)o;
		if(nom.equals(v.getNom())) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int compareTo(Ville v) {
		if(nom.equals(v.getNom())) {
			return 0;
		}
		
		if(nom.compareTo(v.getNom())>0) {
			return 1;
		}else {
			return -1;
		}
	}
	
	/*
	 * 
	public String toString() {
		return nom + "(" + (zoneRecharge ? 1 : 0) + ")";
	}

	// ajouter une zone de recharge
	public void ajouterZoneRecharge() throws VilleException.villeHasZoneRecharge {
		if (this.zoneRecharge == true) {
			throw new VilleException.villeHasZoneRecharge(); // si la ville contient deja une zone de recharge
		}
		setZoneRecharge(true);
	}

	//retirer une zone de recharge
	public void retirerZoneRecharge() throws VilleException.villeHasNotZoneRecharge{
		if(this.zoneRecharge==false){
			throw new VilleException.villeHasNotZoneRecharge();
		}
		setZoneRecharge(false);
	}


	public boolean equals(Ville v) {
		if (this.nom.equals(v.getNom())) {
			return true;
		}
		return false;
	}

	 */
	
	}

