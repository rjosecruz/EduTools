package com.datatecsolutions.edutools;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.Acumulativo;
import com.datatecsolutions.edutools.modelo.EduToolsDb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class crear_acumulativo extends ActionBarActivity {
    Bundle extras;
    String codigo, nombre;
    EditText textnombre, textseccioncod;
    Spinner parcialesSpinner;
    EditText descripcion;
    EditText fecha;
    EditText valor;

    Spinner tipoAcumulativoSpinner;
    EduToolsDb baseDatos = new EduToolsDb(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_acumulativo);
        textnombre = (EditText) findViewById(R.id.eTnombreSeccion);
        textseccioncod = (EditText) findViewById(R.id.etcodigoSeccion);
        descripcion = (EditText) findViewById(R.id.eTDescripcion);
        parcialesSpinner = (Spinner) findViewById(R.id.spParcial);
        tipoAcumulativoSpinner = (Spinner) findViewById(R.id.spTipoAcum);
        fecha = (EditText) findViewById(R.id.etfechaentrega);
        valor = (EditText) findViewById(R.id.etValor);
        textnombre.setEnabled(false);
        extras = getIntent().getExtras();
        codigo = extras.getString("codigo_clase");
        nombre = extras.getString("nombre_clase");
        textnombre.setText(nombre);
        textseccioncod.setText(codigo);
        textseccioncod.setEnabled(false);


        List<String> listTipoAcumulativo = new ArrayList<String>();
        listTipoAcumulativo.add("Clase");
        listTipoAcumulativo.add("Examen");


        ArrayAdapter<String> dataAdapterTipoAcum = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, listTipoAcumulativo);
        dataAdapterTipoAcum.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        tipoAcumulativoSpinner.setAdapter(dataAdapterTipoAcum);

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        parcialesSpinner.setAdapter(dataAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_acumulativo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.guardar_acumulativo) {
            guardar();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardar() {
        ContentValues nuevoRegistro = new ContentValues();
        try {

            nuevoRegistro.put("id_acumulativo", "rowid");
            nuevoRegistro.put("id_seccion", codigo);
            nuevoRegistro.put("descripcion", String.valueOf(descripcion.getText()));
            nuevoRegistro.put("id_tipo_acumulativo", String.valueOf(tipoAcumulativoSpinner.getSelectedItem().toString()));
            nuevoRegistro.put("fecha", String.valueOf(fecha.getText()));
            nuevoRegistro.put("valor", String.valueOf(valor.getText()));
            nuevoRegistro.put("id_clase", codigo);
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.insert("acumulativos", null, nuevoRegistro);
            Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_LONG).show();
        } catch (android.database.sqlite.SQLiteException ex) {

            Log.i("dataops_", ex.getMessage().toString());
        }
    }


}
