package com.klitz.playgod;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Input {
	
	ApplicationType appType;
	
	private boolean kLeft,kRight,kUp,kDown;
	Game game;
	
	public Input (ApplicationType at,Game game_){
		appType = at;
		game = game_;
	}
	
	public void update(){
		if(appType == ApplicationType.Desktop){
			if(Gdx.input.isKeyPressed(Keys.W)){kUp = true;}else {kUp = false;}
			if(Gdx.input.isKeyPressed(Keys.S)){kDown = true;}else {kDown = false;}
			if(Gdx.input.isKeyPressed(Keys.A)){kLeft = true;}else {kLeft = false;}
			if(Gdx.input.isKeyPressed(Keys.D)){kRight = true;}else {kRight = false;}
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){Gdx.app.exit();}
		}
		if(appType == ApplicationType.Android){
			update_android();
		}
	}
	
	private void update_android(){
		if (Gdx.input.getDeltaX() > 20){kRight = true; kLeft = false;}
		if (Gdx.input.getDeltaX() < -20){kLeft = true; kRight = false;}
		if (Gdx.input.getDeltaY() < -20){kUp = true; kDown = false;}
		if (Gdx.input.getDeltaY() > 20){kDown = true; kUp = false;}
		if (Gdx.input.getDeltaX() > 50){kRight = true; kLeft = false; kUp = false; kDown = false;}
		if (Gdx.input.getDeltaX() < -50){kRight = false; kLeft = true; kUp = false; kDown = false;}
		if (Gdx.input.getDeltaY() > 50){kRight = false; kLeft = false; kUp = false; kDown = true;}
		if (Gdx.input.getDeltaY() < -50){kRight = false; kLeft = false; kUp = true; kDown = false;}
		if(!Gdx.input.isTouched()){
			kLeft = false; kRight = false; kUp = false; kDown = false;
		}
	}
	public boolean iskLeft() {
		return kLeft;
	}

	public boolean iskRight() {
		return kRight;
	}

	public boolean iskUp() {
		return kUp;
	}

	public boolean iskDown() {
		return kDown;
	}

}
