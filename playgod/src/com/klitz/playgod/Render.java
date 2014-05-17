package com.klitz.playgod;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render{

	SpriteBatch batch;
	PlayGod playgod;
	public Render(SpriteBatch batch_, PlayGod playgod_){
		batch = batch_;
		playgod = playgod_;
	}
	public void draw(){
		batch.begin();
		for(int x = 0 ;x < playgod.getLevel().getWidth() ; x++){
			for(int y = 0 ;y < playgod.getLevel().getHeight() ; y++){
				
				float localPosX = (float) ( (x) * (PlayGod.getTILESIZE()*playgod.getScale())) - playgod.getCamera().getCamPosX();
				float localPosY = (float) playgod.getH() -(y * (PlayGod.getTILESIZE()*playgod.getScale())) - (PlayGod.getTILESIZE()*playgod.getScale()) - playgod.getCamera().getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -PlayGod.getTILESIZE()*4 && localPosX <= playgod.getW()+PlayGod.getTILESIZE() && localPosY >= -PlayGod.getTILESIZE()*4 && localPosY <= playgod.getH()+PlayGod.getTILESIZE()){ 
					int l_layerPointer = playgod.getLevel().layer1[x][y];
					if(l_layerPointer >= 0){
						batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
					}
					l_layerPointer = playgod.getLevel().layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer ]  <=  playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}
					l_layerPointer = playgod.getLevel().layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer  ]  <=  playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}
					l_layerPointer = playgod.getLevel().layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer  ]   <=  playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}
				}
			}
		}	
			batch.draw(playgod.getPlayer().tRegion,(float) playgod.getPlayer().getPositionX()*playgod.getScale()*PlayGod.getTILESIZE() - playgod.getCamera().getCamPosX() ,playgod.getH() - (float) playgod.getPlayer().getPositionY()*playgod.getScale()*PlayGod.getTILESIZE() - playgod.getCamera().getCamPosY() ,32*playgod.getScale(),48*playgod.getScale());
		for(int x = 0 ;x < playgod.getLevel().getWidth() ;x++){
			for(int y = 0 ;y < playgod.getLevel().getHeight() ;y++){
				
				float localPosX = (float) ( (x) * (PlayGod.getTILESIZE()*playgod.getScale())) - playgod.getCamera().getCamPosX();
				float localPosY = (float) playgod.getH() -(y * (PlayGod.getTILESIZE()*playgod.getScale())) - (PlayGod.getTILESIZE()*playgod.getScale()) - playgod.getCamera().getCamPosY();
				/*
				 * Render alle Objekte , deren Koordinaten im sichtbaren Bereich liegen. (alle anderen nicht)
				 */
				if(localPosX >= -PlayGod.getTILESIZE()*4 && localPosX <= playgod.getW()+PlayGod.getTILESIZE() && localPosY >= -PlayGod.getTILESIZE()*4 && localPosY <= playgod.getH()+PlayGod.getTILESIZE()){ 
					
					int l_layerPointer = playgod.getLevel().layer2[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer ]  >=  playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}
					l_layerPointer = playgod.getLevel().layer3[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer  ]  >=  playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}
					l_layerPointer = playgod.getLevel().layer4[x][y];
					if(l_layerPointer >= 0){
						if(y + playgod.getTextures().rTileHeight[ l_layerPointer  ]   >= playgod.getPlayer().getPositionY() + playgod.getPlayer().getHeight()){
							batch.draw(playgod.getTextures().rTile[ l_layerPointer ], localPosX , localPosY, 0, 0, PlayGod.getTILESIZE(), PlayGod.getTILESIZE(), playgod.getScale(), playgod.getScale(), 0);
						}
					}

				}
				
			}
		}
		batch.end();
	}
}
