import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Pokemon implements Runnable{
    String name;
    int HP;
    Attack attack;
    int normalDefense;
    int specialDefense;
    Ability1 ability1;
    Ability2 ability2;
    ArrayList<Item> items = new ArrayList<>();

    boolean dodged;
    boolean stunned;
    public Pokemon rival;

    public int choice;

    private Pokemon(){}

    public Pokemon(Pokemon pokemon){
        this.name =pokemon.name;
        this.HP = pokemon.HP;
        this.attack = pokemon.attack;
        this.normalDefense = pokemon.normalDefense;
        this.specialDefense = pokemon.specialDefense;
        this.ability1 = pokemon.ability1;
        this.ability2 = pokemon.ability2;
    }

    public void setRival(Pokemon rival) {
        this.rival = rival;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public void run() {
        Command[] listOfCommands = new Command[3];
        listOfCommands[0] = attack;
        listOfCommands[1] = ability1;
        listOfCommands[2] = ability2;

        choice = (int)(Math.random() * 3);
        if(listOfCommands[choice] == null)
            choice = 0;

        if(!stunned)
            this.dodged = listOfCommands[choice].isDodge();

        if(!stunned){
            listOfCommands[choice].execute(this);
        }else {
            new Logger().logger(name + " stunned");
            stunned = false;
        }

        updateAbilities();
    }

    public void updateAbilities(){
        if(ability1 != null && ability1.readyToUse > 0)
            ability1.readyToUse--;
        if(ability2 != null && ability2.readyToUse > 0)
            ability2.readyToUse--;
    }

    public void addPoints(){
        HP ++;
        if(attack.normalAttack != 0)
            attack.normalAttack++;
        else
            attack.specialAttack++;
        normalDefense++;
        specialDefense++;
    }

    public void addItems(){
        for(Item item: this.items) {
            HP += item.HP;
            if (attack.normalAttack != 0)
                attack.normalAttack += item.attack.normalAttack;
            else
                attack.specialAttack += item.attack.specialAttack;
            normalDefense += item.normalDefense;
            specialDefense += item.specialDefense;
        }
    }

    public int totalPoints(){
        return HP + attack.normalAttack + attack.specialAttack + normalDefense + specialDefense;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static class PokemonBuilder {
        private String name;
        private int HP;
        private Attack attack;
        private int normalDefense;
        private int specialDefense;

        private Ability1 ability1;
        private Ability2 ability2;

        public PokemonBuilder Name(String name){
            this.name = name;
            return this;
        }
        public PokemonBuilder HP(int HP){
            this.HP = HP;
            return this;
        }
        public PokemonBuilder withAttack(Attack attack){
            this.attack = attack;
            return this;
        }
        public PokemonBuilder withDefenses(int normalDefense, int specialDefense){
            this.normalDefense = normalDefense;
            this.specialDefense = specialDefense;
            return this;
        }
        public PokemonBuilder withAbilities(Ability1 ability1, Ability2 ability2){
            this.ability1 = ability1;
            this.ability2 = ability2;
            return this;
        }
        public Pokemon build(){
            Pokemon pokemon = new Pokemon();
            pokemon.name = this.name;
            pokemon.HP = this.HP;
            pokemon.attack = this.attack;
            pokemon.normalDefense = this.normalDefense;
            pokemon.specialDefense = this.specialDefense;
            pokemon.ability1 = this.ability1;
            pokemon.ability2 = this.ability2;

            return pokemon;
        }
    }
}
