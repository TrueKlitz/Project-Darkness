package com.klitz.playgod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
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
		shader.end();
		//Gdx.gl20.glBindTexture(GL20.GL_TEXTURE_2D , tGradient.getTextureObjectHandle());
		//Gdx.gl20.glTexImage2D(GL20.GL_TEXTURE_2D , tGradient.getTextureObjectHandle(), GL20.GL_RGBA, tGradient.getWidth(), tGradient.getHeight(), GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, 0, tGradient.g);
		fBuffer = new FrameBuffer(Format.RGBA8888, game.getW(), game.getH(), false);
	}

	public Texture render(Texture renderSpace){
		
		
		
		fBuffer.begin();
			sB.begin();
			sB.draw(renderSpace, 0, 0, renderSpace.getWidth(), renderSpace.getHeight(), 0, 0, game.getW(), game.getH(), false, true);
			sB.end();
		fBuffer.end();
		
		return fBuffer.getColorBufferTexture();
	}
	public void tickUpdate(){
		shader.begin();
			shader.setUniformf("u_noise", (float) Math.sin(game.getLastTime()), (float) Math.tan(game.getLastTime()), (float) Math.cos(game.getLastTime()) );
		shader.end();
	}
}
