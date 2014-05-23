package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;


public class RenderColorGradient {
	
	Color oldColor;
	Color newColor;
	Texture tGradient;
	Game game;
	ShaderProgram shader;
	SpriteBatch sB;
	FrameBuffer fBuffer;
	
    
	public RenderColorGradient (String content ,Game game_){
		game = game_;
		
		String[] l_content = content.split(";");
		for(int i = 0; i < l_content.length ;i++){
			if(l_content[i].contains("new")){
				String [] l_newColor = l_content[i].split(" ");
				newColor = new Color( Float.parseFloat(l_newColor[1]) ,Float.parseFloat(l_newColor[2]) , Float.parseFloat(l_newColor[3]) , 1.0f);
			}
			if(l_content[i].contains("old")){
				String [] l_oldColor = l_content[i].split(" ");
				oldColor = new Color( Float.parseFloat(l_oldColor[1]) ,Float.parseFloat(l_oldColor[2]) , Float.parseFloat(l_oldColor[3]) , 1.0f);
			}
			if(l_content[i].contains("texture")){
				tGradient = new Texture(Gdx.files.internal("data/textures/"+ game.getLevel().tileset + "/" + l_content[i].split(" ")[1] + ".png"));
				tGradient.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			}
			
		}
		sB = new SpriteBatch();
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/color_gradient.vsh"),Gdx.files.internal("shaders/color_gradient.fsh"));
		Gdx.app.log(""+this, shader.isCompiled() ? " shader is compiled" : shader.getLog());
		sB.setShader(shader);
		shader.begin();
		shader.setUniformf("u_oldcolor", oldColor.r, oldColor.g, oldColor.b, 1.0f);
		shader.setUniformf("u_newcolor", newColor.r, newColor.g, newColor.b, 1.0f);
		shader.setUniformf("u_noise", (MathUtils.sin(game.getLastTime()) + 1.0f) / 2.0f);
		shader.setUniformi("u_texture1", 2);
		shader.end();
		
		Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE2);
		tGradient.bind();
		Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
	

		fBuffer = new FrameBuffer(Format.RGB565, game.getW() , game.getH() , false);
		fBuffer.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public Texture render(Texture renderSpace){
		
		fBuffer.begin();
			sB.begin();
			sB.draw(renderSpace, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight() , 0, 0, game.getW(), game.getH(), false, true);
			sB.end();
		fBuffer.end();
		
		return fBuffer.getColorBufferTexture();
	}
	float test;
	float last_camPosX,last_camPosY;
	public void tickUpdate(){
		test += 0.00f;
		if(test > 1.0f){
			test = 0.0f;
		}
		
		shader.begin();
			shader.setUniformf("u_cameraPos", test + -(last_camPosX  - game.getCamera().getCamPosX()) ,  -(last_camPosY - game.getCamera().getCamPosY()) );
		shader.end();
		last_camPosX = game.getCamera().getCamPosX();
		last_camPosY = game.getCamera().getCamPosY();
	}
}
