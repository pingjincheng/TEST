package com.mashibing.tank.chain;

import java.awt.Rectangle;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Explode;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class BulletTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet) o1;
			Tank t = (Tank) o2;
			return collideWith(b, t);
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			Bullet b = (Bullet) o2;
			Tank t = (Tank) o1;
			return collideWith(b, t);
		}
		return false;
	}

	// 子弹的碰撞方法 true
	public boolean collideWith(Bullet b, Tank tank) {
		if (b.group == tank.getGroup())
			return false;
		Rectangle r1 = b.rect;
		Rectangle r2 = tank.rect;
		if (r1.intersects(r2)) {
			b.die();
			tank.die();
			int eX = tank.getX() + Tank.width / 2 - Explode.width / 2;
			int eY = tank.getY() + Tank.height / 2 - Explode.height / 2;
			new Explode(eX, eY);
			return true;
		}
		return false;
	}
}
