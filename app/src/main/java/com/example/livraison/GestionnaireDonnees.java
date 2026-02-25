package com.example.livraison;

import android.content.Context;
import java.io.*;
import java.util.ArrayList;

// Classe singleton pour gérer toutes les données de l'application
public class GestionnaireDonnees {

    private static GestionnaireDonnees instance; // Instance unique
    private ArrayList<Route> routes;             // Liste des routes
    private ArrayList<Livreur> livreurs;         // Liste des livreurs
    private ArrayList<Abonnement> abonnements;   // Liste des abonnements
    private static final String FICHIER_DONNEES = "donnees_livraison.dat"; // Nom du fichier pour sauvegarde

    // Constructeur privé (singleton)
    private GestionnaireDonnees() {
        routes = new ArrayList<>();
        livreurs = new ArrayList<>();
        abonnements = new ArrayList<>();
        routes.add(new Route(0)); // Route 0 = route par défaut pour les abonnements non affectés
    }

    // Méthode pour récupérer l'instance unique
    public static GestionnaireDonnees getInstance() {
        if (instance == null) {
            instance = new GestionnaireDonnees();
        }
        return instance;
    }

    // Getters pour accéder aux listes
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public ArrayList<Livreur> getLivreurs() {
        return livreurs;
    }

    public ArrayList<Abonnement> getAbonnements() {
        return abonnements;
    }

    // Recherche d'une route par son numéro
    public Route trouverRoute(int numero) {
        for (Route r : routes) {
            if (r.getNumero() == numero) {
                return r;
            }
        }
        return null;
    }

    // Recherche d'un livreur par son nom (insensible à la casse)
    public Livreur trouverLivreur(String nom) {
        for (Livreur l : livreurs) {
            if (l.getNom().equalsIgnoreCase(nom)) {
                return l;
            }
        }
        return null;
    }

    // Recherche d'un abonnement par son numéro unique
    public Abonnement trouverAbonnement(int numero) {
        for (Abonnement a : abonnements) {
            if (a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    // Vérifie que toutes les routes (sauf route 0) ont un livreur affecté
    public boolean toutesRoutesOntLivreur() {
        for (Route r : routes) {
            if (r.getNumero() != 0 && !r.aLivreur()) {
                return false;
            }
        }
        return true;
    }

    // Sauvegarde des données dans un fichier local
    public void sauvegarderDonnees(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FICHIER_DONNEES, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(routes);
            oos.writeObject(livreurs);
            oos.writeObject(abonnements);
            oos.writeInt(Abonnement.getCompteur()); // Sauvegarde du compteur d'abonnements
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Chargement des données depuis le fichier local
    @SuppressWarnings("unchecked")
    public void chargerDonnees(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FICHIER_DONNEES);
            ObjectInputStream ois = new ObjectInputStream(fis);
            routes = (ArrayList<Route>) ois.readObject();
            livreurs = (ArrayList<Livreur>) ois.readObject();
            abonnements = (ArrayList<Abonnement>) ois.readObject();
            Abonnement.setCompteur(ois.readInt()); // Restaure le compteur d'abonnements
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace(); // Si le fichier n'existe pas, on capture l'exception
        }
    }
}
