package Plants;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Images {

    public static BufferedImage background; //背景图
    public static BufferedImage bullets; //子弹图
    public static BufferedImage[] sun; //太阳图
    public static BufferedImage[] plants; //植物切换
    public static BufferedImage[] zombies; //僵尸
    public static BufferedImage select0; //背景图
    public static BufferedImage select1;
    public static BufferedImage[] eat;
    public static BufferedImage[] die;
    public static BufferedImage start;
    public static BufferedImage gameover;
    public static BufferedImage sunback;

    static{
        background = readImage("background.png");
        bullets = readImage("bullet.png");
        sun = new BufferedImage[10];
        for(int i = 0; i< 10;i++){
            sun[i] = readImage("Sun"+i+".png");
        }

        select0 = readImage("select0.png");
        select1 = readImage("select1.png");

        sunback = readImage("SunBack.png");

        eat = new BufferedImage[4];
        for(int i = 0;i<4;i++){
            eat[i] = readImage("eat"+i+".png");
        }

        start = readImage("start.png");

        gameover = readImage("gameover.png");


        die = new BufferedImage[3];
        for (int i = 0; i<3;i++){
            die[i] = readImage("die"+i+".png");
        }


        plants = new BufferedImage[13];
        for(int i = 0;i<13;i++){
            plants[i] = readImage("Peashooter["+(i+1)+"].png");
        }


        zombies = new BufferedImage[18];
        for(int i = 0 ;i<18;i++){
            zombies[i] = readImage("Zombie["+(i+1)+"].png");
        }

    }

    public static BufferedImage readImage(String fileName){
        //同包下读取图片
        try {
            BufferedImage img = ImageIO.read(MovingObject.class.getResource(fileName));
            return img;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

