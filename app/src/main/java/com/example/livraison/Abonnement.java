package com.example.livraison;

import java.io.Serializable;

// La classe Abonnement représente un abonnement d'un client à des magazines et journaux
public class Abonnement implements Serializable {

    // Compteur statique pour attribuer un numéro unique à chaque abonnement
    private static int compteur = 1;

    // Numéro unique de l'abonnement
    private int numero;

    // Nom du client
    private String nomClient;

    // Adresse du client
    private String adresse;

    // Quantité de magazines à livrer
    private int quantiteMagazines;

    // Quantité de journaux à livrer
    private int quantiteJournaux;

    // Numéro de la route de livraison
    private int numeroRoute;

    // Constructeur : crée un nouvel abonnement et assigne un numéro unique automatiquement
    public Abonnement(String nomClient, String adresse, int quantiteMagazines, int quantiteJournaux, int numeroRoute) {
        this.numero = compteur++; // On augmente le compteur pour le prochain abonnement
        this.nomClient = nomClient;
        this.adresse = adresse;
        this.quantiteMagazines = quantiteMagazines;
        this.quantiteJournaux = quantiteJournaux;
        this.numeroRoute = numeroRoute;
    }

    // --- Méthodes d'accès (getters et setters) ---

    // Retourne le numéro de l'abonnement
    public int getNumero() {
        return numero;
    }

    // Retourne le nom du client
    public String getNomClient() {
        return nomClient;
    }

    // Modifie le nom du client
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    // Retourne l'adresse du client
    public String getAdresse() {
        return adresse;
    }

    // Modifie l'adresse du client
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Retourne la quantité de magazines
    public int getQuantiteMagazines() {
        return quantiteMagazines;
    }

    // Modifie la quantité de magazines
    public void setQuantiteMagazines(int quantiteMagazines) {
        this.quantiteMagazines = quantiteMagazines;
    }

    // Retourne la quantité de journaux
    public int getQuantiteJournaux() {
        return quantiteJournaux;
    }

    // Modifie la quantité de journaux
    public void setQuantiteJournaux(int quantiteJournaux) {
        this.quantiteJournaux = quantiteJournaux;
    }

    // Retourne le numéro de la route de livraison
    public int getNumeroRoute() {
        return numeroRoute;
    }

    // Modifie le numéro de la route de livraison
    public void setNumeroRoute(int numeroRoute) {
        this.numeroRoute = numeroRoute;
    }

    // --- Méthodes pour le compteur (utile si on veut le réinitialiser) ---

    // Permet de modifier le compteur (par exemple pour recommencer à 1)
    public static void setCompteur(int valeur) {
        compteur = valeur;
    }

    // Retourne la valeur actuelle du compteur
    public static int getCompteur() {
        return compteur;
    }
}
