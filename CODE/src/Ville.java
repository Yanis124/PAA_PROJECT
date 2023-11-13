
public class Ville {
	private String nom;
	private boolean zoneRecharge;

	public Ville(String nom) {
		this.nom = nom;
		zoneRecharge = false;
	}

	// vérifier que la ville contient une zone de recharge
	public boolean getZoneRecharge() {
		return zoneRecharge;
	}

	// ajouter ou retirer une zone de recharge
	private void setZoneRecharge(boolean zoneRecharge) {
		this.zoneRecharge = zoneRecharge;
	}

	public String getNom() {
		return nom;
	}

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

	// retirer une zone de recharge
	public void retirerZoneRecharge() throws VilleException.villeHasNotZoneRecharge {
		if (this.zoneRecharge == false) {
			throw new VilleException.villeHasNotZoneRecharge(); // si la ville ne contient pas de ville de recharge
		}
		setZoneRecharge(false);
	}

	public boolean equals(Ville v) {
		if (this.nom.equals(v.getNom())) {
			return true;
		}
		return false;
	}
}
