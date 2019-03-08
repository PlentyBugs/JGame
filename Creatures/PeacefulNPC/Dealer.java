package Creatures.PeacefulNPC;

import Conversations.Conversation;

import java.awt.*;

public class Dealer extends Peaceful {

    public Dealer(){
        this(0,0,"Торговец",1,100);
    }

    public Dealer(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Торговец " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(255, 100, 204);

        stats.setStrength(510);
        stats.setSpeed(120);
        stats.setAgility(5);
        stats.setIntelligence(310);
        stats.setLuck(210);
        stats.setEloquence(650);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5);
        stats.setTwo_handed_weapon(5);
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5);
        stats.setLong_range_weapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }

    @Override
    public Dealer clone() throws CloneNotSupportedException
    {
        return (Dealer) super.clone();
    }

    @Override
    public Dealer getClearCopy() {
        return new Dealer();
    }
}