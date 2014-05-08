package com.klitz.playgod;

import java.awt.Point;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class PlayGod implements ApplicationListener {

	private SpriteBatch batch;
	
	public Player player;
	public Textures textures;
	public Level level;
	public Camera camera;
	public Input input;

	private static float VIEWDISTANCE = 20.0f;
	private int w,h;
	private float scale;
	protected static int TILESIZE = 32;
	private long lastTime;
	private double deltaTime;
	
	ShapeRenderer srender ;
	
	public static int getTILESIZE() {
		return TILESIZE;
	}

	@Override
	public void create() {
		
		lastTime = System.nanoTime();
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		scale = (Gdx.graphics.getWidth()/VIEWDISTANCE)/TILESIZE;
		
		
		textures = new Textures();
		level = new Level();
		camera = new Camera(0,0);
		input = new Input(Gdx.app.getType());
		batch = new SpriteBatch();
		srender = new ShapeRenderer();

		level.load("data/level/test.tmx");
		
		textures.load();
		textures.loadTiles(level.tileset);
		
		
		player = new Player(16.0f,16.0f,textures.tPlayer);
		
		level.load_collision(textures.rTileCollision);
		
		for(int i = 0; i < level.script.length; i++){
			level.script[i].execute_onload();
		}
		Gdx.app.log(""+this, level.script[0].content +"" );
	}

	@Override
	public void dispose() {
		batch.dispose();
		textures.dispose();
	}

	
	@Override
	public void render() {	
		getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		scale = (Gdx.graphics.getWidth()/VIEWDISTANCE)/TILESIZE;
		
		//Updates
		camera.update( (int) ( player.getPositionX()*TILESIZE*scale -  (w/2 - 32) ), (int) ( player.getPositionY()*TILESIZE*scale - (h/2 + 48)),
				(int) ( level.getWidth()*TILESIZE*scale ),(int) ( level.getHeight()*TILESIZE*scale ),
				w, h);
		input.update();
		
		if(input.iskLeft()){
			player.move((float) (player.getPositionX() - (player.getMovementSpeed() * deltaTime) ), (float) (player.getPositionY()), level);
		}
		
		if(input.iskRight()){
			player.move((float) (player.getPositionX() + (player.getMovementSpeed() * deltaTime) ), (float) (player.getPositionY()), level);
		}
		if(input.iskUp()){
			player.move((float) (player.getPositionX()), (float) (player.getPositionY()  - (player.getMovementSpeed() * deltaTime)), level);
		}
		if(input.iskDown()){
			player.move((float) (player.getPositionX()), (float) (player.getPositionY()  + (player.getMovementSpeed() * deltaTime)), level);
		}
		//
		batch.begin();
		for(int x = 0 ;x < level.getWidth() ; x++){
			for(int y = 0 ;y < level.getHeight() ; y++){
				
				float localPosX = (float) ( (x) * (TILESIZE*scale)) - camera.getCamPosX();
				float localPosY = (float) h -(y * (TILESIZE*scale)) - (TILESIZE*scale) - camera.getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -TILESIZE*2 && localPosX <= w+TILESIZE && localPosY >= -TILESIZE*2 && localPosY <= h+TILESIZE){ 
					int l_layerPointer = level.layer1[x][y];
					if(l_layerPointer >= 0){
						batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
					}
					l_layerPointer = level.layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer ]  <=  player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}
					l_layerPointer = level.layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer  ]  <=  player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}
					l_layerPointer = level.layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer  ]   <=  player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}
				}
			}
		}
		batch.draw(player.getTexture(),(float) player.getPositionX()*scale*TILESIZE - camera.getCamPosX() ,h - (float) player.getPositionY()*scale*TILESIZE - camera.getCamPosY() ,32*scale,48*scale);
		for(int x = 0 ;x < level.getWidth() ;x++){
			for(int y = 0 ;y < level.getHeight() ;y++){
				
				float localPosX = (float) ( (x) * (TILESIZE*scale)) - camera.getCamPosX();
				float localPosY = (float) h -(y * (TILESIZE*scale)) - (TILESIZE*scale) - camera.getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -TILESIZE*2 && localPosX <= w+TILESIZE && localPosY >= -TILESIZE*2 && localPosY <= h+TILESIZE){ 
					
					int l_layerPointer = level.layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer ]  >=  player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}
					l_layerPointer = level.layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer  ]  >=  player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}
					l_layerPointer = level.layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + textures.rTileHeight[ l_layerPointer  ]   >= player.getPositionY() + player.getHeight()){
							batch.draw(textures.rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, TILESIZE, TILESIZE, scale, scale, 0);
						}
					}

				}
				
			}
		}
		for(int x = 0 ;x < level.getWidth() ; x++){
			for(int y = 0 ;y < level.getHeight() ; y++){
				
				float localPosX = (float) ( (x) * (TILESIZE*scale)) - camera.getCamPosX();
				float localPosY = (float) h -(y * (TILESIZE*scale)) - (TILESIZE*scale) - camera.getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -TILESIZE*2 && localPosX <= w+TILESIZE && localPosY >= -TILESIZE*2 && localPosY <= h+TILESIZE){
					if( (level.collision[x][y] == 2) ){
						srender.begin(ShapeType.Filled);
							srender.setColor(0,1, 0, 0.6f);
							srender.rect(localPosX, localPosY, TILESIZE*scale, TILESIZE*scale);
						srender.end();
					}
				}
			}
		}
		batch.end();
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

	private double consoleUpdate;
	
	private void getDeltaTime(){
		
		deltaTime = (System.nanoTime() - lastTime) / 1000000000.0;
		lastTime = System.nanoTime();
		
		consoleUpdate += deltaTime;
		
		if(consoleUpdate >= 1.0){
			Gdx.app.log(""+this, deltaTime*1000.0 + " ms per frame");
			Gdx.app.log(""+this, batch.renderCalls +  "");
			consoleUpdate = 0.0;
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		/*w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		scale = (Gdx.graphics.getWidth()/VIEWDISTANCE)/TILESIZE;*/
		batch = new SpriteBatch();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}