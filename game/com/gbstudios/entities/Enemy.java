package com.gbstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.gbstudios.world.Camera;
import com.gbstudios.world.World;
import com.indie.main.Game;

public class Enemy extends Entity{

	private double speed = 0.4;
	
	private int frames = 0, maxFrames = 15, index = 0, maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 5;
	
	private boolean isDamaged = false;
	
	private int damageFrames = 10, damageCurrent = 0;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(112, 16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(112 + 16, 16, 16, 16);
	}

	public void tick() {
		if(isColidingWithPlayer() == false) {
		if((int) x < Game.player.getX() && World.isFree((int) (x + speed), this.getY()) 
				&& !isColidding((int) (x + speed), this.getY())) {
			x += speed;
		}
			else if((int) x > Game.player.getX() && World.isFree((int) (x - speed), this.getY())
					&& !isColidding((int) (x - speed), this.getY())) {
				x -= speed;
			}
		if((int) y < Game.player.getY() && World.isFree(this.getX(), (int) (y + speed)) 
				&& !isColidding(this.getX(), (int) (y + speed))) {
			y += speed;
		}
			else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int) (y - speed)) 
					&& !isColidding(this.getX(), (int) (y - speed))) {
				y -= speed;
		}
		}else {
			//Player e Enemy estão colidindo
			if(Game.rand.nextInt(100) < 10) {
				Game.player.life-=Game.rand.nextInt(3);
				Game.player.isDamaged = true;
//				System.out.println("Vida: " + Game.player.life);
			}
		}
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
			}
			if(index > maxIndex)
				index = 0;
		
			collidingBullet();
			
			if(life <= 0) {
				selfDestruction();
				Player.xp++;
				if(Player.xp == 3) {
					Player.level = 2;
				}
				else if(Player.xp == 9) {
					Player.level = 3;
				}
				else if(Player.xp == 25) {
					Player.level = 4;
				}
				else if(Player.xp == 40) {
					Player.level = 5;
				}
				else if(Player.xp == 70) {
					Player.level = 6;
				}
				System.out.println(Player.xp);
				System.out.println(Player.level);
				return;
			}
			
			if(isDamaged) {
				this.damageCurrent++;
				if(this.damageCurrent == this.damageFrames) {
					this.damageCurrent = 0;
					this.isDamaged = false;
				}
			}
	}

	
	public void selfDestruction() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColliding(this, e)) {
					isDamaged = true;
					life--;
					Game.bullets.remove(i);
					return;
				}
			}
		}
	}
	
	public boolean isColidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), World.TILE_SIZE, World.TILE_SIZE);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		
		return enemyCurrent.intersects(player);
	}
	
	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext, ynext, World.TILE_SIZE, World.TILE_SIZE);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		if(!isDamaged)
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		else
			g.drawImage(Entity.ENEMY_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
