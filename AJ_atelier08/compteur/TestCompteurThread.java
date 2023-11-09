package compteur;

public class TestCompteurThread {

	public static void main(String[] args) {
		CompteurThread[] compteurs = {
				new CompteurThread("Bolt", 12),
				new CompteurThread("Jakson", 10),
				new CompteurThread("Robert", 15),
				new CompteurThread("Stéphanie", 10) };

		// Lancer les compteurs
		for(CompteurThread compteur : compteurs) {
			compteur.start();
		}

		for(CompteurThread compteur : compteurs) {
			// Attendre la fin de l'exécution de tous les compteurs
			//		pour attendre un thread t, utiliser t.join();
			try{
				compteur.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

		System.out.println("Le(la) gagnant(e) est " + CompteurThread.getGagnant().getNom());
	}

}
