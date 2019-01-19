package Abilities.Passive;


import Abilities.Ability;
import Abilities.AbilityType;
import LiveCreatures.Player;

public class Evasion extends Ability {
    public Evasion(){
        setLevel(1);
        setAbilityType(AbilityType.PASSIVE);
        name = "Уклонение";
        maxLevel = 5;
    }

    public Evasion(int level){
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        power = 0;
        this.level = level;
        if (level == 1){
            chance = 15;
        } else if(level == 2){
            chance = 20;
        } else if(level == 3){
            chance = 25;
        } else if(level == 4){
            chance = 35;
        } else if(level == 5){
            chance = 45;
        }

        cost = level;
    }

    public boolean check(Player player){
        System.out.println(player.getStats().speed);
        System.out.println(level);
        System.out.println(player.getStats().agility);
        if (player.getStats().speed >= (level - 1)*10 + 5 && player.getStats().agility >= (level - 1)*10 + 5){
            return true;
        }
        return false;
    }
}
