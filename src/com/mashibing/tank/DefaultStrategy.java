package com.mashibing.tank;

public class DefaultStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.width / 2 - Bullet.width / 2;
		int bY = t.y + Tank.height / 2 - Bullet.height / 2;

		// new RectDecorator(new TailDecorator(new Bullet(bX, bY, t.dir, t.group)));
		new Bullet(bX, bY, t.dir, t.group);
		// new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

}
