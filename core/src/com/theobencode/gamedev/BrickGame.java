package com.theobencode.gamedev;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theobencode.gamedev.screens.MenuScreen;

public class BrickGame extends Game {

	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new MenuScreen(this));
	}
}
