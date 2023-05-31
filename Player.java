public class Player extends ConsoleProgram
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    public Grid playerGrid = new Grid();
    
    
    public int hitAmount()
    {
        int hitsNeeded = 0;
        for(int i=0; i< SHIP_LENGTHS.length; i++)
        {
            hitsNeeded += SHIP_LENGTHS[i];
        }
        return hitsNeeded;
    }
    
    public void placeShips(int length, int direction, int row, int col)
    {
        Ship s = new Ship(length);
        s.setDirection(direction);
        s.setLocation(row, col);
        
        playerGrid.addShip(s);
    }

    public void askPlayer()
    {
        playerGrid.printShips();
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            System.out.println("");
            String gridLocation = "";
            
            while(gridLocation.length()<2 || !Character.isDigit(gridLocation.charAt(1)) || !Character.isLetter(gridLocation.charAt(0)))
            {
                gridLocation = readLine("Where do you want to place the ship of size " + SHIP_LENGTHS[i] + "? write as (A-" +  (char)(playerGrid.numRows()+64) + ")(1-" + playerGrid.numCols() + ")");
            }
            int numToInt= ((int)gridLocation.charAt(1))-49;
            if(gridLocation.length() == 3)
            {
                if(gridLocation.charAt(1) == '1' && gridLocation.charAt(2) == '0')
                {
                    numToInt = 9;
                }
            }
            char letterToCap = Character.toUpperCase(gridLocation.charAt(0));
            int letter = ((int)letterToCap)-65;
            
            String direction = readLine("(V)ertical or (H)orizontal? ");
            int directionToInt = -1;
            if(direction.equalsIgnoreCase("Vertical") || direction.equalsIgnoreCase("V"))
            {
                directionToInt = VERTICAL;
            }
            else if(direction.equalsIgnoreCase("Horizontal") || direction.equalsIgnoreCase("H"))
            {
                directionToInt = HORIZONTAL;
            }
            
            boolean check = checkIfShip(SHIP_LENGTHS[i],directionToInt,letter,numToInt);
            if(check)
            {
                placeShips(SHIP_LENGTHS[i],directionToInt,letter+1,numToInt+1);
                playerGrid.printShips();
            }
            else
            {
                i--;
            }
        }
        System.out.println("\033[2J");
    }
    
    public void computer()
    {
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            int computerRow = Randomizer.nextInt(0,playerGrid.numRows()-1);
            int computerCol = Randomizer.nextInt(0,playerGrid.numCols()-1);
            int computerDirect = Randomizer.nextInt(0,1);
            
            boolean check = checkIfShip(SHIP_LENGTHS[i],computerDirect,computerRow,computerCol);
            if(check)
            {
                placeShips(SHIP_LENGTHS[i],computerDirect,computerRow+1,computerCol+1);
            }
            else
            {
                i--;
            }
        }
    }
    
    public boolean checkIfShip(int length, int direction, int row, int col)
    {
        if(row < 0 || row > playerGrid.numRows()-1 || col < 0 || col > playerGrid.numCols()-1)
        {
            return false;
        }
        if(direction == VERTICAL)
        {
            if(row-length < -1 )
            {
                return false;
            }
            for(int i = 0; i < length ;i++)
            {
                if(playerGrid.hasShip(row-i, col))
                {
                    return false;
                }
            }
            return true;
        } 
        else if(direction == HORIZONTAL)
        {
            if(col > (playerGrid.numCols()-length))
            {
                return false;
            }
            for(int i = 0; i < length;i++)
            {
                if(playerGrid.hasShip(row,col+i))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public void printStatus()
    {
        playerGrid.printStatus();
    }
    
    public boolean playerTurn(Grid otherPlayer)
    {
        boolean check = true;
        int letter = UNSET;
        int numToInt= UNSET;
        while(check)
        {
            String guessLocation = "";
            System.out.println("");
            while(guessLocation.length()<2 || !Character.isDigit(guessLocation.charAt(1)) || !Character.isLetter(guessLocation.charAt(0)))
            {
                guessLocation = readLine("Where do you want to guess? write as (A-" +  (char)(playerGrid.numRows()+64) + ")(1-" + playerGrid.numCols() + ")");
            }
            numToInt= ((int)guessLocation.charAt(1))-49;
            if(guessLocation.length() == 3)
            {
                if(guessLocation.charAt(1) == '1' && guessLocation.charAt(2) == '0')
                {
                    numToInt = 9;
                }
            }
            char letterToCap = Character.toUpperCase(guessLocation.charAt(0));
            letter = ((int)letterToCap)-65;
            if(letter < 0 || letter > otherPlayer.numRows()-1 || numToInt < 0 || numToInt > otherPlayer.numCols()-1)
            {
            }
            else
            {
                check = otherPlayer.alreadyGuessed(letter, numToInt);
            }
        }
        return adjustStatus(otherPlayer, letter, numToInt);
    }
    
    public boolean computerTurn(Grid otherPlayer)
    {
        boolean check = true;
        int computerRow = UNSET;
        int computerCol = UNSET;
        while(check)
        {
            computerRow = Randomizer.nextInt(0,playerGrid.numRows()-1);
            computerCol = Randomizer.nextInt(0,playerGrid.numCols()-1);
            check = otherPlayer.alreadyGuessed(computerRow, computerCol);
        }
        return adjustStatus(otherPlayer, computerRow, computerCol);
    }
    
    public boolean adjustStatus(Grid otherPlayer, int row, int col)
    {
            if(otherPlayer.hasShip(row, col))
            {
                otherPlayer.markHit(row, col);
                return true;
            }
            else
            {
                otherPlayer.markMiss(row, col);
                return false;
            }
    }
    
    public Grid getGrid()
    {
        return playerGrid;
    }
}
