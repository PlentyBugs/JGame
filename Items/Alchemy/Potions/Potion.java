package Items.Alchemy.Potions;

import Effects.Effect;
import Effects.EffectType;
import Effects.Poison;
import Items.Grade;
import Items.Item;
import Items.Rarity;
import Creatures.LiveCreature;

public class Potion extends Item {

    protected PotionMaterial potionMaterial;
    protected Effect effect;

    public Potion(PotionMaterial potionMaterial, Rarity rarity, Grade grade){
        this.name = "Зелье";
        this.potionMaterial = potionMaterial;
        this.rarity = rarity;
        this.grade = grade;
        stackable = true;
        effect = new Poison(EffectType.MOMENT);
    }

    public void use(LiveCreature liveCreature){
        if(effect.getType() != EffectType.MOMENT){
            liveCreature.addEffect(effect);
        } else {
            liveCreature.useMomentEffect(effect);
        }
    }

    public void setPotionMaterial(PotionMaterial potionMaterial) {
        this.potionMaterial = potionMaterial;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public PotionMaterial getPotionMaterial() {
        return potionMaterial;
    }

    @Override
    public int compareTo(Object o) {
        if(getName().equals(((Item) o).getName()) && getGrade() == ((Item) o).getGrade() && getQuality() == ((Item) o).getQuality() && getRarity() == ((Item) o).getRarity() && effect.getPower() == ((Potion)o).getEffect().getPower()){
            return 0;
        }
        return -1;
    }

}
