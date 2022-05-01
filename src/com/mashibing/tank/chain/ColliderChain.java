package com.mashibing.tank.chain;

import java.util.LinkedList;

import com.mashibing.tank.GameObject;

public class ColliderChain implements Collider {

	public LinkedList<Collider> clls = new LinkedList<>();

	public void ADDClls(Collider clls) {
		this.clls.add(clls);
	}

	public ColliderChain() {
		this.clls.add(new BulletTankCollider());
		this.clls.add(new TankTankCollider());
		this.clls.add(new BulletWallCollider());
		this.clls.add(new TankWallCollider());
	}

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		for (int i = 0; i < clls.size(); i++) {
			Boolean flag = clls.get(i).collide(o1, o2);
			if (flag) {
				return true;
				// 已经碰撞不再继续
			}
		}
		return false;
	}

}
