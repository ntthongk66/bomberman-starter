package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedEntity{
    private int swap;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
    public  void setSwap(int s){
        this.swap = s;
    }
    public int getSwap(){
        return this.swap;
    }

    @Override
    public void update() {

    }
}
