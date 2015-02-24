package com.datatecsolutions.edutools.modelo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.datatecsolutions.edutools.R;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by reynaldo.cruz on 11/02/2015.
 */

public class adaptadorListaAcumulativos extends ArrayAdapter<Acumulativo> {
    /*Hacemos que nuestra clase sea un ArrayAdapter extendiendo de la misma*/

    Activity context;
    ArrayList<Acumulativo> datos;
    /*Creamos las variables necesarias para capturar el contexto y otra para capturar
     * los datos que se publicaran en la lista*/

    public adaptadorListaAcumulativos(Activity context, ArrayList<Acumulativo> datos) {
        super(context, R.layout.milistlayout, datos);
        this.context = context;
        this.datos = datos;
        // TODO Auto-generated constructor stub
    }

    /*Constructor de la clase, donde pasamos por parametro los datos a mostrar en la lista y el contexto*/
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.milistaacumulativos, null);
        Acumulativo miacum = getItem(position);

        TextView title = (TextView) item.findViewById(R.id.codigoasignatura);
        title.setText(String.valueOf(miacum.getidAcumlativo()));

        TextView descrip = (TextView) item.findViewById(R.id.descripcionacumulativo);
        descrip.setText(miacum.getNombre());

        TextView valor = (TextView) item.findViewById(R.id.tValor);
        valor.setText(String.valueOf(miacum.getValor()));


        return item;
    }
    /*Este metodo es el mas importante ya que es donde inflamos el fichero lista_tema.xml en cada item
     * de la lista y despues a ambos TextView dentro de ficehro le insertamos los datos que queremos que muestre */

}
