package tile;

import javafx.scene.canvas.GraphicsContext;
import map.Map1;
import map.Map2;
import scene.StageGame.StageGame;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class TileMap implements IRenderable {

	public int mapTileNum[][];
	private int stageNumber;
	private int layer;

	public TileMap(StageGame sg, int stageNumber, int layer) {
		this.stageNumber = stageNumber;
		this.layer = layer;
		mapTileNum = new int[StageGame.maxScreenCol][StageGame.maxScreenRow];
		loadMap();
	}

	public void loadMap() {

		String[][][] allMap = { { Map1.map1, Map1.map2, Map1.map3, Map1.map4, Map1.map5, Map1.map6 },
				{ Map2.map1, Map2.map2, Map2.map3, Map2.map4, Map2.map5, Map2.map6 } };

		int col = 0;
		int row = 0;
		while (col < StageGame.maxScreenCol && row < StageGame.maxScreenRow) {

			String line = allMap[layer][stageNumber - 1][row];

			while (col < StageGame.maxScreenCol) {
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[col]);
				mapTileNum[col][row] = num;
				col++;
			}
			if (col == StageGame.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}

	@Override
	public int getZ() {
		return layer;
	}

	@Override
	public void draw(GraphicsContext gc) {

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < StageGame.maxScreenCol && row < StageGame.maxScreenRow) {

			int tileNum = mapTileNum[col][row];
			gc.drawImage(RenderableHolder.tile[tileNum].image, x, y);
			col++;
			x += StageGame.tileSize;

			if (col == StageGame.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += StageGame.tileSize;
			}
		}

	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void update() {
		// DO NOTHING
	}

}
