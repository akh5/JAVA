package Plants;

import java.awt.image.BufferedImage;

public class Plants extends MovingObject {

    private int life;
    private int getx;
    private int gety;
    public int moveable = 1;
    private int timer = 0;

    public Plants(int x,int y){
        super(90,90,x,y);
        life = 3;

    }

//    public void Plantarea(int x,int y) {
//        if (x > 331) {
//            if (y > 100 && y < 230) {
//                getx = 340;
//                gety = 110;
//            }
//            if (y > 230 && y < 350) {
//                getx = 340;
//                gety = 340;
//            }
//            if (y > 350 && y < 480) {
//                getx = 340;
//                gety = 360;
//            }
//            if (y > 480 && y < 600) {
//                getx = 340;
//                gety = 490;
//            }
//            if (y > 600 && y < 730) {
//                getx = 340;
//                gety = 610;
//            }
//        }
//    }

    //    public void moveto(int x ,int y) {
//        if(moveable == 1) {
//            this.x = x - this.width / 2;   //英雄机的x = 鼠标的x-1/2英雄机的宽
//            this.y = y - this.height / 2; //英雄机的y = 鼠标的y-1/2英雄机的高
//        }
//        }
    @Override
    public void step() {

    }

    int index = 5;
    @Override
    public BufferedImage getImage() {// 每十毫秒走一次
        if(isLife()) {
            BufferedImage img = Images.plants[index++%13];
            //System.out.println(index);
            return img;
        }
        if(isDead()) {
            return null;
        }
        return null;
		/*                     index = 0;
		 * 10M  返回Images.heros[0] index = 1
		 * 20M  返回Images.heros[1] index = 2
		 * 30M  返回Images.heros[0] index = 3
		 * 40M  返回Images.heros[1] index = 4
		 * */
    }




//    public Bullets[] shoot() {
//        int xStep = this.width/4;
//        int yStep = 20;
//        Bullets[] bs = new Bullets[1];
//        bs[0] = new Bullets(this.x+2*xStep,this.y-yStep);
//        return bs;
//    }

    public void subtaractLife() {
        if(timer++%2 == 0)
            life--;
        if(life<0){
            goDead();
            state =REMOVE;
        }
    }

}