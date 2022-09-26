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

//    @Override
//    public void down_step() {
//        if(this.getY() % 8 == 0){
//            if (this.getSwap() == 1) {
//                this.setImg(Sprite.balloom_right1.getFxImage());
//                this.setSwap(2);
//            } else if (this.getSwap() == 2) {
//                this.setImg(Sprite.balloom_right2.getFxImage());
//                this.setSwap(3);
//            } else if (this.getSwap() == 3) {
//                this.setImg(Sprite.balloom_right3.getFxImage());
//                this.setSwap(4);
//            } else {
//                this.setImg(Sprite.balloom_right2.getFxImage());
//                this.setSwap(1);
//            }
//        }
//    }
//
//    @Override
//    public void up_step() {
//        if(this.getY() % 8 == 0){
//            if (this.getSwap() == 1) {
//                this.setImg(Sprite.balloom_left1.getFxImage());
//                this.setSwap(2);
//            } else if (this.getSwap() == 2) {
//                this.setImg(Sprite.balloom_left2.getFxImage());
//                this.setSwap(3);
//            } else if (this.getSwap() == 3) {
//                this.setImg(Sprite.balloom_left3.getFxImage());
//                this.setSwap(4);
//            } else {
//                this.setImg(Sprite.balloom_left2.getFxImage());
//                this.setSwap(1);
//            }
//        }
//
//    }
//
//    @Override
//    public void right_step() {
//        if(this.getX() % 8 == 0){
//            if (this.getSwap() == 1) {
//                this.setImg(Sprite.balloom_left1.getFxImage());
//                this.setSwap(2);
//            } else if (this.getSwap() == 2) {
//                this.setImg(Sprite.balloom_left2.getFxImage());
//                this.setSwap(3);
//            } else if (this.getSwap() == 3) {
//                this.setImg(Sprite.balloom_left3.getFxImage());
//                this.setSwap(4);
//            } else {
//                this.setImg(Sprite.balloom_left2.getFxImage());
//                this.setSwap(1);
//            }
//        }
//
//    }
//
//    @Override
//    public void left_step() {
//        if(this.getX() % 8 == 0){
//            if (this.getSwap() == 1) {
//                this.setImg(Sprite.balloom_right1.getFxImage());
//                this.setSwap(2);
//            } else if (this.getSwap() == 2) {
//                this.setImg(Sprite.balloom_right2.getFxImage());
//                this.setSwap(3);
//            } else if (this.getSwap() == 3) {
//                this.setImg(Sprite.balloom_right3.getFxImage());
//                this.setSwap(4);
//            } else {
//                this.setImg(Sprite.balloom_right2.getFxImage());
//                this.setSwap(1);
//            }
//        }
//    }
    public void update() {
        if (this.x % 16 == 0 && this.y % 16 == 0) {
            Random random = new Random();
            int dir = random.nextInt(4);
            switch (dir) {
                case 1:
                    AnimatedEntity.move(this,"DOWN");
                    break;
                case 2:
                    AnimatedEntity.move(this,"UP");
                    break;
                case 3:
                    AnimatedEntity.move(this,"LEFT");
                    break;
                case 4:
                    AnimatedEntity.move(this,"RIGHT");
                    break;
            }
        }
    }



}
