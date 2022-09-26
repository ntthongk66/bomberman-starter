package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.idObjects;

public class Enviroment {

    private static final int grassID = 0;

    public static boolean can_move_right(Entity entity) {
        return idObjects[entity.getX() / 32 + 1][entity.getY() / 32] == grassID;
    }

    public static boolean can_move_left(Entity entity) {
        return idObjects[entity.getX() / 32 - 1][entity.getY() / 32] == grassID;
    }

    public static boolean can_move_up(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1] == grassID;
    }

    public static boolean can_move_down(Entity entity) {
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1] == grassID;
    }

    // 3, 6, 7 ,8 still unknown so skip it for now 
    public static boolean can_bomb_down(Entity entity, int power) {
        int id = idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power];
        return id == grassID || id == 3 || id == 6 || id == 7 || id == 8;
    }

    public static boolean can_bomb_up(Entity entity, int power) {
        int id = idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power];
        return id == grassID || id == 3 || id == 6 || id == 7 || id == 8;
    }

    public static boolean can_bomb_left(Entity entity, int power) {

        int id = idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32];
        return id == grassID || id == 3 || id == 6 || id == 7 || id == 8;
    }

    public static boolean can_bomb_right(Entity entity, int power) {

        int id = idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32];
        return id == grassID || id == 3 || id == 6 || id == 7 || id == 8;
    }

}
