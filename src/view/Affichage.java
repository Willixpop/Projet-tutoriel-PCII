package view;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Etat;

/** Cette classe permet l'affichage. c'est ici que les éléments graphique sont implémenté */
public class Affichage extends JPanel{
	
	public static final int WIDTH = 600; // Largeur de la fenetre
	public static final int HEIGHT = 400; // Hauteur de la fenetre	
	
	public Etat player; // instance du joueur de type etat
	
	public Affichage(Etat state) {
		player = state;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	/** Cette methode permet la construction de la ligne brisée à afficher à partir d'une liste de points */
	public void drawPolyline(ArrayList<Point> parc, Graphics g) {
		Point prevP = parc.get(0); // recuperation du premier points de la liste
		for(Point p : parc) { //parcours de la liste des points de la courbes
			g.drawLine(prevP.x, prevP.y, p.x, p.y); // dessin de la ligne entre deux points généré
			prevP = p; // actualisation du point de départ de la ligne
		}
	}
	
	/** Cette méthode permet de dessiner des elements dans la fenetre */
	@Override
	public void paint(Graphics g) {
		super.paint(g); // permet de réinitialiser l'affichage avant de dessiner pour eviter la superposition d'image
		char[] c = (""+player.getScore()).toCharArray(); // récupération du score
		g.drawChars( c, 0, c.length, 10, 50); // affichage du score
		g.drawOval(player.getX(), player.getY(), player.getWidth(), player.getHeight()); // dessin de l'ovale
		drawPolyline(player.p.getParcours(), g); // affichage de la ligne
		super.revalidate();
		super.repaint(); // permet d'actualisé l'image en rapellant paint
	}
}
