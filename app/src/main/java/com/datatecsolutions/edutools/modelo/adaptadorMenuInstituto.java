package com.datatecsolutions.edutools.modelo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datatecsolutions.edutools.R;

import java.util.ArrayList;

/**
 * Created by rj on 21/02/2015.
 */
public class adaptadorMenuInstituto extends BaseAdapter {
    private TextView txtTitle;
    private TextView id;
    private ArrayList<instituto> instNavItem;
    private Context context;


    public adaptadorMenuInstituto(Context context,
                                  ArrayList<instituto> spinnerNavItem) {
        this.instNavItem = spinnerNavItem;
        this.context = context;
    }


    @Override
    public int getCount() {
        return instNavItem.size();
    }

    @Override
    public Object getItem(int position) {

        return instNavItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.layoutmenu, null);
        }

        id = (TextView) convertView.findViewById(R.id.tvcodigoInstituto);
        txtTitle = (TextView) convertView.findViewById(R.id.tvNombreInstituto);


        txtTitle.setText(instNavItem.get(position).getNombre());
        id.setText(instNavItem.get(position).getIdinstituto());
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.layoutmenu, null);
        }

        id = (TextView) convertView.findViewById(R.id.tvcodigoInstituto);
        txtTitle = (TextView) convertView.findViewById(R.id.tvNombreInstituto);

        txtTitle.setText(instNavItem.get(position).getNombre());
        id.setText(instNavItem.get(position).getIdinstituto());
        return convertView;
    }
}
