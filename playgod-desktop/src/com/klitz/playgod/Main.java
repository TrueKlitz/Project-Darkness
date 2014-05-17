package com.klitz.playgod;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "RPG";
		cfg.useGL20 = false;
		cfg.width = 1280;// 20 tiles pro Bild |  480*2;
		cfg.height = 720;// 11.25 tiles pro Bild| 320*2;
		cfg.vSyncEnabled = true;
		cfg.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
		cfg.backgroundFPS = 0; // Setting to 0 disables background fps throttling
		// 20 * 11.25 = 225 Tiles die angezeigt werden (mehr möglich bei ungeraden Koordianten ! )
		
		new LwjglApplication(new Game(), cfg);
	}
}
