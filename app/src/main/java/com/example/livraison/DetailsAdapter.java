package com.example.livraison;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

// Adapter pour afficher une liste de détails dans une GridView ou ListView
public class DetailsAdapter extends BaseAdapter {

    private Context context; // Contexte de l'application ou de l'activité
    private ArrayList<String> details; // Liste de chaînes de texte à afficher

    // Constructeur : on passe le contexte et la liste de détails
    public DetailsAdapter(Context context, ArrayList<String> details) {
        this.context = context;
        this.details = details;
    }

    // Retourne le nombre d'éléments dans la liste
    @Override
    public int getCount() {
        return details.size();
    }

    // Retourne l'objet à une position donnée (ici une chaîne de texte)
    @Override
    public Object getItem(int position) {
        return details.get(position);
    }

    // Retourne l'identifiant d'un élément (ici on utilise sa position)
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Méthode qui crée ou recycle la vue pour chaque élément de la liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vue n'existe pas encore, on la crée à partir du layout item_detail.xml
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false);
        }

        // Récupération du TextView dans lequel on affichera le détail
        TextView txtDetail = convertView.findViewById(R.id.txtDetail);

        // On met le texte correspondant à cet élément
        txtDetail.setText(details.get(position));

        // On retourne la vue qui sera affichée dans la GridView ou ListView
        return convertView;
    }
}
