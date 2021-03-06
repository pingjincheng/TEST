package com.mashibing.tank.chain;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class TankTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Tank) {
			Tank t1 = (Tank) o1;
			Tank t2 = (Tank) o2;
			if (t1.rect.intersects(t2.rect)) {
				t1.back();
				t2.back();
			}
		}
		return false;
	}

}
