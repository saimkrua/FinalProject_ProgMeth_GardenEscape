package scene.StageGame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.IRenderable;

public class StageGameTile extends Canvas {

	public GameLogic logic;

	public StageGameTile(StageGame stageGame) {
		// TODO Auto-generated constructor stub
		super(StageGame.screenWidth, StageGame.screenHeight);
		this.logic = stageGame.logic;
		paintTile();
	}
	
	public void paintTile() {
		
		GraphicsContext gc = this.getGraphicsContext2D();
		for (IRenderable tile : logic.reHolder.getTiles()) {
			// System.out.println(entity.getZ());
			tile.draw(gc);
		}
	}

}
