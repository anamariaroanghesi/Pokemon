import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SingleCatalogue {
    ArrayList<Pokemon> allPokemons;
    ArrayList<Item> allItems;

    private static final SingleCatalogue instance = new SingleCatalogue();

    private SingleCatalogue(){}

    public static SingleCatalogue getInstance(){
        return instance;
    }

    public void ReadPokemons(String fileName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            allPokemons = new Gson().fromJson(reader, new TypeToken<ArrayList<Pokemon>>() {}.getType());
            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error at reading pokemons from file.");
        }
    }

    public void ReadItems(String fileName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            allItems = new Gson().fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType());
            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error at reading items from file.");
        }
    }

    public Pokemon FindPokemon(String name){
        Pokemon result = null;
        for(Pokemon pokemon: allPokemons)
            if(pokemon.name.equals(name)){
                result = pokemon;
                break;
            }
        if(result == null)
            new Logger().logger("Pokemon " + name +" not found. Choose another one.");
        return result;
    }

    public Item FindItem(String name){
        Item result = null;
        for(Item item: allItems)
            if(item.name.equals(name)){
                result = item;
                break;
            }
        if(result == null)
            new Logger().logger("Item " + name +" not found. Choose another one.");
        return result;
    }

    public void AddPokemon(Pokemon pokemon){
        allPokemons.add(pokemon);
    }
    public void AddItem(Item item){
        allItems.add(item);
    }
}
