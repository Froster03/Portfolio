package com.example.frostd.pokemonreference;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by frostd on 2/24/15.
 */
public class PokeMenu extends Fragment {

    Fragment frag;
    FragmentTransaction trans;
    String  Pokemonname;

    public PokeMenu(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView= inflater.inflate(R.layout.frag_menu, container,false);
        Pokemonname= getArguments().getString("Name");
        
        frag= new PokeAttacks();
        Bundle args= new Bundle();
        args.putString("Name", Pokemonname);
        frag.setArguments(args);

        trans= getFragmentManager().beginTransaction().add(R.id.infoContainer, frag);
        trans.commit();

        Button attacks= (Button)rootView.findViewById(R.id.AtkOption);

        attacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new PokeAttacks();
                Bundle args= new Bundle();
                args.putString("Name", Pokemonname);
                frag.setArguments(args);

                trans= getFragmentManager().beginTransaction().replace(R.id.infoContainer, frag);
                trans.commit();

            }
        });

        Button stats= (Button) rootView.findViewById(R.id.StatsOption);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new PokeStats();
                Bundle args= new Bundle();
                args.putString("Name", Pokemonname);
                frag.setArguments(args);
                trans= getFragmentManager().beginTransaction().replace(R.id.infoContainer, frag);
                trans.commit();
            }
        });
        Button pokedex= (Button) rootView.findViewById(R.id.button3);

        pokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new Unavailable();
                Bundle args= new Bundle();
                trans= getFragmentManager().beginTransaction().replace(R.id.infoContainer, frag);
                trans.commit();
            }
        });
        Button type= (Button) rootView.findViewById(R.id.button4);

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new Unavailable();
                Bundle args= new Bundle();
                trans= getFragmentManager().beginTransaction().replace(R.id.infoContainer, frag);
                trans.commit();
            }
        });
        Button suggest= (Button) rootView.findViewById(R.id.button5);

        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new Unavailable();
                Bundle args= new Bundle();
                trans= getFragmentManager().beginTransaction().replace(R.id.infoContainer, frag);
                trans.commit();
            }
        });

        return rootView;

    }

    public void setPokemonname(String name)
    {
        Pokemonname= name;
    }
}
