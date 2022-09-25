package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity{
    private static int ActiveSwap = 1;
    private static int ExplosionSwap = 1;
    private static int powerUp;
    private static int powerDown;
    private static int powerLeft;
    private static int powerRight;
    private static Entity boom;
    private static Entity top;
    private static Entity left;
    private static Entity right;
    public static int powerBomb = 0;
    private static Entity bottom;
    private List<Entity> LeftRight = new ArrayList<>();
    private List<Entity> TopBottom = new ArrayList<>();


    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public static void Active_frame(){
        if (ActiveSwap == 1) {
            boom.setImg(Sprite.bomb.getFxImage());
            ActiveSwap = 2;
        } else if (ActiveSwap== 2) {
            boom.setImg(Sprite.bomb_1.getFxImage());
            ActiveSwap = 3;
        } else if (ActiveSwap == 3) {
            boom.setImg(Sprite.bomb_2.getFxImage());
            ActiveSwap = 4;
        } else {
            boom.setImg(Sprite.bomb_1.getFxImage());
            ActiveSwap = 1;
        }
    }
    public static void creatCorner(){
        if(Enviroment.can_bomb_down(bomberman,0)){
            bottom = new Bomb(bomberman.getX()/32,bomberman.getY()/32 + 1,
                    Sprite.bomb_exploded.getFxImage());
            if(powerBomb > 0){
                for(int i = 1; i< powerBomb; i++){
                    if(Enviroment.can_bomb_down(bomberman,i)){
                        bottom.setY(bottom.getY() + (i+1)*32);
                        powerDown++;
                    }else break;
                }
                stillObjects.add(bottom);
            }
        }
        if(Enviroment.can_bomb_up(bomberman,0)){
            top = new Bomb(bomberman.getX()/32,bomberman.getY()/32 - 1,
                    Sprite.bomb_exploded.getFxImage());
            if(powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_up(bomberman, i)) {
                        top.setY(top.getY() - (i + 1) * 32);
                        powerUp++;
                    } else break;
                }
                stillObjects.add(top);
            }
        }
        if(Enviroment.can_bomb_left(bomberman,0)){
            left = new Bomb(bomberman.getX()/32-1,bomberman.getY()/32,
                    Sprite.bomb_exploded.getFxImage());
            if(powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_left(bomberman, i)) {
                        left.setX(bottom.getX() - (i + 1) * 32);
                        powerLeft++;
                    } else break;
                }
                stillObjects.add(left);
            }
        }
        if(Enviroment.can_bomb_right(bomberman,0)){
            right = new Bomb(bomberman.getX()/32+1,bomberman.getY()/32,
                    Sprite.bomb_exploded.getFxImage());
            if(powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_right(bomberman, i)) {
                        right.setX(bottom.getX() + (i + 1) * 32);
                        powerRight++;
                    } else break;
                }
                stillObjects.add(right);
            }
        }
    }

    @Override
    public void update() {

    }
}
