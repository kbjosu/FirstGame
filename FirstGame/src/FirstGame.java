import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FirstGame extends Application {
    public static Game game;

    public static void main(String[] args) throws InterruptedException {
        game = new Game();
        game.setup(game);
        launch(args);

    }

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Game");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(500, 500);
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                game.process();
                FirstGame.this.drawEverything(gc);

            }
        }.start();
        theStage.show();

    }

    void drawEverything(GraphicsContext gc) {
        gc.clearRect(0, 0, 500, 500);
        gc.setFill(new Color(.5, .5, 1.0, 1.0));
        gc.fillOval((game.base1.x / 20) - 10, (game.base1.y / 20) - 10, 20, 20);
        gc.setFill(new Color(1.0, 0, .5, 1.0));
        gc.fillOval((game.base2.x / 20) - 10, (game.base2.y / 20) - 10, 20, 20);

        for (int i = 0; i < game.MAX_VEHICLES; i++) {
            if (game.vehicle[i].owner == game.base1) {
                gc.setFill(new Color(.5, .5, 1.0, 1.0));
            } else {
                gc.setFill(new Color(1.0, 0, .5, 1.0));

            }
            gc.fillRect(game.vehicle[i].x / 20, game.vehicle[i].y / 20, 5, 5);
        }

    }

}
