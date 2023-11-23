
import java.util.Scanner;
import java.util.InputMismatchException;

public class AgglomerationFactory {

	// créer une agglomeration
	public static Agglomeration fabriqueAgglomeration() {
		return new Agglomeration();
	}

	// créer une ville( retourner la ville )
	/*public static Ville fabriqueVille(Agglomeration agg) {
		Scanner lectureClavier = new Scanner(System.in);
		String nom = lectureClavier.nextLine();
		try {
			Ville ville = agg.rechercherVilleParNom(nom); // si la ville existe on retourne la ville
			return ville;
		} catch (AgglomerationException.villeNotFoundException e) {
			return new Ville(nom); // sinon on creer une nouvelle ville
		}
	}
*/
	public static Ville fabriqueVille(Agglomeration agg,String nom) {
		try {
			Ville ville = agg.rechercherVilleParNom(nom); // si la ville existe on retourne la ville
			return ville;
		} catch (AgglomerationException.villeNotFoundException e) {
			return new Ville(nom); // sinon on creer une nouvelle ville
		}
	}
	// menu pour créer une agglomeration
	public static void menuCreationAgglomeration(Agglomeration agg) {
		boolean tourne = true;
		Scanner lectureClavier = new Scanner(System.in);
		//TODO : Modifier pour ajouter les routes
		// Il faut donc vérifier si la ville existe déjà

		/*while (tourne) {

			System.out.println("1 : Ajouter une route");
			System.out.println("2 : Fin");
			try {
				int choix = lectureClavier.nextInt();

				switch (choix) {
					case 1:
						try {
							System.out.println("Entrez le nom de la première ville :"); // ajouter les deux villes
																						// l'agglomeration
							Ville premiereVille = fabriqueVille(agg);
							agg.ajouterVille(premiereVille);
							System.out.println("Entrez le nom de la deuxième ville :");
							Ville deuxiemeVille = fabriqueVille(agg);
							agg.ajouterVille(deuxiemeVille);

							agg.ajouterRoute(premiereVille, deuxiemeVille); // ajouter la route entre les deux villes
						} catch (AgglomerationException.routeMemeVilleException
								| AgglomerationException.routeDuplicateException e) { 
								// on envoie un message d'erreur si la route exite déjà		
							System.err.println(e.getLocalizedMessage());
						}
						break;

					case 2:
						System.out.println("L'agglomération a été créer");
						tourne = false;
						break;

					default:
						System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
						break;
				}
			} catch (InputMismatchException e) {
				System.err.println("veuillez entrez 1 ou 2");
			}
		}*/
	}

	// menu pour ajouter et supprimer des zones de recharge
	public static void menuGestionAgglomeration(Agglomeration agg) {

		boolean tourne = true;
		//System.out.println("maintenant nous allons rajouter des zones de recharge dans les villes");
		Scanner lectureClavier = new Scanner(System.in);
		while (tourne) {

			System.out.println("\n1 : Ajouter une zone de recharge");
			System.out.println("2 : Retirer une zone de recharge");
			System.out.println("3 : Fin");
			int choix = lectureClavier.nextInt();

			try {
				switch (choix) {

					case 1:
						String nomVilleAjouterZoneRecharge = Saisie
								.lireChaine("Entrez le nom de la ville où vous voulez ajouter une Zone de Recharge:\n");

						try {
							Ville villeAjouterZoneRecharge = agg.rechercherVilleParNom(nomVilleAjouterZoneRecharge);
							villeAjouterZoneRecharge.ajouterZoneRecharge();
						} catch (AgglomerationException.villeNotFoundException
								| VilleException.villeHasZoneRecharge e) {
							System.err.println(e.getMessage());
						}
						break;

					case 2:
						String nomVilleRetirerZoneRecharge = Saisie
								.lireChaine("Entrez le nom de la ville où vous voulez retirer une Zone de Recharge\n");

						try {
							Ville villeRetirerZoneRecharge = agg.rechercherVilleParNom(nomVilleRetirerZoneRecharge);
							agg.retirerZoneRecharge(villeRetirerZoneRecharge);
						} catch (AgglomerationException.villeNotFoundException
								| VilleException.villeHasNotZoneRecharge
								| VilleException.villeVoisinesHasNotZoneRecharge e) {
							System.err.println(e.getMessage());
						}
						break;

					case 3:
						System.out.println("L'agglomération est terminé");
						tourne = false;
						break;

					default:
						System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
						break;
				}

			} catch (InputMismatchException e) {
				System.err.println("veuillez entrez 1 ou 2");
			}
		}

		agg.afficherVilleAvecZoneRecharge(); // afficher les villes avec une zone de recharge
	}

	// afficher les deux menus
	public static void menuAgglomeration() {
		Agglomeration agg = fabriqueAgglomeration(); // créer l'agglomeration
		Scanner nbVilles =new Scanner(System.in);

		System.out.println("Entrez le nombre de villes de votre agglomération");
		int nb=nbVilles.nextInt();
		for(char i=0;i<nb;i++){
			char nomVille=(char) ('A'+i);
			Ville ville = fabriqueVille(agg,String.valueOf(nomVille));
			System.out.println(ville.getNom());
			agg.ajouterVille(ville);
		}
		menuCreationAgglomeration(agg); // ajouter les routes
		System.out.println(agg.toString());
		menuGestionAgglomeration(agg); // ajouter les zones de recharges
	}

}
