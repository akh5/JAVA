package Plants;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame; //相框
import javax.swing.JPanel; //相坂 ，面板

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.event.MouseAdapter; // 鼠标监听器
import java.awt.event.MouseEvent; //监听事件，鼠标的坐标封装在内

public class World extends JPanel{
    public static final int WIDTH = 1434;
    public static final int HEIGHT = 780;

    private Back back = new Back();
    private Plants[] plants = {};
    private Bullets[] bullets = {};
    private MovingObject[] Zombiesun = {};

    private int Sun = 50;

    private int score = 0;


    public static final int START = 0;
    public static final int RUNNING = 1;
    //    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    private int state = START; //当前状态默认为启动状态


    public MovingObject nextOne() {
        Random rand = new Random();
        int type = rand.nextInt(33);
        if(type <4) {
            return new Zombies(1434,70);
        }
        else if(type <8 ) {
            return new Zombies(1434,220);
        }
        else if(type < 24) {
            Sun+=50;
            Random sun = new Random();
            int locatex = rand.nextInt(400)+1000;
            return new Sun(locatex,0);
        }
        else if(type < 28) {
            return new Zombies(1434,470);
        }
        else if(type < 32)  {
            return new Zombies(1434,590);
        }else {
            return new Zombies(1434,340);

        }
    }

    public Plants nextPlant(int x,int y) {
        return new Plants(x,y);
    }

    int enterIndex = 0; //敌人入场计数器
    public void enterAction() {
        enterIndex++;
        if(enterIndex%40 == 0) {  //每400（10M*40）毫秒走一次
            MovingObject obj = nextOne(); //获取敌人对象
            Zombiesun = Arrays.copyOf(Zombiesun, Zombiesun.length+1);//扩容长度
            Zombiesun[Zombiesun.length-1] = obj;
        }
    }

    int plantIndex = 0;
    public void plantAction(int x,int y){

        Plants obj = nextPlant(x,y); //获取敌人对象
        plants = Arrays.copyOf(plants, plants.length+1);//扩容长度
        plants[plants.length-1] = obj;
        //  }

    }


    public void stepAction() {
        for(int i = 0; i < Zombiesun.length;i++) {
            Zombiesun[i].step();
        }
        for(int i = 0;i < bullets.length;i++) {
            bullets[i].step();
        }

    }

//    int shootIndex = 0;  //子弹入场计数器
//    /*子弹入场（英雄机发射子弹对象）*/
//    public void shootAction() {
//        shootIndex++;
//        if(shootIndex%30==0) {
//            Bullets[] bs = plants[plantIndex].shoot();
//            System.out.println(bs.length);
//            //扩容（bs有几个元素，就扩大多少容量）
//            bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
//			/*
//			 * bs是原数组
//			 * 0是从原数组bs第一个元素开始
//			 * bullets是目标数组
//			 * bullets.length-bs.length从下标几开始复制
//			 * bs.length代表是复制几个元素
//			 * */
//
//            System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
//        }
//    }

    public void bulletHitAction() {
        for(int i = 0 ; i<bullets.length;i++) {
            Bullets b = bullets[i];
            for(int j = 0; j < Zombiesun.length;j++) {
                MovingObject f = Zombiesun[j];
                if(b.isLife()&&f.isLife()&&f.hit(b)) {
                    b.goDead();
                    f.goDead();
                    score+=10;
                }
            }
        }
    }

    public void PlantsMeetAction() {
        for(int i = 0;i<Zombiesun.length;i++) { // 遍历所有敌人
            MovingObject f = Zombiesun[i]; //获取每一个敌人
            for(int j = 0;j<plantIndex;j++){
                if(plants[j].isLife()&&f.isLife()&&f.hit(plants[j])) {//撞上了
                    f.goEat();
                    plants[j].subtaractLife();

                }
            }

        }
    }

    public void outOfBoundsAction() {
        int index = 0;
        MovingObject[] zombieLives = new MovingObject[Zombiesun.length];

        for(int i = 0 ;i<Zombiesun.length;i++) {
            MovingObject f = Zombiesun[i];
            //实际向保留的是，不越界的，并且状态是活着的
            //死了的是爆破的效果，不要的是REMOVE状态
            if(!f.outofBounds()&&!f.isRemove()) { //不越界，判断f.outofbounds（）取反的值
                zombieLives[index] = f;
                index++;
            }
        }
        //把不越界的敌人装到enemi中，此时的长度是index
        Zombiesun = Arrays.copyOf(zombieLives, index);

        index = 0; //下标不越界的敌人的计数器
        //统计不越界的敌人,
        Bullets[] bulletLives = new Bullets[bullets.length];

        for(int i = 0 ;i<bullets.length;i++) {
            Bullets b = bullets[i];
            if(!b.outofBounds()&&!b.isRemove()) { //不越界，判断f.outofbounds（）取反的值
                bulletLives[index] = b;
                index++;
            }
        }
        //把不越界的敌人装到enemi中，此时的长度是index
        bullets = Arrays.copyOf(bulletLives, index);

//        index = 0; //下标不越界的敌人的计数器
//        //统计不越界的敌人,
//        Plants[] plantLives = new Plants[plants.length];
//
//        for(int i = 0 ;i<plants.length;i++) {
//            Plants p = plantLives[i];
//            if(!p.outofBounds()&&!p.isRemove()) { //不越界，判断f.outofbounds（）取反的值
//                plantLives[index] = p;
//                index++;
//            }
//        }
//        //把不越界的敌人装到enemi中，此时的长度是index
//        plants = Arrays.copyOf(plantLives, index);
//


    }

