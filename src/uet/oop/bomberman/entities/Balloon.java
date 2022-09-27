package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends AnimatedEntity{


    public Balloon(int xUnit, int yUnit, Image img) {

        super(xUnit, yUnit, img);
        imgDown.add(Sprite.balloom_right1.getFxImage());
        imgDown.add(Sprite.balloom_right2.getFxImage());
        imgDown.add(Sprite.balloom_right3.getFxImage());
        imgDown.add(Sprite.balloom_right2.getFxImage());

        imgUp.add(Sprite.balloom_left1.getFxImage());
        imgUp.add(Sprite.balloom_left2.getFxImage());
        imgUp.add(Sprite.balloom_left3.getFxImage());
        imgUp.add(Sprite.balloom_left2.getFxImage());

        imgLeft.add(Sprite.balloom_right1.getFxImage());
        imgLeft.add(Sprite.balloom_right2.getFxImage());
        imgLeft.add(Sprite.balloom_right3.getFxImage());
        imgLeft.add(Sprite.balloom_right2.getFxImage());

        imgRight.add(Sprite.balloom_left1.getFxImage());
        imgRight.add(Sprite.balloom_left3.getFxImage());
        imgRight.add(Sprite.balloom_left2.getFxImage());
        imgRight.add(Sprite.balloom_left1.getFxImage());

        isRunning = false;
        MAXNUMSTEP = 8;
        MAXDELAYTIME = 8;
        speed = 4;
    }
    public void RandomMove(){
        if (this.x % 16 == 0 && this.y % 16 == 0) {
            Random random = new Random();
            int dir = random.nextInt(4);
            switch (dir) {
                case 1:
                    AnimatedEntity.move(this, "DOWN");
                    break;
                case 2:
                    AnimatedEntity.move(this, "UP");
                    break;
                case 3:
                    AnimatedEntity.move(this, "LEFT");
                    break;
                case 4:
                    AnimatedEntity.move(this, "RIGHT");
                    break;
            }
        }
    }
    public void update() {
        RandomMove();
    }



}
