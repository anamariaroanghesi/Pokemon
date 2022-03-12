
public interface Command {
    void execute(Pokemon pokemon);
    boolean isDodge();
}

class Ability1 extends Ability implements Command{
    public Ability1 (int damage, boolean stun, boolean dodge, int cooldown){
        super(damage, stun, dodge, cooldown);
    }

    public void execute(Pokemon pokemon) {
        if (this.readyToUse == 0) {
            if (!pokemon.rival.dodged) {
                new Logger().logger(pokemon.name + " executa ability1 contra " + pokemon.rival.name);
                readyToUse = cooldown;
                pokemon.rival.HP -= damage;
                pokemon.rival.stunned = stun;
            }
            else
                new Logger().logger(pokemon.name + " incearca sa execute ability1 contra " + pokemon.rival.name + " dar se fereste");
        }else
            pokemon.attack.execute(pokemon);
    }
}

class Ability2 extends Ability implements Command{
    public Ability2 (int damage, boolean stun, boolean dodge, int cooldown){
        super(damage, stun, dodge, cooldown);
    }

    public void execute(Pokemon pokemon) {
        if (this.readyToUse == 0) {
            if (!pokemon.rival.dodged) {
                new Logger().logger(pokemon.name + " executa ability2 contra " + pokemon.rival.name);
                readyToUse = cooldown;
                pokemon.rival.HP -= damage;
                pokemon.rival.stunned = stun;
            }
            else
                new Logger().logger(pokemon.name + " incearca sa execute ability2 contra " + pokemon.rival.name + " dar se fereste");
        }else
            pokemon.attack.execute(pokemon);
    }
}
