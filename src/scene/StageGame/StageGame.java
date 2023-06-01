package scene.StageGame;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import logic.CollisionChecker;
import logic.GameLogic;
import logic.GameLogic.GameState;
import scene.RenderNum;

public class StageGame extends Canvas implements Runnable {

	public StackPane root = new StackPane();
	public Scene scene;
	public StageGameTile stageGameTile;
	public StageGameUI stageGameUI;

	public final static int originalTileSize = 16; // 16x16
	public final static int scale = 3;

	public final static int tileSize = originalTileSize * scale; // 3(16x16)
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 12;
	public final static int screenWidth = tileSize * maxScreenCol; // 768
	public final static int screenHeight = tileSize * maxScreenRow; // 576

	public int countdraw;
	public logic.KeyHandler keyH = new logic.KeyHandler();
	public int stageNumber;

	private Thread gameThread;
	public GameLogic logic;
	public CollisionChecker collisionCheck = new CollisionChecker(this);
	public GraphicsContext gc = this.getGraphicsContext2D();

	public StageGame(int stagenumber) {

		super(screenWidth, screenHeight);
		this.stageNumber = stagenumber;
		this.setVisible(true);
		this.addListener();
		logic = new GameLogic(this);
		logic.addNewObstacles();


		stageGameTile = new StageGameTile(this);
		stageGameUI = new StageGameUI(this);
		root.getChildren().addAll(stageGameTile, this, stageGameUI);
		scene = new Scene(root);

	}

	public void addListener() {
		this.setOnKeyPressed(e -> keyH.keyPressed(e));
		this.setOnKeyReleased(e -> keyH.keyReleased(e));
	}

	public void startGameThread() throws IOException {
		this.logic.gameState = GameState.PLAY;
		this.logic.player.setDefault();
		this.logic.reHolder.setDefault(); 
		
		gameThread = new Thread(this);
		gameThread.setPriority(RenderNum.priorGame);
		gameThread.start();
		System.out.println("thread game start");
		
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / RenderNum.FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		countdraw = 0;
		while (gameThread.getState() != Thread.State.TERMINATED
				&& (this.logic.gameState == GameState.PLAY || this.logic.gameState == GameState.PAUSE)) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				
				countdraw += 1;
				
				gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
				logic.logicUpdate();
				logic.paintComponent();
				logic.hpBar.update();
				logic.hpBar.draw(this.getGraphicsContext2D());
				
				delta--;

			}
			

		}
		System.out.println("thread game end");

	}
}
