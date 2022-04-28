package game.items;

import edu.monash.fit2099.engine.items.Item;

public class SuperMushroom extends Item {
    private double value;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable, double value) {
        super(name, displayChar, portable);
        this.value = value;
    }

}
