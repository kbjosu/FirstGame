import java.util.Scanner;

public class Game {
    final int MAP_WIDTH = 10000, MAP_HEIGHT = 10000;

    Base base1 = new Base(2500, 2500);
    Base base2 = new Base(7500, 7500);
    int MAX_VEHICLES;
    boolean gameOver = false;
    Base winner = null;
    public Vehicle[] vehicle;

    void setup() {

        System.out.print(
                "How many total vehicles do you want? Enter an even number: ");
        Scanner in = new Scanner(System.in);
        this.MAX_VEHICLES = in.nextInt();
        this.vehicle = new Vehicle[this.MAX_VEHICLES];
        for (int i = 0; i < this.MAX_VEHICLES; i++) {
            this.vehicle[i] = new Vehicle();
            System.out.println("Vehicle " + (i + 1) + " has speed "
                    + this.vehicle[i].speed);
        }

        for (int i = 0; i < this.MAX_VEHICLES; i++) {
            System.out.print("Player " + (i % 2 + 1)
                    + " chose a vehicle by entering the vehicle number: ");
            int choice = in.nextInt();

            while (this.vehicle[choice - 1].owner != null) {
                System.out.print("Vehicle " + choice
                        + " has already been chosen. Please chose another vehicle number: ");
                choice = in.nextInt();
            }

            if (i % 2 == 0) {
                this.vehicle[choice - 1].assignBase(this.base1);
            } else {
                this.vehicle[choice - 1].assignBase(this.base2);
            }
        }
        in.close();
    }

    void process() {
        /*
         * System.out.println("Vehicle 1 is at " + vehicle1.x + ", " +
         * vehicle1.y + ". Vehicle 2 is at " + vehicle2.x + ", " + vehicle2.y +
         * ".");
         */

        for (int i = 0; i < this.MAX_VEHICLES && this.gameOver == false; i++) {
            this.move(this.vehicle[i]);
            Base target = this.inRange(this.vehicle[i]);
            if (target != null && this.vehicle[i].ammo > 0 && target.health > 0
                    && this.gameOver == false) {
                target.health -= this.vehicle[i].gunDamage;
                System.out.println("Vehicle " + (i + 1) + " hit base "
                        + target.getName() + ". Base " + target.getName()
                        + " now has " + target.health + " health.");
                this.vehicle[i].ammo--;
                this.gameOver = (this.base1.health <= 0)
                        || (this.base2.health <= 0);
                if (target != null && target.health <= 0) {
                    this.winner = (this.vehicle[i].owner.getName() == "1")
                            ? this.base2 : this.base1;

                }
            }
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

}
