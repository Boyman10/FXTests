package com.fx.buddy;

import java.io.IOException;
import java.time.LocalDate;

import com.fx.buddy.model.Buddy;
import com.fx.buddy.model.Sex;
import com.fx.buddy.view.BuddyDialogMapping;
import com.fx.buddy.view.BuddyMapping;
import com.fx.buddy.view.BuddyMenuMapping;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	//Nous crÃ©ons des variable de classes afin de pouvoir y accÃ©der partout
	//Ceci afin de pouvoir y positionner les Ã©lÃ©ments que nous avons fait
	//Il y a un BorderPane car le conteneur principal de notre IHM
	//est un BorderPane, nous reparlerons de l'objet Stage
	private Stage stagePrincipal,  stageDialogue;
	private BorderPane conteneurPrincipal;
	
	private ObservableList<Buddy> listDePersonne = FXCollections.observableArrayList();

	
	public Main() {
		
		listDePersonne.add(new Buddy("Proviste", "Alain", LocalDate.of(1970, 1, 1), Sex.MASCULIN));
		listDePersonne.add(new Buddy("D'Arc", "Jeanne", LocalDate.of(1431, 5, 30), Sex.FEMININ));
		listDePersonne.add(new Buddy("Caisse", "Jean", LocalDate.of(1950, 3, 3), Sex.MASCULIN));
		
		// add a listener on change
		listDePersonne.addListener(new ListChangeListener<Buddy>() { 
			  
			@Override
			public void onChanged(Change<? extends Buddy> arg0) {
				// TODO Auto-generated method stub
				System.out.println("list has changed");
			}            
		});
	}
	
	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		//Ca ne vous rappelle pas une JFrame ?
		stagePrincipal.setTitle("Application de gestion de personnes");
		
		
		//Nous allons utiliser nos fichier FXML dans ces deux mÃ©thodes
		initialisationConteneurPrincipal();
		initialisationContenu();
	}

	private void initialisationConteneurPrincipal() {
		//On créé un chargeur de FXML
		FXMLLoader loader = new FXMLLoader();
		//On lui spécifie le chemin relatif à notre classe
		//du fichier FXML a charger : dans le sous-dossier view
		loader.setLocation(Main.class.getResource("view/MainContainer.fxml"));
		//*
		try {
			//Le chargement nous donne notre conteneur
			conteneurPrincipal = (BorderPane) loader.load();
			System.out.println(conteneurPrincipal);
			//On définit une scène principale avec notre conteneur
			Scene scene = new Scene(conteneurPrincipal);
			//Que nous affectons à notre Stage
			stagePrincipal.setScene(scene);
			
			//Initialisation de notre contrôleur
			BuddyMenuMapping controleur = loader.getController();
			//On spécifie la classe principale afin de pour récupérer le Stage
			//Et ainsi fermer l'application
			controleur.setMainApp(this);
			
			stagePrincipal.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void initialisationContenu() {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/BuddyView.fxml"));
		
		
		try {
			//Nous rÃ©cupÃ©rons notre conteneur qui contiendra les donnÃ©es
			//Pour rappel, c'est un AnchorPane...
			AnchorPane conteneurPersonne = (AnchorPane) loader.load();
			//Qui nous ajoutons Ã  notre conteneur principal
			//Au centre, puisque'il s'agit d'un BorderPane
			conteneurPrincipal.setCenter(conteneurPersonne);
			
			//Nous rÃ©cupÃ©rons notre mappeur via l'objet FXMLLoader
			BuddyMapping controleur = loader.getController();
			//Nous lui passons notre instance de classe
			//pour qu'il puisse rÃ©cupÃ©rer notre liste observable
			controleur.setMainApp(this);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Méthode qui va va afficher la popup d'édition
	//ou de création d'une personne et initialiser son contrôleur
	public void affichePersonneDialogue(Buddy personne, String titre) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/BuddyDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        // Création d'un nouveau Stage qui sera dépendant du Stage principal
	        stageDialogue = new Stage();
	        stageDialogue.setTitle(titre);
	        stageDialogue.initModality(Modality.WINDOW_MODAL);
	        
	        //Avec cette instruction, notre fenêtre modifiée sera modale
	        //par rapport à notre stage principal
	        stageDialogue.initOwner(stagePrincipal);
	        Scene scene = new Scene(page);
	        stageDialogue.setScene(scene);
	        
	        // initialisation du contrôleur
	        BuddyDialogMapping controller = loader.getController();
	        //On passe la personne avec laquelle nous souhaitons travailler
	        //une existante ou une nouvelle
	        controller.setPersonne(personne);
	        controller.setMainClass(this);
	        
	        // Show the dialog and wait until the user closes it
	        stageDialogue.showAndWait();
	        //return controller.isOkClicked();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public ObservableList<Buddy> getListDePersonne(){return listDePersonne;}
	
	public Stage getStage() {
		
		return stagePrincipal;
	}
	public Stage getDialogStage() {
		
		return stageDialogue;
	}	
	
}
