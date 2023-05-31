public class Player extends ConsoleProgram
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    public Grid playerGrid = new Grid();
    public Grid computerGrid = new Grid();
    
    public void placeShips(Grid grid, int length, int direction, int row, int col)
    {
        Ship s = new Ship(length);
        s.setDirection(direction);
        s.setLocation(row, col);
        
        grid.addShip(s);
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
            
            boolean check = checkIfShip(playerGrid, SHIP_LENGTHS[i],directionToInt,letter,numToInt);
            if(check)
            {
                placeShips(playerGrid, SHIP_LENGTHS[i],directionToInt,letter+1,numToInt+1);
                playerGrid.printShips();
            }
            else
            {
                i--;
            }
        }
        computer();
    }
    
    public void computer()
    {
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            int computerRow = Randomizer.nextInt(0,computerGrid.numRows()-1);
            int computerCol = Randomizer.nextInt(0,computerGrid.numCols()-1);
            int computerDirect = Randomizer.nextInt(0,1);
            
            boolean check = checkIfShip(computerGrid, SHIP_LENGTHS[i],computerDirect,computerRow,computerCol);
            if(check)
            {
                placeShips(computerGrid, SHIP_LENGTHS[i],computerDirect,computerRow+1,computerCol+1);
            }
            else
            {
                i--;
            }
        }
    }
    
    public boolean checkIfShip(Grid grid, int length, int direction, int row, int col)
    {
        if(row < 0 || row > grid.numRows() || col < 0 || col > grid.numCols())
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
                if(grid.hasShip(row-i, col))
                {
                    return false;
                }
            }
            return true;
        } 
        else if(direction == HORIZONTAL)
        {
            if(col > (grid.numCols()-length))
            {
                return false;
            }
            for(int i = 0; i < length;i++)
            {
                if(grid.hasShip(row,col+i))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
