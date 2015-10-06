package com.example.frostd.pokemonreference;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by frostd on 2/24/15.
 */
public class FillPokemonImage extends Fragment {

    String PokemonName;
    View rootView;
    public FillPokemonImage(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        PokemonName= getArguments().getString("Name");
        rootView= inflater.inflate(R.layout.frag_poke_image, container,false);
        fillInfo();
        return rootView;
    }
    public void fillInfo()
    {
        //home/frostd/cs310/PokemonReference/app/src/main/assets/altaria_sprite.gif
        String path= "file:///android_asset/"+PokemonName.toLowerCase()+"_sprite.gif";
       // String html="<html><body><img style=\"width: 100%\" src=\"" + path + "\"></body></html>";
        WebView image= (WebView) rootView.findViewById(R.id.pokeImage);
        image.getSettings().setUseWideViewPort(true);
        image.getSettings().setLoadWithOverviewMode(true);

        image.loadUrl(path);
        //image.getSettings().set

        TextView nameField = (TextView) rootView.findViewById(R.id.PokeNameView);
        nameField.setText(PokemonName);
    }

}
