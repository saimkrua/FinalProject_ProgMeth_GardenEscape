package object;

import javafx.scene.canvas.GraphicsContext;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class HeadTower extends entity.Object {
	
	private int objectNum;

	public HeadTower(StageGame sg, double x, double y, int objectNum) {
		super(sg, x, y, objectNum);
		this.objectNum = objectNum;
		sg.logic.addNewObject(this);
		setZ(RenderNum.headtowerZ);

	}

	public void draw(GraphicsContext gc) {

		image = RenderableHolder.Object[objectNum + spriteNum - 1];
		gc.drawImage(image, x, y);

	}

	@Override
	public void update() {
		updateSpriteNum(17, RenderNum.headTowerSprite);
	}

}
