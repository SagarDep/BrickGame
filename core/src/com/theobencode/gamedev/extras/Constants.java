package com.theobencode.gamedev.extras;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public interface Constants {

    float WORLD_SIZE = 20.0f;
    int WORLD_SIZE1 = 40;
    float WORLD_BORDER_THICKNESS = 0.2f;
    Color BORDER_COLOR = Color.DARK_GRAY;
    Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    float HUD_MARGIN = 20.0f;


    // Player constants
    float PLAYER_SQUARE_DIMENSIONS = 1.0f;
    Color PLAYER_COLOR= Color.BLACK;
    float PLAYER_OFFSET = 1.1f;

    // Enemy constants
    Color ENEMY_COLOR= Color.BROWN;
    Vector2 ENEMY_GRAVITY = new Vector2(0, -13.0f);
    int ENEMY_COUNT = 4;

    int HUD_FONT_REFERENCE_SCREEN_SIZE = 480;
}
