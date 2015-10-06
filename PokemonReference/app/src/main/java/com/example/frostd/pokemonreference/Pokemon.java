package com.example.frostd.pokemonreference;

/**
 * Created by frostd on 2/17/15.
 */
public class Pokemon {
    private String name;
    private int[][] Stats= new int[3][6];
    private String move_Set;
    private String type;
    private String abilities;

    public Pokemon(String inName) //Must send it a name to get file
    {
        name= inName;
        String PokemonInfo= name+".txt";
       //File to onPokemon

    }
    public String getMoveSet()
    {
        return move_Set;
    }
    public String getType()
    {
        return type;
    }
    public String getAbilities()
    {
        return abilities;
    }
    public void updateStat(int[] InStats, int lvl)
    {
        for(int i=0;i<6;i--)
        {
            InStats[1]= Stats[lvl][i];
        }
    }
}
