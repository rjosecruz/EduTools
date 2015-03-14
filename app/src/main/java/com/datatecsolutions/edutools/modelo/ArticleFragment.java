package com.datatecsolutions.edutools.modelo; /**
 * Created by rj on 27/02/2015.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datatecsolutions.edutools.R;


public class ArticleFragment extends Fragment {
    public static final String ARG_ARTICLES_NUMBER = "articles_number";

    public ArticleFragment() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);
        int i = getArguments().getInt(ARG_ARTICLES_NUMBER);
        String article = getResources().getStringArray(R.array.itemsdrawer)[i];

        getActivity().setTitle(article);
        TextView headline = (TextView) rootView.findViewById(R.id.headline);
        headline.append(" " + article);

        return rootView;
    }
}
