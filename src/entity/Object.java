package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public abstract class Object extends Entity {

	protected Image image;
	protected AudioClip audioClip;

	public Object(StageGame sg, double x, double y, int objectNum) {
		super(sg, x, y);
		image = RenderableHolder.Object[objectNum];
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}

	@Override
	public abstract void update();

}
