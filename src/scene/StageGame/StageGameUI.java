package scene.StageGame;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import logic.GameLogic.GameState;
import scene.RenderNum;

public class StageGameUI extends AnchorPane implements Runnable {

	private StageGame sg;
	private Thread gameThread;
	public UIHolder uiHolder;
	
	public StageGameUI(StageGame sg) {

		this.sg = sg;
		this.setPrefSize(768, 576);
		
		this.getStylesheets().add(ClassLoader.getSystemResource("css/StageGameUIStyle.css").toString());
		this.getStylesheets().add(ClassLoader.getSystemResource("css/font.css").toString());
		this.uiHolder = new UIHolder();
		setUp();
		
	}
	
	public void setUp() {
		uiHolder.setUp(sg);

		List<UI> uiList = uiHolder.getUiList();
		for (int i = uiList.size() - 1; i >= 0; i--) {
			System.out.println("add "+uiList.get(i).getClass());
			this.getChildren().add((Node) uiList.get(i));
		}
	}
	
	public void startGameThread() {
		this.sg.logic.gameState = GameState.PLAY;
		gameThread = new Thread(this);
		gameThread.start();
		gameThread.setPriority(RenderNum.priorUI);
		System.out.println("thread UI start");
	}
	
	@Override
	public void run() {

		double drawInterval = 1000000000 / RenderNum.FPSUI;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
//		System.out.println("state: " + this.sg.logic.gameState);
//		System.out.println("state UI: " + this.sg.logic.gameState);
		while (gameThread.getState() != Thread.State.TERMINATED && sg.logic.gameState != GameState.STOP) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				uiHolder.update();
				
				delta--;
			}
			if (sg.logic.gameState == GameState.STOP) {
				break;
			}
			
		}
		
		System.out.println("thread UI end");

	}

}
