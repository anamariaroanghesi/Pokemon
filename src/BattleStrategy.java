public interface BattleStrategy {
    int start(Pokemon pokemon1, Pokemon pokemon2);
}
class ContextBattle {
    private final BattleStrategy battleStrategy;

    public ContextBattle(BattleStrategy strategy){
        this.battleStrategy = strategy;
    }

    public int start(Pokemon pokemon1, Pokemon pokemon2){
        return battleStrategy.start(pokemon1, pokemon2);
    }
}
