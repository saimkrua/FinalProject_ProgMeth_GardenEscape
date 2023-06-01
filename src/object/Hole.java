package object;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Hole extends entity.Object {

	private double onHoleX;
	private double onHoleY;
	private int objectNum;

	public Hole(StageGame sg, int x, int y, int objectNum) {
		super(sg, x, y, objectNum);
		audioClip = RenderableHolder.audios.get("effect/fall1");
		setZ(RenderNum.holeZ);
		onHoleX = -1;
		onHoleY = -1;
		setZ(RenderNum.holeZ);
		this.objectNum = objectNum;

	}

	public void update() {

		updateSpriteNum(10, RenderNum.holeSprite);

		if (onHoleX == -1 && onHoleY == -1) {
			sg.collisionCheck.checkObject(this);
			if (collisionOn) {
				audioClip.play();
				Player.onHole = true;
				onHoleX = this.x;
				onHoleY = this.y;
				sg.logic.player.sethP(sg.logic.player.gethP() - 40);
			}

		} else {
			if (onHoleX == this.x && onHoleY == this.y) {
				if (sg.collisionCheck.checkOutofObject(this)) {
					collisionOn = false;
					Player.onHole = false;
					onHoleX = -1;
					onHoleY = -1;
				}
			}

		}
	}

	public void draw(GraphicsContext gc) {
		image = RenderableHolder.Object[objectNum + spriteNum - 1];
		gc.drawImage(image, x, y);
	}
}
