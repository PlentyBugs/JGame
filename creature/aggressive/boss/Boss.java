package creature.aggressive.boss;

import creature.aggressive.Aggressive;
import creature.LiveCreature;
import item.Item;
import item.Key;
import support.Property;
import support.CreatureProperty;

import java.util.ArrayList;
import java.util.List;

public class Boss extends LiveCreature implements Aggressive {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(LiveCreature.propertyList);
        propertyList.add(CreatureProperty.BOSS);
    }

    protected Item[] dropItems;
    protected Key key;

    public Item[] getDropItems() {
        return dropItems;
    }

    public Boss getRandomBoss(){
        return new Boss[]{new Frank(), new HigherGhost(), new DeadGuardian()}[(int)(Math.random()*3)];
    }

    public Key getKey() {
        return key;
    }
}
