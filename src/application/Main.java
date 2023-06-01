package application;

import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneController;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {
		
		SceneController.getSceneController().switchToMainMenu();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
