package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.Random;

public class Balloon extends AnimatedEntity {
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
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
                    AnimatedEntity.move_down(this);
                    break;
                case 2:
                    AnimatedEntity.move_up(this);
                    break;
                case 3:
                    AnimatedEntity.move_left(this);
                    break;
                case 4:
                    AnimatedEntity.move_right(this);
                    break;
            }
        }
    }


}
