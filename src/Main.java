import java.io.File;

public class Main {
    public static void main(String[] args) {
        final File file = new java.io.File("output1.txt");
        SingleCatalogue catalogue = SingleCatalogue.getInstance();
        catalogue.ReadPokemons("pokemons.json");
        catalogue.ReadItems("items.json");

        Pokemon pokemon = new Pokemon.PokemonBuilder()
                .Name("Gyarados").HP(90)
                .withAttack(new Attack(0, 7))
                .withDefenses(6,6)
                .withAbilities(new Ability1(7, true, false, 5),
                        new Ability2(6, false, false, 4)).build();

        catalogue.AddPokemon(pokemon);

        int i = 1;
        {
            Pokemon neutral1 = new Pokemon(catalogue.allPokemons.get(0));
            Pokemon neutral2 = new Pokemon(catalogue.allPokemons.get(1));

            Antrenor antrenor1 = new Antrenor();
            Antrenor antrenor2 = new Antrenor();

            antrenor1.Read("Tests/Test"+i+"/antrenor1.txt", catalogue);
            antrenor2.Read("Tests/Test"+i+"/antrenor2.txt", catalogue);

            Adventure adventure = new Adventure(neutral1, neutral2);

            for(int j = 0; j < 3; j++){
                Pokemon pokemon1 = antrenor1.pokemons.get(j);
                Pokemon pokemon2 = antrenor2.pokemons.get(j);

                pokemon1.addItems();
                pokemon2.addItems();

                adventure.start(pokemon1, pokemon2);
            }
            BattleThreads finalBattle = new BattleThreads();
            Pokemon bestPokemon1 = antrenor1.bestPokemon();
            Pokemon bestPokemon2 = antrenor2.bestPokemon();

            finalBattle.start(bestPokemon1, bestPokemon2);
            new Logger().logger("!FINAL BATTLE WINNER!");
        }
    }
}
