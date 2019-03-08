package Creatures.AggressiveNPC;

import Abilities.Passive.CriticalStrike;
import Creatures.Human;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Item;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Swords.Sword;

import java.awt.*;

public class Goblin extends Human {

    public Goblin(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;

        color = new Color(170,200,10);
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/10, (new CriticalStrike()).getMaxLevel()))));
        uniqueDropItems = new Item[]{new Sword(), new HealPotion(), new PoisonPotion(), new ShortBow(), new Axe()};
    }

    public Goblin(){
        this(0,0, "Гоблин",1,50);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl+2) + lvl*6));
        stats.setSpeed(5 + (int)(Math.random()*(lvl+2) + lvl*2));
        stats.setAgility(5 + (int)(Math.random()*(lvl+2) + lvl*4));
        stats.setIntelligence(5);
        stats.setLuck(5);
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*5));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl+8) + lvl*6));
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5);
        stats.setLong_range_weapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
    }

    @Override
    public Goblin clone() throws CloneNotSupportedException
    {
        return (Goblin) super.clone();
    }

    @Override
    public Goblin getClearCopy() {
        return new Goblin();
    }
}