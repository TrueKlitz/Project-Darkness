package com.klitz.playgod;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render{

	SpriteBatch batch;
	Game game;
	public Render(SpriteBatch batch_, Game game_){
		batch = batch_;
		game = game_;
	}
	public void draw(){
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
	}
}
