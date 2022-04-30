package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actions.ConsumeItemAction;

public class SuperMushroom extends ConsumableItem {

    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addAction(new ConsumeItemAction(this));
    }

    @Override
    public void consume(Actor actor) {

        actor.increaseMaxHp(50);
        actor.addCapability(Status.SUPER_MUSHROOM);
    }
}
