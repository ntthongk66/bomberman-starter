package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


public class FlameBoost extends Item{
    public static int speed = 1;
    public FlameBoost(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public FlameBoost(boolean status) {
        super(status);
    }
    public FlameBoost(){}
    @Override
    public void update(){
        for(Entity entity: stillObjects){
            if(entity instanceof FlameBoost && !this.statusItem){
                if(listKill[entity.getX()/32][entity.getY()/32] == 4){
                    entity.setImg(Sprite.powerup_flames.getFxImage());
                }
            }
        }
        if(!this.statusItem){
            if(bomberman.getX() == this.x && bomberman.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.statusItem = true;
                Bomb.powerBomb++;
//            speed++;
            }
        }
    }
}
