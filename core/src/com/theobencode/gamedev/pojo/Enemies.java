package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.theobencode.gamedev.extras.Constants.PLAYER_SQUARE_DIMENSIONS;
import static com.theobencode.gamedev.extras.Constants.WORLD_BORDER_THICKNESS;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Enemies {

    Array<Enemy> enemyArray;
    Viewport viewport;
    Vector2 leftStartBoundary;
    Vector2 rightStartBoundary;
    long initialTime;

    public Enemies(Viewport viewport) {
        this.viewport = viewport;
        initialTime = TimeUtils.nanoTime();
        init();
    }

    public void init() {
        enemyArray = new Array<Enemy>();
        leftStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS, (PLAYER_SQUARE_DIMENSIONS * 4) - 0.4f);
        rightStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                + (3 * PLAYER_SQUARE_DIMENSIONS) + 2f * WORLD_BORDER_THICKNESS, 0);

        for (int i = 1; i <= 9; i++) {
            Vector2 newEnemyPos = new Vector2(spawnPos(), (i * viewport.getWorldHeight()) + 5);
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

    }

    public float spawnPos(){
        float xPos;
        float a = MathUtils.random();
        if (a < 0.5) {
            xPos = leftStartBoundary.x;
        } else {
            xPos = rightStartBoundary.x - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS;
        }
        return xPos;
    }
}




      /*  // Randomly choose position to spawn from 0- 0.5 left spawn, 0.5 - 0.9 right spawn
        if (MathUtils.random() < 0.5) {
            xPos = leftStartBoundary.x;
        } else {
            xPos = rightStartBoundary.x - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS;
        }*/

       /* if (MathUtils.random() < delta * 1f) {
            Vector2 newEnemyPos = new Vector2(xPos, viewport.getWorldHeight() + 5);

            Enemy enemy = new Enemy(newEnemyPos);
            enemyArray.add(enemy);
        }

        for (Enemy enemy : enemyArray) {
            enemy.update(delta);
        }*/