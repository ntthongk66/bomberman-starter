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
    }
    public  void setSwap(int s){
        this.swap = s;
    }
    public int getSwap(){
        return this.swap;
    }
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
//            if(ax == bx){
//                if((by <= ay && by + 32 >= ay) ||(by <= ay + 32 && by + 32 >= ay+32))
//                    this.life = false;
//            }
//            else if(ay == by){
//                if((bx <= ax && bx + 32 >= ax) ||(bx <= ax + 32 && bx + 32 >= ax+32))
//                    this.life = false;
//            }

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
        checkEnemy();
        deadFrame++;
        if(!this.life){
            Dead_frame();
        }
    }
}
