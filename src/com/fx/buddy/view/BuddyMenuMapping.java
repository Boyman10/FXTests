package com.fx.buddy.view;

import com.fx.buddy.Main;
import com.fx.buddy.model.Buddy;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class BuddyMenuMapping {
	//Objet servant de référence à notre classe principale
	//afin de pouvoir récupérer le Stage principal.
	//et ainsi fermer l'application
	private Main main;

	//Méthode qui sera utilisée dans l'initialisation de l'IHM
	//dans notre classe principale
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}

	//Fermer l'application
	@FXML
	public void fermer() {
		//On affiche un message car on est poli.
		Alert bye = new Alert(AlertType.INFORMATION);
		bye.setTitle("Au revoir !");
		bye.setHeaderText("See you soon...");
		bye.setContentText("Et merci d'avoir suivi ce cours");
		bye.showAndWait();

		//Et on clos le stage principal, donc l'application
		this.main.getStage().close();
	}

	@FXML
	public void nouveau() {
		//On affiche la popup avec une personne inexistante
	    this.main.affichePersonneDialogue(new Buddy(), "Création d'une personne");	    
	}
	
	@FXML
	public void edit() {
		
		System.out.println("Clicked on edit");
		// we edit the selected item if any
		Buddy curBuddy;
		if((curBuddy = this.main.getBuddyFromIndex()) != null)
			this.main.affichePersonneDialogue(curBuddy, "Edition of Buddy");	    
	}
	

	
}
