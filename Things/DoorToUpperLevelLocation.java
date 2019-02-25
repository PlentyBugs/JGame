package Things;

import java.awt.*;

public class DoorToUpperLevelLocation extends Thing {
    public DoorToUpperLevelLocation(){
        this(0,0);
    }
    public DoorToUpperLevelLocation(int x, int y){
        this.x = x;
        this.y = y;
        name = "Дверь";
        color = new Color(100, 24, 22);
        isStep = true;
    }
    @Override
    public DoorToUpperLevelLocation getClearCopy() {
        return new DoorToUpperLevelLocation();
    }
}
