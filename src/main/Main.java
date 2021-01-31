package main;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Control;
import model.Avancer;
import model.Etat;
import model.Voler;
import view.Affichage;


/** Partie main du projet qui est executer. Elle initialise l'affichage et permet d'instancier le MVC*/
public class Main {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Flappy birds"); //crer une fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programe lorsque la feetre est fermé
		
		
		Etat modele = new Etat(Affichage.WIDTH, Affichage.HEIGHT, 100, 200); // Initialise le modele
		Affichage vue = new Affichage(modele); // Initialise la vue
		Control c = new Control(modele, vue); // Initialise le controle
		
		Thread g = new Voler(modele); // permet de lancer le thread pour le vol de l'oiseau
		g.start();
		
		
		Thread scrolling = new Avancer(modele);
		scrolling.start(); 
		
		fenetre.addMouseListener(c); // permet de tenir au courant le programme lorsque la souris est cliquée	
		fenetre.add(vue);
		
		
		fenetre.pack();
		fenetre.setVisible(true);
		
		// test si l'on a perdu et renvoie une fenetre avec le score
		if (modele.testPerdu()) {
			JOptionPane d = new JOptionPane();
			d.showMessageDialog( fenetre, "Votre socre est : " + modele.getScore(), 
			      "Tableau final", JOptionPane.INFORMATION_MESSAGE);
			
			
		}
	}

}
