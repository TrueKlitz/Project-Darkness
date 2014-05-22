package com.klitz.playgod;

import com.badlogic.gdx.math.Rectangle;

public class Script{
	
	
	
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

	public Rectangle position;
	private String name;
	private String typ;
	private String content;
	
	public Game game;
	
	public Script(Rectangle position_,String name_, String typ_,String content_,Game game_) {
		position = position_;
		name = name_;
		typ = typ_;
		content = content_;
		game = game_;
	}
	
	public void runScript(String[] commando){
		/*
		 * Command Types:
		 * "teleport_player [x] [y]" teleports the player to the destination [x|y] !
		 * "if [condition] [command] " checks for an condition ?
		 * "load_level [level_name] " loads a new level ?
		 * "gamespeed [tickspersecond]" changes game speed 
		 */
		for(int i = 0; i < commando.length;i++){
			if(commando[i].contains("if")){
				
			}
			if(commando[i].contains("load_level")){
				game.load_level(commando[i].split(" ")[1]);
			}
			if(commando[i].contains("teleport_player")){
				float l_x = Float.parseFloat(commando[i].split(" ")[1]);
				float l_y = Float.parseFloat(commando[i].split(" ")[1]);
				game.getPlayer().setPositionX( l_x );
				game.getPlayer().setPositionY( l_y );
			}
			if(commando[i].contains("gamespeed")){
				Game.setTICKSPERSECOND( Integer.parseInt( commando[i].split(" ")[1] ));
			}
			if(commando[i].contains("change_tile")){
				String[] l_comando = commando[i].split(" ");
					if(l_comando[1].equals("1")){
						game.getLevel().layer1[(int) position.x ][(int) position.y ] = (short) (Integer.parseInt(l_comando[2]));
					}
					if(l_comando[1].equals("2")){
						game.getLevel().layer2[(int) position.x ][(int) position.y ] = (short) (Integer.parseInt(l_comando[2]));
					}
					if(l_comando[1].equals("3")){
						game.getLevel().layer3[(int) position.x ][(int) position.y ] = (short) (Integer.parseInt(l_comando[2]));
					}
					if(l_comando[1].equals("4")){
						game.getLevel().layer4[(int) position.x ][(int) position.y ] = (short) (Integer.parseInt(l_comando[2]));
					}
			}
		}
	}

}
