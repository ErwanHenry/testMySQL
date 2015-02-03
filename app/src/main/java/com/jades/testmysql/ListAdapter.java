package com.jades.testmysql;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by erwan on 03/02/2015.
 */
public class ListAdapter extends ArrayAdapter<User> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);

        }

        User p = getItem(position);

        if (p != null) {

            TextView tt = (TextView) v.findViewById(R.id.nom);
            TextView tt1 = (TextView) v.findViewById(R.id.prenom);
            TextView tt3 = (TextView) v.findViewById(R.id.statut);

            if (tt != null) {
                tt.setText(p.getNom());
            }
            if (tt1 != null) {

                tt1.setText(p.getPrenom());
            }
            if (tt3 != null) {

                tt3.setText(p.getStatut());
            }
        }

        return v;

    }
}