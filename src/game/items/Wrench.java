package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class representing a Wrench weapon.
 * Only a wrench can destroy {@link game.actors.Shell Shells}.
 */
public class Wrench extends WeaponItem {

    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'w', 50, "slaps", 80);
    }


}
