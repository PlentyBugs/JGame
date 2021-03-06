package item.weapon.bow;

import creature.LiveCreature;
import creature.Player;
import item.Grade;
import item.Material;
import item.Rarity;
import item.weapon.Weapon;
import item.weapon.WeaponType;
import window.battle.FightWindow;
import support.ItemProperty;
import support.Property;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Bow extends Weapon {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Weapon.propertyList);
        propertyList.add(ItemProperty.BOW);
    }

    @Serial
    private static final long serialVersionUID = -8079067509250088207L;

    public Bow(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "Лук";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Bow(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.LONG_RANGE);
        name = "Лук";
    }
    @Override
    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){
        steal(attacker, enemy, fightWindow);
    }

    public void steal(LiveCreature attacker, LiveCreature enemy, FightWindow fightWindow){
        if(Math.random()*100 < 5){
            double count = 0;
            if(getDamage()*7 > enemy.getMoney()){
                attacker.addMoney(enemy.getMoney());
                count = enemy.getMoney();
                enemy.reduceMoney(enemy.getMoney());
            } else {
                attacker.addMoney(getDamage()*7);
                count = getDamage()*7;
                enemy.reduceMoney(getDamage()*7);
            }
            if(attacker instanceof Player){
                fightWindow.writeToPlayerConsole(attacker.getName() + " украл у " + enemy.getName() + count + " золотых");
            } else {
                fightWindow.writeToEnemyActionConsole(attacker.getName() + " украл у " + enemy.getName() + count + " золотых");
            }
        }
    }
}
