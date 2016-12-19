package com.theobencode.gamedev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.theobencode.gamedev.BrickGame;
import com.theobencode.gamedev.extras.Constants;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by galaxywizkid on 12/18/16.
 */

public class MenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 200;
    private Texture playBtn;
    private Texture backgroundImg;

    BrickGame game;

    public MenuScreen(BrickGame game) {
        this.game = game;
        playBtn = new Texture("playbtn.png");
        backgroundImg = new Texture("brick_prev.png");

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        if (Gdx.input.justTouched()) {
            this.dispose();
            game.setScreen(new GameScreen(game));
        }

        game.batch.draw(backgroundImg, 0, 0, graphics.getWidth(), graphics.getHeight());
        game.batch.draw(playBtn, graphics.getWidth() / 2 - 237, graphics.getHeight()/2 - 100, 500, 300);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playBtn.dispose();
        //batch.dispose();
    }
}
