package com.fx.buddy.view;


import com.fx.buddy.Main;
import com.fx.buddy.model.Buddy;
import javafx.fxml.FXML;
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

	//Objet servant de référence à notre classe principale
	//afin de pouvoir récupérer la liste de nos objets.
	private Main main;

	//Un constructeur par défaut
	public BuddyMapping() { }

	//Méthode qui initialise notre interface graphique
	//avec nos données métier
	@FXML
	private void initialize() {
		// Initialize the Personne table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
		firstnameColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenom());
	}

	//Méthode qui sera utilisée dans l'initialisation de l'IHM
	//dans notre classe principale
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		// On lie notre liste observable au composant TableView
		buddyTable.setItems(main.getListDePersonne());
	}
}
