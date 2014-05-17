package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class ScriptOnTimer extends Script{

	private int max_time = 0;
	private int passed_time = 0;
	String l_script = this.getContent();
	
	public ScriptOnTimer(Rectangle position_, String name_, String typ_,
			String content_,Game game_) {
		super(position_, name_, typ_, content_, game_);
	}
	String[] aScript;
	public void load(){
		aScript = l_script.split(";");
		max_time = ( Integer.parseInt( aScript[0] )) * Game.getTICKSPERSECOND();
	}
	
	public void execute(){
		passed_time++;
		if(passed_time >= max_time){
			runScript(aScript);
		}
	}

}
