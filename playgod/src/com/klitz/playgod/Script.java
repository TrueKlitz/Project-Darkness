package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class Script extends PlayGod{
	
	
	
	public Rectangle getPosition() {
		return position;
	}

	public void setPosition(Rectangle position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private Rectangle position;
	private String name;
	private String typ;
	private String content;
	
	public Script(Rectangle position_,String name_, String typ_,String content_) {
		position = position_;
		name = name_;
		typ = typ_;
		content = content_;
	}
	
	public void execute_onload(){
		
	}

}
