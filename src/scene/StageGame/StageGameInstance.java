package scene.StageGame;

import java.util.ArrayList;
import java.util.List;

public class StageGameInstance {

	public static final StageGameInstance instanceAllStage = new StageGameInstance(6);
	private static List<StageGame> allStage;

	public StageGameInstance(int numStage) {
		allStage = new ArrayList<StageGame>();
		for (int i = 1; i <= numStage; i++) {
			StageGame instance = new StageGame(i);
			StageGameInstance.allStage.add(instance);
		}
	}

	public static StageGameInstance getInstance() {
		return instanceAllStage;
	}

	public StageGame getStage(int stageNumber) {
		return StageGameInstance.allStage.get(stageNumber - 1);
	}

}
