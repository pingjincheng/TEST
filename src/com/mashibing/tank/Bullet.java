package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	private Dir dir = Dir.DOWN;
	private static final int SPEED = 10;

	// 长宽
	public static final int width = ResourceMgr.bulletD.getWidth();
	public static final int height = ResourceMgr.bulletD.getHeight();
	// 子弹是否存活
	private boolean living = true;
	// 子弹区分好坏
	public Group group;

	public Rectangle rect = new Rectangle();

	public Bullet(int x, int y, Dir dir, Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = width;
		rect.height = height;
		// 将自己加入子弹队列
		GameModel.getInstance().add(this);
	}

	public void paint(Graphics g) {
		if (!living) {
			GameModel.getInstance().remove(this);
		}
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;
		}
		move();
	}

	private void move() {
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		// 当移时超出边界
		if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT)
			this.living = false;
		// 修改碰撞块的位置
		rect.x = this.x;
		rect.y = this.y;
	}

	public void die() {
		this.living = false;

	}

}
