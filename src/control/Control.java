package control;

/** Cette classe represente la partie controle de ce projet. C'est ici que sont gere les intéraction 
 * entre le joueur et le jeu et les effet que cela produit sur l'etat du modele et sur l'affichage */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Etat;
import view.Affichage;

public class Control implements MouseListener {
	Etat modele; // Etat des objet du jeu
	Affichage vue; // affichage du jeu
	
	public Control(Etat etat, Affichage screen) {
		modele = etat;
		vue = screen;
	}
	
	/** Permet d'effectuer un saut lorsque le joeur clic*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		modele.jump(); // ici on fait appel à la méthode jump pour recalculer la position de l'ovale après le saut
		vue.revalidate(); // permet de forcer l'actualisation du dessin
		vue.repaint();  // on relance paint pour redessiner le nouvel ovale et modifier la vue
	}

	/** Toutes ces fonction sont présente dans l'interface MouseListener et doivent donc etre 
	 * implemente cependant elle ne nous interesse pas dans ce projet*/
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
