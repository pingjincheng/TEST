package com.mashibing.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mashibing.tank.chain.ColliderChain;

public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    public static Tank myTank;

    static {
        myTank = new Tank(200, 400, Dir.UP, Group.GOOD, 8);
        myTank.setMoving(false);
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, 4);
        }
        new Wall(150, 150, 200, 50);
        new Wall(550, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(550, 300, 50, 200);
    }

    // 封装所有物体
    private List<GameObject> gameObjects = new ArrayList<>();
    // 碰撞的责任链
    private ColliderChain collChain = new ColliderChain();

//后续添加物体，和它碰撞类就可扩展 当前类无关了，可以在 配置文件中完成

//	BulletTankCollider c1 = new BulletTankCollider();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
        super();
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
//		g.drawString("存活的子弹数量" + bullets.size(), 10, 50);
//		g.drawString("存活的坦克数量" + tanks.size(), 10, 70);
//		g.drawString("存活爆炸的数量" + e.size(), 10, 90);
        g.setColor(c);

        // 坦克自己画自己
        myTank.paint(g);
        // 子弹自己画自己
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject b = gameObjects.get(i);
            b.paint(g);
        }

        // 子弹的碰撞测试
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                Boolean flag = collChain.collide(gameObjects.get(i), gameObjects.get(j));
                if (flag) {
                    break; // 发生碰撞跳出本次循环
                }
            }

        }
    }

    public void add(GameObject a) {
        this.gameObjects.add(a);

    }

    public void remove(GameObject a) {
        this.gameObjects.remove(a);

    }

    //保存记录
    public void save() {
        File f = new File("c:/pingjincheng/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(gameObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /*回到保存点*/
    public void load() {
        File f = new File("c:/pingjincheng/tank.data");
        ObjectInputStream ois = null;
        try {
            ois =new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank)ois.readObject();
            gameObjects=(List<GameObject>)ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
