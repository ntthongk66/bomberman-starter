package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Enviroment;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Items.speedBoost.speedBoost;

public abstract class AnimatedEntity extends Entity {
    protected  int swap;
    protected int deadFrame = 0;
    protected  int fps = 0;
    protected int numStep = 4;
    protected boolean life = true;
    public boolean getLife(){
        return this.life;
    }
    public void setLife(boolean life){
        this.life = life;
    }


    protected  String direction = "";
    public void setFps(int s){
        fps = s;
    }
    public int getFps(){
        return this.fps;
    }
    public static void controlFrame(AnimatedEntity entity){
        entity.setFps(entity.getFps()+1);
        if(entity instanceof Bomber){
            if(entity.getFps() == 4){
                running(entity);
                entity.setFps(0);
            }
        }else
        {
            if(entity.getFps() == 8){
                running(entity);
                entity.setFps(0);
            }
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
        switch (s){

            case "UP" :
               up_frame(entity);
               entity.setY(entity.getY()-speed);
                break;
            case "DOWN" :
                down_frame(entity);
                entity.setY(entity.getY()+speed);
                break;
            case "LEFT" :
                left_frame(entity);
                entity.setX(entity.getX()-speed);
                break;
            case "RIGHT" :
                right_frame(entity);
                entity.setX(entity.getX()+speed);
                break;
        }
    }
    public static void running(AnimatedEntity entity){
        if(entity.getNumStep() > 0){
            setDirectionMove(entity.getDirection(),Bomber.speedNormal *
                    speedBoost ,entity);
            entity.setNumStep(entity.getNumStep()-1);
        }
    };
    public static void move_down(AnimatedEntity entity){
        if(entity.getX()%32 == 0 && entity.getY() % 32 == 0 && Enviroment.can_move_down(entity)){
            if(entity instanceof Bomber){
                entity.setDirection("DOWN");
                entity.setNumStep(8/speedBoost);
                running(entity);
            }
            else
            {
                entity.setDirection("DOWN");
                entity.setNumStep(8);
                running(entity);
            }

        }

    }
    public static void move_up(AnimatedEntity entity){
        if(entity.getX()%32 == 0 && entity.getY() % 32 == 0 && Enviroment.can_move_up(entity)){
            if(entity instanceof Bomber){
                entity.setDirection("UP");
                entity.setNumStep(8/speedBoost);
                running(entity);
            }
            else
            {
                entity.setDirection("UP");
                entity.setNumStep(8);
                running(entity);
            }
        }
    }
    public static void move_left(AnimatedEntity entity){
        if(entity.getX()%32 == 0 && entity.getY() % 32 == 0 && Enviroment.can_move_left(entity)){
            if(entity instanceof Bomber){
                entity.setDirection("LEFT");
                entity.setNumStep(8/speedBoost);
                running(entity);
            }
            else
            {
                entity.setDirection("LEFT");
                entity.setNumStep(8);
                running(entity);
            }
        }
    }
    public static void move_right(AnimatedEntity entity){
        if(entity.getX()%32 == 0 && entity.getY() % 32 == 0 && Enviroment.can_move_right(entity)){
            if(entity instanceof Bomber){
                entity.setDirection("RIGHT");
                entity.setNumStep(8/speedBoost);
                running(entity);
            }
            else
            {
                entity.setDirection("RIGHT");
                entity.setNumStep(8);
                running(entity);
            }
        }
    }


    public static void down_frame(AnimatedEntity entity){
        if(entity instanceof Bomber){
            if(entity.getY() % (4*speedBoost) == 0){
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.player_down.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.player_down_1.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.player_down.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.player_down_2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
        if(entity instanceof Balloon){
            if(entity.getY() % 8 == 0){
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.balloom_right1.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.balloom_right2.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.balloom_right3.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.balloom_right2.getFxImage());
                entity.setSwap(1);
            }
        }
        }
    };
    public static void up_frame(AnimatedEntity entity){
        if(entity instanceof Bomber){
            if (entity.getY() % (4*speedBoost) == 0) {
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.player_up.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.player_up_1.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.player_up.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.player_up_2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
        if(entity instanceof  Balloon){
            if(entity.getY() % 8 == 0){
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.balloom_left1.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.balloom_left2.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.balloom_left3.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.balloom_left2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }

    };
    public static void right_frame(AnimatedEntity entity){

        if(entity instanceof Bomber){
            if (entity.getY() % (4*speedBoost) == 0) {
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.player_right.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.player_right_1.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.player_right.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.player_right_2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
        if(entity instanceof  Balloon){
            if(entity.getY() % 8 == 0){
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.balloom_left1.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.balloom_left2.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.balloom_left3.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.balloom_left2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
    };
    public static void left_frame(AnimatedEntity entity){
        if(entity instanceof Bomber){
            if (entity.getY() % (4*speedBoost) == 0) {
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.player_left.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.player_left_1.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.player_left.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.player_left_2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
        if(entity instanceof  Balloon){
            if(entity.getY() % 8 == 0){
                if (entity.getSwap() == 1) {
                    entity.setImg(Sprite.balloom_right1.getFxImage());
                    entity.setSwap(2);
                } else if (entity.getSwap() == 2) {
                    entity.setImg(Sprite.balloom_right2.getFxImage());
                    entity.setSwap(3);
                } else if (entity.getSwap() == 3) {
                    entity.setImg(Sprite.balloom_right3.getFxImage());
                    entity.setSwap(4);
                } else {
                    entity.setImg(Sprite.balloom_right2.getFxImage());
                    entity.setSwap(1);
                }
            }
        }
    };

    







    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



    @Override
    public void update() {

    }
}
