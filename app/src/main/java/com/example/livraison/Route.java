package com.example.livraison;

import java.io.Serializable;
import java.util.ArrayList;

// La classe Route représente une route de livraison
public class Route implements Serializable {

    private int numero; // Numéro de la route
    private String nomLivreur; // Nom du livreur affecté à cette route (null si aucun)
    private ArrayList<Abonnement> abonnements; // Liste des abonnements (clients) sur cette route

    // Constructeur : crée une route avec un numéro donné, sans livreur et liste vide d'abonnements
    public Route(int numero){
        this.numero = numero;
        this.nomLivreur = null;
        this.abonnements = new ArrayList<>();
    }

    // Retourne le numéro de la route
    public int getNumero() {
        return numero;
    }

    // Change le numéro de la route
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Retourne le nom du livreur affecté
    public String getNomLivreur() {
        return nomLivreur;
    }

    // Affecte ou retire un livreur
    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }

    // Retourne la liste des abonnements de cette route
    public ArrayList<Abonnement> getAbonnements() {
        return abonnements;
    }

    // Ajoute un abonnement à la route
    public void ajouterAbonnement(Abonnement abonnement) {
        this.abonnements.add(abonnement);
    }

    // Retire un abonnement de la route
    public void retirerAbonnement(Abonnement abonnement) {
        this.abonnements.remove(abonnement);
    }

    // Vérifie si la route a un livreur affecté
    public boolean aLivreur() {
        return nomLivreur != null && !nomLivreur.isEmpty();
    }
}
