package com.fx.buddy.view;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fx.buddy.Main;
import com.fx.buddy.model.Buddy;
import com.fx.buddy.model.Sex;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * modal dialog aimed to edit or add a new Buddy
 * @author hackme
 *
 */
public class BuddyDialogMapping {

	private Stage stageDialogue;
	@FXML
	private TextField formName;
	@FXML
	private TextField formFirstname;
	@FXML
	private DatePicker formBirth;
	@FXML
	private ComboBox<Sex> formSex;

	private Main main;	
	private Buddy personne;

	public void setMainClass(Main m) {
		main = m;
		stageDialogue = main.getDialogStage();
	}

	//On initialise ici les valeurs de la liste d�roulante
	//avant de s�lectionner la valeur de la personne
	public void initialize() {
		formSex.getItems().setAll(Sex.values());
	}

	//Afin de r�cup�rer le stage de la popup
	//et pouvoir la clore
	public void setStage(Stage s) {stageDialogue = s;}

	public void setPersonne(Buddy p) {
		personne = p;
		formName.setText(personne.getNom().get());
		formFirstname.setText(personne.getPrenom().get());
		formBirth.setValue(personne.getDateDeNaissance().get());
		formSex.getSelectionModel().select(personne.getSexe().get());
	}

	//M�thode de contr�le de la validit� des donn�es saisies
	private boolean controlerFormulaire() {
		boolean isOk = true;
		List<String> messageErreur = new ArrayList<>();
		if (formName.getText() == null || formName.getText().isEmpty()) {
			isOk = false;
			messageErreur.add("Le champ \"Nom\" est obligatoire");
		}
		if (formFirstname.getText() == null || formFirstname.getText().isEmpty()) {
			isOk = false;
			messageErreur.add("Le champ \"Pr�nom\" est obligatoire");
		}	
		if (formBirth.getValue() == null || formBirth.getValue().toString().isEmpty()) {
			isOk = false;
			messageErreur.add("Le champ \"Date\" est obligatoire");
		}

		if(!isOk) {
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setTitle("Erreur ! ");
			StringBuilder sb = new StringBuilder();
			messageErreur.stream().forEach((x) -> sb.append("\n" + x));
			erreur.setHeaderText(sb.toString());
			erreur.showAndWait();
		}		
		return isOk;
	}

	@FXML
	public void annuler() {
		//On ferme la bo�te de dialogue
		stageDialogue.close();
	}

	//sauvegarde de la personne, que ce soit une �dition ou une cr�ation
	public void sauvegarder() {
		
		if(controlerFormulaire()) {
			personne.setNom(new SimpleStringProperty(formName.getText()));
			personne.setPrenom(new SimpleStringProperty(formFirstname.getText()));

			//Afin de pouvoir g�rer la modification de date � la souris
			//ou en modifiant le texte du composant directement
			//On r�cup�re la date au format texte pour la r�injecter 
			//dans le composant
			formBirth.setValue(
					formBirth.getConverter()
					.fromString(
							//Date du composant au format texte
							formBirth.getEditor().getText()
							)
					);

			personne.setDateDeNaissance(new SimpleObjectProperty<LocalDate>(formBirth.getValue()));
			personne.setSexe(new SimpleObjectProperty<Sex>(formSex.getValue()));

			//S'il s'agit d'une cr�ation, on ajoute la personne dans le tableau
			if(stageDialogue.getTitle().startsWith("Cr�ation")) {
				main.getListDePersonne().add(personne);
			}
			else if(stageDialogue.getTitle().startsWith("Edition")) {
				
				// replace existing Buddy : TODO : replace 0 with current index !
				main.getListDePersonne().set(main.getIndex(), personne);
			}

			//On ferme la bo�te de dialogue
			stageDialogue.close();
		}
	}
}
