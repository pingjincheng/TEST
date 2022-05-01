package com.mashibing.tank.decorator;

import java.awt.Graphics;

import com.mashibing.tank.GameObject;

public abstract class GoDecorator extends GameObject {
	GameObject go;

	public GoDecorator(GameObject go) {
		super();
		this.go = go;
	}

	@Override
	public abstract void paint(Graphics g);

}
