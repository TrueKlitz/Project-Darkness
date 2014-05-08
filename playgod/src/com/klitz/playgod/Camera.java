package com.klitz.playgod;

public class Camera {
	
	private int camPosX,camPosY;

	public Camera(int camX,int camY){
		camPosX = camX;
		camPosY = camY;
	}

	public int getCamPosX() {
		return camPosX;
	}

	public void setCamPosX(int camPosX) {
		this.camPosX = camPosX;
	}

	public int getCamPosY() {
		return camPosY;
	}

	public void setCamPosY(int camPosY) {
		this.camPosY = camPosY;
	}
	
	public void update(int pointerX,int pointerY,int maxX,int maxY,int screenSizeX,int screenSizeY){
		if( pointerX <= maxX - screenSizeX && pointerX >= 0){
			camPosX = pointerX;
		}
		if( pointerY <= maxY - screenSizeY && pointerY >= 0 ){
			camPosY = -pointerY;
		}
		if( (pointerX + screenSizeX) >= maxX){
			camPosX = maxX-screenSizeX;
		}
		if( (pointerY + screenSizeY) >= maxY){
			camPosY =  -(maxY - screenSizeY) ;
		}
		if(pointerX <= 0){
			camPosX = 0;
		}
		if(pointerY <= 0){
			camPosY = 0;
		}
	}
	
}
