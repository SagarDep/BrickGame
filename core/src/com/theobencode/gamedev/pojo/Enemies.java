package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.theobencode.gamedev.extras.Constants.*;
/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Enemies {

    Array<Enemy> enemyArray;
    Viewport viewport;

    public Enemies(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        enemyArray = new Array<Enemy>(false, 50);
    }

    public void update(float delta) {

        // TODO: Replace hard-coded spawn rate with a constant

        if (MathUtils.random() < delta * 5) {
            // TODO: Add a new icicle at the top of the viewport at a random x position
            float xPos = viewport.getWorldWidth() / 4 + WORLD_BORDER_THICKNESS;
            float xPos2 = viewport.getWorldWidth() / 4 + WORLD_BORDER_THICKNESS;

            Vector2 newEnemyPos = new Vector2(xPos, viewport.getWorldHeight());

            Enemy enemy = new Enemy(newEnemyPos);
            enemyArray.add(enemy);
        }

    }
}
