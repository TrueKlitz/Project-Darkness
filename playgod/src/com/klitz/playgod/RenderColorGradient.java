package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Rectangle;

public class RenderColorGradient {
	
	Color oldColor;
	Color newColor;
	Texture tGradient;
	Game game;
	
	public RenderColorGradient (String content ,Game game_){
		/*oldColor = oldColor_;
		newColor = newColor_;
		tGradient = tGradient_;*/
		game = game_;
		
		String[] l_content = content.split(";");
		for(int i = 0; i < l_content.length ;i++){
			if(l_content[i].contains("new")){
				String [] l_newColor = l_content[i].split(" ");
				newColor = new Color( Float.parseFloat(l_newColor[1]) ,Float.parseFloat(l_newColor[2]) , Float.parseFloat(l_newColor[3]) , 1.0f);
			}
			if(l_content[i].contains("old")){
				String [] l_oldColor = l_content[i].split(" ");
				oldColor = new Color( Float.parseFloat(l_oldColor[1]) ,Float.parseFloat(l_oldColor[2]) , Float.parseFloat(l_oldColor[3]) , 1.0f);
			}
			if(l_content[i].contains("texture")){
				tGradient = new Texture(Gdx.files.internal("data/textures/"+ game.getLevel().tileset + "/" + l_content[i].split(" ")[1] + ".png"));
			}
			
		}
	}
	public Texture render(Texture renderSpace){
		Texture primary = renderSpace;
		TextureData tD = primary.getTextureData();
		for(int x = 0; x < primary.getWidth();x++){
			for(int y = 0; y < primary.getWidth();y++){
				
			}
		}
		
		return primary;
	}
}
