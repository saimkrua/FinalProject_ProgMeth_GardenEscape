package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import scene.StageGame.StageGame;
import tile.Tile;

public class RenderableHolder {

	private List<IRenderable> entities;
	private List<IRenderable> defaultEntities;
	private List<IRenderable> tiles;
	private Comparator<IRenderable> comparator;

	public static Image[] character = new Image[44];
	public static Tile[] tile = new Tile[150];
	public static Image[] Object = new Image[500];
	public static Map<String, AudioClip> audios = new HashMap<String, AudioClip>();;
	public static Font brokenConsole;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		defaultEntities = new ArrayList<IRenderable>();
		tiles = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static void loadResource() {

		brokenConsole = Font.loadFont(ClassLoader.getSystemResource("font/brokenConsoleRegular.ttf").toString(), 25);

		loadImageFromBigImageResize(character, 44, 44, 1, 0, "Player/girl"); // load character
		loadTile(); // load tiles
		loadObject(); // load objects
		loadAudio();

	}

	private static void loadAudio() {

		// click
		loadAudioFromURL("effect/click1");
		loadAudioFromURL("effect/click2");
		loadAudioFromURL("effect/click3");
		loadAudioFromURL("effect/click4");

		// collect
		loadAudioFromURL("effect/collect1");
		loadAudioFromURL("effect/collect2");

		// hit
		loadAudioFromURL("effect/hit1");
		loadAudioFromURL("effect/hit2");

		// hit
		loadAudioFromURL("effect/fall1");
		loadAudioFromURL("effect/fall2");

		// over
		loadAudioFromURL("effect/over1");

		// music
		loadAudioFromURL("stageselect");
		loadAudioFromURL("mainmenu");
		for (int i = 0; i < 2; i++) {
			loadAudioFromURL("stage/stage" + (i + 1));
		}

	}

	private static void loadAudioFromURL(String path) {

		AudioClip audioClip = new AudioClip(ClassLoader.getSystemResource("sound/" + path + ".wav").toString());
		audios.put(path, audioClip);
		System.out.println("load audio: " + path);
	}

	private static void loadObject() {
		// null
		Object[0] = null;
		// arrow
		loadImageFromURL(Object, 1, "Object/arrowup");
		loadImageFromURL(Object, 2, "Object/arrowdown");
		loadImageFromURL(Object, 3, "Object/arrowleft");
		loadImageFromURL(Object, 4, "Object/arrowright");

		// diamond
		loadImageFromBigImageResize(Object, 6, 3, 2, 5, "Object/diamond"); // 5-10

		// hole
		loadImageFromBigImageResize(Object, 3, 3, 1, 11, "Object/hole"); // 11-13

		// endpoint
		loadImageFromURL(Object, 14, "Object/endpoint");

		// from objectBig1
		loadImageFromBigImage(Object, 350, 16, 22, 15, 32, "Object/objectBig1"); // 15-364

		// fireball goright
		for (int i = 0; i < 14; i++) {
			loadImageFromURL(Object, 365 + i, "Object/fireball/x/1_" + i); // 365-378
		}
		// fireball godown
		for (int i = 0; i < 14; i++) {
			loadImageFromURL(Object, 379 + i, "Object/fireball/y/1_" + i); // 379-392
		}
		// explosion
		for (int i = 0; i < 12; i++) {
			loadImageFromURL(Object, 393 + i, "Object/explosion/" + (i + 1)); // 393-404
		}

		// headtower
		loadImageFromBigImageResize(Object, 34, 17, 2, 405, "Object/head/head1"); // 405-438 //red
		loadImageFromBigImageResize(Object, 34, 17, 2, 439, "Object/head/head2"); // 439-472 //green

		// star
//		for (int i = 0; i < 15; i++) {
//			loadImageFromURL(Object, 473 + i, "Object/star/star1/" + (i+1)); // 473-487
//		}
		loadImageFromBigImageResize(Object, 4, 4, 1, 473, "Object/star/star1"); // 473-476

		// trap
		loadImageFromBigImageResize(Object, 10, 10, 1, 477, "Object/trap/trap1"); // 477-486

	}

