package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class ScriptRender extends Script{

	String renderType;
	public ScriptRender(Rectangle position_, String name_, String typ_,String content_,PlayGod playgod_) {
		super(position_, name_, typ_, content_, playgod_);
	}
	String l_script = this.getContent();
	public void load(){
		String aScript[] = l_script.split(";");
		for(int i = 0 ; i  < aScript.length;i++){
			if(aScript[i].contains("type")){
				renderType = aScript[i].split(" ")[1];
			}
		}
	}
	public void execute(){
		
		String aScript[] = l_script.split(";");
		
		if(renderType.equals("color_change")){
			
		}
		
	}
}
