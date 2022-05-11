package game.actors;

/**
 * A class to handle an Actor's money
 * @author Sok Huot Ear
 * @version 1.0
 */
public class Wallet{
    /**
     * The current balance in the wallet
     */
    private int balance = 1000 ;

    /**
     * Gets the current wallet balance
     * @return the balance of the wallet
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the wallet
     * @param balance the balance to put in the wallet
     */
    public void setBalance(int balance){
        this.balance = balance;
    }

    /**
     * Adds a certain amount of money to the wallet
     * @param value the money to be added
     */
    public void addBalance(int value){
        this.balance += value;
    }

    /**
     * Removes a certain amount of money from the wallet
     * @param value the money to be removed from the wallet
     */
    public void spend(int value){
        this.balance -= value;
    }

}
