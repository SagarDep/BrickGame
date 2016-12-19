package com.theobencode.gamedev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.theobencode.gamedev.BrickGame;
import com.theobencode.gamedev.extras.Constants;
import com.theobencode.gamedev.pojo.Boundaries;
import com.theobencode.gamedev.pojo.Enemies;
import com.theobencode.gamedev.pojo.Player;

import static com.theobencode.gamedev.extras.Constants.BORDER_COLOR;
import static com.theobencode.gamedev.extras.Constants.HUD_FONT_REFERENCE_SCREEN_SIZE;
import static com.theobencode.gamedev.extras.Constants.HUD_MARGIN;
import static com.theobencode.gamedev.extras.Constants.WORLD_SIZE;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class GameScreen implements Screen {

    private Player player;
    private Boundaries boundaries;
    private ExtendViewport extendViewport;
    private ShapeRenderer renderer;
    private Enemies enemies;

    // On-screen drawing constants
    private ScreenViewport hudViewport;
    public static SpriteBatch batch;
    private BitmapFont font;
    private int topScore;
    private Music gameMusic;
    private Sound dyingSound;
    private BrickGame game;
    Preferences prefs;

    public GameScreen(BrickGame game){
        this.game = game;
       prefs = Gdx.app.getPreferences("TopScore Preferences");
    }


    @Override
    public void show() {
        extendViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("chubby_cat_song.wav"));
        gameMusic.setLooping(true);
        gameMusic.play();
        dyingSound = Gdx.audio.newSound(Gdx.files.internal("dead_sound.mp3"));
        renderer = new ShapeRenderer();
        boundaries = new Boundaries(extendViewport);
        player = new Player(extendViewport);
        enemies = new Enemies(extendViewport);
        renderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        hudViewport = new ScreenViewport();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        topScore = prefs.getInteger("TOP_SCORE");
    }

    @Override
    public void render(float delta) {

        player.update(delta);
        enemies.update(delta);
        extendViewport.apply(true);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(extendViewport.getCamera().combined);
        renderer.begin();

        if (player.collidesWithEnemy(enemies)) {
            dyingSound.play();
            //enemies.init();
            game.setScreen(new MenuScreen(game));
            this.dispose();
            gameMusic.dispose();
        }

        // Draw world boundaries
        renderer.set(ShapeType.Filled);
        renderer.setColor(BORDER_COLOR);
        boundaries.render(renderer);

        //Draw the enemies
        enemies.render(renderer);

        // Draw Player
        player.render(renderer);
        renderer.end();

        // HUD related drawing
        if(enemies.getEnemiesDodged() > topScore){
            prefs.putInteger("TOP_SCORE", enemies.getEnemiesDodged());
            prefs.flush();
            topScore = enemies.getEnemiesDodged();
        }

        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.draw(batch, "Speed: " + enemies.getSpeed(),
                HUD_MARGIN, hudViewport.getWorldHeight() - HUD_MARGIN);

        font.draw(batch, "Score: " + enemies.getEnemiesDodged() + "\nTop Score: " + topScore,
                 hudViewport.getWorldWidth() - HUD_MARGIN, hudViewport.getWorldHeight()  - Constants.HUD_MARGIN,
                0, Align.right, false);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / HUD_FONT_REFERENCE_SCREEN_SIZE, Math.min(width, height) / HUD_FONT_REFERENCE_SCREEN_SIZE);

        player.init();
        enemies.init();
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
        renderer.dispose();
        batch.dispose();
        font.dispose();
        enemies.dispose();
    }
}
