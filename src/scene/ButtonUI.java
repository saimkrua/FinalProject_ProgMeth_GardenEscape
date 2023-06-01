package scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import logic.GameLogic.GameState;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class ButtonUI {

	private static AudioClip audioClip = RenderableHolder.audios.get("effect/click2");

	public static Button createButton(String name) {

		Button newButton = new Button(name);
		newButton.setPrefHeight(50);
		newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {

					audioClip.play();

					switch (name) {
					case "PLAY":
						SceneController.getSceneController().switchToStageSelect();
						break;
					case "TUTORIAL":
						SceneController.getSceneController().switchToTutorial();
						break;
					case "QUIT":
						SceneController.getSceneController().closeGame();
						break;
					case "BACK":
						SceneController.getSceneController().switchToMainMenu();
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return newButton;
	}

	public static void createButton(StageGame sg, String name, Button button) {

		button.setText(name);
		button.setPrefHeight(50);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {

					audioClip.play();

					switch (name) {
					case "PLAY AGAIN":
						sg.logic.gameState = GameState.STOP;
						SceneController.getSceneController().switchToMainMenu();
						break;
					case "CONTINUE":
						sg.logic.gameState = GameState.PLAY;
						sg.requestFocus();
						break;
					case "PAUSE":
						sg.logic.gameState = GameState.PAUSE;
						break;
					case "MAIN MENU":
						sg.logic.gameState = GameState.STOP;
						SceneController.getSceneController().switchToMainMenu();
						break;
					case "QUIT":
						sg.logic.gameState = GameState.STOP;
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
