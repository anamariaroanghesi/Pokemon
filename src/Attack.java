public class Attack implements Command{
    int normalAttack;
    int specialAttack;

    public Attack(int normalAttack, int specialAttack) {
        this.normalAttack = normalAttack;
        this.specialAttack = specialAttack;
    }

    public boolean isDodge(){
        return false;
    }

    public void execute(Pokemon pokemon){
        if(!pokemon.rival.dodged) {
            new Logger().logger(pokemon.name + " executa attack contra " + pokemon.rival.name);
            if (pokemon.attack.normalAttack != 0) {
                int damage = pokemon.attack.normalAttack - pokemon.rival.normalDefense;
                if (damage > 0)
                    pokemon.rival.HP -= damage;
            } else {
                int damage = pokemon.attack.specialAttack - pokemon.rival.specialDefense;
                if (damage > 0)
                    pokemon.rival.HP -= damage;
            }
        }else
            new Logger().logger(pokemon.name + " incearca sa execute attack contra " + pokemon.rival.name + " dar se fereste");
    }
}