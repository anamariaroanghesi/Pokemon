import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BattleThreads implements BattleStrategy{

    public int start(Pokemon pokemonA, Pokemon pokemonB){
        new Logger().logger("\nBatalie " + pokemonA.name + " contra " + pokemonB.name);
        int attackNr = 1;

        Pokemon pokemon1 = new Pokemon(pokemonA);
        Pokemon pokemon2 = new Pokemon(pokemonB);

        pokemon1.setRival(pokemon2);
        pokemon2.setRival(pokemon1);

        ExecutorService executor;

        while(pokemon1.HP > 0 && pokemon2.HP > 0)
        {
            new Logger().logger("ROUND " + attackNr);
            executor = Executors.newFixedThreadPool(2);
            executor.execute(pokemon1);
            executor.execute(pokemon2);

            executor.shutdown();
            try{
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            new Logger().logger(pokemon1.name + " HP:"+pokemon1.HP+" dodged:"+pokemon1.dodged+" stunned:"+pokemon1.stunned);
            new Logger().logger(pokemon2.name + " HP:"+pokemon2.HP+" dodged:"+pokemon2.dodged+" stunned:"+pokemon2.stunned);
            pokemon1.dodged = false;
            pokemon2.dodged = false;
            attackNr++;
        }

        if(pokemon1.HP > 0) {
            new Logger().logger("\nWINNER: antrenorul1 with " + pokemon1.name);
            return 1;
        }
        if(pokemon2.HP > 0) {
            new Logger().logger("\nWINNER: antrenorul2 with " + pokemon2.name);
            return 2;
        }

        new Logger().logger("\nDRAW!");
        return 0;
    }

}
