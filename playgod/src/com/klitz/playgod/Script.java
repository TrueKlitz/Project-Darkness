package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class Script extends PlayGod{
	
	
	
	private Rectangle position;
	private String name;
	private String typ;
	public String content;
	public Script(Rectangle position_,String name_, String typ_,String content_) {
		position = position_;
		name = name_;
		typ = typ_;
		content = content_;
	}
	
	public void execute_onload(){
		
	}

}
