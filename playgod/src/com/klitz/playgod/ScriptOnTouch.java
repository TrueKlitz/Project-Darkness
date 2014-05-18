package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class ScriptOnTouch extends Script{

	private boolean reUse = false;
	private int uses = 0;
	public ScriptOnTouch(Rectangle position_, String name_, String typ_,
			String content_,Game game_) {
		super(position_, name_, typ_, content_, game_ );
		
		game = game_;
		
		
	}
	String[] aScript;
	public void load(){
		aScript = l_script.split(";");
		for(int i = 0; i < aScript.length;i++){
			if(aScript[i].contains("reuse")){
				reUse = true;
			}
		}
	}
	String l_script = this.getContent();
	public void execute(){
		if(uses >= 1 && reUse){
			if(game.playerCollision(this.position.x , this.position.y , this.position.x+ this.position.width , this.position.y + this.position.height)){
				runScript(aScript);
				uses++;
			}
		}else{
			if(game.playerCollision(this.position.x , this.position.y , this.position.x+ this.position.width , this.position.y + this.position.height) && uses == 0){
				runScript(aScript);
				uses++;
			}
		}
	}

}
