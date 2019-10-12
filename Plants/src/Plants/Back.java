package Plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Back extends MovingObject {

    public Back() {
        super(World.WIDTH, World.HEIGHT, 0, 0);

    }

    @Override
    public void step() {

    }

    @Override
    public BufferedImage getImage() {
        return Images.background;
    }

    public void paintObject(Graphics g) {
        g.drawImage(this.getImage(), this.x, this.y, null);
    }


}
