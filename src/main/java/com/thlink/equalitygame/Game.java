
package com.thlink.equalitygame;

import java.util.ArrayList;
import java.util.List;

public class Game 
{
    private List<Person> lstPerson;
    private int mPlayers, mInitialBalance;
    private int token = 1, deltaDBG = 100;
    
    public void setGame (int pToken,int pPlayers, int pInitialBalance, Integer pDeltaDGB)
    {
        token = pToken;
        mPlayers = pPlayers;
        mInitialBalance = pInitialBalance;
        if (pDeltaDGB != null)
            deltaDBG = pDeltaDGB;
        lstPerson = new ArrayList<>(pPlayers);
        for (int i = 0; i < mPlayers; i++)
        {
            Person p = new Person(String.format("P%03d", i), pInitialBalance);
            lstPerson.add(p);
        }
    }
    
    public void go ()
    {
        int qtdPlays = 0;
        do
        {
            playBall();
            qtdPlays++;
            if (qtdPlays % deltaDBG == 0)
                debugGame(qtdPlays);
            if (getTotalBalance() != mPlayers * mInitialBalance)
            {
                System.out.println("ERRO!");
                System.exit(0);
            }
        } while (moreThenOnePlayer());
        System.out.printf("Jogo de %d jogadores com saldo inicial de %d terminou em %d jogadas. \n", mPlayers, mInitialBalance, qtdPlays);
        Person winner = getWinner();
        if (winner == null)
        {
            System.out.println("ERRO");
            return;
        }
        System.out.printf("Vencedor: %s. Saldo: %d\n", winner.getName(), winner.getBalance());
    }
    
    private void playBall ()
    {
        //Escolher 2 jogadores, randomicamente
        int inx1 = (int)(Math.random() * lstPerson.size());
        int inx2;
        Person p1 = lstPerson.get(inx1);
        boolean p1Bet = p1.getRndBet();
        do
        {
            inx2 = (int)(Math.random() * lstPerson.size());
        } while (inx1 == inx2);
        Person p2 = lstPerson.get(inx2);
        //System.out.printf("Jogo entre %d[%s] e %d[%s]. ", inx1, p1Bet ? "T" : "F", inx2, !p1Bet ? "T" : "F");
        if (houseCall() == p1Bet)
        {
            //System.out.printf("Vit: %d.\n", inx1);
            p1.addBalance(token);   
            if (p2.addBalance(-token))
            {
                lstPerson.remove(inx2);
                //if (lstPerson.size() == 2)
                //    deltaDBG = 1;
            }
        }
        else
        {
            //System.out.printf("Vit: %d.\n", inx2);
            if (p1.addBalance(-token))
            {
                lstPerson.remove(inx1);
                if (lstPerson.size() == 2)
                    deltaDBG = 1;
            }
            p2.addBalance(token);   
        }
    }
    
    private Person getWinner ()
    {
        Person winner = null;
        for (Person p : lstPerson)
        {
            if (p.isActive())
            {
                if (winner == null)
                    winner = p;
                else
                {
                    System.out.println("2 ganhadores! ERRO!!\n");
                    return null;
                }
                    
            }
        }
        return winner;
    }
    
    private boolean houseCall ()
    {
        return (Math.random() > 0.50);
    }
    private boolean moreThenOnePlayer ()
    {
        return lstPerson.size() > 1;
    }
    
    private int getActivePlayers ()
    {
        int numActivePlayers = 0;
        for (Person p : lstPerson)
        {
            if (p.isActive())
                numActivePlayers++;
        }
        return numActivePlayers;
    }
    
    private String getPlayersNamesAndBalances()
    {
        String strPN = "";
        for (Person p : lstPerson)
        {
            if (p.isActive())
                strPN = strPN.concat(String.format("%s-%d", p.getName(), p.getBalance()).concat(" "));
        }
        return strPN;
    }
    
    private int getTotalBalance()
    {
        int lTotalBalance = 0;
        for (Person p : lstPerson)
        {
            if (p.isActive())
                lTotalBalance += p.getBalance();
        }
        return lTotalBalance;
    }

    private void debugGame (int pRounds)
    {
        String dbg = String.format("[%03d] AP: %3d (T: %3d) (%s).", pRounds, getActivePlayers(), getTotalBalance(), getPlayersNamesAndBalances());
        System.out.println(dbg);
    }
}
