package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RenderFieldAnimation {

	String content;
	Game game;
	Rectangle position;
	SpriteBatch sB;
	ShaderProgram shader;
	Texture tGround;
	Texture tOverlay;
	Vector2 flow;
	Vector2 flow_ground;
	
	public RenderFieldAnimation(String content_ ,Rectangle position_,Game game_){
		content = content_;
		position = position_;
		game = game_;
		sB = new SpriteBatch();
		
		flow = new Vector2();
		flow_ground = new Vector2(0.0f,0.0f);
		
		String[] l_content = content.split(";");
		for(int i = 0; i < l_content.length ;i++){
			if(l_content[i].contains("texture1")){
				tGround = new Texture(Gdx.files.internal("data/textures/"+ game.getLevel().tileset + "/" + l_content[i].split(" ")[1] + ".png"));
				tGround.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			}
			if(l_content[i].contains("texture2")){
				tOverlay = new Texture(Gdx.files.internal("data/textures/"+ game.getLevel().tileset + "/" + l_content[i].split(" ")[1] + ".png"));
				tOverlay.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			}
			if(l_content[i].contains("flow")){
				flow.x = Float.parseFloat(l_content[i].split(" ")[1]);
				flow.y = Float.parseFloat(l_content[i].split(" ")[2]);
			}
		}
		shader = new ShaderProgram(Gdx.files.internal("shaders/field_anim.vsh"),Gdx.files.internal("shaders/field_anim.fsh"));
		sB.setShader(shader);
		
		shader.begin();
			shader.setUniformi("u_overlay", 3);
		shader.end();
		
		Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE3);
		tOverlay.bind();
		Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
	}
	
	
	
	public void render(){
		
		flow_ground.x += flow.x * game.getDeltaTime();
		flow_ground.y += flow.y * game.getDeltaTime();
		
		shader.begin();
			shader.setUniformf("u_flow", flow_ground);
		shader.end();
		
		float localPosX = (float) ( (position.x ) * (Game.getTILESIZE()*game.getScale())) - game.getCamera().getCamPosX();
		float localPosY = (float) game.getH() -((position.y ) * (Game.getTILESIZE()*game.getScale())) - (Game.getTILESIZE()*game.getScale()) - game.getCamera().getCamPosY();
		
		sB.begin();

			sB.draw(tGround,  localPosX, localPosY , position.width * (game.getTILESIZE()*game.getScale()), position.height * (game.getTILESIZE()*game.getScale()) );
		sB.end();
			
	}
}
