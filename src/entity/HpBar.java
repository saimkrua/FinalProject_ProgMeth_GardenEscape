package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class HpBar {

	private int playerHp;
	private final int MAXHP = 100;
	private StageGame sg;

	private float x = StageGame.tileSize;
	private float y = StageGame.tileSize / 4;
	private float widthBar = StageGame.tileSize * 5;
	private float heightBar = StageGame.tileSize / 2;
	private float scale = widthBar / MAXHP;
	private float round = 20;

	public HpBar(StageGame sg) {
		this.sg = sg;
	}

	public void draw(GraphicsContext gc) {
		
		gc.setFont(RenderableHolder.brokenConsole);
		gc.setFill(Color.BLACK);
		gc.fillText("HP", x - 40, y+heightBar);

		gc.setFill(Color.BLANCHEDALMOND);
		gc.fillRoundRect(x, y, widthBar, heightBar, round, round);

		gc.setFill(Color.INDIANRED);
		gc.fillRoundRect(x, y, playerHp * scale, heightBar, round, round);

		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2.5);
		gc.strokeRoundRect(x, y, widthBar, heightBar, round, round);

		gc.setFill(Color.BLACK);
		gc.fillText(Integer.toString(playerHp), x + widthBar + 16, y+heightBar);

	}

	public void update() {

		playerHp = sg.logic.player.gethP();

	}

}
