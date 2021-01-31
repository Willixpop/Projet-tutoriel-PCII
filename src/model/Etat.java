package model;

import java.awt.Point;

/** Cette classe represente la partie modèle avec l'etat des différents composant du projet*/

public class Etat {
	
	private int x, y; //Position du joueur // oval
	private int xBound, yBound; //Les bornes de l'�cran
	private float dx; //Vitesse de deplacement en X soit de combien de pixel on se deplace � l'horizontale
	private int dy = 0; //Vitesse de deplacement en Y soit de combien de pixel on se deplace en haut
	private static int width = 20, height = 60;
	
	private static int JUMP = -15; // nombre de pixel a effectuer vers le haut lors d'un saut	
	private static int GRAVITY = 1; // nombre de pixel à effectuer vers le bas lorsque l'anneau retombe
	
	public Parcours p; // prise en compte du parcours
	
	public Etat(int screenWidth, int screenHeight, int initX, int initY) {
		yBound = screenHeight;
		xBound = screenWidth;
		
		x = initX;
		y = initY;
		
		p = new Parcours(screenWidth,screenHeight, initX + width /2, initY - height /2, JUMP, GRAVITY);
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
	 * l'orsqu'il entre en contacte avec le bord de la fenêtre mais aussi l'éventuelle gravité */
	public void move() {
		y += dy;
		dy += GRAVITY;
		if (dy >=10)
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
	
	/** Fonction qui test si l'ovale entre en contact avec la ligne brisée*/
	public boolean testPerdu() {
		Point fstp = p.getParcours().get(0); // récupération du premier point de la courbe
		Point sndp = p.getParcours().get(1); // récupération du second point de la courbe
		float pente = (((sndp.y - fstp.y)/(sndp.x - fstp.x))); // calcul de la pente
		float ycourbe = (pente * x)+(fstp.y - (pente * fstp.x)); // calcule de l'y sur la courbe
		return (((y+height) <= ycourbe)  ||  (y >= ycourbe)); // test de la place de l'ovale sur la courbe et renvoie true si l'ovale n'est plis sur la courbe
	}
}
