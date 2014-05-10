package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Level extends PlayGod{
	
	XmlReader reader = new XmlReader();
	
	private int width,height;

	public String tileset;
	public short[][] layer1;
	public short[][] layer2;
	public short[][] layer3;
	public short[][] layer4;
	public short[][] collision;
	public Script[] script_master;
	public Script[] script_onload;
	public Script[] script_ontouch;
	public Script[] script_render;
	public Script[] script_ontimer;

	/*
	 *Minimale Levelgröße : 32x32 ,damit Kamera richtig funktioniert!
	 */
	
	public void load(String level){
		FileHandle handle = Gdx.files.internal(level);
		Element root;
		try {
			root = reader.parse(handle);
		
			width = root.getChild(1).getIntAttribute("width");
			height = root.getChild(1).getIntAttribute("height");
			
			tileset = root.getChild(0).getChild(0).getAttribute("source");
			tileset = tileset.substring(12, tileset.length()-8);
			Gdx.app.log(""+this,tileset  + " is the used tileset");
			//Layer Ground
			layer1 = new short[width][height];
			String layer1String = root.getChild(1).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			layer1String = layer1String.replace('\t', '\0');
			layer1String = layer1String.replace('\n', '\0');
			layer1String = layer1String.replace('\r', '\0');
			layer1String = layer1String.replaceAll("\r\n", "");
			layer1String = layer1String.replaceAll("\0", "");
			layer1String = layer1String.replaceAll("", "");
			layer1String = layer1String.replaceAll(" ", "");
			
			String[] layer1Temp = new String[ width*height ];
			layer1Temp = layer1String.split(",");
			for(int i = 0 ; i < width*height ;i++){
				layer1[i % width][ i / width ] =(short) (Short.parseShort( layer1Temp[i])  - 1);
			}
			
			//Layer Detail 1
			layer2 = new short[width][height];
			String layer2String = root.getChild(2).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			layer2String = layer2String.replace('\t', '\0');
			layer2String = layer2String.replace('\n', '\0');
			layer2String = layer2String.replace('\r', '\0');
			layer2String = layer2String.replaceAll("\r\n", "");
			layer2String = layer2String.replaceAll("\0", "");
			layer2String = layer2String.replaceAll("", "");
			layer2String = layer2String.replaceAll(" ", "");
			
			String[] layer2Temp = new String[ width*height ];
			layer2Temp = layer2String.split(",");
			for(int i = 0 ; i < width*height ;i++){
				layer2[i % width][ i / width ] =(short) (Short.parseShort( layer2Temp[i])  - 1);
			}
			
			//Layer Detail 2
			layer3 = new short[width][height];
			String layer3String = root.getChild(3).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			layer3String = layer3String.replace('\t', '\0');
			layer3String = layer3String.replace('\n', '\0');
			layer3String = layer3String.replace('\r', '\0');
			layer3String = layer3String.replaceAll("\r\n", "");
			layer3String = layer3String.replaceAll("\0", "");
			layer3String = layer3String.replaceAll("", "");
			layer3String = layer3String.replaceAll(" ", "");
			
			String[] layer3Temp = new String[ width*height ];
			layer3Temp = layer3String.split(",");
			for(int i = 0 ; i < width*height ;i++){
				layer3[i % width][ i / width ] =(short) (Short.parseShort( layer3Temp[i])  - 1);
			}
			
			//Layer Detail 3
			layer4 = new short[width][height];
			String layer4String = root.getChild(4).getChild(0).getText(); // Zweites Kind , Erstes Kind (1,0) , Inhalt	
			
			layer4String = layer4String.replace('\t', '\0');
			layer4String = layer4String.replace('\n', '\0');
			layer4String = layer4String.replace('\r', '\0');
			layer4String = layer4String.replaceAll("\r\n", "");
			layer4String = layer4String.replaceAll("\0", "");
			layer4String = layer4String.replaceAll("", "");
			layer4String = layer4String.replaceAll(" ", "");
			
			String[] layer4Temp = new String[ width*height ];
			layer4Temp = layer4String.split(",");
			for(int i = 0 ; i < width*height ;i++){
				layer4[i % width][ i / width ] =(short) (Short.parseShort( layer4Temp[i])  - 1);
			}
			
			layer1String = null;
			layer2String = null;
			layer3String = null;
			layer4String = null;
			layer1Temp = null;
			layer2Temp = null;
			layer3Temp = null;
			layer4Temp = null;
			root = null;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		load_scripts(level);
	}

	public void load_collision(short[] texture_data){
		collision = new short[width][height];
		for(int x = 0; x < width;x++){
			for(int y = 0; y < height;y++){
				collision[x][y] = 0;
				if( layer1[x][y] >= 0 && texture_data[ layer1[x][y] ] == 2){
					collision[x][y] = 2;
				}
				if( layer2[x][y] >= 0 &&  texture_data[ layer2[x][y] ] == 2){
					collision[x][y] = 2;
				}
				if( layer3[x][y] >= 0 &&  texture_data[ layer3[x][y] ] == 2){
					collision[x][y] = 2;
				}
				if( layer4[x][y] >= 0 &&  texture_data[ layer4[x][y] ] == 2){
					collision[x][y] = 2;
				}
			}
		}

	}

	public void load_scripts(String level){
		FileHandle handle = Gdx.files.internal(level);
		Element root;
		try {
			root = reader.parse(handle);

			
			
			Element scripts = root.getChildByName("objectgroup");
			
			script_master = new Script[ scripts.getChildCount() ];
			for(int i = 0; i < scripts.getChildCount();i++){
				Element l_script = scripts.getChild(i);
				Rectangle r_ = new Rectangle();
				r_.x = l_script.getIntAttribute("x") / TILESIZE;
				r_.y = l_script.getIntAttribute("y") / TILESIZE;
				r_.width = l_script.getIntAttribute("width") / TILESIZE;
				r_.height = l_script.getIntAttribute("height") / TILESIZE;
				String content = "";
				for(int ii = 0;ii < l_script.getChildByName("properties").getChildCount();ii++){
					content = content + l_script.getChildByName("properties").getChild(ii).getAttribute("value") + ";";
				}
				script_master[i] = new Script( r_ , l_script.getAttribute("name"), l_script.getAttribute("type"), content );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		int l_ScriptOL = 0;
		int l_ScriptOTO = 0;
		int l_ScriptOTI = 0;
		int l_ScriptR = 0;
		for(int i = 0; i < script_master.length ; i++){
			Gdx.app.log(""+this, script_master[i].getTyp()+"");
			if(script_master[i].getTyp().equals("on_load") ){
				l_ScriptOL++;
			}
			if(script_master[i].getTyp().equals("on_touch") ){
				l_ScriptOTO++;
			}
			if(script_master[i].getTyp().equals("on_timer") ){
				l_ScriptOTI++;
			}
			if(script_master[i].getTyp().equals("render") ){
				l_ScriptR++;
			}
		}
		
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
