package com.example.frostd.pokemonreference;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;


/**
 * Created by frostd on 2/24/15.
 */
public class Unavailable extends Fragment {

    private View rootView;

    public Unavailable(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView= inflater.inflate(R.layout.frag_unavailable, container,false);

        return rootView;
    }

}
