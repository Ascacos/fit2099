package game.bank;

/**
 * Represents a Wallet object.
 * This class holds all logic required to handle simple arithmetic of a Wallet and it's balance.
 */
public class Wallet {

    /**
     * The amount of coins in the wallet.
     */
    private int balance;

    /**
     * Constructor
     */
    public Wallet() {
        balance = 0;
    }

    /**
     * A method to get the balance of a wallet.
     * @return The balance of the wallet.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * A method to add money to the wallet.
     * @param amount The amount of money to add.
     */
    public void addMoney(int amount) {
        this.balance += amount;
    }

    /**
     * A method to remove money from a wallet
     * @param amount The amount of money to remove.
     */
    public void takeMoney(int amount) {
        this.balance += amount;
    }
}
