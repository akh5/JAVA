package Plants;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Sun extends MovingObject {
    public Sun(int x,int y) {
        super(100,100,x,y);
        Random rand = new Random();
        x = rand.nextInt(World.WIDTH-this.width);
    }

    @Override
    public void step() {
        y+=3;
    }

    int index = 0;
    @Override
    public BufferedImage getImage() {
        return Images.sun[index++%9];
    }



}