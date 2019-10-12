package Plants;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Zombies extends MovingObject {

    private int speed;
    private int timer = 0;
    private int life = 3;

    public Zombies(int getx,int gety) {

        super(100, 120,getx,gety);
        speed = 2;
    }

    @Override
    public void step() {
        if(!isEat()&&timer<50)
            x-=speed;
        if (timer>50)
            x-=speed;
    }

//    public void hited(){
//        if(isHit()) {
//            life--;
//        }
//        if(life<0){
//            state = DEAD;
//        }
//    }
//
//    public void Die(){
//        if(life<0){
//            state = DEAD;
//        }
//    }

    int index = 0;
    @Override
    public BufferedImage getImage() {
        if(isLife()) {
            return Images.zombies[index++%18];
        }
        if(isEat()&&timer++<50){
            return Images.eat[index++%4];
        }
        if(timer>=50){
            state = LIFE;
        }
        if(isLife()) {
            return Images.zombies[index++%4];
        }
        if(isDead()){
            int i = 0;
            state = REMOVE;
            return Images.die[i++];


            //    state = REMOVE;
            // return img;

        }
        return null; //删除状态，直接返回NULL；
    }


}