	public static void loadTile() {

		tile[0] = new Tile();
		tile[0].image = null;

		// grass
		loadTileFromURL(1, "grass", false);

		// water
		loadTileFromURL(2, "water", true);

		// bridge
		loadTileFromURL(3, "bridge", false);

		// fence
		loadTileFromURL(4, "fence1", true);
		loadTileFromURL(5, "fence2", true);
		loadTileFromURL(6, "fence3", true);

		loadTileFromBigImage(64, 8, 8, 34, "all", true);

		// ladder
		tile[53].collision = false;
		tile[54].collision = false;
		tile[61].collision = false;
		tile[62].collision = false;

		// hill
		loadTileFromBigImage(20, 5, 4, 14, "hill", true);

		// walkable hill
		loadTileFromBigImage(10, 5, 4, 98, "hill", false);
		loadTileFromURL(108, "hillright", false);
		loadTileFromURL(109, "hillleft", false);

		// tower
		loadTileFromBigImage(6, 3, 2, 110, "tower/tower1", true); // 110-115
		loadTileFromBigImage(6, 3, 2, 116, "tower/tower2", true); // 116-121

	}

	public static void loadTileFromURL(int index, String path, boolean collision) {

		tile[index] = new Tile();
		tile[index].image = new Image(ClassLoader.getSystemResource("image/tile/" + path + ".png").toString(),
				StageGame.tileSize, StageGame.tileSize, false, false);
		tile[index].collision = collision;
		System.out.println("load tile: "+path);
	}

	public static void loadTileFromBigImage(int number, int nCol, int nRow, int index, String path, boolean collision) {

		System.out.println("load tile: " + path);

		Image all = new Image(ClassLoader.getSystemResource("image/tile/" + path + ".png").toString(),
				StageGame.tileSize * nCol, StageGame.tileSize * nRow, false, false);
		int row = -1;
		int col = 0;
		for (int i = 0; i < number; i++) {
			col = i % nCol;
			if (col == 0)
				row++;
			tile[index + i] = new Tile();
			tile[index + i].collision = collision;
			tile[index + i].image = new WritableImage(all.getPixelReader(), col * StageGame.tileSize,
					row * StageGame.tileSize, StageGame.tileSize, StageGame.tileSize);
		}
	}

	public static void loadImageFromURL(Image[] array, int index, String path) {

		array[index] = new Image(ClassLoader.getSystemResource("image/" + path + ".png").toString(), StageGame.tileSize,
				StageGame.tileSize, false, false);
		System.out.println("load: "+path);
	}

	public static void loadImageFromBigImage(Image[] array, int number, int nCol, int nRow, int index, int size,
			String path) {

		Image all = new Image(ClassLoader.getSystemResource("image/" + path + ".png").toString());
		int row = -1;
		int col = 0;
		for (int i = 0; i < number; i++) {
			col = i % nCol;
			if (col == 0)
				row++;
			array[index + i] = new WritableImage(all.getPixelReader(), col * size, row * size, size, size);
		}

		System.out.println("load: " + path);

	}

	public static void loadImageFromBigImageResize(Image[] array, int number, int nCol, int nRow, int index,
			String path) {

		Image all = new Image(ClassLoader.getSystemResource("image/" + path + ".png").toString(),
				StageGame.tileSize * nCol, StageGame.tileSize * nRow, false, false);
		int row = -1;
		int col = 0;
		for (int i = 0; i < number; i++) {
			col = i % nCol;
			if (col == 0)
				row++;
//			System.out.println("col=" + col + " row=" + row);
			array[index + i] = new WritableImage(all.getPixelReader(), col * StageGame.tileSize,
					row * StageGame.tileSize, StageGame.tileSize, StageGame.tileSize);
		}

		System.out.println("load: " + path);

	}

	public void add(IRenderable entity) {
		System.out.println("add: " + entity.getClass());
		entities.add(entity);
		Collections.sort(entities, comparator);
		defaultEntities.add(entity);
		Collections.sort(defaultEntities, comparator);
	}

	public void addTile(IRenderable tile) {
		System.out.println("add: " + tile.getClass());
		tiles.add(tile);
		Collections.sort(tiles, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).update();
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public List<IRenderable> getTiles() {
		return tiles;
	}

	public void setDefault() {

		entities = new ArrayList<IRenderable>(defaultEntities);

	}
}
