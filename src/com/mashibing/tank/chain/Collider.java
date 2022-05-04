package com.mashibing.tank.chain;

import com.mashibing.tank.GameObject;
@FunctionalInterface
public interface Collider {
	public boolean collide(GameObject o1, GameObject o2);
}
