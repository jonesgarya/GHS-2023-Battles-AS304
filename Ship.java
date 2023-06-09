public class Ship
{
    // Direction constants
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row = UNSET;
    private int col = UNSET;
    private int length = UNSET;
    private int direction = UNSET;
    // Constructor. Create a ship and set the length.
    public Ship(int length)
    {
        this.length = length;
    }
    // Has the location been initialized
    public boolean isLocationSet()
    {
        if(row == UNSET && col == UNSET)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    // Has the direction been initialized
    public boolean isDirectionSet()
    {
        if(direction == UNSET)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    // Set the location of the ship 
    public void setLocation(int row, int col)
    {
        this.row = row-1;
        this.col = col;
    }
    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    // Getter for the row value
    public int getRow()
    {
        return row;
    }
    // Getter for the column value
    public int getCol()
    {
        return col-1;
    }
    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }
    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }
    // Helper method to get a string value from the direction
    private String directionToString()
    {
        if(direction == VERTICAL)
        {
            return "Vertical";
        }
        else if(direction == HORIZONTAL)
        {
            return "Horizontal";
        }
        else
        {
            return "Unset";
        }
    }
    // toString value for this Ship
    public String toString()
    {
        if(col == UNSET || row == UNSET || directionToString().equals("Unset"))
        {
            return "The ship is unset.";
        }
        else
        {
        char[] arr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        if (row > 25 || row < 0) {
            return null;
        }
        String rowLetter = Character.toString(arr[row]);
        return  rowLetter + col + " " + directionToString();
        }
    }
}
