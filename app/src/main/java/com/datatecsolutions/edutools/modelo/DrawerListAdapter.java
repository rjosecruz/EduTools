package com.datatecsolutions.edutools.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.datatecsolutions.edutools.R;

import java.util.List;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {


    public DrawerListAdapter(Context context, List<DrawerItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // convertView = inflater.inflate(R.layout.drawer_list_item, null);
        }


        DrawerItem item = getItem(position);


        return convertView;
    }
}
