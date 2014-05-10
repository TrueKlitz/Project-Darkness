package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class ScriptRender extends Script{

	public ScriptRender(Rectangle position_, String name_, String typ_,String content_,PlayGod playgod_) {
		super(position_, name_, typ_, content_, playgod_);
	}
	String l_script = this.getContent();
	public void execute(){
		
		String aScript[] = l_script.split(";");
		
		runScript(aScript);
	}
}
