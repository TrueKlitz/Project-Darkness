package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends PlayGod{
	
	private float positionX,positionY;
	private float movementSpeed = 3.0f;
	private int height = 0;

	private Texture texture;
	private int movementState;
	
	public Player(float posx,float posy, Texture playertexture){
		positionX = posx;
		positionY = posy;
		texture = playertexture;
	} 
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMovementState() {
		return movementState;
	}

	public void setMovementState(int movementState) {
		this.movementState = movementState;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	public float getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	public void move(float l_playerNewX,float l_playerNewY, Level level){
		int l_playerNewXToInt = (int)(l_playerNewX);
		int l_playerNewYToInt = (int)(l_playerNewY);
		
		boolean can_move = true;
		for(int x = l_playerNewXToInt -2 ;x <= l_playerNewXToInt + 2 ; x++){
			for(int y = l_playerNewYToInt -2 ;y <= l_playerNewYToInt + 2  ; y++){
				if(x >= 0 && x < level.getHeight() && y >= 0 && x < level.getWidth() ){
					if( 
						boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, 
						x +0.0f, y +0.0f, x + 1.0f , y +1.0f )
					){
						if((level.collision[x][y] == 2)){
							can_move = false;
						}
					}
				}
			}
		}
		if(can_move){
			setPositionX(l_playerNewX);
			setPositionY(l_playerNewY);
		}
	}
}

