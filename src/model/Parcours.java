package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Parcours {
	private ArrayList<Point> parcours = new ArrayList<Point>(); // permet de contenir les différents points de la ligne brisee
	public static final Random rand = new Random(); // permet d'obenir une valeur aleatoire
	private int position; // valeur de la position du joueur pa rapport au debut du jeu
	
	private int xBound, yBound; // bornes de la fenetre
	
	private static int dx = 5; // vitesse de deplacement de la position en x (en npmbre de pixel)
	
	public Parcours(int screenWidth, int screenHeight, int initX, int initY, int jump, int gravity) {
		int xOld = initX ;
		int yOld = initY;
		
		xBound = screenWidth;
		yBound = screenHeight;
		
		Point p0 = new Point(xOld,yOld);
		parcours.add(p0);
		
		int xNew = 0;
		int yNew = 0;
		
		int dx = 0, dy = 0;
		
		position = 0;
		
		//Generation des points
		
		while(xNew < screenWidth) { // temps que le dernier point est visible
			xNew= xOld + rand.nextInt(80); // avancement de l'abssice aleatoirement entre 0 et 80 pixels
			yNew = rand.nextInt(screenHeight); // avancement de l'ordonnee aleatoirement entre 0 et la limite en y de la fenetre
			
			dx =(xNew - xOld !=0) ? xNew - xOld : 1;
			dy = yNew - yOld; // calcul de l'ecart entre la valeur de l'ancien y et le nouveau
			
			boolean isWellInclined = (dy/dx < 0) ? dy/dx <= jump : dy/dx >= gravity; // permet de vérifier l'inclinaison de la pente grace au taux d'accroissement
			
			while(isWellInclined) { // tant que la pente est valide
				xNew= xOld + rand.nextInt(80); 
				yNew = rand.nextInt(screenHeight -100);
				
				dx =(xNew - xOld !=0) ? xNew - xOld : 1;
				dy = yNew - yOld; // calcul de l'ecart entre la valeur de l'ancien y et le nouveau
				
				isWellInclined = (dy/dx < 0) ? dy/dx <= jump : dy/dx >= gravity; // revérification de l'inclinaison de la pente
				
			}
			
			// si la pente n'est pas valide
			Point pi = new Point(xNew,yNew);
			parcours.add(pi); // ajout du point à la liste des points 				
			xOld = xNew; // actualisation de l'ancien x
			yOld = yNew; // actualisation de l'ancien y
		}
		
		xNew += rand.nextInt(80); // creation d'une valeur x aleatoire entre 0 et 80 pour limiter le progression en x
		yNew = rand.nextInt(screenHeight); // creation d'une valeur x aleatoire compris entre les bornes y de la fenetre
		Point pn = new Point(xNew,yNew); // generation du point xNew, yNew
		parcours.add(pn); // ajout du point à la liste du parcour
	}
	
	/** Cette methode permet l'affichage des différents points du parcour */
	public void printParcours() {
		for(Point p : parcours)
			System.out.println("x : " + p.x + " y : " + p.y);
	}
	
	/** Cette methode permet de recuperer la position */
	public int getPos() {
		return position;
	}
	
	/** Cette methode permet de mettre à jour la position */
	public void incrementPos() {
		position += dx; // la position est incrémenté de dx 
		
		ArrayList<Point> res = newParcours(); // generation d'une liste de point
		
		Point lastE = parcours.get(parcours.size() -1); // recuperation du dernier point du parcour
		if (lastE.x <= xBound ) {
			int x= lastE.x + rand.nextInt(80); // creation d'un x aléatoire à l'intérieur de la fenetre
			int y = rand.nextInt(yBound); // creation d'un y aléatoire à l'intérieur de la fenetre
			Point tmp = new Point(x,y); // creation du point avec les nouvelle coordonnees
			res.add(tmp); // ajout du points au parcour
		}
		
		parcours = res;	// actualisation de la liste de points du parcour	
	}

	private ArrayList<Point> newParcours() {
		
		List<Point> tmp =parcours
				.parallelStream()
				.map(e -> new Point(e.x - dx, e.y))
				.filter(e -> e.x > 0)
				.collect(Collectors.toList());
		return (ArrayList<Point>) tmp;
	}
	
	/** Cette methode permet de recuperer la liste des points du parcour */
	public ArrayList<Point> getParcours() {
		return parcours;
	}

}
