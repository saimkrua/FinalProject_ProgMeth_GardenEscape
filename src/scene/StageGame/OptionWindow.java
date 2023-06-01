package scene.StageGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameLogic.GameState;
import scene.ButtonUI;

public class OptionWindow extends StackPane implements UI {

	private StageGame sg;

	public OptionWindow(StageGame sg) {

		this.sg = sg;
		this.setPrefSize(768, 576);
		this.getStylesheets().add("/css/WindowStyle.css");
		this.getStylesheets().add("/css/font.css");
		this.setPadding(new Insets(138, 259, 138, 259));

		Rectangle window = new Rectangle(250, 300, Color.web("#FFFFFF", 0.7));
		window.setArcHeight(25);
		window.setArcWidth(25);
		window.setStroke(Color.BLACK);
		window.setStrokeWidth(2.5);

		Text text = new Text("Game Pause");
		text.setId("text1");

		Button continueButton = new Button();
		ButtonUI.createButton(sg, "CONTINUE", continueButton);
		Button mainMenuButton = new Button();
		ButtonUI.createButton(sg, "MAIN MENU", mainMenuButton);

		VBox detail = new VBox(40, text, continueButton, mainMenuButton);
		detail.setAlignment(Pos.CENTER);
		detail.setSpacing(40);

		this.getChildren().addAll(window, detail);

		this.setVisible(false);
		this.setDisable(true);

	}

	@Override
	public void update() {
		if (sg.logic.gameState == GameState.PAUSE) {
			this.setVisible(true);
			this.setDisable(false);
		} else {
			this.setVisible(false);
			this.setDisable(true);
		}
	}

}
