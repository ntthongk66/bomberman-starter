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
    private static long timeBomb;
    private static long timeTmp;
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
    public static int powerBomb = 1;
    private static Entity bottom;
    private static final List<Entity> LeftRight = new ArrayList<>();
    private static final List<Entity> TopBottom = new ArrayList<>();
    private static final List<Image> ActiveFrame = new ArrayList<>();
    private static final List<List<Image>> ExplosionFrame = new ArrayList<>(6);



    private static int status = 0;
    // 0: nothing
    // 1: had put bomb
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
    public static void createExplosion(){
        for(int i = 1; i<powerBomb;i++){
            if(Enviroment.can_bomb_down(boom,i)){
                powerDown++;
                Bomb temp = new Bomb(boom.getX()/32,boom.getY()/32+1 + i,Sprite.bomb_exploded.getFxImage());
                bottom.setY(bottom.getY() + (i + 1) * 32);
                TopBottom.add(temp);
            }else break;
        }
        for(int i = 1; i<powerBomb;i++){
            if(Enviroment.can_bomb_up(boom,i)){
                powerUp++;
                Bomb temp = new Bomb(boom.getX()/32,boom.getY()/32-1 - i,Sprite.bomb_exploded.getFxImage());
                top.setY(bottom.getY() - (i + 1) * 32);
                TopBottom.add(temp);
            }else break;
        }
        for(int i = 1; i<powerBomb;i++){
            if(Enviroment.can_bomb_left(boom,i)){
                powerLeft++;
                Bomb temp = new Bomb(boom.getX()/32 - i - 1,boom.getY()/32,Sprite.bomb_exploded.getFxImage());
                left.setX(left.getX() - (i + 1) * 32);
                LeftRight.add(temp);
            }else break;
        }
        for(int i = 1; i<powerBomb;i++){
            if(Enviroment.can_bomb_right(boom,i)){
                powerRight++;
                Bomb temp = new Bomb(boom.getX()/32 +i + 1,boom.getY()/32,Sprite.bomb_exploded.getFxImage());
                right.setX(right.getX() + (i + 1) * 32);
                LeftRight.add(temp);
            }else break;
        }
    }

    public static void createCorner() {
        if (Enviroment.can_bomb_down(boom, 0)) {
            bottom = new Bomb(boom.getX() / 32, boom.getY() / 32 + 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_down(boom, i)) {
                        bottom.setY(bottom.getY() + (i + 1) * 32);
                        powerDown++;
                    } else break;
                }
                stillObjects.add(bottom);
            }
        }
        if (Enviroment.can_bomb_up(boom, 0)) {
            top = new Bomb(boom.getX() / 32, boom.getY() / 32 - 1,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_up(boom, i)) {
                        top.setY(top.getY() - (i + 1) * 32);
                        powerUp++;
                    } else break;
                }
                stillObjects.add(top);
            }
        }
        if (Enviroment.can_bomb_left(boom, 0)) {
            left = new Bomb(boom.getX() / 32 - 1, boom.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_left(boom, i)) {
                        left.setX(bottom.getX() - (i + 1) * 32);
                        powerLeft++;
                    } else break;
                }
                stillObjects.add(left);
            }
        }
        if (Enviroment.can_bomb_right(boom, 0)) {
            right = new Bomb(boom.getX() / 32 + 1, boom.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for (int i = 1; i < powerBomb; i++) {
                    if (Enviroment.can_bomb_right(boom, i)) {
                        right.setX(bottom.getX() + (i + 1) * 32);
                        powerRight++;
                    } else break;
                }
                stillObjects.add(right);
            }
        }
    }

    public static void createMiddle() {
        Entity middle;
        for (int i = 1; i <= powerDown; i++) {
            middle = new Bomb(boom.getX() / 32, boom.getY() / 32 + i,
                    Sprite.bomb_exploded.getFxImage());
            TopBottom.add(middle);
        }
        for (int i = 1; i <= powerUp; i++) {
            middle = new Bomb(boom.getX() / 32, boom.getY() / 32 - i,
                    Sprite.bomb_exploded.getFxImage());
            TopBottom.add(middle);
        }
        for (int i = 1; i <= powerLeft; i++) {
            middle = new Bomb(boom.getX() / 32 - i, boom.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            LeftRight.add(middle);
        }
        for (int i = 1; i <= powerRight; i++) {
            middle = new Bomb(boom.getX() / 32 - i, boom.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            LeftRight.add(middle);
        }
        stillObjects.addAll(LeftRight);
        stillObjects.addAll(TopBottom);
    }

    public static void Explosion_frame() {
        ExpCount++;
        ExpCount = ExpCount % 3;
        boom.setImg(ExplosionFrame.get(0).get(ExpCount));
        if(Enviroment.can_bomb_down(boom,powerDown)){
            bottom.setImg(ExplosionFrame.get(2).get(ExpCount));
            listKill[bottom.getX() / 32][bottom.getY() / 32] = 4;
        }
        if(Enviroment.can_bomb_up(boom,powerUp)){
            top.setImg(ExplosionFrame.get(1).get(ExpCount));
            listKill[top.getX() / 32][top.getY() / 32] = 4;
        }
        if(Enviroment.can_bomb_left(boom,powerLeft)){
            left.setImg(ExplosionFrame.get(3).get(ExpCount));
            listKill[left.getX() / 32][left.getY() / 32] = 4;
        }
        if(Enviroment.can_bomb_right(boom,powerRight)){
            right.setImg(ExplosionFrame.get(4).get(ExpCount));
            listKill[right.getX() / 32][right.getY() / 32] = 4;
        }
        for(Entity e: TopBottom){
            e.setImg(ExplosionFrame.get(6).get(ExpCount));
            listKill[e.getX() / 32][e.getY() / 32] = 4;
        }

        for(Entity e: LeftRight){
            e.setImg(ExplosionFrame.get(5).get(ExpCount));
            listKill[e.getX() / 32][e.getY() / 32] = 4;
        }
    }

    public static void resetBoom() {
        status = 0;
        idObjects[boom.getX() / 32][boom.getY() / 32] = 0;
        stillObjects.removeAll(LeftRight);
        stillObjects.removeAll(TopBottom);
        LeftRight.clear();
        TopBottom.clear();
        powerLeft = 0;
        powerUp = 0;
        powerRight = 0;
        powerLeft = 0;
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

    public void checkActive() {
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

    public static void checkExplosion() {
        if (status == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
//                    if (!isEdge) {
//                        createCorner();
//                        isEdge = true;
//                    }
//                    if (powerBomb > 0) {
//                        if (!isMiddle) {
//                            createMiddle();
//                            isMiddle = true;
//                        }
//                    }
                    createExplosion();
//                    new SoundManager("sound/bomb_explosion.wav", "explosion");
                    Explosion_frame();
                    timeTmp += 100;
                }
            }
        else
        {
            status = 0;
            idObjects[boom.getX() / 32][boom.getY() / 32] = 0;
            listKill[boom.getX() / 32][boom.getY() / 32] = 0;
            boom.setImg(Sprite.transparent.getFxImage());
            if (Enviroment.can_bomb_down(boom, powerDown)) {
                bottom.setImg(Sprite.transparent.getFxImage());
                idObjects[bottom.getX() / 32][bottom.getY() / 32] = 0;
                listKill[bottom.getX() / 32][bottom.getY() / 32] = 0;
            }
            if (Enviroment.can_bomb_up(boom, powerUp)) {
                top.setImg(Sprite.transparent.getFxImage());
                idObjects[top.getX() / 32][top.getY() / 32] = 0;
                listKill[top.getX() / 32][top.getY() / 32] = 0;
            }
            if (Enviroment.can_bomb_left(boom, powerLeft)) {
                left.setImg(Sprite.transparent.getFxImage());
                idObjects[left.getX() / 32][left.getY() / 32] = 0;
                listKill[left.getX() / 32][left.getY() / 32] = 0;
            }
            if (Enviroment.can_bomb_right(boom, powerRight)) {
                right.setImg(Sprite.transparent.getFxImage());
                idObjects[right.getX() / 32][right.getY() / 32] = 0;
                listKill[right.getX() / 32][right.getY() / 32] = 0;
            }
            if (isMiddle) {
                for (Entity e : LeftRight) {
                    listKill[e.getX() / 32][e.getY() / 32] = 0;
                    idObjects[e.getX() / 32][e.getY() / 32] = 0;
                }
                for (Entity e : TopBottom) {
                    listKill[e.getX() / 32][e.getY() / 32] = 0;
                    idObjects[e.getX() / 32][e.getY() / 32] = 0;
                }
            }
            stillObjects.removeAll(LeftRight);
            stillObjects.removeAll(TopBottom);
            LeftRight.clear();
            TopBottom.clear();

            isEdge = false;
            isMiddle = false;
            powerDown = 0;
            powerUp = 0;
            powerLeft = 0;
            powerRight = 0;
        }
        }


    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}

