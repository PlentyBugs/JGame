package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlueHerb extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public BlueHerb(){
        super();
        name = "Синяя трава";
        color = new Color(159, 130,255);
        usage.add(new HealPotion());
        usage.add(new PoisonPotion());
    }
}
