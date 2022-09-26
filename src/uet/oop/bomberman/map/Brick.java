package uet.oop.bomberman.map;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.listKill;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Brick extends Entity {
    private static int swap = 1;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private void checkHidden() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick)
                if (listKill[entity.getX() / 32][entity.getY() / 32] == 4) {
                    explosion(entity);
                }
        }
    }

    private void explosion(Entity entity) {
        if (swap == 1) {
            entity.setImg(Sprite.brick_exploded.getFxImage());
            swap = 2;
        }
        if (swap == 2) {
            entity.setImg(Sprite.brick_exploded1.getFxImage());
            swap = 3;
        }
        if (swap == 3) {
            entity.setImg(Sprite.brick_exploded2.getFxImage());
            swap = 4;
        } else {
            entity.setImg(Sprite.grass.getFxImage());
        }
    }

    @Override
    public void update() {
        checkHidden();
    }
}
