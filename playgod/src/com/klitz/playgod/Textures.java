package com.klitz.playgod;

import java.io.IOException;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;


public class Textures {
	
	public Texture tPlayer ;
	public TextureRegion[] rTile = new TextureRegion[0];
	public byte[] rTileHeight = new byte[0];
	public short[] rTileCollision = new short[0];
	
	private int tilesize;
	
	XmlReader reader = new XmlReader();
	
	public Textures(){
		
		tilesize = PlayGod.getTILESIZE();
	}
	
	public void load(){
		
		tPlayer = new Texture(Gdx.files.internal("data/textures/player_test.png"));
		
	}
	
	public void loadTiles(String tileset_name){
		Texture tTileset = new Texture(Gdx.files.internal("data/textures/"+tileset_name+"/img.png"));
		tTileset.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		rTile = new TextureRegion[( tTileset.getWidth() / tilesize ) * ( tTileset.getHeight() / tilesize )];
		for(int x = 0;x < ( tTileset.getWidth() / tilesize );x++){
			for(int y = 0;y < (tTileset.getHeight() / tilesize );y++){
				rTile[y *  ( tTileset.getWidth() / tilesize ) + x] = new TextureRegion(tTileset,x*tilesize,y*tilesize,tilesize,tilesize);
			}
		}
		FileHandle handle = Gdx.files.internal("data/textures/"+tileset_name+"/data.tmx");
		Element root;
		try {
			root = reader.parse(handle);
		
			int width = root.getChild(3).getIntAttribute("width");
			int height = root.getChild(3).getIntAttribute("height");
			
			//Layer Height
			rTileHeight = new byte[width*height];
			String l_heightString = root.getChild(4).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			l_heightString = l_heightString.replace('\t', '\0');
			l_heightString = l_heightString.replace('\n', '\0');
			l_heightString = l_heightString.replace('\r', '\0');
			l_heightString = l_heightString.replaceAll("\r\n", "");
			l_heightString = l_heightString.replaceAll("\0", "");
			l_heightString = l_heightString.replaceAll("", "");
			l_heightString = l_heightString.replaceAll(" ", "");
			
			String[] layer1Temp = new String[ width*height ];
			int lsubstractHeight = width*height + 1 ;
			layer1Temp = l_heightString.split(",");
			for(int i = 0 ; i < width*height ;i++){
				rTileHeight[i] = (byte) (Short.parseShort( layer1Temp[i]) - lsubstractHeight );
			}
			
			//Layer Collision
			rTileCollision = new short[width*height];
			String l_CollisionString = root.getChild(5).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			l_CollisionString  = l_CollisionString.replace('\t', '\0');
			l_CollisionString  = l_CollisionString.replace('\n', '\0');
			l_CollisionString  = l_CollisionString.replace('\r', '\0');
			l_CollisionString  = l_CollisionString.replaceAll("\r\n", "");
			l_CollisionString  = l_CollisionString.replaceAll("\0", "");
			l_CollisionString  = l_CollisionString.replaceAll("", "");
			l_CollisionString  = l_CollisionString.replaceAll(" ", "");
			
			String[] collisionTemp = new String[ width*height ];
			int lsubstractCollision = width*height + 9;
			collisionTemp = l_CollisionString.split(",");
			for(int i = 0 ; i < width*height ;i++){
				rTileCollision[i] = (short) (Short.parseShort( collisionTemp[i] ) - lsubstractCollision + 1 /* +1 um der "-1 Falle" zu entgehen :D */);
			}
			rTileCollision[0] = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void dispose(){
		tPlayer.dispose();
	}
}
