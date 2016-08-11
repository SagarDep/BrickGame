package com.theobencode.gamedev.extras;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public interface Constants {

    float WORLD_SIZE = 20.0f;
    float WORLD_BORDER_THICKNESS = 0.2f;
    Color BORDER_COLOR = Color.DARK_GRAY;
    Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    // Player constants
    float PLAYER_SQUARE_DIMENSIONS = 1.0f;
    Color PLAYER_COLOR= Color.BLACK;
    float PLAYER_MOVEMENT_SPEED = 10.0f;
    float PLAYER_OFFSET = 1.1f;

    // Enemy constants
    Vector2 ENEMY_GRAVITY = new Vector2(0, -5.0f);







}
