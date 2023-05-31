public class LocationTester extends ConsoleProgram
{
    public void run()
    {
        // This code tests the methods of the Location class
        
        Location loc = new Location();
        
        // Unguessed
        System.out.println("UNGUESSED LOCATION");
        System.out.println("Hit? " + loc.checkHit());
        System.out.println("Miss? " + loc.checkMiss());
        System.out.println("Unguessed? " + loc.isUnguessed());
        System.out.println("Ship? " + loc.hasShip());
        System.out.println("Status: " + loc.getStatus());
        System.out.println();
        
        // Miss
        loc.markMiss();
        System.out.println("MISSED LOCATION");
        System.out.println("Hit? " + loc.checkHit());
        System.out.println("Miss? " + loc.checkMiss());
        System.out.println("Unguessed? " + loc.isUnguessed());
        System.out.println("Ship? " + loc.hasShip());
        System.out.println("Status: " + loc.getStatus());
        System.out.println();
        
        // Hit with ship
        Location hit = new Location();
        hit.markHit();
        hit.setShip(true);
        
        System.out.println("HIT LOCATION");
        System.out.println("Hit? " + hit.checkHit());
        System.out.println("Miss? " + hit.checkMiss());
        System.out.println("Unguessed? " + hit.isUnguessed());
        System.out.println("Ship? " + hit.hasShip());
        System.out.println("Status: " + hit.getStatus());
        System.out.println();
        
        // Set the status
        hit.setStatus(0);
        System.out.println("SET STATUS TO 0");
        System.out.println("Hit? " + hit.checkHit());
        System.out.println("Miss? " + hit.checkMiss());
        System.out.println("Unguessed? " + hit.isUnguessed());
        System.out.println("Ship? " + hit.hasShip());
        System.out.println("Status: " + hit.getStatus());
    }
}
