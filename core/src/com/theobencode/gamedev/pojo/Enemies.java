package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.theobencode.gamedev.extras.Constants;

import static com.theobencode.gamedev.extras.Constants.ENEMY_COUNT;
import static com.theobencode.gamedev.extras.Constants.PLAYER_SQUARE_DIMENSIONS;
import static com.theobencode.gamedev.extras.Constants.WORLD_BORDER_THICKNESS;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Enemies {

    private Array<Enemy> enemyArray;
    private Viewport viewport;
    private Vector2 leftStartBoundary;
    private Vector2 rightStartBoundary;
    private int enemiesDodged;
    private Sound dodgeSound;
    private Sound levelUpSound;
    private int speed;


    public Enemies(Viewport viewport) {
        this.viewport = viewport;
        dodgeSound = Gdx.audio.newSound(Gdx.files.internal("dodge_sound.wav"));
        levelUpSound = Gdx.audio.newSound(Gdx.files.internal("level_up.mp3"));

        init();
    }

    public void init() {

        enemiesDodged = 0;
        speed = 1;
        Constants.ENEMY_GRAVITY.y = -13.0f;

        enemyArray = new Array<Enemy>();
        leftStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS, (PLAYER_SQUARE_DIMENSIONS * 4) - 0.4f);
        rightStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                + (3 * PLAYER_SQUARE_DIMENSIONS) + 2f * WORLD_BORDER_THICKNESS, 0);


        for (int i = 1; i <= ENEMY_COUNT; i++) {

            Vector2 newEnemyPos = new Vector2(spawnPos(), (i * viewport.getWorldHeight() * 0.3f) + 30);
            Enemy enemy = new Enemy(newEnemyPos);
            enemyArray.add(enemy);
        }
    }

    public void update(float delta) {

        for (Enemy enemy : enemyArray) {
            enemy.update(delta);
        }

    }

    public void render(ShapeRenderer renderer) {
        for (Enemy enemy : enemyArray) {
            enemy.render(renderer);
        }

        // When Enemy is below screen and out of view
        repositionEnemyWhenBelowScreen();

    }

    public float spawnPos() {
        float xPos;
        float rand = MathUtils.random();
        if (rand < 0.5) {
            xPos = leftStartBoundary.x;
        } else {
            xPos = rightStartBoundary.x - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS;
        }
        return xPos;
    }

    public Array<Enemy> getEnemyArray() {
        return enemyArray;
    }

    public void repositionEnemyWhenBelowScreen() {
        for (int i = 0; i < enemyArray.size; i++) {
            if (enemyArray.get(i).getPosition().y < - PLAYER_SQUARE_DIMENSIONS) {
                dodgeSound.play();
                enemiesDodged++;
                if(enemiesDodged > 0 && enemiesDodged % 7 == 0){
                    Constants.ENEMY_GRAVITY.y -= 2.0f;
                    speed++;
                }

                enemyArray.get(i).getPosition().set(spawnPos(), (viewport.getWorldHeight() * 0.3f) + 33);
            }
        }
    }



    public int getEnemiesDodged() {
        return enemiesDodged;
    }

    public void dispose(){
        dodgeSound.dispose();
        levelUpSound.dispose();
    }

    public int getSpeed() {
        return speed;
    }



}

