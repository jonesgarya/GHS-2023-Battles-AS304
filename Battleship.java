public class Battleship extends ConsoleProgram
{
    public int hitsOne;
    public int hitsTwo;
    public boolean isComputer = false;
    public void run()
    {
        // Start here! This class should interact with the user
        // to play the game of Battleship
        
        // You only need to allow for the user to set up each player's
        // ships and for each player to make a guess on the other player's grid
        // Don't worry about finishing the whole game yet.
        
        // You will probably need to make additions to the Player class to
        // allow for this setting up and guessing
        String playerType = "";
        boolean temp = false;
        while(!temp)
        {
            playerType = readLine("vs player or computer ");
            if(playerType.equalsIgnoreCase("player") || playerType.equalsIgnoreCase("computer"))
            {
                temp = true;
            }
            else
            {
                temp = false;
            }
        }
        Player playerOne = new Player();
        Player playerTwo = new Player();
        hitsOne = playerOne.hitAmount();
        System.out.println(hitsOne);
        hitsTwo = playerTwo.hitAmount();
        System.out.println("Player One");
        playerOne.askPlayer();
        if(playerType.equalsIgnoreCase("player"))
        {
            System.out.println("Hand to other player.");
            System.out.println("Player Two");
            playerTwo.askPlayer();
        } 
        else 
        {
            playerTwo.computer();
            isComputer = true;
        }
        int i = 0;
        while(hitsOne != 0 && hitsTwo != 0)
        {
            if(i%2==0)
            {
                System.out.println("");
                System.out.println("PlayerOne's turn");
                playerTwo.printStatus();
                if(askForGuess(playerOne,playerTwo,1))
                {
                    hitsOne--;
                }
                playerTwo.printStatus();
            }
            else if (i%2==1)
            {
                System.out.println("");
                System.out.println("PlayerTwo's turn");
                playerOne.printStatus();
                System.out.println("");
                if(askForGuess(playerTwo,playerOne,2))
                {
                    hitsTwo--;
                }
                playerOne.printStatus();
            }
            i++;
        }
        if(hitsOne == 0)
        {
            System.out.println("");
            System.out.println("Player One Wins!");
        } 
        else if(hitsTwo == 0)
        {
            System.out.println("");
            System.out.println("Player Two Wins");
        }
    }
    
    public boolean askForGuess(Player curPlayer, Player otherPlayer,int whichPlayer)
    {
        if(whichPlayer == 2 && isComputer)
        {
            return curPlayer.computerTurn(otherPlayer.getGrid());
        } 
        else 
        {
            return curPlayer.playerTurn(otherPlayer.getGrid());
        }
    }
}
