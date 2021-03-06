package item.weapon.sword;

import abilities.buffs.DamageUpBuff;
import abilities.buffs.DecreaseDamageBuff;
import creature.LiveCreature;
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

public class Sword extends Weapon {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Weapon.propertyList);
        propertyList.add(ItemProperty.SWORD);
    }
    @Serial
    private static final long serialVersionUID = -1973203379601612951L;

    public Sword(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "меч";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Sword(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.ONE_HANDED);
        name = "меч";
    }

    @Override
    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){
        if(weaponType.contains(WeaponType.TWO_HANDED))
            injury(enemy, fightWindow);
        else if(weaponType.contains(WeaponType.ONE_HANDED))
            battleTendency(attacker, fightWindow);
    }

    private void injury(LiveCreature enemy, FightWindow fightWindow){
        if(Math.random()*100 < 20)
            enemy.addBuffs(new DecreaseDamageBuff(30, 3));
    }

    private void battleTendency(LiveCreature attacker, FightWindow fightWindow){
        if(Math.random()*100 < 1)
            attacker.addBuffs(new DamageUpBuff(150, 2));
    }

    public Sword setWeaponType(WeaponType weaponType) {
        this.weaponType.add(weaponType);
        return this;
    }
}
