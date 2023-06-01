package object;

import javafx.scene.canvas.GraphicsContext;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Diamond extends entity.Object {

	public Diamond(StageGame sg, int x, int y, int objectNum) {
		super(sg, x, y, objectNum);
		audioClip = RenderableHolder.audios.get("effect/collect1");
		setZ(RenderNum.diamondZ);

	}

	public void update() {
		sg.collisionCheck.checkObject(this);
		if (collisionOn) {
			audioClip.play();
			sg.logic.player.sethP(sg.logic.player.gethP() + 15);
			this.destroyed = true;
			collisionOn = false;
		} else {
			this.visible = true;
			this.destroyed = false;
		}

		updateSpriteNum(2, RenderNum.diamondSprite);
	}

	@Override
	public void draw(GraphicsContext gc) {

		gc.drawImage(image, x + 8, y + 8 + (spriteNum * 2));
	}
}
