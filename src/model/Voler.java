package model;

/** Cette classe est en charge du vol de l'anneau */
public class Voler extends Thread{
	
	private Etat player;  // récupération de l'etat du modele
	private boolean stopped; // booleen d'interuption
	
	public Voler(Etat state) {
		player = state;
		stopped = false;
	}
	
	/** Cette methode permet de mettre la valeur de stopped à true */
	public void end() {
		stopped = true;
	}
	
	/** Implémentation de la méthode run du thread pour gerer la retombé de l'anneau */
	@Override
	public void run() {
		while(!stopped) {
			if (player.testPerdu()) {
				end();
			}
			try {
				Thread.sleep(20); // application d'un delai entre chage retombé (permet une retombé progressive)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			player.move(); // permet la modification de l'etat lorsque l'anneau retombe
		}
	}

}
