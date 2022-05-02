package game.bank;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.*;

public class WalletManager {
    /**
     * A map of Actors (Key) and their Wallets (Value)
     */
    private Map<Actor, Wallet> wallets;

    /**
     * A singleton wallet manager instance
     */
    private static WalletManager instance;

    /**
     * Get the singleton instance of wallet manager
     * @return WalletManager singleton instance
     */
    public static WalletManager getInstance(){
        if(instance == null){
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Constructor
     * Will initialise an empty HashMap
     */
    private WalletManager(){
        // initialise an empty HashMap
        wallets = new HashMap<>();
    }

    /**
     * Adds an Actor to the WalletManager, with a new Wallet object.
     * Adding actor's to this WalletManager allows manipulation of this actor's economy.
     * @param actor The actor whom you wish to issue a wallet
     */
    public void add(Actor actor) {
        this.wallets.put(actor, new Wallet());
    }

    /**
     * A method to get all wallets
     * Essential for performing operations on actor's wallets!
     *
     * Access an actor's wallet with getWallets.get(actor)
     *
     * @return A map of Actor, Wallet (K,V)
     */
    public Map<Actor, Wallet> getWallets() {
        return this.wallets;
    }
}
