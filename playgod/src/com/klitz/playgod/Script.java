package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
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
	
	public void runScript(String[] commandos){
		for(int i = 0; i < commandos.length;i++){
			Gdx.app.log(""+this, commandos[i] +  " ");
			if(commandos[i].contains("teleport_player")){
				float l_x = Float.parseFloat(commandos[i].split(" ")[1]);
				float l_y = Float.parseFloat(commandos[i].split(" ")[1]);
				PlayGod.player.setPositionX( l_x );
				PlayGod.player.setPositionY( l_y );
			}
		}
	}

}
