public abstract class Ability {
    int damage;
    boolean stun;
    boolean dodge;
    int cooldown;

    int readyToUse;

    public Ability(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
        this.readyToUse = 0;
    }

    public boolean isDodge(){
        return dodge && readyToUse == 0;
    }
}
