public class GridTester extends ConsoleProgram
{
    public void run()
    {
        Grid g1 = new Grid();
        g1.markMiss(5,8);
        g1.markHit(7,8);
        g1.setStatus(7,4,2);
        g1.printStatus();
        
        Ship tempOne = new Ship(2);
        tempOne.setDirection(1);
        tempOne.setLocation(3,1);
        
        Ship tempTwo = new Ship(3);
        tempTwo.setDirection(1);
        tempTwo.setLocation(4,7);
        
        Ship tempThree = new Ship(4);
        tempThree.setDirection(0);
        tempThree.setLocation(8,5);
        System.out.println(tempThree.toString());
        Grid g1 = new Grid();
        g1.addShip(tempThree);
        g1.addShip(tempOne);
        g1.addShip(tempTwo);
        g1.printShips();
    }
}
