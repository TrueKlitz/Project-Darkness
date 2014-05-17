package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class ScriptOnLoad extends Script{

	public ScriptOnLoad(Rectangle position_, String name_, String typ_,String content_, Game game_) {
		super(position_, name_, typ_, content_, game_);
	}
	String l_script = this.getContent();
	public void execute(){
		
		String aScript[] = l_script.split(";");
		
		runScript(aScript);
	}
}
