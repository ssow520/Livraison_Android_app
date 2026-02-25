package com.example.livraison;

import java.io.Serializable;

// Classe représentant un livreur
public class Livreur implements Serializable {

    private String nom;          // Nom du livreur
    private String adresse;      // Adresse du livreur
    private String telephone;    // Numéro de téléphone
    private int numeroRoute;     // Numéro de la route affectée (0 si aucune)

    // Constructeur : initialise un livreur avec ses informations
    public Livreur(String nom, String adresse, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.numeroRoute = 0; // 0 = pas de route assignée
    }

    // Getters et setters pour accéder et modifier les informations

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNumeroRoute() {
        return numeroRoute;
    }

    public void setNumeroRoute(int numeroRoute) {
        this.numeroRoute = numeroRoute;
    }
}
