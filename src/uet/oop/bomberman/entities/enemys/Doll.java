package uet.oop.bomberman.entities.enemys;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends AnimatedEntity {


    public Doll(int xUnit, int yUnit, Image img) {

        super(xUnit, yUnit, img);
        imgDown.add(Sprite.doll_right1.getFxImage());
        imgDown.add(Sprite.doll_right2.getFxImage());
        imgDown.add(Sprite.doll_right3.getFxImage());
        imgDown.add(Sprite.doll_right2.getFxImage());

        imgUp.add(Sprite.doll_left1.getFxImage());
        imgUp.add(Sprite.doll_left2.getFxImage());
        imgUp.add(Sprite.doll_left3.getFxImage());
        imgUp.add(Sprite.doll_left2.getFxImage());

        imgLeft.add(Sprite.doll_right1.getFxImage());
        imgLeft.add(Sprite.doll_right2.getFxImage());
        imgLeft.add(Sprite.doll_right3.getFxImage());
        imgLeft.add(Sprite.doll_right2.getFxImage());

        imgRight.add(Sprite.doll_left1.getFxImage());
        imgRight.add(Sprite.doll_left3.getFxImage());
        imgRight.add(Sprite.doll_left2.getFxImage());
        imgRight.add(Sprite.doll_left1.getFxImage());

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
                case 0:
                    AnimatedEntity.move(this, "DOWN");
                    break;
                case 1:
                    AnimatedEntity.move(this, "UP");
                    break;
                case 2:
                    AnimatedEntity.move(this, "LEFT");
                    break;
                case 3:
                    AnimatedEntity.move(this, "RIGHT");
                    break;
            }
        }
    }
    public void update() {
        RandomMove();
    }



}
