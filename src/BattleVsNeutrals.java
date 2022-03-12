public class BattleVsNeutrals implements BattleStrategy{

    public int start(Pokemon pokemonA, Pokemon pokemonB){
        new Logger().logger("\nBatalie " + pokemonA.name + " contra " + pokemonB.name);
        int attackNr = 1;

        Pokemon pokemon1 = new Pokemon(pokemonA);
        Pokemon pokemon2 = new Pokemon(pokemonB);

        pokemon1.setRival(pokemon2);
        pokemon2.setRival(pokemon1);

        Command[] listOfCommands1 = new Command[3];
        listOfCommands1[0] = pokemon1.attack;
        listOfCommands1[1] = pokemon1.ability1;
        listOfCommands1[2] = pokemon1.ability2;

        while(pokemon1.HP > 0 && pokemon2.HP > 0)
        {
            new Logger().logger("ROUND " + attackNr);
            int choice = (int)(Math.random() * 3);

            if(!pokemon1.stunned)
                pokemon1.dodged = listOfCommands1[choice].isDodge();

            if(!pokemon2.stunned)
                pokemon2.attack.execute(pokemon2);
            else {
                new Logger().logger(pokemon2.name + " stunned");
                pokemon2.stunned = false;
            }

            listOfCommands1[choice].execute(pokemon1);

            new Logger().logger(pokemon1.name + " HP:"+pokemon1.HP+" dodged:"+pokemon1.dodged+" stunned:"+pokemon1.stunned);
            new Logger().logger(pokemon2.name + " HP:"+pokemon2.HP+" dodged:"+pokemon2.dodged+" stunned:"+pokemon2.stunned);

            pokemon1.dodged = false;
            pokemon1.updateAbilities();
            attackNr++;
        }

        if(pokemon1.HP > 0)
            return 1;
        if(pokemon2.HP > 0)
            return 2;

        return 0;
    }

}
