package com.datatecsolutions.edutools.modelo;


/**
 * Created by reynaldo.cruz on 10/02/2015.
 */


import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.datatecsolutions.edutools.R;

public class adaptadorListaAsignaturas extends ArrayAdapter {
    /*Hacemos que nuestra clase sea un ArrayAdapter extendiendo de la misma*/

    Activity context;
    NameValuePair[] datos;
	/*Creamos las variables necesarias para capturar el contexto y otra para capturar
	 * los datos que se publicaran en la lista*/

    public adaptadorListaAsignaturas(Activity context, NameValuePair[] datos) {
        super(context, R.layout.milistlayout, datos);
        this.context = context;
        this.datos = datos;
        // TODO Auto-generated constructor stub
    }

    /*Constructor de la clase, donde pasamos por parametro los datos a mostrar en la lista y el contexto*/
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.milistlayout, null);

        TextView title = (TextView) item.findViewById(R.id.codigoasignatura);
        title.setText(datos[position].getName());

        TextView descrip = (TextView) item.findViewById(R.id.nombreasignatura);
        descrip.setText(datos[position].getValue().toString());

        return item;
    }
    /*Este metodo es el mas importante ya que es donde inflamos el fichero lista_tema.xml en cada item
     * de la lista y despues a ambos TextView dentro de ficehro le insertamos los datos que queremos que muestre */

}
