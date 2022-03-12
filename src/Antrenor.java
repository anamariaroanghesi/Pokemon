import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Antrenor {
    String name;
    int age;
    ArrayList<Pokemon> pokemons;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void Read(String fileName, SingleCatalogue catalogue){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            line = br.readLine();
            String[] field = line.split("#");
            setName(field[0]);
            int age = Integer.parseInt(field[1]);
            setAge(age);

            line = br.readLine();
            String[] field1 = line.split("#");
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            for(int i = 0; i <3; i++){
                Pokemon pokemon = new Pokemon(catalogue.FindPokemon(field1[i]));

                line = br.readLine();
                String[] field2 = line.split("#");
                ArrayList<Item> items = new ArrayList<>();

                for(int j = 0; j < 3; j++)
                    items.add(catalogue.FindItem(field2[j]));

                pokemon.setItems(items);
                pokemons.add(pokemon);
                setPokemons(pokemons);
            }

        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Pokemon bestPokemon(){
        int max = 0;
        Pokemon result = null;
        for(Pokemon pokemon: pokemons)
            if(pokemon.totalPoints() >= max){
                result = pokemon;
                max = pokemon.totalPoints();
            }
        return result;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
