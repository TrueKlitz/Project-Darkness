package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;
import com.klitz.playgod.Script;

public class ScriptOnTouch extends Script{

	public ScriptOnTouch(Rectangle position_, String name_, String typ_,
			String content_,PlayGod playgod_) {
		super(position_, name_, typ_, content_, playgod_ );
		
		playgod = playgod_;
		
	}
	String l_script = this.getContent();
	public void execute(){
		if(playgod.playerCollision(this.position.x , this.position.y , this.position.x+ this.position.width , this.position.y + this.position.height)){
			String aScript[] = l_script.split(";");
			
			runScript(aScript);
		}
	}

}
