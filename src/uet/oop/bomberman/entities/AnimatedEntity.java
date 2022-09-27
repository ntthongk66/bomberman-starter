package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimatedEntity extends Entity {
    protected  int swap;
    protected int deadFrame = 0;
    protected  int fps = 0;
    protected int Delaytime = 0;
    protected int MAXDELAYTIME;
    protected int numStep ;
    protected int MAXNUMSTEP;
    protected int speed;
    protected boolean life = true;
    public boolean getLife(){
        return this.life;
    }
    public void setLife(boolean life){
        this.life = life;
    }
    public  Image chooseImg(String direction){
        if(!isRunning)
            return imgRight.get(0);
        int chooseFrame = count  ;
        switch (direction){
            case "UP":
                return imgUp.get(chooseFrame);
            case "DOWN":
                return imgDown.get(chooseFrame);
            case "LEFT":
                return imgLeft.get(chooseFrame);
            case "RIGHT":
                return imgRight.get(chooseFrame);
        }
        return  null;
    }
    protected List<Image> imgLeft = new ArrayList<>();
    protected List<Image> imgRight = new ArrayList<>();
    protected List<Image> imgDown = new ArrayList<>();
    protected List<Image> imgUp = new ArrayList<>();
    protected int count = 1;
    protected boolean isRunning = false;

    protected  String direction = "";
    public void setFps(int s){
        fps = s;
    }
    public int getFps(){
        return this.fps;
    }
    public static void controlFrame(AnimatedEntity entity){
        entity.setFps(entity.getFps()+1);
        if(entity.getFps() == entity.MAXDELAYTIME){
            running(entity);
            entity.setFps(0);
        }

    }
    public void setSwap(int s){
        this.swap = s;
    }
    public int getSwap(){
        return this.swap;
    }
    public int getNumStep(){
        return this.numStep;
    }
    public void setNumStep(int s){
        this.numStep = s;
    }

    public String getDirection(){
        return this.direction;
    }
    public void setDirection(String s) {
        this.direction = s;
    }
    public static void setDirectionMove(String s, int speed,AnimatedEntity entity){
        entity.setImg(entity.chooseImg(s));
        switch (s){
            case "UP" :
                entity.setY(entity.getY()-speed);
                break;
            case "DOWN" :
                entity.setY(entity.getY()+speed);
                break;
            case "LEFT" :
                entity.setX(entity.getX()-speed);
                break;
            case "RIGHT" :
                entity.setX(entity.getX()+speed);
                break;
        }
        entity.count++;
        if(entity.count > 3) entity.count = 0;
    }
    public static void running(AnimatedEntity entity){

        if(entity.getNumStep() > 0){

            setDirectionMove(entity.getDirection(), entity.speed,entity);
            entity.setNumStep(entity.getNumStep()-1);
        }
    };
    public static void move(AnimatedEntity entity, String direction){
        entity.isRunning = true;
        boolean can_move = false;
        if(entity.getX() % 32 == 0 && entity.getY() % 32 == 0){
            switch (direction){
                case "UP":
                    if(Enviroment.can_move_up(entity)){
                        entity.setDirection("UP");
                        can_move = true;
                    }

                    break;
                case "DOWN":
                    if(Enviroment.can_move_down(entity)){
                        entity.setDirection("DOWN");
                        can_move = true;
                    }

                    break;
                case "LEFT":
                    if(Enviroment.can_move_left(entity)){
                        entity.setDirection("LEFT");
                        can_move = true;
                    }
                    break;
                case "RIGHT":
                    if(Enviroment.can_move_right(entity)){
                        entity.setDirection("RIGHT");
                        can_move = true;
                    }
                    break;
            }
            if(can_move){
                entity.setNumStep(entity.MAXNUMSTEP);
                running(entity);
            }

        }
    }


    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



    @Override
    public void update() {

    }
}
