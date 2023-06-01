package logic;

import java.util.ArrayList;
import entity.Entity;
import entity.HpBar;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import map.ObjectMap;
import object.Bullet;
import object.Diamond;
import object.Endpoint;
import object.Hole;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import tile.TileMap;

public class GameLogic {

	private StageGame sg;
	private int stageNumber;
	
	public HpBar hpBar;
	public Player player;
	public RenderableHolder reHolder;
	public GameState gameState;

	public enum GameState {
		PLAY, PAUSE, STOP, DIE, SUCCESS
	}

	public ArrayList<TileMap> tileMaps;
	public TileMap tileMap0;
	public TileMap tileMap1;

	public GameLogic(StageGame sg) {
		this.reHolder = new RenderableHolder();
		this.tileMaps = new ArrayList<TileMap>();
		this.sg = sg;
		this.stageNumber = sg.stageNumber;
		this.gameState = GameState.PLAY;

		tileMap0 = new TileMap(this.sg, stageNumber, 0);
		tileMap1 = new TileMap(this.sg, stageNumber, 1);
		addNewTileMap(tileMap0);
		addNewTileMap(tileMap1);

		player = new Player(this.sg);
		hpBar = new HpBar(this.sg);

	}

	public void addNewObstacles() {

		int mapObstacleNum[][] = new int[StageGame.maxScreenCol][StageGame.maxScreenRow];
		loadObstacleMap(mapObstacleNum);

		int col = 0;
		int row = 0;

		while (col < StageGame.maxScreenCol && row < StageGame.maxScreenRow) {

			int obstacleNum = mapObstacleNum[col][row];
			if (obstacleNum == 365 || obstacleNum == 379) { //365-378 379-392
				Bullet bullet = new Bullet(sg, col * StageGame.tileSize, row * StageGame.tileSize, obstacleNum, RenderNum.bulletSpeed);
				addNewObject(bullet);
			} else if (obstacleNum >= 142 && obstacleNum <= 150) { // diamond 142-150
				Diamond diamond = new Diamond(sg, col * StageGame.tileSize, row * StageGame.tileSize, obstacleNum);
				addNewObject(diamond);
			} else if (obstacleNum >= 11 && obstacleNum <= 13) {// hole
				Hole hole = new Hole(sg, col * StageGame.tileSize, row * StageGame.tileSize, 477);
				addNewObject(hole);
			} else if (obstacleNum == 14) {// endpoint
				Endpoint endpoint = new Endpoint(sg, col * StageGame.tileSize, row * StageGame.tileSize, 14);
				addNewObject(endpoint);
			}
			col++;
			if (col == StageGame.maxScreenCol) {
				col = 0;
				row++;
			}
		}
		System.out.println("finish add obstacle ");
	}

	public void loadObstacleMap(int[][] mapObstacleNum) {

		String[][] allMap = { ObjectMap.map1, ObjectMap.map2, ObjectMap.map3, ObjectMap.map4, ObjectMap.map5,
				ObjectMap.map6 };

		int col = 0;
		int row = 0;
		while (col < StageGame.maxScreenCol && row < StageGame.maxScreenRow) {

			String line = allMap[stageNumber - 1][row];

			while (col < StageGame.maxScreenCol) {
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[col]);
				mapObstacleNum[col][row] = num;
				col++;
			}
			if (col == StageGame.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}

	public void addNewTileMap(TileMap tileMap) {
		tileMaps.add(tileMap);
		reHolder.addTile(tileMap);
	}

	public void addNewObject(Entity entity) {
		reHolder.add(entity);
	}

	public void logicUpdate() {
		if (player.gethP() == 0) {
			RenderableHolder.audios.get("effect/over1").play();
			gameState = GameState.DIE;
			
		}
		if (gameState == GameState.PLAY ) {
			player.update();
			reHolder.update();
		}
		
	}

	public void paintComponent() {
		GraphicsContext gc = sg.gc;
		for (IRenderable entity : reHolder.getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
		player.draw(gc);
	}

}