package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
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
    private static List<Entity> LeftRight = new ArrayList<>();
    private static List<Entity> TopBottom = new ArrayList<>();
    private static int status = 0;
    // 0: nothing
    // 1: had put bomb
    // 2: explosion
    public static int bombNumber = 20;
    public static boolean isEdge = false;
    public static boolean isMiddle = false;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void Active_frame() {
        if (ActiveSwap == 1) {
            boom.setImg(Sprite.bomb.getFxImage());
            ActiveSwap = 2;
        } else if (ActiveSwap == 2) {
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
        if (ExplosionSwap == 1) {
            boom.setImg(Sprite.bomb_exploded.getFxImage());
            if (Enviroment.can_bomb_down(boom, powerDown)) {
                bottom.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                listKill[bottom.getX() / 32][bottom.getY() / 32] = 4;
            }
            if (Enviroment.can_bomb_up(boom, powerUp)) {
                top.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                listKill[top.getX() / 32][top.getY() / 32] = 4;
            }
            if (Enviroment.can_bomb_left(boom, powerLeft)) {
                left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                listKill[left.getX() / 32][left.getY() / 32] = 4;
            }
            if (Enviroment.can_bomb_right(boom, powerRight)) {
                right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                listKill[right.getX() / 32][right.getY() / 32] = 4;
            }
            for (Entity e : TopBottom) {
                e.setImg(Sprite.explosion_vertical.getFxImage());
                listKill[e.getX() / 32][e.getY() / 32] = 4;
            }
            for (Entity e : LeftRight) {
                e.setImg(Sprite.explosion_horizontal.getFxImage());
                listKill[e.getX() / 32][e.getY() / 32] = 4;
            }
            ExplosionSwap = 2;
        }
        if (ExplosionSwap == 2) {
            boom.setImg(Sprite.bomb_exploded1.getFxImage());
            if (Enviroment.can_bomb_down(boom, powerDown)) {
                bottom.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }
            if (Enviroment.can_bomb_up(boom, powerUp)) {
                top.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }
            if (Enviroment.can_bomb_left(boom, powerLeft)) {
                left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }
            if (Enviroment.can_bomb_right(boom, powerRight)) {
                right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }
            for (Entity e : TopBottom) {
                e.setImg(Sprite.explosion_vertical1.getFxImage());
            }
            for (Entity e : LeftRight) {
                e.setImg(Sprite.explosion_horizontal1.getFxImage());
            }
            ExplosionSwap = 3;
        }
        if (ExplosionSwap == 3) {
            boom.setImg(Sprite.bomb_exploded2.getFxImage());
            if (Enviroment.can_bomb_down(boom, powerDown)) {
                bottom.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            }
            if (Enviroment.can_bomb_up(boom, powerUp)) {
                top.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }
            if (Enviroment.can_bomb_left(boom, powerLeft)) {
                left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }
            if (Enviroment.can_bomb_right(boom, powerRight)) {
                right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }
            for (Entity e : TopBottom) {
                e.setImg(Sprite.explosion_vertical2.getFxImage());
            }
            for (Entity e : LeftRight) {
                e.setImg(Sprite.explosion_horizontal2.getFxImage());
            }
            ExplosionSwap = 1;
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

    public static void checkActive() {
        if (status == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    Active_frame();
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
                    if (!isEdge) {
                        createCorner();
                        isEdge = true;
                    }
                    if (powerBomb > 0) {
                        if (!isMiddle) {
                            createMiddle();
                            isMiddle = true;
                        }
                    }
//                    new SoundManager("sound/bomb_explosion.wav", "explosion");
                    Explosion_frame();
                    timeTmp += 100;
                }
            }
        else
        {
            status = 0;
            idObjects[boom.getX() / 32][boom.getY() / 32] = 0;
            //listKill[bomb.getX() / 32][bomb.getY() / 32] = 0;
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

