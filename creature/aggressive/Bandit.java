package creature.aggressive;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import creature.Human;
import item.alchemy.potion.HealPotion;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.*;
import item.weapon.bow.Bow;
import item.weapon.bow.LongBow;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import item.weapon.WeaponType;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bandit extends Human implements Aggressive {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(CreatureProperty.BANDIT);
    }

    public Bandit(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = Color.RED;

        uniqueDropItems = new Item[]{
                new Sword(),
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWO_HANDED),
                new Torso(),
                new Helmet(),
                new HealPotion(),
                new Bow(),
                new Ring(),
                new LongBow(),
                new Axe(),
                new Staff(),
                new Key()
        };
    }

    public Bandit(){
        this(0,0,"Бандит",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl*3) + lvl*9));
        stats.setSpeed(5 + (int)(Math.random()*(lvl*4) + lvl));
        stats.setAgility(5 + (int)(Math.random()*(lvl*3) + lvl*4));
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*5));
        stats.setLuck(5 + (int)(Math.random()*(lvl*4) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl*3) + lvl));
        stats.setTwoHandedWeapon(5 + (int)(Math.random()*(lvl*6)));
        stats.setPoleWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChoppingWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLongRangeWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);


        Sword banditSword = new Sword();
        Torso banditArmorTorso = new Torso();
        Helmet banditArmorHelmet = new Helmet();
        if(lvl < 10){
            banditSword.setMaterial(Material.COPPER);
            banditSword.setRarity(Rarity.COMMON);
            banditSword.setGrade(Grade.COMMON);
            banditSword.setWeaponType(WeaponType.ONE_HANDED);
            banditArmorTorso.setMaterial(Material.COPPER);
            banditArmorTorso.setRarity(Rarity.COMMON);
            banditArmorTorso.setGrade(Grade.COMMON);
            banditArmorHelmet.setMaterial(Material.COPPER);
            banditArmorHelmet.setRarity(Rarity.COMMON);
            banditArmorHelmet.setGrade(Grade.COMMON);
        } else if(lvl < 22){
            banditSword.setMaterial(Material.BRONZE);
            banditSword.setRarity(Rarity.RARE);
            banditSword.setGrade(Grade.MAGIC);
            banditSword.setWeaponType(WeaponType.TWO_HANDED);
            banditArmorTorso.setMaterial(Material.BRONZE);
            banditArmorTorso.setRarity(Rarity.RARE);
            banditArmorTorso.setGrade(Grade.MAGIC);
            banditArmorHelmet.setMaterial(Material.BRONZE);
            banditArmorHelmet.setRarity(Rarity.RARE);
            banditArmorHelmet.setGrade(Grade.MAGIC);
        } else if(lvl < 45){
            banditSword.setMaterial(Material.MYTHRIL);
            banditSword.setRarity(Rarity.MYSTICAL);
            banditSword.setGrade(Grade.CURSE);
            banditSword.setWeaponType(WeaponType.TWO_HANDED);
            banditArmorTorso.setMaterial(Material.MYTHRIL);
            banditArmorTorso.setRarity(Rarity.MYSTICAL);
            banditArmorTorso.setGrade(Grade.CURSE);
            banditArmorHelmet.setMaterial(Material.MYTHRIL);
            banditArmorHelmet.setRarity(Rarity.MYSTICAL);
            banditArmorHelmet.setGrade(Grade.CURSE);
        } else {
            banditSword.setMaterial(Material.DEEP);
            banditSword.setRarity(Rarity.DRAGON);
            banditSword.setGrade(Grade.ARTIFACT);
            banditSword.setWeaponType(WeaponType.TWO_HANDED);
            banditArmorTorso.setMaterial(Material.MYTHRIL);
            banditArmorTorso.setRarity(Rarity.MYSTICAL);
            banditArmorTorso.setGrade(Grade.CURSE);
            banditArmorHelmet.setMaterial(Material.MYTHRIL);
            banditArmorHelmet.setRarity(Rarity.MYSTICAL);
            banditArmorHelmet.setGrade(Grade.CURSE);
        }
        banditSword.countProperty();
        banditArmorTorso.countProperty();
        banditArmorHelmet.countProperty();
        addItemToInventory(banditSword);
        addItemToInventory(banditArmorTorso);
        addItemToInventory(banditArmorHelmet);
        equip(banditSword);
        equip(banditArmorTorso);
        equip(banditArmorHelmet);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/20, (new DamageUp()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/20, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/12, (new Evasion()).getMaxLevel()))));
    }

    @Override
    public Bandit clone() throws CloneNotSupportedException
    {
        return (Bandit) super.clone();
    }

    @Override
    public Bandit getClearCopy() {
        return new Bandit();
    }
}
