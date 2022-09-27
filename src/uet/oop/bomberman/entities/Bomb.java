package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    private static int AcCount = 0;
    private static int ExpCount = 0;
    private static boolean setRange = false;
    private static long timeBomb;
    private static long timeTmp;
    private static int ActiveSwap = 1;
    private static int ExplosionSwap = 1;
    private static int powerUp = 0;
    private static int powerDown = 0;
    private static int powerLeft = 0;
    private static int powerRight = 0;
    private static Entity boom;
    private static Entity top;
    private static Entity left;
    private static Entity right;
    public static int powerBomb = 2;
    private static Entity bottom;
    private static final List<Entity> LeftRight = new ArrayList<>();
    private static final List<Entity> TopBottom = new ArrayList<>();
    private static final List<Image> ActiveFrame = new ArrayList<>();
    private static final List<List<Image>> ExplosionFrame = new ArrayList<>(6);



    private static int status = 0;
    // 0: nothing
    // 1: had put boom
    // 2: explosion
    public static int bombNumber = 20;
    public static boolean isEdge = false;
    public static boolean isMiddle = false;


    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ActiveFrame.add(Sprite.bomb.getFxImage());
        ActiveFrame.add(Sprite.bomb_1.getFxImage());
        ActiveFrame.add(Sprite.bomb_2.getFxImage());
        ActiveFrame.add(Sprite.bomb_1.getFxImage());

        /*
        * 0 : Middle
        * 1: TopCorner
        * 2: BottomCorner
        * 3: LeftCorner
        * 4: RightCorner
        * 5: Horizon
        * 6: Vertical
        * */
        List<Image> temp = new ArrayList<>();
        for(int i = 0; i< 7; i++)
            ExplosionFrame.add(new ArrayList<>());
        ExplosionFrame.get(0).add(Sprite.bomb_exploded.getFxImage());
        ExplosionFrame.get(0).add(Sprite.bomb_exploded1.getFxImage());
        ExplosionFrame.get(0).add(Sprite.bomb_exploded2.getFxImage());

        ExplosionFrame.get(1).add(Sprite.explosion_vertical_top_last.getFxImage());
        ExplosionFrame.get(1).add(Sprite.explosion_vertical_top_last1.getFxImage());
        ExplosionFrame.get(1).add(Sprite.explosion_vertical_top_last2.getFxImage());

        ExplosionFrame.get(2).add(Sprite.explosion_vertical_down_last.getFxImage());
        ExplosionFrame.get(2).add(Sprite.explosion_vertical_down_last1.getFxImage());
        ExplosionFrame.get(2).add(Sprite.explosion_vertical_down_last2.getFxImage());

        ExplosionFrame.get(3).add(Sprite.explosion_horizontal_left_last.getFxImage());
        ExplosionFrame.get(3).add(Sprite.explosion_horizontal_left_last1.getFxImage());
        ExplosionFrame.get(3).add(Sprite.explosion_horizontal_left_last2.getFxImage());

        ExplosionFrame.get(4).add(Sprite.explosion_horizontal_right_last.getFxImage());
        ExplosionFrame.get(4).add(Sprite.explosion_horizontal_right_last1.getFxImage());
        ExplosionFrame.get(4).add(Sprite.explosion_horizontal_right_last2.getFxImage());

        ExplosionFrame.get(5).add(Sprite.explosion_horizontal.getFxImage());
        ExplosionFrame.get(5).add(Sprite.explosion_horizontal1.getFxImage());
        ExplosionFrame.get(5).add(Sprite.explosion_horizontal2.getFxImage());

        ExplosionFrame.get(6).add(Sprite.explosion_vertical.getFxImage());
        ExplosionFrame.get(6).add(Sprite.explosion_vertical1.getFxImage());
        ExplosionFrame.get(6).add(Sprite.explosion_vertical2.getFxImage());
    }

    public Image choosingImg(){
        AcCount++;
        AcCount = AcCount % 4;
        return ActiveFrame.get(AcCount);
    }
    public static void rangeOfExplosion(){
        //Check to Down
        for(int i = 1 ; i<= powerBomb; i++){
            if(Enviroment.can_bomb(boom,0,i)){
               powerDown++;
            }else{
                break;
            }
        }
        if(powerDown  > 0){
            for(int j = 1; j < powerDown;j++){
                Entity temp = new Bomb(boom.getX()/32,boom.getY()/32 + j,Sprite.bomb_exploded.getFxImage());
                TopBottom.add(temp);
            }
            bottom = new Bomb(boom.getX()/32,boom.getY()/32 + powerDown,Sprite.bomb_exploded.getFxImage());
            stillObjects.add(bottom);
        }
        //Check to Up
        for(int i = 1 ; i<= powerBomb; i++){
            if(Enviroment.can_bomb(boom,0,-i)){
                powerUp++;
            }else{
                break;
            }
        }
        if(powerUp  > 0){
            for(int j = 1; j < powerUp;j++){
                Entity temp = new Bomb(boom.getX()/32,boom.getY()/32 - j,Sprite.bomb_exploded.getFxImage());
                TopBottom.add(temp);
            }
            top = new Bomb(boom.getX()/32,boom.getY()/32 - powerUp,Sprite.bomb_exploded.getFxImage());
            stillObjects.add(top);
        }
        //Check to Left
        for(int i = 1 ; i<= powerBomb; i++){
            if(Enviroment.can_bomb(boom,i,0)){
                powerRight++;
            }else{
                break;
            }
        }
        if(powerRight > 0){
            for(int j = 1; j < powerDown;j++){
                Entity temp = new Bomb(boom.getX()/32 + j,boom.getY()/32,Sprite.bomb_exploded.getFxImage());
                LeftRight.add(temp);
            }
            right = new Bomb(boom.getX()/32 + powerRight,boom.getY()/32 ,Sprite.bomb_exploded.getFxImage());
            stillObjects.add(right);
        }
        //Check to Left
        for(int i = 1 ; i<= powerBomb; i++){
            if(Enviroment.can_bomb(boom,-i,0)){
                powerLeft++;
            }else{
                break;
            }
        }
        if(powerLeft > 0){
            for(int j = 1; j < powerDown;j++){
                Entity temp = new Bomb(boom.getX()/32 - j,boom.getY()/32,Sprite.bomb_exploded.getFxImage());
                LeftRight.add(temp);
            }
            left = new Bomb(boom.getX()/32 - powerLeft,boom.getY()/32 ,Sprite.bomb_exploded.getFxImage());
            stillObjects.add(left);
        }

        stillObjects.addAll(TopBottom);
        stillObjects.addAll(LeftRight);
    }
    public static void setExpFrame(){
        ExpCount++;
        ExpCount = ExpCount % 3;
        boom.setImg(ExplosionFrame.get(0).get(ExpCount));
        if(top != null){
            listKill[top.getX() / 32][top.getY() / 32] = 4;
            top.setImg(ExplosionFrame.get(1).get(ExpCount));
        }
        if(bottom != null){
            listKill[bottom.getX() / 32][bottom.getY() / 32] = 4;
            bottom.setImg(ExplosionFrame.get(2).get(ExpCount));
        }
        if(left != null){
            listKill[left.getX() / 32][left.getY() / 32] = 4;
            left.setImg(ExplosionFrame.get(3).get(ExpCount));
        }
        if(right != null){
            listKill[right.getX() / 32][right.getY() / 32] = 4;
            right.setImg(ExplosionFrame.get(4).get(ExpCount));
        }
        if(!LeftRight.isEmpty()){
            for(Entity e : LeftRight){
                e.setImg(ExplosionFrame.get(5).get(ExpCount));
                listKill[e.getX() / 32][e.getY() / 32] = 4;
            }
        }
        if(!TopBottom.isEmpty()){
            for(Entity e: TopBottom){
                e.setImg(ExplosionFrame.get(6).get(ExpCount));
                listKill[e.getX() / 32][e.getY() / 32] = 4;
            }
        }

    }
    public static void putBomb() {
        if (status == 0 && bombNumber > 0) {
            bombNumber--;
            status = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = bomberman.getX() / 32;
            int y = bomberman.getY() / 32;
            x = Math.round(x);
            y = Math.round(y);
            boom = new Bomb(x, y, Sprite.bomb.getFxImage());
            stillObjects.add(boom);
            idObjects[bomberman.getX() / 32][bomberman.getY() / 32] = 4;
        }
    }
    private void checkActive() {
        if (status == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    boom.setImg(choosingImg());
                    timeTmp += 100;
                }
            } else {
                status = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }
    private static void checkExplosion() {
        if (status == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    if(!setRange){
                        rangeOfExplosion();
                        setRange = true;
                    }

                   // new SoundManager("sound/bomb_explosion.wav", "explosion");
                    setExpFrame();
                    timeTmp += 100;
                }
            } else {
                resetBomb();
            }
    }
    public static void resetBomb(){
        if(!LeftRight.isEmpty()){
            for (Entity e : LeftRight) {
                e.setImg(Sprite.transparent.getFxImage());
                listKill[e.getX() / 32][e.getY() / 32] = 0;
                idObjects[e.getX() / 32][e.getY() / 32] = 0;
            }
        }
        if(!TopBottom.isEmpty()){
            for (Entity e : TopBottom) {

                e.setImg(Sprite.transparent.getFxImage());

                listKill[e.getX() / 32][e.getY() / 32] = 0;
                idObjects[e.getX() / 32][e.getY() / 32] = 0;
            }
        }
        if(bottom != null){

            bottom.setImg(Sprite.transparent.getFxImage());
            idObjects[bottom.getX() / 32][bottom.getY() / 32] = 0;
            listKill[bottom.getX() / 32][bottom.getY() / 32] = 0;
        }
        if(top != null){

            top.setImg(Sprite.transparent.getFxImage());

            idObjects[top.getX() / 32][top.getY() / 32] = 0;
            listKill[top.getX() / 32][top.getY() / 32] = 0;
        }
        if(left != null){

            left.setImg(Sprite.transparent.getFxImage());
            idObjects[left.getX() / 32][left.getY() / 32] = 0;
            listKill[left.getX() / 32][left.getY() / 32] = 0;
        }
        if(right != null){
            right.setImg(Sprite.transparent.getFxImage());
            idObjects[right.getX() / 32][right.getY() / 32] = 0;
            listKill[right.getX() / 32][right.getY() / 32] = 0;
        }
        status = 0;
        idObjects[boom.getX() / 32][boom.getY() / 32] = 0;
        listKill[boom.getX() / 32][boom.getY() / 32] = 0;
        boom.setImg(Sprite.transparent.getFxImage());
        stillObjects.removeAll(TopBottom);
        stillObjects.removeAll(LeftRight);
        TopBottom.clear();
        LeftRight.clear();
        setRange = false;
        powerDown = 0;
        powerUp = 0;
        powerLeft = 0;
        powerRight = 0;
        top = null;
        bottom = null;
        right = null;
        left = null;
    }


    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}