    //    public Bullets nextBullet(int x,int y) {
//        return new Bullets(x,y);
//    }
//
//    int bulletIndex = 0;
//    public void bulletAction(int x,int y){
//        bulletIndex++;
//        Bullets obj = nextBullet(x,y); //获取敌人对象
//        bullets = Arrays.copyOf(bullets, bullets.length+1);//扩容长度
//        bullets[bullets.length-1] = obj;
//
//
//    }
    int SX =-50;
    int SY = -50;
    public Bullets[] shoot(){

        Bullets[] bs = new Bullets[1];
        bs[0] = new Bullets(SX,SY);
        return bs;
    }

    int shootIndex = 0;  //子弹入场计数器
    /*子弹入场（英雄机发射子弹对象）*/
    public void shootAction() {
        shootIndex++;
        if(shootIndex%40==0) {
            Bullets[] bs = shoot();
            System.out.println(bs.length);
            //扩容（bs有几个元素，就扩大多少容量）
            bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			/*
			 * bs是原数组
			 * 0是从原数组bs第一个元素开始
			 * bullets是目标数组
			 * bullets.length-bs.length从下标几开始复制
			 * bs.length代表是复制几个元素
			 * */

            System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
        }
    }
//    int bulletIndex = 0;
//    public void shoot(int bx,int by){
//       // if(!plants[plantIndex].isRemove()&&bulletIndex<300){
//            bullets[bulletIndex] = new Bullets(bx,by);
//            bulletIndex++;
//        //}
//    }


    public void checkGameOverAction() { //每10毫秒走一次
        for (int i = 0; i<Zombiesun.length;i++) {
            if (Zombiesun[i].x < 200) {
                try {
                    UserDao.save("李四", score);
                } catch (Exception e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }


                state = GAME_OVER;
            }
        }

    }



    public void action() {

        MouseAdapter l = new MouseAdapter() {

//            public void mouseMoved(MouseEvent e) {
//                int x = e.getX();
//                int y = e.getY();
//                plants[p].moveto(x, y);
//            } //英雄机随着鼠标移动了；

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (state == START)
                    state = RUNNING;

                if (state == RUNNING) {

                    int sx;
                    int sy;
                    if (Sun >= 100) {
                        sx = e.getX() - 45;
                        sy = e.getY() - 45;
                        plantAction(sx, sy);
                        SX =sx+65;
                        SY = sy+6;
                        plantIndex++;
                        if(Sun>10) {
                            shoot();
                        }
                        Sun -= 100;
                    }
//                    Sun+=50;
                }
            }
        };


        this.addMouseListener(l); //处理鼠标的操作事件
        this.addMouseMotionListener(l); //处理鼠标滑动事件

        Timer timer = new Timer();
        int intervel = 50;
        timer.schedule(new TimerTask() {

            public void run() {
                if(state == RUNNING) {

                    enterAction();


                    shootAction();

                    PlantsMeetAction();

                    stepAction();
                    bulletHitAction();

                    outOfBoundsAction();
                    checkGameOverAction();

                    repaint(); //所有事件都在repain之前完成
                    //System.out.println(bullets.length);
                }
            }
        }, intervel, intervel);

    }

    /** 重写paint()画  g:画笔  在里面调用刚刚写的 paintObject()*/
    public void paint(Graphics g){
        back.paintObject(g);  //画天空
        for(int i = 0;i<plants.length;i++){
            plants[i].paintObject(g); //画敌人
        }

        for(int i=0;i<Zombiesun.length;i++){ //遍历所有敌人
            Zombiesun[i].paintObject(g); //画敌人
        }
        for(int i=0;i<bullets.length;i++){ //遍历所有子弹
            bullets[i].paintObject(g); //画子弹
        }



        if(Sun<100){
            g.drawImage(Images.select0,10,600,null);
        }else{
            g.drawImage(Images.select1,10,600,null);
        }
        if(state == START)
            g.drawImage(Images.start,0,0,null);
        if(state == GAME_OVER) {
            g.drawImage(Images.gameover, 500, 200, null);
            g.setFont(new Font("黑体",Font.BOLD,35));
            g.drawString("排行榜", 350, 350);
            String[] result;
            try {
                result = UserDao.getScoreAndUsername();
                int y = 360;
                for(int i = 0;i<10;i++) {//只显示十个数据在窗口上
                    if(result[i]!=null) {
                        g.setFont(new Font("",Font.PLAIN,20));
                        g.drawString(result[i], 500, y);
                        y+=30;//依次往下挪30给像素，排开
                    }
                }
            } catch (Exception e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        g.drawString("SCORE:"+score,10,25);



    }


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Music m = new Music();
        m.start();

        world.action();



    }

}
