package object;

import logic.GameLogic.GameState;
import scene.RenderNum;
import scene.StageGame.StageGame;
import sharedObject.RenderableHolder;

public class Endpoint extends entity.Object {

	public Endpoint(StageGame sg, int x, int y, int objectNum) {
		super(sg, x, y, objectNum);
		audioClip = RenderableHolder.audios.get("effect/collect2");
		new Star(sg, x, y, 473);
		setZ(RenderNum.endpointZ);
		
	}

	public void update() {
		sg.collisionCheck.checkObject(this);
		if (collisionOn) {
			audioClip.play();
			sg.logic.gameState = GameState.SUCCESS;
			collisionOn = false;
		}
	}

}
