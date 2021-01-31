package model;

/** Cette methode permet la gestion de la position et du score du joueur */
public class Avancer extends Thread{
	
	private boolean stopped; // booleen d'interuption
	private Etat modele; // récupération de l'etat du modele
	
	public Avancer(Etat state) {
		modele = state;
		stopped = false;
	}
	
	/** Cette methode permet de mettre la valeur de stopped à true */
	public void end() {
		stopped = true;
	}
	
	/** Implémentation de la méthode run du thread pour actualiser la position du joueur */
	@Override
	public void run() {
		while(!stopped) {
			if (modele.testPerdu()) {
				end();
			}
			modele.p.incrementPos(); // incrementation de la position du joueur
			try {
				Thread.sleep(200); // application d'un delai entre chaque actualisation de la position
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
