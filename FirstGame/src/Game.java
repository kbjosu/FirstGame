import java.util.Random;

public class Game {
    final int MAP_WIDTH = 10000, MAP_HEIGHT = 10000;
    int MAX_VEHICLES = 20;
    boolean gameOver = false;
    Base winner = null;
    public Vehicle[] vehicle = new Vehicle[this.MAX_VEHICLES];
    Random rnd = new Random();
    Base base1 = new Base("Brent",(int) (this.rnd.nextDouble() * 10000),
            (int) (this.rnd.nextDouble() * 10000));
    Base base2 = new Base("Kyle",(int) (this.rnd.nextDouble() * 10000),
            (int) (this.rnd.nextDouble() * 10000));

    void setup(Game game) {
        for (int i = 0; i < this.MAX_VEHICLES; i++) {
            if (i % 2 == 0) {
                this.vehicle[i] = new Brent(game);
                this.vehicle[i].assignBase(this.base1);
            } else {
                this.vehicle[i] = new Kyle(game);
                this.vehicle[i].assignBase(this.base2);
            }
        }
    }

    void process() {
        for (int i = 0; i < this.MAX_VEHICLES && this.gameOver == false; i++) {
            this.vehicle[i].calculate();
            this.move(this.vehicle[i]);

            this.gameOver = (this.base1.health <= 0)
                    || (this.base2.health <= 0);
        }
    }

    void move(Vehicle vehicle) {
        vehicle.x = ((int) (vehicle.x
                + vehicle.speed * Math.cos(vehicle.heading)) + this.MAP_WIDTH)
                % this.MAP_WIDTH;
        vehicle.y = ((int) (vehicle.y
                + vehicle.speed * Math.sin(vehicle.heading)) + this.MAP_HEIGHT)
                % this.MAP_HEIGHT;
    }

    Base inRange(Vehicle v) {
        Base b = (this.base1 == v.owner) ? this.base2 : this.base1;
        double distance = Math
                .sqrt(Math.pow(v.x - b.x, 2) + Math.pow(v.y - b.y, 2));
        return ((distance < v.gunRange) ? b : null);
    }

    void shootAt(Vehicle v, Base b) {
    	long currentTime = System.currentTimeMillis();
    	
        if (b != null && b.health > 0 && this.gameOver == false && (currentTime - v.lastShot) > 1000) {
            v.lastShot = currentTime;
        	b.health -= v.gunDamage;
            System.out.println("Vehicle X hit base " + b.getName() + ". Base "
                    + b.getName() + " now has " + b.health + " health.");
            System.out.println("Base "+base1.name+": "+base1.health+" Base "+base2.name+": "+base2.health);
        }
    }
}
