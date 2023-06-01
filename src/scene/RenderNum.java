package scene;

public class RenderNum {

	public static final double scale = 1;

	public static final int FPS = (int) (60 * scale);
	public static final int FPSUI = (int) (30 * scale);
	
	public static final int priorBoom = 10;
	public static final int priorGame = 10;
	public static final int priorUI = 5;

	public static final int playerSpeed = (int) (FPS / 20 / scale); // bigger denminator -> slower
	public static final int bulletSpeed = (int) (FPS / 20 / scale);

	public static final int defaultSprite = (int) (FPS / 6 / scale);
	public static final int playerSprite = (int) defaultSprite;
	public static final int bulletSprite = (int) defaultSprite;
	public static final int headTowerSprite = (int) (FPS / 4 / scale);
	public static final int holeSprite = defaultSprite;
	public static final int diamondSprite = (int) (FPS / 2 / scale);
	public static final int boomSprite = (int) (FPS / 1 / scale);
	public static final int starSprite = (int) (FPS / 5 / scale);
	
	public static final int defaultZ = 0;
	public static final int bulletZ = 2;
	public static final int boomZ = 2;
	public static final int diamondZ = defaultZ ;
	public static final int endpointZ = defaultZ;
	public static final int headtowerZ = defaultZ;
	public static final int holeZ = defaultZ;
	public static final int starZ = 1;
}
