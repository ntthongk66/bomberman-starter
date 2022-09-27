package uet.oop.bomberman.entities.enemys;


import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.enemys.AI.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Kondoria extends AnimatedEntity {

    private int dir;
    public Kondoria(int xUnit, int yUnit, Image img) {

        super(xUnit, yUnit, img);
        imgDown.add(Sprite.kondoria_right1.getFxImage());
        imgDown.add(Sprite.kondoria_right2.getFxImage());
        imgDown.add(Sprite.kondoria_right3.getFxImage());
        imgDown.add(Sprite.kondoria_right2.getFxImage());

        imgUp.add(Sprite.kondoria_left1.getFxImage());
        imgUp.add(Sprite.kondoria_left2.getFxImage());
        imgUp.add(Sprite.kondoria_left3.getFxImage());
        imgUp.add(Sprite.kondoria_left2.getFxImage());

        imgLeft.add(Sprite.kondoria_right1.getFxImage());
        imgLeft.add(Sprite.kondoria_right2.getFxImage());
        imgLeft.add(Sprite.kondoria_right3.getFxImage());
        imgLeft.add(Sprite.kondoria_right2.getFxImage());

        imgRight.add(Sprite.kondoria_left1.getFxImage());
        imgRight.add(Sprite.kondoria_left3.getFxImage());
        imgRight.add(Sprite.kondoria_left2.getFxImage());
        imgRight.add(Sprite.kondoria_left1.getFxImage());

        isRunning = false;
        MAXNUMSTEP = 8;
        MAXDELAYTIME = 8;
        speed = 4;


    }
    private AI ai = new smartAI(BombermanGame.bomberman, this);

    public void RandomMove(){
        if (this.x % 16 == 0 && this.y % 16 == 0) {
           dir = ai.calculateDirection();
            switch (dir) {
                case 0:
                    AnimatedEntity.move(this, "UP");
                    break;
                case 1: // here is changed
                    AnimatedEntity.move(this, "RIGHT");
                    break;
                case 2:
                    AnimatedEntity.move(this, "DOWN");
                    break;
                case 3:
                    AnimatedEntity.move(this, "LEFT");
                    break;
            }
        }
    }
    public void update() {
        RandomMove();
    }



}
