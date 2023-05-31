public class GridTester extends ConsoleProgram
{
    public void run()
    {
        Grid g1 = new Grid();
        g1.markMiss(5,8);
        g1.markHit(7,8);
        g1.setStatus(7,4,2);
        g1.printStatus();
    }
}
