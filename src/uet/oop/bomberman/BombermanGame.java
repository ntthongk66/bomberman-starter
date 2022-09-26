package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    public static Bomber bomberman;
    public static int[][] listKill;
    public static Balloon ballom;
    public static Bomb bomm;
    private GraphicsContext gc;
    private Canvas canvas;
    public List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enemy = new ArrayList<>();
    public static int[][] idObjects;
    public CreateMap crM = new CreateMap("Level1");


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (true)
                switch (event.getCode()) {
                    case UP:
                        AnimatedEntity.move(bomberman,"UP");
                        break;
                    case DOWN:
                        AnimatedEntity.move(bomberman,"DOWN");
                        break;
                    case LEFT:
                        AnimatedEntity.move(bomberman,"LEFT");
                        break;
                    case RIGHT:
                        AnimatedEntity.move(bomberman,"RIGHT");
                        break;
                    case SPACE:
                        Bomb.putBomb();
                        break;
                }
        });
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();



        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        ballom = new Balloon(4,5,Sprite.balloom_left1.getFxImage());
        //bomm = new Bomb(2,3,Sprite.bomb_2.getFxImage());

        entities.add(bomberman);
        enemy.add(ballom);
    }


    public void update() {
        entities.forEach(Entity::update);
        enemy.forEach(Entity::update);

        AnimatedEntity.controlFrame(bomberman);
        AnimatedEntity.controlFrame(ballom);
        stillObjects.forEach(Entity::update);

        //bomberman.controlFrame();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemy.forEach(g->g.render(gc));
    }
}
