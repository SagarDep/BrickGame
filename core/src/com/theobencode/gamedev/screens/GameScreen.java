package com.theobencode.gamedev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.theobencode.gamedev.extras.Constants;
import com.theobencode.gamedev.pojo.Boundaries;
import com.theobencode.gamedev.pojo.Player;

import static com.theobencode.gamedev.extras.Constants.BORDER_COLOR;
import static com.theobencode.gamedev.extras.Constants.WORLD_SIZE;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class GameScreen implements Screen {

    private Player player;
    private Boundaries boundaries;
    private ExtendViewport extendViewport;
    private ShapeRenderer renderer;

    @Override
    public void show() {
        extendViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        renderer = new ShapeRenderer();
        player = new Player(extendViewport);
        boundaries = new Boundaries(extendViewport);

        renderer.setAutoShapeType(true);

    }

    @Override
    public void render(float delta) {

        player.update(delta);
        extendViewport.apply(true);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(extendViewport.getCamera().combined);
        renderer.begin();

        // Draw world boundaries
        renderer.set(ShapeType.Filled);
        renderer.setColor(BORDER_COLOR);
        boundaries.render(renderer);

        // Draw Player
        player.render(renderer);

        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width, height, true);
        player.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
