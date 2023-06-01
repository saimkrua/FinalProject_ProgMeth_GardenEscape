package scene;

import java.util.ArrayList;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {

	private static Scene scene;
	private static final MainMenu instance = new MainMenu();

	public static MainMenu getInstance() {
		return instance;
	}

	private ArrayList<Button> Buttons = new ArrayList<Button>();

	public MainMenu() {

		scene = new Scene(this);
		scene.setCursor(new ImageCursor(
				new Image(ClassLoader.getSystemResource("image/scene/Cursor/Triangle1.png").toString())));

		this.setPrefSize(768, 576);
		this.getStylesheets().add(ClassLoader.getSystemResource("css/MainMenuStyle.css").toString());
		this.getStylesheets().add(ClassLoader.getSystemResource("css/font.css").toString());

		VBox gameName = new VBox();
		Image name = new Image(ClassLoader.getSystemResource("image/mainmenu/name.png").toString());
		gameName.getChildren().add(new ImageView(name));
		gameName.getStyleClass().add("gamename");

		VBox menuButton = new VBox();
		menuButton.getStyleClass().add("menubox");

		setupButton("PLAY");
		setupButton("TUTORIAL");
		setupButton("QUIT");

		menuButton.getChildren().addAll(Buttons);
		this.getChildren().addAll(gameName, menuButton);
	}

	public void setupButton(String name) {

		Buttons.add(ButtonUI.createButton(name));
	}

}
