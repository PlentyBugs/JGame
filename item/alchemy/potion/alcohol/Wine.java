package item.alchemy.potion.alcohol;

import effect.AlcoholBuff;
import effect.EffectType;
import item.alchemy.potion.PotionMaterial;
import item.Grade;
import item.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Wine extends Alcohol {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Alcohol.propertyList);
    }

    public Wine(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        super(potionMaterial, rarity, grade);
        this.name = "Вино";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;

        effect = new AlcoholBuff(EffectType.MOMENT);
    }

    public Wine(){
        this(PotionMaterial.WATER, Rarity.COMMON, Grade.COMMON);
    }
}
