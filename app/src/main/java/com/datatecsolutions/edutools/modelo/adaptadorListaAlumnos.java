package com.datatecsolutions.edutools.modelo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.datatecsolutions.edutools.R;

import java.util.ArrayList;

/**
 * Created by rj on 23/02/2015.
 */
public class adaptadorListaAlumnos extends ArrayAdapter<Persona> {
    Activity context;
    ArrayList<Persona> datos;

    public adaptadorListaAlumnos(Activity context, ArrayList<Persona> datos) {
        super(context, R.layout.itemalumnos, datos);

        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            return convertView;
        } else {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.itemalumnos, null);
            Persona persona = getItem(position);
            TextView codigo = (TextView) item.findViewById(R.id.tvcodigoAlumno);
            codigo.setText(persona.getCodigo());

            TextView nombre = (TextView) item.findViewById(R.id.tvNombreAlumno);
            nombre.setText(persona.getPrimerNombre());
            return item;
        }
    }

}
