package com.mashibing.tank;

import java.awt.Graphics;

public class Explode extends GameObject {
    public static final int width  = ResourceMgr.explodes[0].getWidth();
    public static final int height = ResourceMgr.explodes[0].getHeight();

    private int             step   = 0;

    public Explode(int x, int y) {
        super();
        this.x = x;
        this.y = y;


        GameModel.getInstance().add(this);
        // 加入声音
        // new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            GameModel.getInstance().remove(this);
        }

    }

}
