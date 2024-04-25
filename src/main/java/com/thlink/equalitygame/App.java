
package com.thlink.equalitygame;
/*
    Implements Game of Life: a game that shows how the wealthy is distributed over time.
!!!
*/
public class App 
{
    public static void main (String args[])
    {
        System.out.println("Length: " + args.length);
        if (args.length >= 3)
        {
            int iTokens = Integer.parseInt(args[0]);
            int iPlayers = Integer.parseInt(args[1]);
            int iBalance = Integer.parseInt(args[2]);
            
            Game g = new Game();
            g.setGame(iTokens, iPlayers, iBalance, 100);
            g.go();
        }
        else
        {
            System.out.println("Especifique pelo menos três parâmetros: iToken, iPlayers, iBalance");
        }
            
        
    }
}
