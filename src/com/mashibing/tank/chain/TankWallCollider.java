package com.mashibing.tank.chain;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;
import com.mashibing.tank.Wall;

public class TankWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Wall && o2 instanceof Tank) {
			Wall w = (Wall) o1;
			Tank t = (Tank) o2;
			if (w.rect.intersects(t.rect)) {
				t.back();
			}
		} else if (o1 instanceof Tank && o2 instanceof Wall) {
			Wall w = (Wall) o2;
			Tank t = (Tank) o1;
			if (w.rect.intersects(t.rect)) {
				t.back();
			}
		}
		return false;
	}
}
