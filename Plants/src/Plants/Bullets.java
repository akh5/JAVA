package Plants;

import java.awt.image.BufferedImage;

public class Bullets extends MovingObject {
    private int speed; //移动速度


    public Bullets(int x ,int y){
        super(40,40,x,y);
        speed = 4;

    }

    public void step() {
        x+=speed;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()) {
            return Images.bullets;
        }else if(isDead()) {
            state = REMOVE;
        }
        return null;
    }
    public boolean outofBounds() {
        return this.y<=-this.height; //子弹的Y<= -子弹的高，即为越界
    }



}
