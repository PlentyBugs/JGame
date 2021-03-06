package thing;

import item.blacksmith.resource.Resource;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Ore extends Stone  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Stone.propertyList);
        propertyList.add(GeneralProperty.ORE);
    }

    protected Resource resource;

    public Ore(Resource resource){
        this.resource = resource;
        name = resource.getName();
        color = Color.LIGHT_GRAY;
        isStep = true;
        color = resource.getColor();
    }

    public Resource getResource() {
        return resource;
    }
}
