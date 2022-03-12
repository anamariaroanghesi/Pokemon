public class Adventure {
    private final Pokemon neutral1;
    private final Pokemon neutral2;

    public Adventure(Pokemon neutral1, Pokemon neutral2){
        this.neutral1 = neutral1;
        this.neutral2 = neutral2;
    }

    public void start(Pokemon pokemon1, Pokemon pokemon2){
        int choice = -1;
        ContextBattle battle = new ContextBattle(new BattleVsNeutrals());
        new Logger().logger("\nADVENTURE " + pokemon1.name +" VS " + pokemon2.name);

        while(choice != 0){
            choice = (int)(Math.random() * 3);

            switch (choice) {
                case 2 -> {
                    updatePoints(battle.start(pokemon1, neutral2), pokemon1, neutral2);
                    updatePoints(battle.start(pokemon2, neutral2), pokemon2, neutral2);
                }
                case 1 -> {
                    updatePoints(battle.start(pokemon1, neutral1), pokemon1, neutral1);
                    updatePoints(battle.start(pokemon2, neutral1), pokemon2, neutral1);
                }
                case 0 -> {
                    battle = new ContextBattle(new BattleThreads());
                    updatePoints(battle.start(pokemon1, pokemon2), pokemon1, pokemon2);
                }
            }
        }
    }

    public void updatePoints(int choice, Pokemon pokemon1, Pokemon pokemon2){
        if(choice == 1)
            pokemon1.addPoints();
        if(choice == 2)
            pokemon2.addPoints();
    }
}
