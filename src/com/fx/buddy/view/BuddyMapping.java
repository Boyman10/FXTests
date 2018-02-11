package com.fx.buddy.view;


import com.fx.buddy.Main;
import com.fx.buddy.model.Buddy;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BuddyMapping {

	@FXML
	private TableView<Buddy> buddyTable;
	@FXML
	private TableColumn<Buddy, String> nameColumn;
	@FXML
	private TableColumn<Buddy, String> firstnameColumn;
	@FXML
	private Label valueName;
	@FXML
	private Label valueFirstname;
	@FXML
	private Label valueBirth;
	@FXML
	private Label valueSex;

	//Objet servant de rÃ©fÃ©rence Ã  notre classe principale
	//afin de pouvoir rÃ©cupÃ©rer la liste de nos objets.
	private Main main;

	//Un constructeur par dÃ©faut
	public BuddyMapping() { }

	//MÃ©thode qui initialise notre interface graphique
	//avec nos donnÃ©es mÃ©tier
	@FXML
	private void initialize() {
		// Initialize the Personne table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
		firstnameColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenom());
		
	    //Nous récupérons le model de notre tableau (vous connaissez maintenant)
	    //où nous récupérons l'item sélectionné et où nous y attachons un écouteur
	    //Qui va utiliser notre méthode de mise à jour d'IHM
		buddyTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> initializeDescription(newValue));		
	}

	//MÃ©thode qui sera utilisÃ©e dans l'initialisation de l'IHM
	//dans notre classe principale
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		// On lie notre liste observable au composant TableView
		buddyTable.setItems(main.getListDePersonne());
	}
	
	/**
	 * Method which insert elements value within components
	 * @param p
	 */
	public void initializeDescription(Buddy p) {
		// initialize by default :
		valueName.setText("");
		valueFirstname.setText("");
		valueBirth.setText("");
		valueSex.setText("");
		
		//Si un objet est passé en paramètre, on modifie l'IHM
		if(p != null) {
			
			//ATTENTION : les accesseurs retournent des objets Property Java FX
			//Pour récupérer leurs vrais valeurs vous devez utiliser la méthode get()
			valueName.setText(p.getNom().get());
			valueFirstname.setText(p.getPrenom().get());
			
			//Sur les deux champs si dessous, en plus de get()
			//vous devez utiliser toString() car ce sont des objets particuliers
			valueBirth.setText(p.getDateDeNaissance().get().toString());
			valueSex.setText(p.getSexe().get().toString());
		}
	}
	
	@FXML
	public void removeBuddy() {
		int index = buddyTable.getSelectionModel().getSelectedIndex();
		//Si aucune ligne n'est sélectionnée, index vaudra -1
		if (index > -1) {
			buddyTable.getItems().remove(index);
		}
		else {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Veuillez sélectionnez une ligne dans le tableau");
			probleme.showAndWait();
		}
	}
	
	@FXML
	public void editBuddy() {
		int index = buddyTable.getSelectionModel().getSelectedIndex();
		//Si aucune ligne n'est sélectionnée, index vaudra -1
		
		System.out.println("Clicked on edit Buddy index: " + index);
		if (index > -1) {
			
			main.affichePersonneDialogue(buddyTable.getItems().get(index),"Edition of buddy...") ;
			
		}
		else {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Veuillez sélectionnez une ligne dans le tableau");
			probleme.showAndWait();
		}
	}	
	
	/**
	 * Tell the main class which item is selected :
	 */
	@FXML
	public void setBuddy() {	
		
		int index = buddyTable.getSelectionModel().getSelectedIndex();
		//Si aucune ligne n'est sélectionnée, index vaudra -1
		if (index > -1) {
			
			main.setBuddyTableIndex(index) ;
			
		}
		else {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Veuillez sélectionnez une ligne dans le tableau");
			probleme.showAndWait();
		}	
		
	}
	
	
}
