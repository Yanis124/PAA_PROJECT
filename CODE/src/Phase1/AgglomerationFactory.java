package Phase1;
/** Permet toute la Gestion du déroulement du programme
 * @author Fayel Degguiche
 * @author Yanis Hammaci
 * @author Yanis Allain
 */
import java.util.Scanner;

public class AgglomerationFactory {
	
	/** Permet de créer le menu général */
	public void menuAgglomeration() {
		Agglomeration agg = fabriqueAgglomeration();
		
		creationVille(agg);
		menuCreationAgglomeration(agg);
		
		menuGestionAgglomeration(agg);
		
	}
	
	/**Permet de créer une agglomeration à partir du menu
	 * @param agg l'agglomeration que l'on souhaite créer
	 */
	public void menuCreationAgglomeration(Agglomeration agg) {
		boolean tourne = true;
		
		while(tourne) {
			Scanner lectureClavier = new Scanner(System.in) ;
			afficherMenuCreationAgglomeration();
			int choix = lectureClavier.nextInt();
			switch(choix) {
				case 1: creationRoute(agg);
						break;
				case 2: System.out.println("L'agglomération a été créer");
						
						tourne = false;
						break;	
				default:System.out.println("Mauvais choix, réessayer");
			}
		}
		agg.afficherVilleAvecZoneRecharge();
		agg.afficherListeAdjacence();
		
	}
	/**Permet de gérer une agglomeration à partir du Menu
	 * @param agg l'agglomeration que l'on souhaite gérer
	 */
	public void menuGestionAgglomeration(Agglomeration agg)  {
	
	
		boolean tourne = true;
		
		while(tourne) {
			Scanner lectureClavier = new Scanner(System.in) ;
			afficherMenuGestionAgglomeration();
			int choix = lectureClavier.nextInt();
			
			switch(choix) {
			
			
				case 1: String nomAAjouter = Saisie.lireChaine("Entrez le nom de la ville où vous voulez ajouter une Zone de Recharge:\n");
				
						Ville aAjouter = agg.rechercherVille(nomAAjouter);
						agg.ajouterZoneRecharge(aAjouter);

						break;
				case 2: 
						String nomARetirer = Saisie.lireChaine("Entrez le nom de la ville où vous voulez retirer une Zone de Recharge\n");
						Ville aRetirer = agg.rechercherVille(nomARetirer);
						agg.retirerZoneRecharge(aRetirer);
						//agg.retirerVilleAvecZoneRecharge(aRetirer);
						//agg.ajouterVilleSansZoneRecharge(aRetirer);
						break;
						
				case 3: System.out.println("L'agglomération est terminé");
						tourne = false;
						break;	
				default:System.out.println("Mauvais choix, réessayer");
				
			}
			agg.afficherVilleAvecZoneRecharge();
			agg.afficherListeAdjacence();
		
		}
	
	}
	/**Permet de créer les villes de l'agglomeration
	 * @param agg l'agglomeration dont on veux créer les villes
	 */
	public void creationVille(Agglomeration agg) {
		Scanner lectureClavier = new Scanner(System.in);
		System.out.println("Combien de ville contient l'agglomération ?");
		int nbVille = lectureClavier.nextInt();
		
		for(int i = 0; i<nbVille ; i++) {
			System.out.println("Entrez le nom de la ville n°"+(i+1)+":");
			Ville v = fabriqueVille();
			agg.ajouterVille(v);
		}
		agg.afficherVilleAvecZoneRecharge();
	}
	
	/**Permet de créer les routes de l'agglomeration
	 * @param agg l'agglomeration dont on veut créer les routes
	 */
	public void creationRoute(Agglomeration agg) {
		String nomPremiereVille = Saisie.lireChaine("Entrez le nom de la premiere Ville : \n");
		Ville premiereVille = agg.rechercherVille(nomPremiereVille);
		
		String nomDeuxiemeVille = Saisie.lireChaine("Entrez le nom de la deuxième Ville : \n");
		Ville deuxiemeVille = agg.rechercherVille(nomDeuxiemeVille);
		agg.ajouterRoute(premiereVille, deuxiemeVille);
		//System.out.println("La route entre " + premiereVille + " et " + deuxiemeVille + " a été créer");
		//System.out.println(premiereVille + " - " + deuxiemeVille);
	}
	
	/** Instancie une Agglomeration */
	public Agglomeration fabriqueAgglomeration() {
		return new Agglomeration();
	}
	
	
	/** Instancie une ville */
	public Ville fabriqueVille() {
		Scanner lectureClavier = new Scanner(System.in);
		//System.out.println("Entrez le nom de la ville : ") ;
		String nom = lectureClavier.nextLine();
		return new Ville(nom) ;
	}
	
	public void afficherMenuCreationAgglomeration() {
		System.out.println("1 : Ajouter une route");
		System.out.println("2 : Fin");
	}
	
	public void afficherMenuGestionAgglomeration() {
		System.out.println("\n1 : Ajouter une zone de recharge");
		System.out.println("2 : Retirer une zone de recharge");
		System.out.println("3 : Fin");
	}
	
	/*
	 * 
	 * // créer une agglomeration
	public static Agglomeration fabriqueAgglomeration() {
		return new Agglomeration();
	}

	// créer une ville( retourner la ville )
	public static Ville fabriqueVille(Agglomeration agg) {
		Scanner lectureClavier = new Scanner(System.in);
		String nom = lectureClavier.nextLine();
		try {
			Ville ville = agg.rechercherVilleParNom(nom); // si la ville existe on retourne la ville
			return ville;
		} catch (AgglomerationException.villeNotFoundException e) {
			return new Ville(nom); // sinon on creer une nouvelle ville
		}
	}
	public static void afficherMenuCreationAgglomeration() {
		System.out.println("1 : Ajouter une route");
		System.out.println("2 : Fin");
	}
	// menu pour créer une agglomeration
	public static void menuCreationAgglomeration(Agglomeration agg) {
		boolean tourne = true;
		Scanner lectureClavier = new Scanner(System.in);

		while (tourne) {

			
			try {
				int choix = lectureClavier.nextInt();
				afficherMenuCreationAgglomeration();
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
								| AgglomerationException.routeDuplicateException e) { // on envoie un message d'erruer
																						// si
							// la
							// route existe deja
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
		}
	}
	
	public static void afficherMenuGestionAgglomeration() {
		System.out.println("\n1 : Ajouter une zone de recharge");
		System.out.println("2 : Retirer une zone de recharge");
		System.out.println("3 : Fin");
	}
	// menu pour ajouter et supprimer des zones de recharge
	public static void menuGestionAgglomeration(Agglomeration agg) {

		boolean tourne = true;
		System.out.println("maintenant nous allons rajouter des zones de recharge dans les villes");
		Scanner lectureClavier = new Scanner(System.in);
		while (tourne) {

			afficherMenuGestionAgglomeration();
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

		menuCreationAgglomeration(agg); // ajouter les routes
		System.out.println(agg.toString());
		menuGestionAgglomeration(agg); // ajouter les zones de recharges
	}

	 */

}
