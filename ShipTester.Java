public class ShipTester extends ConsoleProgram
{
    public void run() 
    { 
        // This code tests out the methods of the Ship class. 
        Ship ship = new Ship(4); 
        
        System.out.println("Ship: " + ship); 
        System.out.println("Location set: " + ship.isLocationSet()); 
        System.out.println("Direction set: " + ship.isDirectionSet()); 
        
        ship.setDirection(0); 
        ship.setLocation(4, 5); 
        
        System.out.println("Ship: " + ship); System.out.println("Location set: " + ship.isLocationSet()); 
        System.out.println("Direction set: " + ship.isDirectionSet()); 
        System.out.println("Length: " + ship.getLength()); 
        System.out.println("Direction: " + ship.getDirection()); 
        System.out.println("Row: " + ship.getRow()); 
        System.out.println("Col: " + ship.getCol());
    }
}
