package game.bank;

public class Bank {

    private int balance;

    public Bank() {
        balance = 0;
    }

    public int getBalance() {
        return this.balance;
    }

    public void addMoney(int amount) {
        this.balance += amount;
    }

    public void takeMoney(int amount) {
        this.balance += amount;
    }
}
