package com.example.livraison;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;

// Écran d'accueil avec détection de geste (swipe) pour passer au menu principal
public class EcranAccueil extends AppCompatActivity {

    private GestureDetector gestureDetector; // Détecteur de gestes pour l'écran

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_ecran_accueil.xml
        setContentView(R.layout.activity_ecran_accueil);

        // Initialisation du détecteur de gestes avec notre listener personnalisé
        gestureDetector = new GestureDetector(this, new SwipeListener());
    }

    // Cette méthode reçoit les événements tactiles et les transmet au détecteur de gestes
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    // Classe interne pour détecter les swipes (glissements)
    private class SwipeListener extends GestureDetector.SimpleOnGestureListener {

        // Seuils pour détecter un swipe
        private static final int SWIPE_THRESHOLD = 100; // distance minimale
        private static final int SWIPE_VELOCITY_THRESHOLD = 100; // vitesse minimale

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // Calcul de la distance horizontale du geste
            float diffX = e2.getX() - e1.getX();

            // Vérification que le geste est un swipe horizontal suffisamment rapide
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX < 0) { // Swipe vers la gauche
                    // On ouvre le MenuPrincipal
                    Intent intent = new Intent(EcranAccueil.this, MenuPrincipal.class);
                    startActivity(intent);
                    finish(); // On ferme l'écran d'accueil
                    return true;
                }
            }
            return false;
        }
    }
}
