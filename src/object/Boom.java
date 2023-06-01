package object;

import javafx.scene.canvas.GraphicsContext;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Boom extends entity.Object {

	public boolean isBoom;
	private int objectNum;

	public Boom(StageGame sg, double x, double y, int objectNum) {
		super(sg, x, y, objectNum);
		this.objectNum = objectNum;
		this.visible = true;
		setZ(RenderNum.boomZ);

	}

	public void draw(GraphicsContext gc) {

		image = RenderableHolder.Object[objectNum + spriteNum - 1];
		gc.drawImage(image, x, y);

	}

	@Override
	public void update() {
		updateSpriteNum(12, RenderNum.boomSprite);
		if (spriteNum == 12)
			this.visible = false;
	}

}
