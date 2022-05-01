package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank extends GameObject {
	public int prex, prey;
	Dir dir = Dir.DOWN;
	private int SPEED = 1;
	private Boolean moving = true;
	private boolean living = true;
	public static final int width = ResourceMgr.goodTankU.getWidth();
	public static final int height = ResourceMgr.goodTankU.getHeight();
	Group group = Group.BAD;
	private Random random = new Random();
	public Rectangle rect = new Rectangle();
	FireStrategy fs;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Tank(int x, int y, Dir dir, Group group, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.SPEED = speed;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = width;
		rect.height = height;

		if (this.group == Group.GOOD) {
			String goodfs = (String) PropertyMgr.get("goodFs");
			try {
				fs = (FireStrategy) Class.forName(goodfs).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			fs = new DefaultStrategy();

			GameModel.getInstance().add(this);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (!this.living) {
			GameModel.getInstance().remove(this);
		}
		switch (dir) {
		case LEFT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		default:
			break;
		}
		move();
	}

	private void move() {
		prex = this.x;
		prey = this.y;
		// 如果不移动 不改变坐标
		if (!moving)
			return;

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

		// 随机发射子弹
		if (this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();
		// 随机改变方向
		if (this.group == Group.BAD && random.nextInt(100) > 95) {
			this.dir = Dir.values()[random.nextInt(4)];
		}
		// 自己坦克的声音
		// if (this.group == Group.GOOD)
		// new Thread(() -> new Audio("audio/tank_move.wav").play()).start();

		// 边界检测
		boundsCheck();

		// 修改碰撞块的位置
		rect.x = this.x;
		rect.y = this.y;
	}

	private void boundsCheck() {
		if (this.x < 2)
			this.x = 2;
		if (this.y < 30)
			this.y = 30;
		if (this.x > TankFrame.GAME_WIDTH - Tank.width)
			this.x = TankFrame.GAME_WIDTH - Tank.width;
		if (this.y > TankFrame.GAME_HEIGHT - Tank.height)
			this.y = TankFrame.GAME_HEIGHT - Tank.height;
	}

	// 发射子弹的方法
	public void fire() {
		fs.fire(this);
	}

	public void die() {
		this.living = false;

	}

	public void back() {
		this.x = this.prex;
		this.y = this.prey;
	}

}
