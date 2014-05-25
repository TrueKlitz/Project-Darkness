package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class Render{

	SpriteBatch batch;
	Game game;
	FrameBuffer fBuffer;
	public Render(SpriteBatch batch_, Game game_){
		batch = batch_;
		game = game_;
		fBuffer = new FrameBuffer(Format.RGB565,(int) (game.getW()) ,(int) (game.getH()), false);
		fBuffer.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	public void draw(){

		
		fBuffer.begin();
		for(int i = 0; i < game.getLevel().renderFieldAnim.length;i++){
			game.getLevel().renderFieldAnim[i].render();
		}
		batch.begin();
		for(int x = 0 ;x < game.getLevel().getWidth() ; x++){
			for(int y = 0 ;y < game.getLevel().getHeight() ; y++){
				
				float localPosX = (float) ( (x) * (Game.getTILESIZE()*game.getScale())) - game.getCamera().getCamPosX();
				float localPosY = (float) game.getH() -(y * (Game.getTILESIZE()*game.getScale())) - (Game.getTILESIZE()*game.getScale()) - game.getCamera().getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -Game.getTILESIZE()*4 && localPosX <= game.getW()+Game.getTILESIZE() && localPosY >= -Game.getTILESIZE()*4 && localPosY <= game.getH()+Game.getTILESIZE()){ 
					int l_layerPointer = game.getLevel().layer1[x][y];
					if(l_layerPointer >= 0){
						batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
					}
					l_layerPointer = game.getLevel().layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer ]  <=  game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}
					l_layerPointer = game.getLevel().layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer  ]  <=  game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}
					l_layerPointer = game.getLevel().layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer  ]   <=  game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}
				}
			}
		}	
			batch.draw(game.getPlayer().tRegion,(float) game.getPlayer().getPositionX()*game.getScale()*Game.getTILESIZE() - game.getCamera().getCamPosX() ,game.getH() - (float) game.getPlayer().getPositionY()*game.getScale()*Game.getTILESIZE() - game.getCamera().getCamPosY() ,32*game.getScale(),48*game.getScale());
		for(int x = 0 ;x < game.getLevel().getWidth() ;x++){
			for(int y = 0 ;y < game.getLevel().getHeight() ;y++){
				
				float localPosX = (float) ( (x) * (Game.getTILESIZE()*game.getScale())) - game.getCamera().getCamPosX();
				float localPosY = (float) game.getH() -(y * (Game.getTILESIZE()*game.getScale())) - (Game.getTILESIZE()*game.getScale()) - game.getCamera().getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -Game.getTILESIZE()*4 && localPosX <= game.getW()+Game.getTILESIZE() && localPosY >= -Game.getTILESIZE()*4 && localPosY <= game.getH()+Game.getTILESIZE()){ 
					
					int l_layerPointer = game.getLevel().layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer ]  >=  game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}
					l_layerPointer = game.getLevel().layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer  ]  >=  game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}
					l_layerPointer = game.getLevel().layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + game.getTextures().rTileHeight[ l_layerPointer  ]   >= game.getPlayer().getPositionY() + game.getPlayer().getHeight()){
							batch.draw(game.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, Game.getTILESIZE(), Game.getTILESIZE(), game.getScale(), game.getScale(), 0);
						}
					}

				}
				
			}
		}
		batch.end();
		fBuffer.end();
		PostProcess();
	}

	Texture tPostProcess;
	private void PostProcess(){

		tPostProcess = fBuffer.getColorBufferTexture();
		for(int i = 0 ; i < game.getLevel().renderGradient.length;i++){
			tPostProcess = game.getLevel().renderGradient[i].render(tPostProcess);
		}
		tPostProcess.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		batch.begin();
			batch.draw(tPostProcess, 0, 0, Gdx.graphics.getWidth()  , Gdx.graphics.getHeight() , 0, 0,(int) (game.getW() / game.getRenderQuality() ),(int) (game.getH() / game.getRenderQuality()) , false, true);
		batch.end();
	}
	public void onResize(){
		fBuffer = new FrameBuffer(Format.RGBA8888, game.getW(), game.getH(), false);
	}
}
