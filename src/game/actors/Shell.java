package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface to allow an actor to have a shell!
 */
public interface Shell {

    /**
     * Destroys the shell.
     * @param map The map in which the shell presides.
     */
    void destroyShell(GameMap map);
}
