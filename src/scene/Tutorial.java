package scene;

import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sharedObject.RenderableHolder;

public class Tutorial extends AnchorPane {

	private static final Tutorial instance = new Tutorial();
	private Button mainMenuButton;
	private static Scene scene;

	
	public static Tutorial getInstance() {
		return instance;
	}

	public Tutorial() {
		scene = new Scene(this);
		scene.setCursor(new ImageCursor(
				new Image(ClassLoader.getSystemResource("image/scene/Cursor/Triangle1.png").toString())));
		
		
		this.setPrefSize(768, 576);
		this.setPadding(new Insets(20));
		this.getStylesheets().add(ClassLoader.getSystemResource("css/TutorialStyle.css").toString());

		mainMenuButton = ButtonUI.createButton("BACK");
		mainMenuButton.setId("backbutton");
		mainMenuButton.setLayoutX(690);
		mainMenuButton.setLayoutY(15);
		
		ImageView fire = new ImageView(RenderableHolder.Object[365]);
		fire.setLayoutX(163);
		fire.setLayoutY(363);
		
		ImageView trap = new ImageView(RenderableHolder.Object[481]);
		trap.setLayoutX(163);
		trap.setLayoutY(425);
		
		ImageView diamond = new ImageView(RenderableHolder.Object[142]);
		diamond.setLayoutX(170);
		diamond.setLayoutY(485);


		this.getChildren().addAll(mainMenuButton,fire,trap,diamond);
	}
	

}
