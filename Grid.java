import java.util.ArrayList;
public class Grid
{
    private Location[][] grid;
    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    // Create a new Grid. Initialize each Location in the grid
    // to be a new Location object.
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        for(int x = 0; x<NUM_ROWS;x++)
        {
            for(int y = 0; y<NUM_COLS;y++)
            {
                grid[x][y] = new Location();
            }
        }
    }
    
    // Mark a hit in this location by calling the markHit method
    // on the Location object.  
    public void markHit(int row, int col)
    {
        grid[row][col].markHit();
    }
    // Mark a miss on this location.    
    public void markMiss(int row, int col)
    {
        grid[row][col].markMiss();
    }
    // Set the status of this location object.
    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }
    // Get the status of this location in the grid  
    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }
    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col)    
    {
        return !grid[row][col].isUnguessed();
    }
    // Set whether or not there is a ship at this location to the val   
    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }
    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col)
    {
        return grid[row][col].hasShip();
    }
    
    // Get the Location object at this row and column position
    public Location get(int row, int col)
    {
        return grid[row][col];
    }
    // Return the number of rows in the Grid
    public int numRows()
    {
        return NUM_ROWS;
    }
    // Return the number of columns in the grid
    public int numCols()
    {
        return NUM_COLS;
    }
    
    // Print the Grid status including a header at the top
    // that shows the columns 1-10 as well as letters across
    // the side for A-J
    // If there is no guess print a -
    // If it was a miss print a O
    // If it was a hit, print an X
    // A sample print out would look something like this:
    
    public void printStatus()
    {
        ArrayList<Character> colAlph = new ArrayList<Character>();
        for(int i = 0; i < NUM_ROWS; i++)
        {
            colAlph.add((char)(i+65));
        }
        System.out.print("  ");
        for(int col = 1; col <= NUM_COLS; col++)
        {
            System.out.print(" " + col);
        }
        for(int row = 0; row < NUM_ROWS; row++)
        {
            System.out.println("");
            System.out.print(colAlph.get(row) + " ");
            for(int col = 0; col < NUM_COLS; col++)
            {
                if(grid[row][col].checkHit())
                {
                    System.out.print(" X");
                }
                else if(grid[row][col].checkMiss())
                {
                    System.out.print(" O");
                }
                else
                {
                    System.out.print(" -");
                }
            }
        }
    }
    // Print the grid and whether there is a ship at each location.
    // If there is no ship, you will print a - and if there is a
    // ship you will print a X. You can find out if there was a ship
    // by calling the hasShip method.
    
    public void printShips()
    {
        ArrayList<Character> colAlph = new ArrayList<Character>();
        for(int i = 0; i < NUM_ROWS; i++)
        {
            colAlph.add((char)(i+65));
        }
        System.out.print(" ");
        for(int col = 1; col <= NUM_COLS; col++)
        {
            System.out.print(" " + col);
        }
        for(int row = 0; row < NUM_ROWS; row++)
        {
            System.out.println("");
            System.out.print(colAlph.get(row));
            for(int col = 0; col < NUM_COLS; col++)
            {
                if(grid[row][col].hasShip())
                {
                    System.out.print(" X");
                }
                else
                {
                    System.out.print(" -");
                }
            }
        }
    }
    /**
     * This method can be called on your own grid. To add a ship
     * we will go to the ships location and mark a true value
     * in every location that the ship takes up.
     */
    public void addShip(Ship s)
    {
        
        if(s.getDirection() == VERTICAL)
        {
            for(int i = 0; i < s.getLength();i++)
            {
                setShip(s.getRow()-i,s.getCol(), true);
            }
        }
        else if(s.getDirection() == HORIZONTAL)
        {
            for(int i = 0; i < s.getLength();i++)
            {
                setShip(s.getRow(),s.getCol()+i, true);
            }
        }
    }
}
