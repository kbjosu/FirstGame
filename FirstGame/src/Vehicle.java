import java.util.Random;

public class Vehicle {
    Base owner;
    double heading;
    int speed = 5;
    int gunRange = 500;
    int gunDamage = 50;
    int health = 100;
    int x, y;
    int ammo = 3;

    Vehicle(Base startingBase) {
        this.owner = startingBase;
        Random rnd = new Random();
        this.heading = rnd.nextDouble() * 2 * Math.PI;
        this.x = startingBase.x;
        this.y = startingBase.y;

    }

}
