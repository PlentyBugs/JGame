package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;

import java.awt.*;

public class BlueHerb extends Ingredient {

    public BlueHerb(){
        super();
        name = "Синяя трава";
        color = new Color(159, 130,255);
        usage.add(new HealPotion());
        usage.add(new PoisonPotion());
    }
}