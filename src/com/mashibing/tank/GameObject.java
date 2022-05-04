package com.mashibing.tank;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class GameObject  implements Serializable {
	public int x, y;

	public abstract void paint(Graphics g);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
