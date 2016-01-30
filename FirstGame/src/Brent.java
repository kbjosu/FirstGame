import java.util.Random;

public class Brent extends Vehicle {
	
	Random rnd = new Random();
	
    Brent(Game g) {
        super(g);
    }

    void calculate() {
    	
    	// Change our heading slightly.
    	this.heading -= rnd.nextDouble()/100;
    	
        Base b = this.game.inRange(this);
        if (b != null) {
        	// If base is in range, stop and shoot it...  alot.
        	this.speed = 0;
            this.game.shootAt(this, b);

        }
    }
}
