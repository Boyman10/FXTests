package com.fx.buddy.model;

	import java.time.LocalDate;

	import javafx.beans.property.ObjectProperty;
	import javafx.beans.property.SimpleObjectProperty;
	import javafx.beans.property.SimpleStringProperty;
	import javafx.beans.property.StringProperty;

	public class Buddy {
		private ObjectProperty<LocalDate> dateDeNaissance = new SimpleObjectProperty<>();
		private ObjectProperty<Sex> sexe = new SimpleObjectProperty<>();
		private StringProperty nom = new SimpleStringProperty();
		private StringProperty prenom = new SimpleStringProperty();
		public Buddy() {
			sexe.set(Sex.INCONNU);
			nom.set("");
			prenom.set("");
			dateDeNaissance.set(LocalDate.of(0, 1, 1));
		}

		public Buddy(String n, String p, LocalDate ddn, Sex s) {
			nom.set(n);
			prenom.set(p);
			dateDeNaissance.set(ddn);
			sexe.set(s);
		}
		public ObjectProperty<LocalDate> getDateDeNaissance() {return dateDeNaissance;}
		public void setDateDeNaissance(ObjectProperty<LocalDate> dateDeNaissance) {this.dateDeNaissance = dateDeNaissance;}
		public ObjectProperty<Sex> getSexe() {return sexe;}
		public void setSexe(ObjectProperty<Sex> sexe) {this.sexe = sexe;}
		public StringProperty getNom() {return nom;}
		public void setNom(StringProperty nom) {this.nom = nom;}
		public StringProperty getPrenom() {return prenom;}
		public void setPrenom(StringProperty prenom) {this.prenom = prenom;}
		public String toString() { return "#Nom : " + nom.get() + " - prénom : " + prenom.get() + "#";}
	}