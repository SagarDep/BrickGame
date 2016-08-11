package com.theobencode.gamedev;

import com.badlogic.gdx.Game;
import com.theobencode.gamedev.screens.GameScreen;

public class BrickGame extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}
