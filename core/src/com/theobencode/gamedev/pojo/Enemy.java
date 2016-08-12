package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.theobencode.gamedev.extras.Constants.ENEMY_COLOR;
import static com.theobencode.gamedev.extras.Constants.ENEMY_GRAVITY;
import static com.theobencode.gamedev.extras.Constants.PLAYER_OFFSET;
import static com.theobencode.gamedev.extras.Constants.PLAYER_SQUARE_DIMENSIONS;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Enemy {

    private Vector2 velocity;
    private Vector2 position;

    public Enemy(Vector2 position){
        this.position = position;
        this.velocity = new Vector2();
    }

    public void update(float delta) {
        //velocity.mulAdd(ENEMY_GRAVITY, delta);
        position.mulAdd(ENEMY_GRAVITY, delta);
    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(ENEMY_COLOR);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

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

    public void reposition(int x) {

    }
}
