
package com.thlink.equalitygame;

public class Person 
{
    private String name;
    private int balance, numGamesPlayed;
    private boolean active, bet;
    
    public Person (String pName, int pInitBalance)
    {
        this.name = pName;
        this.balance = pInitBalance;
        this.numGamesPlayed = 0;
        this.active = true;
    }
    
    public boolean getRndBet ()
    {
        this.bet = Math.random() > 0.5;
        return this.bet;
    }
    public boolean addBalance (int pValue)
    {
        this.balance += pValue;
        if (this.balance == 0)
        {
            this.active = false;
            //System.out.printf("[%s] saiu do jogo.\n", this.getName());
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }

    public void setNumGamesPlayed(int numGamesPlayed) {
        this.numGamesPlayed = numGamesPlayed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
}
