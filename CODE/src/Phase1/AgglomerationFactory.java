package Phase1;


import Phase2.AlgoApproximationNaif;
import Phase2.InitAgglomeration;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;




public class AgglomerationFactory {

	// créer une agglomeration vide
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
		

		while (tourne) {

			
			try {
				
				afficherMenuCreationAgglomeration();
				Scanner lectureClavier = new Scanner(System.in);
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
						} catch (AgglomerationException.RouteVersSoiMemeException
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
	
	public static void afficherMenuResoudreManuellement() {
		System.out.println("\n1 : Ajouter une zone de recharge");
		System.out.println("2 : Retirer une zone de recharge");
		System.out.println("3 : Fin");
	}
	// menu pour ajouter et supprimer des zones de recharge
	public static void resoudreManuellement(Agglomeration agg) {

		boolean tourne = true;
		System.out.println("maintenant nous allons rajouter des zones de recharge dans les villes");
		Scanner lectureClavier = new Scanner(System.in);
		while (tourne) {

			afficherMenuResoudreManuellement();
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
								| VilleException.ContrainteAccessibiliteException e) {
							System.err.println(e.getMessage());
						}
						break;

					case 3:
						System.out.println("L'agglomération est terminé");
						tourne = false;
						break;

					default:
						System.out.println("Choix invalide. Veuillez entrer 1 ,2 ou bien 3.");
						break;
				}

			} catch (InputMismatchException e) {
				System.err.println("veuillez entrez 1 ou 2");
			}
		}


		agg.afficherVilleAvecZoneRecharge(); // afficher les villes avec une zone de recharge
	}

	// afficher le menus
	public static void menuAgglomeration() {
		Agglomeration agg = fabriqueAgglomeration(); // créer l'agglomeration
		
		String cheminFicherAgglomeration=lectureFichier();
		InitAgglomeration.readAgglomerationFromFile(agg, cheminFicherAgglomeration); // lire l'agglomeration depuis un fichier

		System.out.println(agg.toString());  //afficher l'agglomération a l'utilisateur

		try{
			Scanner lectureClavier = new Scanner(System.in);
			boolean tourne=true;
			
			while(tourne){
				afficherMenu(); //Afficher le menu
				int choix=lectureClavier.nextInt();

				switch (choix) {
					case 1: //resoudre manuellement 
						resoudreManuellement(agg);// ajouter les zones de recharges manuellement
						break;
					case 2:
						resoudreAutomatiquement(agg);  //ajouter les zones de recharge automatiquement
						break;
					case 3:
						String cheminFichierSauvgarderAgglomeration=ecritureFichier();  //sauvgarder le fichier
						InitAgglomeration.writeAgglomerationToFile(agg, cheminFichierSauvgarderAgglomeration);
						tourne=false;

						break;
					case 4:
						System.out.println("Merci d'avoir utiliser notre programme");
						tourne=false;
						break;
					default:
						System.out.println("Choix invalide. Veuillez entrer 1, 2, 3 ou 4.");
						break;
				}
			}
		}catch(InputMismatchException e){
			System.out.println("choix invalide veuillez inserer un nombre");
		}



		

		


	}

	//retourne le chemin du fichier contenant l'agglomération
	public static String lectureFichier(){

		System.out.println("inserer le chemin vers le fichier contenant l'agglomération");
		
		File file;
		String cheminFichier;

		do{
			Scanner lectureClavier = new Scanner(System.in);
			cheminFichier=lectureClavier.nextLine(); //récuperer le fichier contenant l'agglomeration

			file = new File(cheminFichier);
		}while (!file.exists()); //verifier si le fichier existe
        
		return cheminFichier;
	}

	//sauvgarder l'agglomération dans un fichier
	public static String ecritureFichier(){

		System.out.println("inserer le chemin vers le fichier où vous voulez sauvgarder l'agglomération");
		File file;
		String cheminFichier;

		do{
			Scanner lectureClavier = new Scanner(System.in);
			cheminFichier=lectureClavier.nextLine(); //récuperer le fichier contenant l'agglomeration

			file = new File(cheminFichier);
		}while (!file.exists()); //verifier si le fichier existe
        

		return cheminFichier;
	}

	//afficher le menu
	public static void afficherMenu(){
		System.out.println("Veuillez choisir une option");
		System.out.println("1 : Résoudre manuellement");
		System.out.println("2 : Résoudre automatiquement");
		System.out.println("3 : Sauvgarder l'agglomération");
		System.out.println("4 : Quitter");
	}

	//menu pour resoudre automatiquement
	public static void afficherMenuResoudreAutomatiquement(){
		System.out.println("Veuillez choisir une option");
		System.out.println("1 : Algorithme Naif");
		System.out.println("2 : Algorithme Moins Naif");
	}

	public static void resoudreAutomatiquement(Agglomeration agg){

		afficherMenuResoudreAutomatiquement(); //afficher le menu
		Scanner lectureClavier = new Scanner(System.in);

		int choix=lectureClavier.nextInt();

		

		try{
			switch (choix) {
				case 1:
					int scoreAlgoNaif=AlgoApproximationNaif.algoNaif(agg.getNombreVilles(), agg);
					System.out.println("avec le premier algo : "+scoreAlgoNaif);
					break;
				case 2:
					int scoreAlgoMoinNaif=AlgoApproximationNaif.algoMoinNaif(agg.getNombreVilles(),agg);
					System.out.println("avec le deuxieme algo : "+scoreAlgoMoinNaif);
					break;
				default:
					System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
					break;
			}
		}
		catch(InputMismatchException e){
			System.out.println("choix invalide veuillez inserer un nombre");
		}
	}

}