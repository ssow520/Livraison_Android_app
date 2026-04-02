# 📦 Gestion Livraison - Application Android

Application mobile de gestion des livraisons de magazines et journaux développée en Java pour Android.

## 📋 Description

Cette application permet à une entreprise de livraison de magazines et journaux de gérer efficacement ses opérations quotidiennes : clients, livreurs, routes et abonnements.

## ✨ Fonctionnalités principales

### Gestion des Routes
- ➕ Ajouter une nouvelle route
- ❌ Supprimer une route existante
- 🔄 Réaffectation automatique des abonnements à la route 0 lors de la suppression

### Gestion des Livreurs
- 👤 Ajouter un livreur (nom, adresse, téléphone)
- 🚚 Affecter un livreur à une route
- 🔓 Retirer un livreur de sa route

### Gestion des Abonnements
- 📰 Ajouter un abonnement (client, adresse, produits, quantités)
- 🎯 Suggestion automatique de route selon l'adresse
- 🗑️ Retirer un abonnement

### Système de Listage
- 📋 Lister tous les livreurs et leurs routes
- 👥 Lister tous les abonnés avec détails
- 🗺️ Lister toutes les routes avec leurs livreurs
- 📦 Lister les produits et leurs points de livraison

## 🛠️ Technologies utilisées

- **Langage** : Java
- **Plateforme** : Android (SDK 24+)
- **Architecture** : MVC avec Singleton Pattern
- **UI Components** : RecyclerView, GridView, Fragments
- **Persistance** : Sérialisation d'objets (fichiers .dat)

## 📱 Écrans de l'application

1. **Écran de chargement** - Affichage du logo et chargement des données
2. **Écran d'accueil** - Présentation avec swipe pour continuer
3. **Menu principal** - Accès à toutes les fonctionnalités
4. **Écrans de gestion** - Routes, livreurs, abonnements
5. **Écran de listage** - Consultation des données avec fragments

## 🚀 Installation

### Prérequis
- Android Studio Arctic Fox ou supérieur
- JDK 8 ou supérieur
- Device/Emulator Android API 24 (Android 7.0) ou supérieur

### Étapes d'installation

1. Cloner le projet
```bash
git clone [URL_DU_REPO]
```

2. Ouvrir le projet dans Android Studio
```
File > Open > Sélectionner le dossier du projet
```

3. Synchroniser Gradle
```
File > Sync Project with Gradle Files
```

4. Exécuter l'application
```
Run > Run 'app' ou appuyer sur Shift + F10
```

## 📂 Structure du projet

```
com.gestion.livraison/
├── Modèles
│   ├── Route.java
│   ├── Livreur.java
│   ├── Abonnement.java
│   └── GestionnaireDonnees.java (Singleton)
├── Activités
│   ├── EcranChargement.java
│   ├── EcranAccueil.java
│   ├── MenuPrincipal.java
│   ├── AjouterSupprimerRoute.java
│   ├── AffecterRoute.java
│   ├── AjouterAbonnement.java
│   ├── RetirerAbonnement.java
│   ├── AjouterLivreur.java
│   ├── RetirerLivreur.java
│   └── ListerActivity.java
├── Fragments
│   ├── LivreursFragment.java
│   ├── AbonnesFragment.java
│   ├── RoutesFragment.java
│   └── ProduitsFragment.java
└── Adaptateurs
    ├── LivreurAdapter.java
    ├── RouteAdapter.java
    ├── ProduitAdapter.java
    └── DetailsAdapter.java
```

## 🎯 Fonctionnement de la Route 0

La **Route 0** est une route spéciale qui :
- Contient tous les abonnements non affectés à une route valide
- Ne peut pas être supprimée
- Ne peut pas avoir de livreur assigné
- Reçoit automatiquement les abonnements des routes supprimées

## 💾 Sauvegarde des données

- Les données sont automatiquement **sauvegardées** lors de la fermeture de l'application (bouton Quitter)
- Les données sont automatiquement **chargées** au démarrage de l'application
- Format : Sérialisation Java dans un fichier `donnees_livraison.dat`

## 🎨 Interface utilisateur

- **Design Material** avec couleurs thématiques
- **Navigation intuitive** avec boutons "Retour au menu"
- **Swipe gesture** sur l'écran d'accueil
- **Messages de confirmation** pour toutes les actions
- **GridView et RecyclerView** pour l'affichage des listes

## ⚙️ Configuration

### Modifier le thème
Dans `AndroidManifest.xml` :
```xml
android:theme="@style/Theme.AppCompat.Light.NoActionBar"
```

### Modifier les couleurs
Dans `res/values/colors.xml`, ajustez les couleurs principales.

## 📝 Règles métier

1. Une route ne peut avoir qu'**un seul livreur**
2. Un livreur ne peut être affecté qu'à **une seule route**
3. Un abonnement doit avoir au moins **un produit** (magazine ou journal)
4. Le bouton "Ajouter livreur" est **désactivé** si toutes les routes ont déjà un livreur
5. La suppression d'une route transfère ses abonnements à la **Route 0**

## 🐛 Résolution de problèmes

### L'application ne démarre pas
- Vérifier que le SDK Android est correctement installé
- Synchroniser Gradle : `File > Sync Project with Gradle Files`

### Données perdues
- Utiliser le bouton "Quitter" du menu au lieu de fermer brutalement l'app
- Vérifier les permissions de stockage de l'application

### Erreur de compilation
- Nettoyer le projet : `Build > Clean Project`
- Rebuild : `Build > Rebuild Project`

## 👨‍💻 Développeur

Souleymane Sow - Étudiant en Techniques de l'informatique - Collège LaSalle Montréal

## 📄 Licence

Projet académique - Tous droits réservés

## 📞 Contact

Pour toute question concernant ce projet, veuillez contacter votre professeur ou le développeur.

---

**Date de création** : Avril 2026  
**Version** : 1.0  
**Langage** : Java  
**Plateforme** : Android
