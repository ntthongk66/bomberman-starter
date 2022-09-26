package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.enemy;
import static uet.oop.bomberman.BombermanGame.listKill;

public class Bomber extends AnimatedEntity{
    private int swap;
    private int deadSwap = 1;


    public Bomber(int x, int y, Image img) {

        super( x, y, img);
        imgDown.add(Sprite.player_down.getFxImage());
        imgDown.add(Sprite.player_down_1.getFxImage());
        imgDown.add(Sprite.player_down.getFxImage());
        imgDown.add(Sprite.player_down_2.getFxImage());

        imgUp.add(Sprite.player_up.getFxImage());
        imgUp.add(Sprite.player_up_1.getFxImage());
        imgUp.add(Sprite.player_up.getFxImage());
        imgUp.add(Sprite.player_up_2.getFxImage());

        imgLeft.add(Sprite.player_left.getFxImage());
        imgLeft.add(Sprite.player_left_1.getFxImage());
        imgLeft.add(Sprite.player_left.getFxImage());
        imgLeft.add(Sprite.player_left_2.getFxImage());

        imgRight.add(Sprite.player_right.getFxImage());
        imgRight.add(Sprite.player_right_1.getFxImage());
        imgRight.add(Sprite.player_right.getFxImage());
        imgRight.add(Sprite.player_right_2.getFxImage());

        isRunning = false;
        MAXNUMSTEP = 4;
        speed = 8;
        MAXDELAYTIME = 4;
    }

//    @Override
//    public Image chooseImg(String direction) {
//        if(!isRunning)
//            return imgRight.get(0);
//        int chooseFrame = count  ;
//        switch (direction){
//            case "UP":
//                return imgUp.get(chooseFrame);
//            case "DOWN":
//                return imgDown.get(chooseFrame);
//            case "LEFT":
//                return imgLeft.get(chooseFrame);
//            case "RIGHT":
//                return imgRight.get(chooseFrame);
//        }
//        return  null;
//    }

    public  void setSwap(int s){
        this.swap = s;
    }
    public int getSwap(){
        return this.swap;
    }

    /*
    *
    *
    * */
    public void Dead_frame(){
        if(deadFrame % 16 == 0){
            if(deadSwap == 1){
                this.setImg(Sprite.player_dead1.getFxImage());
                deadSwap = 2;
            }if(deadSwap == 2){
                this.setImg(Sprite.player_dead2.getFxImage());
                deadSwap = 3;
            }if(deadSwap == 3){
                this.setImg(Sprite.player_dead1.getFxImage());
                deadSwap = 4;
            }else{
                this.setImg(Sprite.transparent.getFxImage());
            }
        }
    }
    public void checkBoms(){
        if(listKill[this.getX()/32][this.getY()/32] == 4){
            this.life = false;
        }
    }
    public void checkEnemy(){
        int ax  = this.getX();
        int ay = this.getY();
        for(Entity animal : enemy){
            int bx = animal.getX();
            int by = animal.getY();

            if (ax == bx && by - 32 <= ay && by + 32 >= ay
                    || ay == by && bx - 32 <= ax && bx + 32 >= ax) {
                this.life = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBoms();
        //checkEnemy();
        deadFrame++;
        if(!this.life){
            Dead_frame();
        }
    }
}
