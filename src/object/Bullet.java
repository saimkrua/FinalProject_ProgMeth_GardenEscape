package object;

import javafx.scene.canvas.GraphicsContext;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Bullet extends entity.Object {

	public int speed;
	private double yStart, xStart;
	private int timer;
	private int objectNum;

	public Bullet(StageGame sg, int x, int y, int objectNum, int speed) {

		super(sg, x, y, objectNum);
		audioClip = RenderableHolder.audios.get("effect/hit1");
		this.speed = speed;
		this.xStart = x;
		this.yStart = y;
		this.sg = sg;
		setZ(RenderNum.bulletZ);
		this.objectNum = objectNum;
		new HeadTower(sg, x, y, 422);

	}

	public void moveY() {
		y += this.speed;
		if (y > 576) {
			collisionOn = false;
			y = yStart;
			this.visible = true;
		}
	}

	public void moveX() {
		x += this.speed;
		if (x > 768) {
			collisionOn = false;
			x = xStart;
			this.visible = true;
		}
	}

	public void move() {
		if (objectNum == 379) {
			moveY();
		} else if (objectNum == 365) {
			moveX();
		}
	}

	@Override
	public void update() {

		Thread thread = new Thread(() -> {
			timer = sg.countdraw;
			while (sg.countdraw - timer != this.speed * 3) {
			}
			collisionOn = false;
		});

		move();
		if (!collisionOn) {
			sg.collisionCheck.checkObject(this);
			if (collisionOn) {
				audioClip.play();

				// BOOM BOOM BOOM EFFECT : Disable cuz lag
				// FROM HERE
//				Thread boomThread = new Thread(()->{
//					
//					Boom boom = new Boom(sg, x, y, 393);
//					while (boom.visible) {
//						boom.update();
//						boom.draw(sg.gc);
//					}
//					System.out.println("thread Boom end");
//
//				});
//				boomThread.setPriority(RenderNum.priorBoom);
//				boomThread.start();
				// TO HERE
				if (isVisible()) {
					sg.logic.player.sethP(sg.logic.player.gethP() - 30);
				}
				this.visible = false;
				thread.start();
			}
		}

		updateSpriteNum(14, RenderNum.bulletSprite);

	}

	public void draw(GraphicsContext gc) {

		image = RenderableHolder.Object[objectNum + spriteNum - 1];
		gc.drawImage(image, x, y);

	}

}
