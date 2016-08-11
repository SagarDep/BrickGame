package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.theobencode.gamedev.extras.Constants.*;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Player {

    private Vector2 position;
    private Viewport viewport;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(PLAYER_COLOR);
        shapeRenderer.set(ShapeType.Filled);

        // Draw Player's body
        shapeRenderer.rect(position.x, position.y, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x, position.y - OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x, position.y - 2 * OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);

        // Draw Player's arms
        shapeRenderer.rect(position.x + OFFSET, position.y - OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x - OFFSET, position.y - OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);

        // Draw Player's feet
        shapeRenderer.rect(position.x + OFFSET, position.y - 3 * OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);
        shapeRenderer.rect(position.x - OFFSET, position.y - 3 * OFFSET, PLAYER_SQUARE_DIMENSIONS, PLAYER_SQUARE_DIMENSIONS);


    }

}
