package window;

import creature.GodCreature;
import creature.LiveCreature;
import creature.Player;
import item.tool.Pickaxe;
import location.Map;
import location.cave.Cave;
import location.dungeon.Dungeon;
import support.AbilityProperty;
import thing.*;
import thing.alchemy.IngredientThing;
import thing.chest.Chest;
import thing.craft.CraftTable;
import thing.door.CaveDoor;
import thing.door.Door;
import thing.door.DoorToUpperLevelLocation;
import window.conversation.ConversationWindow;
import window.provider.MapProvider;
import window.provider.MultiWindowProvider;
import window.provider.WindowProvider;

public interface Controller extends WindowProvider, MapProvider, MapDrawer, MultiWindowProvider, PlayerInterfaceDrawer {

    default void step(GodCreature creature) {

        final Map map = getMap();
        Player player = map.getPlayer();

        boolean isStep = creature.getIsStep();
        int X = creature.getX();
        int Y = creature.getY();

//        if(!(creature instanceof Player) && creature instanceof LiveCreature){
//            npcController.addNPC((LiveCreature)creature);
//        }

        if(player.hasAbility(AbilityProperty.LITTLE_FOOL) && creature instanceof Tree){
            isStep = true;
        }

        WindowInterface windowInterface = getWindow();
        if (creature instanceof HealBlock healBlock) {
            player.setWindowInterface(windowInterface);
            map.setElementByCoordinates(creature.getX(), creature.getY(), new Grass(creature.getX(), creature.getY()));
            healBlock.heal(player);
            int healBlockY = (int)(Math.random()*(map.getMapHeight()-1));
            int healBlockX = (int)(Math.random()*(map.getMapWidth()-1));
            map.setElementByCoordinates(healBlockX, healBlockY, new HealBlock(healBlockX, healBlockY));
            drawMap();
            drawAllPlayerWindow(player, this);
        } else if (creature instanceof Door doorToUpper) {
            //todo add function to the Door class to check the key
            if(!doorToUpper.getIsLocked() || (doorToUpper.getIsLocked() && player.hasItem(doorToUpper.getKey()))){
                if(doorToUpper.getIsLocked()){
                    player.removeItem(doorToUpper.getKey());
                    doorToUpper.setIsLocked(false);
                }
                map.setPlayerX(player.getX());
                map.setPlayerY(player.getY());
                doorToUpper.setIn(map);
                if(creature instanceof DoorToUpperLevelLocation){
                    doorToUpper.setGeneration(() -> {
                        Map newMap;
                        if(doorToUpper.getOut() == null){
                            newMap = new Map();

                            Dungeon dungeon = new Dungeon(player);
                            GodCreature[][][] zxc = dungeon.getMap();
                            newMap.setMapLowerObjects(zxc[0]);
                            newMap.setMapUpperObjects(zxc[1]);
                            newMap.setLocationName(dungeon.getLocationName());
                            doorToUpper.setOut(newMap);
                            player.setX(dungeon.getPlayerXSafety());
                            player.setY(dungeon.getPlayerYSafety());
                            DoorToUpperLevelLocation door = new DoorToUpperLevelLocation();
                            door.setOut(map);
                            door.setIsLocked(false);
                            newMap.setElementByCoordinates(player.getX(), player.getY(), door);
                        } else {
                            newMap = doorToUpper.getOut();
                            player.setX(newMap.getPlayerX());
                            player.setY(newMap.getPlayerY());
                        }
                        newMap.setPlayer(player);
                        return newMap;
                    });
                } else if(creature instanceof CaveDoor caveDoor){
                    caveDoor.setGeneration(() -> {
                        Map newMap;
                        if(caveDoor.getOut() == null){
                            newMap = new Map();
                            Cave cave = new Cave();

                            newMap.setMapLowerObjects(cave.getCave());
                            caveDoor.setOut(newMap);

                            player.setX(cave.getPlayerSafeX());
                            player.setY(cave.getPlayerSafeY());

                            CaveDoor door = new CaveDoor();
                            door.setOut(map);
                            door.setIsLocked(false);
                            newMap.setElementByCoordinates(player.getX(), player.getY(), door);
                        } else {
                            newMap = caveDoor.getOut();
                            player.setX(newMap.getPlayerX());
                            player.setY(newMap.getPlayerY());
                        }
                        newMap.setPlayer(player);
                        return newMap;
                    });
                }
                setMap(((Door) creature).generate());
                player.setWindowInterface(windowInterface);
                drawMap();
            }
            drawAllPlayerWindow(player, this);
        } else if(creature instanceof CraftTable craftTable) {
            craftTable.setPlayer(player);
            if(!craftTable.getCraftTableWindowOpen()) {
                craftTable.setCraftTableWindow(true);
                craftTable.setCraftTableWindowOpen(true);
            } else {
                craftTable.setCraftTableWindow(false);
                craftTable.setCraftTableWindowOpen(false);
            }
        } else if (creature instanceof Chest chest) {
            player.setWindowInterface(windowInterface);
            if(!chest.getIsInventoryChestOpen()){
                chest.setPlayer(player);
                chest.setInventoryWindow();
                chest.setInventoryWindowChestIsVisible(true);
                chest.setInventoryChestOpen(true);
            } else {
                chest.getInventoryWindow().close();
                chest.setInventoryWindowChestIsVisible(false);
                chest.setInventoryChestOpen(false);
            }
            drawAllPlayerWindow(player, this);
        } else {
            if (isStep) {
                player.setWindowInterface(windowInterface);
                player.setX(X);
                player.setY(Y);
                if (creature instanceof IngredientThing ingredientThing) {
                    player.addItemToInventory(ingredientThing.getIngredient());
                    GodCreature godCreature = ingredientThing.getParent();
                    godCreature.setX(X);
                    godCreature.setY(Y);
                    map.setElementByCoordinates(X, Y, godCreature);
                }
                if (creature instanceof Ore ore && player.hasItem(new Pickaxe())) {
                    player.addItemToInventory(ore.getResource());
                    GodCreature godCreature = new Stone();
                    godCreature.setX(X);
                    godCreature.setY(Y);
                    map.setElementByCoordinates(X, Y, godCreature);
                }
                drawMap();
                drawAllPlayerWindow(player, this);
            } else if (creature instanceof LiveCreature liveCreature) {
                player.setWindowInterface(windowInterface);
                if (liveCreature.getTalkative()) {
                    liveCreature.setConversationWindowPlayer(player);
                    ConversationWindow conversationWindow = liveCreature.getConversationWindow();
                    conversationWindow.setMultiWindow(getMultiWindow());
                    conversationWindow.updateButtonList();
                    getMultiWindow().newWindow(conversationWindow, Screen.CONVERSATION);
                    getMultiWindow().switchScreen(Screen.CONVERSATION);
                } else {
                    if (liveCreature.getHp() == 0) {
                        liveCreature.countStatsAfterBorn();
                    }
                    creature.setChooseEnemyWindow(player, windowInterface, getMultiWindow(), (LiveCreature) creature);
                }
            }
        }
    }
}
