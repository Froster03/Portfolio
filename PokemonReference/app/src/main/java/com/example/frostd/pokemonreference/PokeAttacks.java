package com.example.frostd.pokemonreference;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Scanner;

/**
 * Created by frostd on 2/24/15.
 */
public class PokeAttacks extends Fragment {
    public String PokemonName;
    public View rootView;
    public PokeAttacks(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        PokemonName= getArguments().getString("Name");
        rootView= inflater.inflate(R.layout.frag_attacks, container,false);
        TextView text= (TextView) rootView.findViewById(R.id.PokeInfo);

        text.setText(PokemonName);
        fillAttacks();
        return rootView;
    }

    public void fillAttacks()
    {
        TextView text= (TextView) rootView.findViewById(R.id.PokeInfo);
        String filename= PokemonName.toLowerCase();
        String PokemonFileName= filename+ "_attacks";
        String err= "Not Available";

        try{
            //int recId=getResources().getIdentifier(PokemonFileName, );
           // err= recId+"";
           InputStream fileinput= (InputStream) getResources()
            .openRawResource(getResources().getIdentifier(PokemonFileName,"raw",rootView.getContext().getPackageName()));


            String fileText="";
            err="here";
            Scanner sc = new Scanner(fileinput);
            do {
                fileText=fileText+ sc.nextLine()+"\n";

            }while(sc.hasNextLine());

//            String fileText= sc.toString();
            text.setText(fileText);
        }

        catch(Exception e)
        {
            text.setText(err);
        }


    }
}
