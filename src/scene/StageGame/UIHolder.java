package scene.StageGame;

import java.util.ArrayList;
import java.util.List;

public class UIHolder {

	private List<UI> uiList;

	public void setUp(StageGame sg) {

		uiList = new ArrayList<UI>();

		addUI(new PauseButton(sg));
		addUI(new OptionWindow(sg));
		addUI(new DieWindow(sg));
		addUI(new SuccessWindow(sg));
		

	}
	
	public void addUI(UI ui) {
		uiList.add(ui);
	}

	public void update() {
		for (int i = uiList.size() - 1; i >= 0; i--) {
			uiList.get(i).update();
		}
	}

	public List<UI> getUiList() {
		return uiList;
	}

}
