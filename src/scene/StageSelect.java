package scene;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class StageSelect extends VBox {
	
	private static final StageSelect instance = new StageSelect();
	
	public static StageSelect getInstance(){
		return instance;
	}

	private ArrayList<StageButton> stageButtons = new ArrayList<StageButton>();
	private GridPane stageGrid;
	private Button mainMenuButton;
	private static Scene scene;

	public class StageButton extends Button {

		private int stageNumber;

		public StageButton(int number) {
			super(Integer.toString(number));
			stageNumber = number;
		}

		public int getStageNumer() {
			return stageNumber;
		}
	}

	public StageSelect() {
		
		scene = new Scene(this);
		scene.setCursor(new ImageCursor(
				new Image(ClassLoader.getSystemResource("image/scene/Cursor/Triangle1.png").toString())));
		
		this.setPrefSize(768, 576);

		this.getStylesheets().add(ClassLoader.getSystemResource("css/StageSelectStyle.css").toString());
		this.getStylesheets().add(ClassLoader.getSystemResource("css/font.css").toString());

		mainMenuButton = ButtonUI.createButton("BACK");
		mainMenuButton.setId("backbutton");

		stageGrid = new GridPane();
		stageGrid.getStyleClass().add("stagegrid");

		int row = 0;
		for (int i = 0; i < 6; i++) {
			if (i % 3 == 0)
				row += 1;
			setupStageButton(i + 1);
			stageGrid.add(stageButtons.get(i), i % 3, row);
		}

		this.getChildren().addAll(stageGrid, mainMenuButton);
	}

	public void setupStageButton(int i) {
		StageButton newStageButton = new StageButton(i);
		stageButtons.add(newStageButton);
		newStageButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					AudioController.currentAudioClip.stop();
					SceneController.getSceneController().switchToStage(newStageButton.stageNumber);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
