package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.KeyHandler;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Player extends Entity {

	public static final int playerSpeed = RenderNum.playerSpeed;
	public String direction;
	public static boolean onHole;
	public static boolean walk;
	private double lastX;
	private double lastY;
	private double distance;

	private int hP;
	protected KeyHandler keyH;

	public Player(StageGame sg) {
		super(sg, 0, 0);
		this.keyH = sg.keyH;
		setDefault();
		solidArea.setX(16);
		solidArea.setY(16);
		solidArea.setWidth(16);
		solidArea.setHeight(25);// x y width height
		setZ(10);
	}

	public void setDefault() {
		x = 35;
		y = 35;
		direction = "down";
		this.sethP(100);
		onHole = false;
		walk = false;
		distance = 0;
		lastX = x;
		lastY = y;
	}

	public void checkDistance() {

		if (distance >= StageGame.tileSize * 2) {
			this.sethP(hP - 1);
			distance = 0;
		}
	}

	public void updateDistance() {
		if (walk) {
			distance = distance + Math.abs(x - lastX) + Math.abs(y - lastY);
			lastY = y;
			lastX = x;
		}
	}

	public void checkDirection() {

		if (keyH.upPressed == true) {
			direction = "up";
		} else if (keyH.downPressed == true) {
			direction = "down";
		} else if (keyH.leftPressed == true) {
			direction = "left";
		} else if (keyH.rightPressed == true) {
			direction = "right";
		}
	}

	public void updatePosition() {
		
		collisionOn = false;
		sg.collisionCheck.checkTile(this);
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				setY(y - playerSpeed);
				break;
			case "down":
				setY(y + playerSpeed);
				break;
			case "left":
				setX(x - playerSpeed);
				break;
			case "right":
				setX(x + playerSpeed);
				break;
			}
		}
	}

	public void update() {
		
		updateDistance();
		checkDistance();

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {

			if (!walk) {
				walk = true;
			}

			checkDirection();
			updatePosition();
			

		} else {
			walk = false;
		}

		updateSpriteNum(4, RenderNum.playerSprite);

	}

	@Override
	public void draw(GraphicsContext gc) {

		Image image = null;

		switch (direction) {

		case "down":
			image = RenderableHolder.character[spriteNum - 1];
			break;

		case "left":
			image = RenderableHolder.character[spriteNum + 3];
			break;

		case "right":
			image = RenderableHolder.character[spriteNum + 7];
			break;

		case "up":
			image = RenderableHolder.character[spriteNum + 11];
			break;
		}
		gc.drawImage(image, x, y);

	}

	public int gethP() {
		return hP;
	}

	public void sethP(int hP) {

		if (hP > 100) {
			hP = 100;
		} else if (hP < 0) {
			hP = 0;
		}
		this.hP = hP;
	}

}