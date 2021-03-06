package quest;

import creature.Player;
import item.Item;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class CollectItemQuest extends Quest {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Quest.propertyList);
    }

    private Player player;
    private Item item;
    private int itemCount;
    private int itemCountCurrent;

    public CollectItemQuest(){
        super();
        itemCountCurrent = 0;
    }

    public boolean check(){
        int counter = 0;
        for(Item item : player.getInventory()){
            if(item.getLastProperty() == this.item.getLastProperty()){
                counter ++;
                itemCountCurrent = counter;
            }
        }
        return itemCountCurrent >= itemCount;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public int getItemCountCurrent() {
        return itemCountCurrent;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemCountCurrent(int itemCountCurrent) {
        this.itemCountCurrent = itemCountCurrent;
    }
}
