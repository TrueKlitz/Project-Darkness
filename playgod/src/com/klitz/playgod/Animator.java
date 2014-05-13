package com.klitz.playgod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
	
	/*
	 * The Animator switches the part of the texture being drawed.
	 * tileHeight and width must be a power of 2 !
	 */
	private int animationState = 0;
	
	private int maxAnimStates = 0;
	TextureRegion tRegion;
	Texture texture;
	int tileHeight, tileWidth, animSpeed, currentAnimSpeed, currentAnimState;
	boolean isAnimating;
	public Animator(Texture texture_ , int height_ , int width_, int animSpeed_){
		texture = texture_;
		tileHeight = height_;
		tileWidth = width_;
		maxAnimStates = (texture.getWidth() / tileWidth);
		animSpeed = animSpeed_;
		tRegion = new TextureRegion(texture,0,0,tileHeight,tileWidth);
	}
	
	public float getAnimationState() {
		return animationState;
	}
	
	public void setAnimationState(int animationState) {
		this.animationState = animationState;
	}
	public void setTextureRegion(){
		currentAnimSpeed ++;
		if(currentAnimSpeed >= animSpeed && isAnimating){
			currentAnimState++;
			tRegion = new TextureRegion(this.texture , currentAnimState * tileHeight , tileWidth * animationState , tileHeight, tileWidth);
			currentAnimSpeed = 0;
			if(currentAnimState > maxAnimStates){
				currentAnimState = 0;
			}
		}
		if(!isAnimating){
			tRegion = new TextureRegion(this.texture , 0 , tileWidth * animationState , tileHeight, tileWidth);
		}
	}
}
