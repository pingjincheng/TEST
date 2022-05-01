package com.mashibing.tank;

import java.awt.Graphics;

public abstract class GameObject {
	public int x, y;

	public abstract void paint(Graphics g);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
