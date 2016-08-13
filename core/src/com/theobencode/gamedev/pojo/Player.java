package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.theobencode.gamedev.extras.Constants.PLAYER_COLOR;
import static com.theobencode.gamedev.extras.Constants.PLAYER_OFFSET;
import static com.theobencode.gamedev.extras.Constants.PLAYER_SQUARE_DIMENSIONS;
import static com.theobencode.gamedev.extras.Constants.WORLD_BORDER_THICKNESS;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Player {

    private Vector2 position;
    private Viewport viewport;
    private Vector2 leftStartBoundary;
    private Vector2 rightStartBoundary;
    private int deaths;


    public Player(Viewport viewport) {
        this.viewport = viewport;

        deaths = 0;
        init();
    }

    public void init() {
        leftStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS, (PLAYER_SQUARE_DIMENSIONS * 4) - 0.4f);

        position = new Vector2(leftStartBoundary.x - PLAYER_SQUARE_DIMENSIONS, leftStartBoundary.y);

        rightStartBoundary = new Vector2(viewport.getWorldWidth() / 2
                + (3 * PLAYER_SQUARE_DIMENSIONS) + 2f * WORLD_BORDER_THICKNESS, 0);

    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(PLAYER_COLOR);
        shapeRenderer.set(ShapeType.Filled);

        // Draw Player's body
        shapeRenderer.rect(position.x, position.y, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x, position.y - PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x, position.y - 2 * PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);

        // Draw Player's arms
        shapeRenderer.rect(position.x + PLAYER_OFFSET, position.y - PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x - PLAYER_OFFSET, position.y - PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);

        // Draw Player's feet
        shapeRenderer.rect(position.x + PLAYER_OFFSET, position.y - 3 * PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x - PLAYER_OFFSET, position.y - 3 * PLAYER_OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);

    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x = leftStartBoundary.x;

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x = rightStartBoundary.x;
        }

        ensureBounds();
    }

    private void ensureBounds() {
        if (position.x + (2 * PLAYER_SQUARE_DIMENSIONS) + WORLD_BORDER_THICKNESS >= rightStartBoundary.x) {
            position.x = (rightStartBoundary.x - (2 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS);
        }

        if (position.x <= leftStartBoundary.x) {
            position.x = leftStartBoundary.x;
        }

    }

    public boolean collidesWithEnemy(Enemies enemies) {

        boolean collides = false;
        for (Enemy enemy : enemies.getEnemyArray()) {
            if (enemy.getPosition().dst(position) < PLAYER_SQUARE_DIMENSIONS *3) {
                collides = true;
            }
        }

        if(collides) deaths++;
        return collides;
    }

    public int getDeaths() {
        return deaths;
    }

}
