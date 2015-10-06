package com.example.frostd.pokemonreference;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;


/**
 * Created by frostd on 2/24/15.
 */
public class PokeStats extends Fragment {
    public static String POKE_LVLS[]={"Base","50","100"};
    private View rootView;
    public String PokemonName;
    public String Base[]= new String[6];
    public String Hindering[]= new String[6];
    public String Neutral[]= new String[6];
    public String Benefical[]= new String[6];
    public String HinderingMax[]= new String[6];
    public String NeutralMax[]= new String[6];
    public String BeneficalMax[]= new String[6];
    public PokeStats(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        PokemonName= getArguments().getString("Name");
        rootView= inflater.inflate(R.layout.frag_poke_stats, container,false);
        fillInfo();
        fillSpinner();
        fillStats(0);
        return rootView;
    }
    public void fillInfo()
    {

        String filename= PokemonName.toLowerCase();
        String PokemonFileName= filename+ "_stats";
        String err= PokemonFileName;
        String CurrentLine[];

        try{
            InputStream fileinput= (InputStream) getResources()
                    .openRawResource(getResources().getIdentifier(PokemonFileName,"raw",rootView.getContext().getPackageName()));



            Scanner sc = new Scanner(fileinput);
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                Base[i]=CurrentLine[i];
            }
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                Hindering[i]=CurrentLine[i];
            }
            for (int i =0; i<Base.length;i++)
            {
                HinderingMax[i]=CurrentLine[i];
            }
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                Neutral[i]=CurrentLine[i];
            }
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                NeutralMax[i]=CurrentLine[i];
            }
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                Benefical[i]=CurrentLine[i];
            }
            CurrentLine=sc.nextLine().split("~");
            for (int i =0; i<Base.length;i++)
            {
                BeneficalMax[i]=CurrentLine[i];
            }



        }

        catch(Exception e)
        {

        }

    }
    public void fillSpinner()
    {
        Spinner MySpinner = (Spinner) rootView.findViewById(R.id.LvlSpinner);//get list view

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_expandable_list_item_1,
                POKE_LVLS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MySpinner.setAdapter(adapter);
        MySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillStats(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void fillStats(int selectedLvl)
    {
        TextView Hp= (TextView) rootView.findViewById(R.id.pokeHP);
        TextView Atk= (TextView) rootView.findViewById(R.id.pokeAtk);
        TextView Def= (TextView) rootView.findViewById(R.id.pokeDef);
        TextView SpAtk= (TextView) rootView.findViewById(R.id.pokeSp_Atk);
        TextView SpDef= (TextView) rootView.findViewById(R.id.pokeSp_Def);
        TextView Spd= (TextView) rootView.findViewById(R.id.pokeSpd);
        TextView HpN= (TextView) rootView.findViewById(R.id.pokeHPN);
        TextView AtkN= (TextView) rootView.findViewById(R.id.pokeAtkN);
        TextView DefN= (TextView) rootView.findViewById(R.id.pokeDefN);
        TextView SpAtkN= (TextView) rootView.findViewById(R.id.pokeSp_AtKN);
        TextView SpDefN= (TextView) rootView.findViewById(R.id.pokeSp_DefN);
        TextView SpdN= (TextView) rootView.findViewById(R.id.pokeSpdN);
        TextView HpB= (TextView) rootView.findViewById(R.id.pokeHPB);
        TextView AtkB= (TextView) rootView.findViewById(R.id.pokeAtkB);
        TextView DefB= (TextView) rootView.findViewById(R.id.pokeDefB);
        TextView SpAtkB= (TextView) rootView.findViewById(R.id.pokeSp_AtkB);
        TextView SpDefB= (TextView) rootView.findViewById(R.id.pokeSp_DefB);
        TextView SpdB= (TextView) rootView.findViewById(R.id.pokeSpdB);


        if(selectedLvl==0)
        {
            Hp.setText(Base[0]);
            Atk.setText(Base[1]);
            Def.setText(Base[2]);
            SpAtk.setText(Base[3]);
            SpDef.setText(Base[4]);
            Spd.setText(Base[5]);

            HpN.setText("");
            AtkN.setText("");
            DefN.setText("");
            SpAtkN.setText("");
            SpDefN.setText("");
            SpdN.setText("");

            HpB.setText("");
            AtkB.setText("");
            DefB.setText("");
            SpAtkB.setText("");
            SpDefB.setText("");
            SpdB.setText("");


        }
        else if (selectedLvl==1)
        {
            Hp.setText(Hindering[0]);
            Atk.setText(Hindering[1]);
            Def.setText(Hindering[2]);
            SpAtk.setText(Hindering[3]);
            SpDef.setText(Hindering[4]);
            Spd.setText(Hindering[5]);

            HpN.setText(Neutral[0]);
            AtkN.setText(Neutral[1]);
            DefN.setText(Neutral[2]);
            SpAtkN.setText(Neutral[3]);
            SpDefN.setText(Neutral[4]);
            SpdN.setText(Neutral[5]);

            HpB.setText(Benefical[0]);
            AtkB.setText(Benefical[1]);
            DefB.setText(Benefical[2]);
            SpAtkB.setText(Benefical[3]);
            SpDefB.setText(Benefical[4]);
            SpdB.setText(Benefical[5]);

        }
        else
        {
            Hp.setText(HinderingMax[0]);
            Atk.setText(HinderingMax[1]);
            Def.setText(HinderingMax[2]);
            SpAtk.setText(HinderingMax[3]);
            SpDef.setText(HinderingMax[4]);
            Spd.setText(HinderingMax[5]);

            HpN.setText(NeutralMax[0]);
            AtkN.setText(NeutralMax[1]);
            DefN.setText(NeutralMax[2]);
            SpAtkN.setText(NeutralMax[3]);
            SpDefN.setText(NeutralMax[4]);
            SpdN.setText(NeutralMax[5]);

            HpB.setText(BeneficalMax[0]);
            AtkB.setText(BeneficalMax[1]);
            DefB.setText(BeneficalMax[2]);
            SpAtkB.setText(BeneficalMax[3]);
            SpDefB.setText(BeneficalMax[4]);
            SpdB.setText(BeneficalMax[5]);
        }



    }
}
