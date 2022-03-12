public class Item {
    String name;
    int HP;
    Attack attack;
    int normalDefense;
    int specialDefense;

    public static class ItemBuilder{
        private String name;
        private int HP;
        private Attack attack;
        private int normalDefense;
        private int specialDefense;

        public ItemBuilder withName(String name){
            this.name = name;
            return this;
        }
        public ItemBuilder withHP(int HP){
            this.HP = HP;
            return this;
        }
        public ItemBuilder withAttack(Attack attack){
            this.attack = attack;
            return this;
        }
        public ItemBuilder withDefenses(int normalDefense, int specialDefense){
            this.normalDefense = normalDefense;
            this.specialDefense = specialDefense;
            return this;
        }

        public Item build(){
            Item item = new Item();
            item.name = this.name;
            item.HP = this.HP;
            item.attack = this.attack;
            item.normalDefense = this.normalDefense;
            item.specialDefense = this.specialDefense;

            return item;
        }
    }
}
