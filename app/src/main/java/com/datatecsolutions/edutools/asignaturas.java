package com.datatecsolutions.edutools;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.datatecsolutions.edutools.modelo.EduToolsDb;

import com.datatecsolutions.edutools.modelo.adaptadorListaAsignaturas;
import com.datatecsolutions.edutools.modelo.adaptadorMenuInstituto;
import com.datatecsolutions.edutools.modelo.instituto;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.SQLException;
import java.util.ArrayList;


public class asignaturas extends ActionBarActivity {
    private ListView list;
    adaptadorListaAsignaturas miadaptador;
    private EduToolsDb baseDatos = new EduToolsDb(this);
    private instituto insti;
    private ActionBar actionBar;
    ArrayList<instituto> lista = new ArrayList<>();
    private adaptadorMenuInstituto adapter;
    PopupMenu popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        insti = new instituto(getApplicationContext());
        // guardarIns();
        //guardarEstudiantes();
        lista = insti.listarInstituto();

        adapter = new adaptadorMenuInstituto(getApplicationContext(), lista);
        //guardar();

        list = (ListView) findViewById(R.id.listaasignaturas);

        NameValuePair[] datosLista = consultar("select id_clase,nombre from clase");
        miadaptador = new adaptadorListaAsignaturas(this, datosLista);
        list = (ListView) findViewById(R.id.listaasignaturas);
        list.setAdapter(miadaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                //Object listItem = list.getItemAtPosition(position);
                String codigo_clase, nombre_asignatura;
                TextView v = (TextView) view.findViewById(R.id.codigoasignatura);
                TextView vasignatura = (TextView) view.findViewById(R.id.nombreasignatura);

                codigo_clase = v.getText().toString();
                nombre_asignatura = vasignatura.getText().toString();
                Intent i = new Intent(getApplicationContext(), acumulativos.class);
                i.putExtra("Codigo", codigo_clase);
                i.putExtra("Asignatura", nombre_asignatura);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asignaturas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filtrar) {

            View menuItemView = findViewById(R.id.filtrar);
            PopupMenu popup = new PopupMenu(asignaturas.this, menuItemView);

            popup.getMenu().add(Menu.NONE, '0', Menu.NONE, "Todos");
            if (lista.size() > 0) {
                for (int x = 0; x < lista.size(); x++) {
                    popup.getMenu().add(Menu.NONE, lista.get(x).getIdinstituto(), Menu.NONE, lista.get(x).getNombre());
                }
            }
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem xitem) {
                    //Toast.makeText(asignaturas.this,"You Clicked : " + xitem.getItemId(),Toast.LENGTH_SHORT).show();
                    recargarAsignaturas(xitem.getItemId());
                    return true;
                }
            });
            popup.show();

        }

        return super.onOptionsItemSelected(item);
    }

    public NameValuePair[] consultar(String sqlQuery) {
        ArrayList<String> resultado = new ArrayList<String>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        Cursor datos;
        datos = db.rawQuery(sqlQuery, null);
        NameValuePair[] datosLista = new NameValuePair[datos.getCount()];
        int y = 0;

        while (datos.moveToNext()) {
            resultado.add(datos.getString(1));

            datosLista[y] = new BasicNameValuePair(Integer.toString(datos.getInt(0)), datos.getString(1));
            y++;
        }

        return datosLista;

    }

    public void guardar() {
        try {
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.execSQL("Insert into clase(id_clase,nombre) values(1,'Informatica I')");
            db.execSQL("Insert into clase(id_clase,nombre) values(2,'Informatica II')");
            db.execSQL("Insert into clase(id_clase,nombre) values(3,'Progamacion Estructura II')");
            db.execSQL("Insert into clase(id_clase,nombre) values(4,'Logica Formal')");
            db.execSQL("Insert into clase(id_clase,nombre) values(4,'Base de Datos')");
            db.execSQL("Insert into clase(id_clase,nombre) values(5,'Programacion de IA')");
        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataops_", ex.getMessage().toString());
        }
    }

    public void guardarIns() {
        try {
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.execSQL("Insert into instituto(id_instituto,nombre_instituto) values(1,'San Juan')");
            db.execSQL("Insert into instituto(id_instituto,nombre_instituto) values(2,'IFJM Mejia')");
            db.execSQL("Insert into instituto(id_instituto,nombre_instituto) values(3,'Privado Minerva')");

        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataops_", ex.getMessage().toString());
        }

    }

    public void guardarEstudiantes() {
        try {
            SQLiteDatabase db = baseDatos.getWritableDatabase();
            db.execSQL("Insert into alumnos(rne,nombre) values('0105199900192','ELSY JAKELIN CRUZ MEJIA')");
            db.execSQL("Insert into alumnos(rne,nombre) values('0105199800509','LISBETH NICOLE SOSA VASQUEZ')");
            db.execSQL("Insert into alumnos(rne,nombre) values('0105199700354','DEYBIZ DONALDO VELASQUEZ GUEVARA')");
            db.execSQL("Insert into alumnos(rne,nombre) values('0105199900305','EDAR EMANUEL VELASQUEZ MADSEN')");
            db.execSQL("Insert into alumnos(rne,nombre) values('0107199901143','ENIL BENIGNO ANDINO DELCID')");
            db.execSQL("Insert into alumnos(rne,nombre) values('0105199800200','LESMAN IVAN MATUTE TROCHEZ')");

            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0105199900192',1,2015)");
            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0105199800509',1,2015)");

            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0105199700354',1,2015)");
            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0105199900305',1,2015)");

            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0107199901143',1,2015)");
            db.execSQL("Insert into matricula(rne,id_seccion,annio) values('0105199800200',1,2015)");


        } catch (android.database.sqlite.SQLiteException ex) {
            Log.i("dataops_", ex.getMessage().toString());
        }
    }

    public void recargarAsignaturas(int codigo) {
        NameValuePair[] datosLista = consultar("select id_clase,nombre from clase where id_clase=" + codigo);
        miadaptador = new adaptadorListaAsignaturas(this, datosLista);
        list.setAdapter(miadaptador);
    }


}
