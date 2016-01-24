
public class FirstGame {

    static final int MAP_WIDTH = 10000, MAP_HEIGHT = 10000;

    public static void main(String[] args) throws InterruptedException {

        Base base1 = new Base(2500, 2500);
        Base base2 = new Base(7500, 7500);

        Vehicle vehicle1 = new Vehicle(base1);
        Vehicle vehicle2 = new Vehicle(base2);

        boolean gameOver = false;

        while (gameOver == false) {
            System.out.println("Vehicle 1 is at " + vehicle1.x + ", "
                    + vehicle1.y + ". Vehicle 2 is at " + vehicle2.x + ", "
                    + vehicle2.y + ".");

            move(vehicle1);
            move(vehicle2);

            Thread.sleep(500);
        }
    }

    static void move(Vehicle vehicle) {
        vehicle.x = ((int) (vehicle.x
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_WIDTH)
                % MAP_WIDTH;
        vehicle.y = ((int) (vehicle.y
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_HEIGHT)
                % MAP_HEIGHT;
    }

}
