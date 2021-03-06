package com.klitz.playgod;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener {

	private SpriteBatch batch;
	
	private Player player;
	private Textures textures;
	private Level level;
	private float gameSpeed;
	private Camera camera;
	private Input input;
	private Render render;

	protected static float VIEWDISTANCE = 16.0f;
	protected static int TICKSPERSECOND = 20; // 20 is normal
	protected static int TILESIZE = 32;
	
	private int w;
	private int h;
	private float renderQuality = 1.0f; // Must be between 1 and 2 ( 1 = best , 2 = worst)
	
	
	private float scale;
	private long lastTime;
	private double deltaTime;
	private double consoleUpdate;
	private double gametick;
	

	@Override
	public void create() {
		lastTime = System.nanoTime();
		
		w =(int) (Gdx.graphics.getWidth() / renderQuality);
		h =(int) (Gdx.graphics.getHeight() / renderQuality ) ;

		scale = (Gdx.graphics.getWidth()/VIEWDISTANCE)/TILESIZE;
		
		textures = new Textures();
		level = new Level(this);
		camera = new Camera(0,0);
		input = new Input(Gdx.app.getType(), this);
		batch = new SpriteBatch();
		render = new Render(batch, this);
		
		textures.load();
		player = new Player(16.0f,16.0f,textures.tPlayer,this);
		load_level("city");

		GameTick();
	}

	@Override
	public void dispose() {
		batch.dispose();
		textures.dispose();
		player.dispose();
	}

	int test;
	@Override
	public void render() {	
		test++;
		DeltaTime();
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL10.GL_COLOR_BUFFER_BIT);
		scale = (Gdx.graphics.getWidth()/VIEWDISTANCE)/TILESIZE;
		
		//Updates
		camera.update( (int) ( player.getPositionX()*TILESIZE*scale -  (w/2 - 32) ), (int) ( player.getPositionY()*TILESIZE*scale - (h/2 + 48)),
				(int) ( level.getWidth()*TILESIZE*scale ),(int) ( level.getHeight()*TILESIZE*scale ),
				w, h);
		input.update();
		
		if(input.iskLeft()){
			player.move((float) (player.getPositionX() - (player.getMovementSpeed() * gameSpeed) ), (float) (player.getPositionY()), level);
		}
		
		if(input.iskRight()){
			player.move((float) (player.getPositionX() + (player.getMovementSpeed() * gameSpeed) ), (float) (player.getPositionY()), level);
		}
		if(input.iskUp()){
			player.move((float) (player.getPositionX()), (float) (player.getPositionY()  - (player.getMovementSpeed() * gameSpeed)), level);
		}
		if(input.iskDown()){
			player.move((float) (player.getPositionX()), (float) (player.getPositionY()  + (player.getMovementSpeed() * gameSpeed)), level);
		}
		//
		render.draw();
		
	}
	
	public void load_level(String levelname){
		String l_level = "data/level/"+ levelname +".tmx";
		level.load(l_level);
		textures.loadTiles(level.tileset);
		level.load_collision(textures.rTileCollision);
		for(int ii = 0; ii < level.script_onload.length; ii++){
			level.script_onload[ii].execute();
		}
		for(int ii = 0; ii < level.script_ontimer.length; ii++){
			level.script_ontimer[ii].load();
		}
		for(int ii = 0; ii < level.script_ontouch.length; ii++){
			level.script_ontouch[ii].load();
		}
	}
	public boolean boxCollision
	(
		float object_1_x, float object_1_y, float object_1_endx, float object_1_endy,
		float object_2_x, float object_2_y, float object_2_endx, float object_2_endy
	)
	{
		if(
		(object_2_x < object_1_endx) && 
		(object_2_endx > object_1_x) && 
		(object_2_endy > object_1_y) && 
		(object_2_y  < object_1_endy)
		)
		{
			return true;
		}else{
			return false;
		}
	}
	
	public boolean playerCollision
	(
		float object_2_x, float object_2_y, float object_2_endx, float object_2_endy
	)
	{
		if(
		(object_2_x < player.getPositionX() + 0.8f) && 
		(object_2_endx > player.getPositionX() + 0.2f ) && 
		(object_2_endy > player.getPositionY()  - 0.5f) && 
		(object_2_y  < player.getPositionY() + 0.0f)
		)
		{
			return true;
		}else{
			return false;
		}
	}
	
	private void DeltaTime(){

		deltaTime = (System.nanoTime() - lastTime) / 1000000000.0;
		lastTime = System.nanoTime();
		gameSpeed = (float) (deltaTime * TICKSPERSECOND)/20.0f;
		consoleUpdate += deltaTime;
		gametick += deltaTime;
		if(consoleUpdate >= 1.0){
			Gdx.app.log(""+this, deltaTime*1000.0 + " ms per frame");;
			consoleUpdate = 0.0;
		}
		if(gametick >= (1.0 / TICKSPERSECOND) ){
			GameTick();
			gametick = 0.0;
		}
	}
	
	private void GameTick(){
		for(int i = 0; i < level.script_ontouch.length; i++){
			level.script_ontouch[i].execute();
		}
		for(int i = 0; i < level.script_ontimer.length; i++){
			level.script_ontimer[i].execute();
		}
		for(int i = 0; i < level.renderGradient.length; i++){
			level.renderGradient[i].tickUpdate();
		}
		player.AnimationUpdate();
	}
	
	@Override
	public void resize(int width, int height) {
		scale = (w/VIEWDISTANCE)/TILESIZE;
		batch = new SpriteBatch();
		render.onResize();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public static int getTILESIZE() {
		return TILESIZE;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Textures getTextures() {
		return textures;
	}

	public Level getLevel() {
		return level;
	}
	
	public static int getTICKSPERSECOND() {
		return TICKSPERSECOND;
	}

	public static void setTICKSPERSECOND(int tICKSPERSECOND) {
		TICKSPERSECOND = tICKSPERSECOND;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public float getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(float gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}

	public static float getVIEWDISTANCE() {
		return VIEWDISTANCE;
	}

	public static void setVIEWDISTANCE(float vIEWDISTANCE) {
		VIEWDISTANCE = vIEWDISTANCE;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}

	public double getConsoleUpdate() {
		return consoleUpdate;
	}

	public void setConsoleUpdate(double consoleUpdate) {
		this.consoleUpdate = consoleUpdate;
	}

	public double getL_gametick() {
		return gametick;
	}

	public void setL_gametick(double l_gametick) {
		this.gametick = l_gametick;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setTextures(Textures textures) {
		this.textures = textures;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public static void setTILESIZE(int tILESIZE) {
		TILESIZE = tILESIZE;
	}

	public float getRenderQuality() {
		return renderQuality;
	}

	public void setRenderQuality(float renderQuality) {
		this.renderQuality = renderQuality;
	}
}
