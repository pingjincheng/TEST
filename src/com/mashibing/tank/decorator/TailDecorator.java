package com.mashibing.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.GameModel;
import com.mashibing.tank.GameObject;

public class TailDecorator extends GoDecorator {

	public TailDecorator(GameObject go) {
		super(go);
		GameModel.getInstance().add(this);
	}

	@Override
	public void paint(Graphics g) {
		this.x = go.getX();
		this.y = go.getY();

		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawLine(go.getX(), go.getY(), go.getX() + Bullet.width, go.getY() + Bullet.height);
		g.setColor(c);
	}

}
