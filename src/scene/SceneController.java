package scene;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;
import scene.StageGame.StageGameInstance;

public class SceneController {

	private static SceneController scenecontrol = new SceneController();
	private static Stage primaryStage; // ONLYONE

	public SceneController() {
		primaryStage = new Stage();
		primaryStage.setTitle("Garden Escape");
		primaryStage.setResizable(false);
		primaryStage.setMaxHeight(576);
		primaryStage.setMaxWidth(768);
		primaryStage.getIcons().add(RenderableHolder.Object[78]);
	}

	public static SceneController getSceneController() {
		return scenecontrol;
	}

	public void switchToStageSelect() throws IOException {
		// for click new game
		setupScreen(StageSelect.getInstance());
		primaryStage.show();
	}

	public void switchToMainMenu() throws IOException {
		// for click home
		setupScreen(MainMenu.getInstance());
		if (AudioController.currentAudioClip != RenderableHolder.audios.get("mainmenu")) {
			playMusic("mainmenu");
		}
		primaryStage.show();
	}

	public void switchToTutorial() throws IOException {
		// for click tutorial

		setupScreen(Tutorial.getInstance());
		primaryStage.show();
	}

	public void switchToStage(int stageNumber) throws IOException {
		// for click stage

		setupScreen(StageGameInstance.getInstance().getStage(stageNumber).root);
		StageGameInstance.getInstance().getStage(stageNumber).startGameThread();
		StageGameInstance.getInstance().getStage(stageNumber).requestFocus();
		StageGameInstance.getInstance().getStage(stageNumber).stageGameUI.startGameThread();

		playMusic("stage/stage" + ((stageNumber % 2) + 1));
		primaryStage.show();

	}

	public void setupScreen(Parent root) {

		primaryStage.setScene(root.getScene());
	}

	public void closeGame() throws IOException {
		// for close
		primaryStage.close();
	}

	public void playMusic(String path) {

		if (AudioController.currentAudioClip != null) {
			AudioController.currentAudioClip.stop();
		}
		AudioController.currentAudioClip = RenderableHolder.audios.get(path);
		AudioController.currentAudioClip.setCycleCount(MediaPlayer.INDEFINITE);
		AudioController.currentAudioClip.play();

	}

}
