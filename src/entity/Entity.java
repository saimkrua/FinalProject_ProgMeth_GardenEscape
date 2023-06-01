package entity;

import javafx.scene.shape.Rectangle;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {

	protected StageGame sg;
	protected double x, y;
	protected int z;
	public boolean visible, destroyed;
	public Rectangle solidArea;
	public boolean collisionOn;
	protected int spriteCounter = 0;
	protected int spriteNum = 1;

	protected Entity(StageGame sg, double x, double y) {

		this.sg = sg;
		visible = true;
		destroyed = false;
		collisionOn = false;
		setX(x);
		setY(y);
		setZ(RenderNum.defaultZ);
		solidArea = new Rectangle();
		solidArea.setX(0);
		solidArea.setY(0);
		solidArea.setWidth(StageGame.tileSize);
		solidArea.setHeight(StageGame.tileSize);
	}

	public void setX(double x) {
		if (x < 0) {
			x = 0;
		}
		if (x > StageGame.screenWidth - StageGame.tileSize) {
			x = StageGame.screenWidth - StageGame.tileSize;
		}
		this.x = x;
	}

	public void setY(double y) {
		if (y < 0) {
			y = 0;
		}
		if (y > StageGame.screenHeight - StageGame.tileSize) {
			y = StageGame.screenHeight - StageGame.tileSize;
		}
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getZ() {
		return z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void updateSpriteNum(int maxSprite, int maxCounter) {
		spriteCounter++;
		if (spriteCounter > maxCounter) {
			if (spriteNum == maxSprite) {
				spriteNum = 1;
			} else {
				spriteNum++;
			}
			spriteCounter = 0;
		}
	}
}
