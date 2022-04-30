package game.actors;
import edu.monash.fit2099.engine.actors.Actor;
import java.util.*;

import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;

public class Wallet{
    private int balance = 0 ;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void addBalance(int value){
        this.balance += value;
    }

    public void spend(int value){
        this.balance -= value;
    }

}
