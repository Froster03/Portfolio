package com.example.frostd.pokemonreference;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FragMain extends Activity{

    public static String POKEMONNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_container);
        getActionBar().hide();
        Bundle extras= getIntent().getExtras();

        if(extras!=null){
           POKEMONNAME= extras.getString("PokeSelected");
        }
        if (savedInstanceState== null){

            Fragment frag= new FillPokemonImage();
            Bundle args= new Bundle();
            args.putString("Name", POKEMONNAME);
            frag.setArguments(args);

            FragmentManager manager= getFragmentManager();
            FragmentTransaction trans= manager.beginTransaction();
            trans.add(R.id.PokemonImageContainer, frag);
            frag= new PokeMenu();
            frag.setArguments(args);
            trans.add(R.id.optionContainer, frag);
            trans.commit();
        }


    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (savedInstanceState== null){
               /* getSupportFragmentManager().beginTrasaction().add(R.id.PokemonImageContainer,new FillPokemonImage())
                        .commit();*/
            }
            View rootView = inflater.inflate(R.layout.frag_container, container, false);
            return rootView;
        }
    }
}
