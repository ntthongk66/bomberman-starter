package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;


public class speedBoost extends Item{
    public static int speedBoost = 1;
    public speedBoost(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public speedBoost() {
    }

    public speedBoost(boolean status) {
        super(status);
    }
    @Override
    public void update(){
        for(Entity entity: stillObjects){
            if(entity instanceof speedBoost && !this.statusItem){
                if(listKill[entity.getX()/32][entity.getY()/32] == 4){
                    entity.setImg(Sprite.powerup_speed.getFxImage());
                }
            }
        }
        if(!this.statusItem){
            if(bomberman.getX() == this.x && bomberman.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.statusItem = true;
                speedBoost=2;
            }
        }
    }
}
