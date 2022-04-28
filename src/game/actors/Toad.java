package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;

public class Toad extends Actor {

    public Toad(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public void init(GameMap map){
        int midX = map.getXRange().max()/2;
        int midY = map.getYRange().max()/2;

        NumberRange xRange = new NumberRange(midX-1, 3);
        NumberRange yRange = new NumberRange(midY-1, 3);
        map.add('#', xRange, yRange);
        map.at(midX, midY).addActor(this);
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

    //TODO: check if the player has wrench and decide which monologue to print

        return null;
    }
}
