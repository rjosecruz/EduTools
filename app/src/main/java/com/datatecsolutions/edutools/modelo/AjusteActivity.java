package com.datatecsolutions.edutools.modelo;

import android.app.ListActivity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.edutools.R;

/**
 * Created by Juana Maria on 13/03/2015.
 */
public class AjusteActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Simulamos que extraemos los datos de la base de datos a un cursor
        String[] columnasBD = new String[]{"_id", "textoSuperior", "textoInferior"};
        MatrixCursor cursor = new MatrixCursor(columnasBD);
        cursor.addRow(new Object[]{"0", "Perfil Docente", "Descripcion..."});
        cursor.addRow(new Object[]{"1", "Seguridad", "Descripcion..."});
        cursor.addRow(new Object[]{"2", "Respaldos", "Descripcion..."});
        cursor.addRow(new Object[]{"3", "Sincronizar al Sace", "Descripcion..."});

        //Añadimos los datos al Adapter y le indicamos donde dibujar cada dato en la fila del Layout
        String[] desdeEstasColumnas = {"textoSuperior", "textoInferior"};
        int[] aEstasViews = {R.id.textView_superior, R.id.textView_inferior};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_ajuste, cursor, desdeEstasColumnas, aEstasViews, 0);

        ListView listado = getListView();
        listado.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView lista, View view, int posicion, long id) {
        // Hacer algo cuando un elemento de la lista es seleccionado
        TextView textoTitulo = (TextView) view.findViewById(R.id.textView_superior);

        CharSequence texto = "Seleccionado: " + textoTitulo.getText();
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();

    }

}

