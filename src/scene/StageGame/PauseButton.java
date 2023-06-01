package scene.StageGame;

import javafx.scene.control.Button;
import logic.GameLogic.GameState;
import scene.ButtonUI;

public class PauseButton extends Button implements UI {

	private StageGame sg;

	public PauseButton(StageGame sg) {
		this.sg = sg;
		ButtonUI.createButton(sg, "PAUSE", this);
		this.setLayoutX(690);
		this.setLayoutY(10);
		this.setPrefHeight(30);
	}

	@Override
	public void update() {

		if (sg.logic.gameState == GameState.PLAY) {

			this.setVisible(true);
			this.setDisable(false);
		} else {
			
			this.setVisible(false);
			this.setDisable(true);
		}
	}

}
