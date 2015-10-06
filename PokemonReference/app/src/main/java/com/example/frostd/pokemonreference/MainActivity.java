package com.example.frostd.pokemonreference;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.System.*;
import java.util.concurrent.Callable;


public class MainActivity extends Activity {
    public static final int MAX_POKE= 15;
    public static String[] POKE_NAME;
    public static String POKE_TIER[]={"OU","UU","NU"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillButton();

    }

public void fillButton()
{
    /*Loading POKE_NAME from file*/
    POKE_NAME= new String[MAX_POKE];
    InputStream Names= (InputStream) getResources().openRawResource(R.raw.pokemonnames);
    final Scanner sc = new Scanner(Names).useDelimiter("\\A");


    try
    {

        String[] currentLine;
        String buttonName;

        for (int i=0;i<POKE_NAME.length;i++)
        {
           currentLine= sc.nextLine().split(" ");
           POKE_NAME[i]= currentLine[0];
        }
        /*End of Loading POKE_NAME from file*/

        /*Loading ListView*/
        List<String> PokeArray= new ArrayList<String>();
        System.out.print("Here!");
        for(int i=0;i<POKE_NAME.length;i++)
        {
            PokeArray.add(POKE_NAME[i]);
        }

        ListView listV = (ListView) findViewById(R.id.PokemonNameList);//get list view

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                PokeArray);

        listV.setAdapter(adapter);
        listV.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectPokemon(position);

            }
        });

    }

    catch (Exception e)//if no file
    {
              POKE_NAME[0]="File Not found!";
    }//end of catch for file reading



}
public void SelectPokemon(int prosistion)
{
    Intent intent = new Intent(this, FragMain.class);

    intent.putExtra( "PokeSelected",POKE_NAME[prosistion]);
    startActivity(intent);

}
public void updateText(int x, int y)
{

  TextView text= (TextView) findViewById(R.id.Selected);
    if(y>0) {
        text.setText(POKE_TIER[x]);
    }
    else {
        text.setText(POKE_NAME[x]);
    }
}
}
