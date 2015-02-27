package com.datatecsolutions.edutools.modelo;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        final ViewHolder holder;
        final Persona persona = getItem(position);
        if (convertView == null || (convertView.getTag() == null)) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.itemalumnos, null);

            holder = new ViewHolder();

            holder.rne = (TextView) convertView.findViewById(R.id.tvcodigoAlumno);
            holder.nombre = (TextView) convertView.findViewById(R.id.tvNombreAlumno);
            holder.valor = (EditText) convertView.findViewById(R.id.eTcalificacion);

            holder.rne.setText("");
            holder.nombre.setText("");
            holder.valor.setText("");


            holder.rne.setText(persona.getCodigo());
            holder.nombre.setText(persona.getPrimerNombre());
            holder.valor.setText(String.valueOf(persona.getNota()));
            convertView.setTag(holder);

        } else {

            // return convertView;
            holder = (ViewHolder) convertView.getTag();



        }
        // holder=(ViewHolder) convertView.getTag();


        holder.valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (holder.valor.getText().length() > 0) {
                    persona.actualizarNotaAcumulativa(holder.rne.getText().toString(), 1, Double.parseDouble(holder.valor.getText().toString()));

                }
            }
        });


        // notifyDataSetChanged();

        return convertView;


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    public static class ViewHolder {
        public TextView rne;
        public TextView nombre;
        public EditText valor;

    }

}
