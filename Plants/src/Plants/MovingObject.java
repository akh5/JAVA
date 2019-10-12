package Plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


public abstract class MovingObject {
    public static final int LIFE = 0;
    public static final int DEAD = 1;
    public static final int REMOVE = 2;
    public static final int EAT = 3 ;
    public static final int HIT = 4;
    protected int state = LIFE ;//当前状态（默认为活着）



    protected int width; //宽
    protected int height; //高
    protected int x;  //横坐标
    protected int y; //纵坐标

    /*僵尸，植物的构造方法*/
    public MovingObject(int width,int height) {
        this.width = width;
        this.height = height;
//        Random rand = new Random();
//        x = rand.nextInt(World.WIDTH-this.width); //X坐标0到窗口-敌机 之内的随机数
//        y = -this.height;  //敌机的高
    }

    /*专门给英雄机，子弹，天空提供的构造方法*/
    public MovingObject(int width , int height , int x , int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public abstract void step();

    //获取对象的图片
    public abstract BufferedImage getImage();

    //判断对象是否是活着
    public boolean isLife() {
        return state == LIFE ; //当前状态为LIFE 表示对象活着
    }
    //判断对象是否是死着
    public boolean isDead() {
        return state == DEAD ; //当前状态为LIFE 表示对象活着
    }
    //判断对象是否删除
    public boolean isRemove() {
        return state == REMOVE ; //当前状态为LIFE 表示对象活着
    }

    public boolean isEat(){
        return state == EAT;
    }

    public boolean isHit(){
        return state == HIT ;
    }

    public boolean outofBounds() {
        return this.y>=World.HEIGHT;
    }

    public void paintObject(Graphics g) {
        g.drawImage(this.getImage(), this.x,this.y,null);
    }
//    /*判断敌人是否越界*/
//    public boolean outofBounds() {
//        //return this.y>=World.HEIGHT;
//    }

    /*碰撞检测   this是敌人   other是子弹或英雄机*/
    public boolean  hit(MovingObject other) {
        int x1 = this.x-other.width; //X1敌人的x-子弹/英雄机的宽
        int x2 = this.x+this.width; //
        int y1 = this.y-other.height;
        int y2 = this.y+this.height;
        int x = other.x;
        int y = other.y;
        //x在x1与x2之间，且y在y1与y2之间，即为撞上了
        return x>=x1&&x<=x2&&y>=y1&&y<=y2;
    }
    public void goDead() {
        state = DEAD; //将当前状态修改为DEAD
    }

    public void goEat(){
        state = EAT;
    }
    public void goHit(){
        state = HIT;
    }


}
