package logic;

import entity.Entity;
import entity.Player;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class CollisionChecker {

	private StageGame sg;

	public CollisionChecker(StageGame sg) {
		this.sg = sg;
	}

	public void checkTile(Player player) {

		int playerLeftX = (int) (player.getX() + player.solidArea.getX());
		int playerRightX = (int) (player.getX() + player.solidArea.getX() + player.solidArea.getWidth());
		int playerTopY = (int) (player.getY() + player.solidArea.getY());
		int playerBottomY = (int) (player.getY() + player.solidArea.getY() + player.solidArea.getHeight());

		int playerLeftCol = (int) (playerLeftX / StageGame.tileSize);
		int playerRightCol = (int) (playerRightX / StageGame.tileSize);
		int playerTopRow = (int) (playerTopY / StageGame.tileSize);
		int playerBottomRow = (int) (playerBottomY / StageGame.tileSize);

		int tileNum1, tileNum2;

		for (int i = 0; i < sg.logic.tileMaps.size(); i++) {
			if (player.collisionOn == true)
				break;
			switch (player.direction) {
			case "up":
				playerTopRow = (int) ((playerTopY - Player.playerSpeed) / StageGame.tileSize);
				tileNum1 = sg.logic.tileMaps.get(i).mapTileNum[playerLeftCol][playerTopRow];
				tileNum2 = sg.logic.tileMaps.get(i).mapTileNum[playerRightCol][playerTopRow];
				if (RenderableHolder.tile[tileNum1].collision == true
						|| RenderableHolder.tile[tileNum2].collision == true) {
					player.collisionOn = true;
				}
				break;
			case "down":
				playerBottomRow = (int) ((playerBottomY + Player.playerSpeed) / StageGame.tileSize);
				if (playerBottomRow < 12) {
					tileNum1 = sg.logic.tileMaps.get(i).mapTileNum[playerLeftCol][playerBottomRow];
					tileNum2 = sg.logic.tileMaps.get(i).mapTileNum[playerRightCol][playerBottomRow];
					if (RenderableHolder.tile[tileNum1].collision == true
							|| RenderableHolder.tile[tileNum2].collision == true) {
						player.collisionOn = true;
					}
				}
				break;
			case "left":
				playerLeftCol = (int) ((playerLeftX - Player.playerSpeed) / StageGame.tileSize);
				if (playerBottomRow < 12) {
					tileNum1 = sg.logic.tileMaps.get(i).mapTileNum[playerLeftCol][playerTopRow];
					tileNum2 = sg.logic.tileMaps.get(i).mapTileNum[playerLeftCol][playerBottomRow];
					if (RenderableHolder.tile[tileNum1].collision == true
							|| RenderableHolder.tile[tileNum2].collision == true) {
						player.collisionOn = true;
					}
				}
				break;
			case "right":
				playerRightCol = (int) ((playerRightX + Player.playerSpeed) / StageGame.tileSize);
				if (playerBottomRow < 12) {
					tileNum1 = sg.logic.tileMaps.get(i).mapTileNum[playerRightCol][playerTopRow];
					tileNum2 = sg.logic.tileMaps.get(i).mapTileNum[playerRightCol][playerBottomRow];
					if (RenderableHolder.tile[tileNum1].collision == true
							|| RenderableHolder.tile[tileNum2].collision == true) {
						player.collisionOn = true;
					}
				}
				break;

			}

		}

	}

	public void checkObject(Entity object) {

		Player player = sg.logic.player;

		Rectangle solidPlayer = new Rectangle(player.getX() + player.solidArea.getX(),
				player.getY() + player.solidArea.getY(), player.solidArea.getWidth(), player.solidArea.getHeight());
		Rectangle solidObject = new Rectangle(object.getX() + object.solidArea.getX(),
				object.getY() + object.solidArea.getY(), object.solidArea.getWidth(), object.solidArea.getHeight());

		Shape intersect = Shape.intersect(solidPlayer, solidObject);

		if (intersect.getBoundsInLocal().getWidth() != -1) {
			object.collisionOn = true;
		}
	}

	public boolean checkOutofObject(Entity object) {

		Player player = sg.logic.player;

		Rectangle solidPlayer = new Rectangle(player.getX(), player.getY(), StageGame.tileSize, StageGame.tileSize);
		Rectangle solidObject = new Rectangle(object.getX(), object.getY(), StageGame.tileSize, StageGame.tileSize);

		Shape intersect = Shape.intersect(solidPlayer, solidObject);

		return intersect.getBoundsInLocal().getWidth() == -1; // return not intersect
	}

}
