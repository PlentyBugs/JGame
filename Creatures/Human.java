package Creatures;

public class Human extends LiveCreature {
    protected String name;
    protected String location = "Пустота";

    protected int upPointCount = 0;
    protected double hp = 100;

    protected String race = "Человек";

    public Human(){
        this(0,0,"Человек",1,100);
    }

    public Human(int x, int y, String name, int lvl, int hp){

        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        this.x = x;
        this.y = y;
        isStep = false;
    }

    public void addUpPoints(int count){
        upPointCount += count;
    }

    public int getUpPointCount() {
        return upPointCount;
    }

    public Stats getStats() {
        return stats;
    }

    public void setUpPointCount(int upPointCount) {
        this.upPointCount = upPointCount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5);
        stats.setSpeed(5);
        stats.setAgility(5);
        stats.setIntelligence(5);
        stats.setLuck(5);
        stats.setEloquence(5);
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
    }

    @Override
    public Human clone() throws CloneNotSupportedException
    {
        return (Human) super.clone();
    }

    @Override
    public Human getClearCopy() {
        return new Human();
    }
}