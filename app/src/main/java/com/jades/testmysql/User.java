package com.jades.testmysql;

public class User {

	private int id;
	private String nom;
	private String prenom;
    private String statut;



	public User(){}

	public User(String nom, String prenom,String statut){

		setNom(nom);
		setPrenom(prenom);
		setStatut(statut);

	}


    public User(String nom, String prenom){

        this.nom = nom;
        this.prenom = prenom;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}


}