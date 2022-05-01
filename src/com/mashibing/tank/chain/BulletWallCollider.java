package com.mashibing.tank.chain;

import java.awt.Rectangle;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Wall;

public class BulletWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Wall) {
			Bullet b = (Bullet) o1;
			Wall t = (Wall) o2;
			collideWith(b, t);
		} else if (o1 instanceof Wall && o2 instanceof Bullet) {
			Bullet b = (Bullet) o2;
			Wall t = (Wall) o1;
			collideWith(b, t);
		}
		return false;
	}

	// 子弹的碰撞方法 true
	public boolean collideWith(Bullet b, Wall w) {

		Rectangle r1 = b.rect;
		Rectangle r2 = w.rect;
		if (r1.intersects(r2)) {
			b.die();
		}
		return false;
	}
}
