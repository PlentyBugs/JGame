package LiveCreatures;

import Effects.Effect;
import Items.Item;

import java.util.ArrayList;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected double hp;
    protected int maxHp;
    protected int lvl;
    protected int money = 0;
    protected ArrayList<Effect> effects = new ArrayList<Effect>();
    protected Stats stats = new Stats();
    protected Item[] uniqueDropItems;


    public LiveCreature(int x, int y, String name, int lvl, int hp){

        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
    }

    public double getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setMaxHp(int hp) {
        this.maxHp = maxHp;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void addEffect(Effect effect){
        effects.add(effect);
    }

    public Stats getStats() {
        return stats;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void reduceMoney(int money) {
        this.money -= money;
    }

    public Item[] getUniqueDropItems() {
        return uniqueDropItems;
    }

    public void useMomentEffect(Effect effect){
        effect.use(this);
    }

    public void countStatsAfterBorn(){}
}
