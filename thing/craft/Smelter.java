package thing.craft;

import creature.Player;
import item.blacksmith.resource.Resource;
import thing.Thing;
import window.craft.SmelterTableWindow;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Smelter extends Thing implements BlackSmithCraftTable {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.SMELTER);
    }

    private boolean isCraftTableWindowOpen;
    private final SmelterTableWindow smelterTableWindow;
    private int maxTemperature;
    private int power;

    public Smelter(){
        name = "Плавильня";
        color = new Color(255, 39, 0);
        isStep = false;
        maxTemperature = 1500;
        power = 100;
        smelterTableWindow = new SmelterTableWindow(this);
        setCraftTableWindow(false);
        setCraftTableWindowOpen(false);
    }

    @Override
    public void setPlayer(Player player) {
        smelterTableWindow.setPlayer(player);
    }

    @Override
    public void setCraftTableWindow(boolean isVisible) {
        smelterTableWindow.setVisible(isVisible);
    }

    public void setCraftTableWindowOpen(boolean isCraftTableWindowOpen) {
        this.isCraftTableWindowOpen = isCraftTableWindowOpen;
    }

    @Override
    public boolean getCraftTableWindowOpen() {
        return isCraftTableWindowOpen;
    }

    @Override
    public void create(Resource ... resources) {
        for(Resource resource : resources){
            resource.setTemperature(Math.min(resource.getTemperature() + power/resources.length, maxTemperature));
        }
    }

    public Smelter setPower(int power) {
        this.power = power;
        return this;
    }

    public Smelter setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    public int getPower() {
        return power;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }
}
