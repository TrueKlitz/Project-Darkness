package com.klitz.playgod;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Animator{
	
	private float positionX,positionY;
	private float movementSpeed = 3.0f;
	private int height = 0;
	private Game game;


	private int movementState;
	
	public Player(float posx,float posy, Texture playertexture, Game game_){
		super(playertexture , 32 , 48, 2);
		positionX = posx;
		positionY = posy;
		texture = playertexture;
		game = game_;
	} 
	
	public void AnimationUpdate(){
		
		this.animSpeed = (int)(9/movementSpeed);
		if( game.getInput().iskDown() ){this.setAnimationState(0);isAnimating = true;} 
		if( game.getInput().iskRight() ){this.setAnimationState(2);isAnimating = true;} 
		if( game.getInput().iskLeft() ){this.setAnimationState(1);isAnimating = true;}
		if( game.getInput().iskUp() ){this.setAnimationState(3);isAnimating = true;}
		if(!game.getInput().iskDown() && !game.getInput().iskUp() && !game.getInput().iskLeft() && !game.getInput().iskRight()){ isAnimating = false; } 
		
		this.setTextureRegion();
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
	
	boolean can_move = true;
	public void move(float l_playerNewX,float l_playerNewY, Level level){
		int l_playerNewXToInt = (int)(l_playerNewX);
		int l_playerNewYToInt = (int)(l_playerNewY);
		
		can_move = true;
		for(int x = l_playerNewXToInt -2 ;x <= l_playerNewXToInt + 2 ; x++){
			for(int y = l_playerNewYToInt -2 ;y <= l_playerNewYToInt + 2  ; y++){
				if(x >= 0 && x < level.getHeight() && y >= 0 && x < level.getWidth() ){
					if( 
						game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.01f, 
						x +0.0f, y +0.0f, x + 1.0f , y +1.0f )
					){
						if((level.collision[x][y] == 1)){
							if( game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, x + 0.25f, y + 0.25f, x + 0.75f , y + 0.75f )){
								can_move = false;
							}
						}
						if((level.collision[x][y] == 2)){
							can_move = false;
						}
						if((level.collision[x][y] == 5)){
							if( game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, x +0.0f, y + 0.75f, x + 1.0f , y + 0.85f )){
								can_move = false;
							}
						}
						if((level.collision[x][y] == 6)){
							if( game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, x +0.0f, y + 0.0f, x + 1.0f , y + 0.25f )){
								can_move = false;
							}
						}
						if((level.collision[x][y] == 7)){
							if( game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, x +0.0f, y + 0.0f, x + 0.25f , y + 1.0f )){
								can_move = false;
							}
						}
						if((level.collision[x][y] == 8)){
							if( game.boxCollision(l_playerNewX + 0.1f, l_playerNewY - 0.5f, l_playerNewX + 0.9f, l_playerNewY -0.05f, x + 0.75f, y + 0.0f, x + 1.0f , y + 1.0f )){
								can_move = false;
							}
						}
					}
				}
			}
		}
		if(can_move){
			if(l_playerNewX >= 0 && l_playerNewY >= 0 && l_playerNewX < level.getWidth() && l_playerNewY < level.getHeight()){
				setPositionX(l_playerNewX);
				setPositionY(l_playerNewY);
			}
		}
	}

}

