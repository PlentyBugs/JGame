package creature.aggressive;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.passive.CriticalStrike;
import creature.Human;
import item.alchemy.ingredient.EntBranch;
import item.alchemy.ingredient.Leaf;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.PoisonPotion;
import item.Item;
import item.Key;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ent extends Human implements Aggressive {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(CreatureProperty.ENT);
    }

    public Ent(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;

        color = new Color(22,200, 116);
        uniqueDropItems = new Item[]{
                new HealPotion(),
                new PoisonPotion(),
                new EntBranch(),
                new Leaf(),
                new Leaf(),
                new Leaf(),
                new Key()
        };

        race = "Древесная";
    }

    public Ent(){
        this(0,0, "Энт",1,50);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl*50) + lvl*6));
        stats.setSpeed(5);
        stats.setAgility(5);
        stats.setIntelligence(5);
        stats.setLuck(5);
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOneHandedWeapon(0);
        stats.setTwoHandedWeapon(0);
        stats.setPoleWeapon(5);
        stats.setChoppingWeapon(5);
        stats.setLongRangeWeapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
        addAbility((new CriticalStrike(Math.max(0, Math.min(lvl/45, (new CriticalStrike()).getMaxLevel())))),
                new DamageUp(Math.max(0, Math.min(lvl/10, (new DecreaseDamage()).getMaxLevel()))));
    }

    @Override
    public Ent clone() throws CloneNotSupportedException
    {
        return (Ent) super.clone();
    }

    @Override
    public Ent getClearCopy() {
        return new Ent();
    }
}