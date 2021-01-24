package model;

/** Cette classe represente la partie modèle avec l'etat des différents composant du projet*/

public class Etat {
	
	private int x, y; //Position du joueur // oval
	private int xBound, yBound; //Les bornes de l'�cran
	private float dx; //Vitesse de deplacement en X soit de combien de pixel on se deplace � l'horizontale
	private int dy = 0; //Vitesse de deplacement en Y soit de combien de pixel on se deplace en haut
	private static int width = 20, height = 60;
	
	private static int JUMP = -15; // nombre de pixel a effectuer vers le haut lors d'un saut	
	private static int GRAVITY = 2; // nombre de pixel à effectuer vers le bas lorsque l'anneau retombe
	
	public Parcours p; // prise en compte du parcours
	
	public Etat(int screenWidth, int screenHeight, int initX, int initY) {
		yBound = screenHeight;
		xBound = screenWidth;
		
		x = initX;
		y = initY;
		
		p = new Parcours(screenWidth,screenHeight, initX + width /2, yBound - height /2, JUMP, GRAVITY);
	}
	
	/** rend la constante y*/
	public int getY(){
		return y;
	}
	
	/** rend la constante x*/
	public int getX() {
		return x;
	}
	
	/** rend la Height x*/
	public int getHeight() {
		return height;
	}
	
	/** rend la constante Width*/
	public int getWidth() {
		return width;
	}
	

	/** Permet la gesdtion du comportement du joueur 
	 * l'orsqu'il entre en contacte avec le bord de la fenêtre mais aussi l'éventuelle gravité
	 * Cette fonction n'est pas encore definitive */
	public void move() {
		y += dy;
		dy += GRAVITY;
		if (dy > 10)
			dy = 10;
		if (y + height >= yBound) {
			dy = 2;
			y = yBound - height;
		}		
		if(y <= 0)
			dy = 5;
	}
	
	/** Cette methode permet de récupéré le score du joueur que l'on considère comme sa position par rap^port au debut du jeu*/
	public int getScore() {
		return p.getPos();
	}
	
	/** Cette methode permet de retrenscrire l'effet d'un saut sur l'etat de l'ovale*/
	public void jump() {
		dy = JUMP;
	}
	
}
