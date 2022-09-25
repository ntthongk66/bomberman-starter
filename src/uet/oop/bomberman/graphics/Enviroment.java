package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.idObjects;

public class Enviroment {
    public static boolean can_move_right(Entity entity){
        return idObjects[entity.getX()/32 + 1][entity.getY()/32] == 0;
    }
    public static boolean can_move_left(Entity entity){
        return idObjects[entity.getX()/32 -1][entity.getY()/32] == 0;
    }
    public static boolean can_move_up(Entity entity){
        return idObjects[entity.getX()/32][entity.getY()/32 -1] ==0;
    }
    public static boolean can_move_down(Entity entity){
        return idObjects[entity.getX()/32][entity.getY()/32 +1] == 0;
    }
    public static boolean can_bomb_down(Entity entity, int power){
        return idObjects[entity.getX()/32][entity.getY()/32+1+power] == 0||
                idObjects[entity.getX()/32][entity.getY()/32+1+power] == 3||
                idObjects[entity.getX()/32][entity.getY()/32+1+power] == 6||
                idObjects[entity.getX()/32][entity.getY()/32+1+power] == 7||
                idObjects[entity.getX()/32][entity.getY()/32+1+power] == 8;
    }
    public  static boolean can_bomb_up(Entity entity,int power){
        return idObjects[entity.getX()/32][entity.getY()/32-1-power] == 0||
                idObjects[entity.getX()/32][entity.getY()/32-1-power] == 3||
                idObjects[entity.getX()/32][entity.getY()/32-1-power] == 6||
                idObjects[entity.getX()/32][entity.getY()/32-1-power] == 7||
                idObjects[entity.getX()/32][entity.getY()/32-1-power] == 8;
    }
    public  static boolean can_bomb_left(Entity entity,int power){
        return idObjects[entity.getX()/32-1-power][entity.getY()/32] == 0||
                idObjects[entity.getX()/32-1-power][entity.getY()/32] == 3||
                idObjects[entity.getX()/32-1-power][entity.getY()/32] == 6||
                idObjects[entity.getX()/32-1-power][entity.getY()/32] == 7||
                idObjects[entity.getX()/32-1-power][entity.getY()/32] == 8;
    }
    public  static boolean can_bomb_right(Entity entity,int power){
        return idObjects[entity.getX()/32+1+power][entity.getY()/32] == 0||
                idObjects[entity.getX()/32+1+power][entity.getY()/32] == 3||
                idObjects[entity.getX()/32+1+power][entity.getY()/32] == 6||
                idObjects[entity.getX()/32+1+power][entity.getY()/32] == 7||
                idObjects[entity.getX()/32+1+power][entity.getY()/32] == 8;
    }

}
