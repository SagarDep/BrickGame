package com.theobencode.gamedev.pojo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.theobencode.gamedev.extras.Constants.PLAYER_SQUARE_DIMENSIONS;
import static com.theobencode.gamedev.extras.Constants.WORLD_BORDER_THICKNESS;

/**
 * Created by galaxywizkid on 8/10/16.
 */
public class Boundaries {

    private Viewport extendViewport;

    public Boundaries(Viewport extendViewport) {
        this.extendViewport = extendViewport;
    }

    public void render(ShapeRenderer renderer){
        Vector2 leftStartBoundary = new Vector2(extendViewport.getWorldWidth() / 2
                - (3 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS, 0);
        Vector2 leftEndBoundary = new Vector2(extendViewport.getWorldWidth() / 2
                - (3 * PLAYER_SQUARE_DIMENSIONS) - WORLD_BORDER_THICKNESS, extendViewport.getWorldHeight());

        renderer.rectLine(leftStartBoundary, leftEndBoundary, WORLD_BORDER_THICKNESS);

        // Right Line boundary
        Vector2 rightStartBoundary = new Vector2(extendViewport.getWorldWidth() / 2
                + (3 * PLAYER_SQUARE_DIMENSIONS) + WORLD_BORDER_THICKNESS, 0);
        Vector2 rightEndBoundary = new Vector2(extendViewport.getWorldWidth() / 2
                + (3 * PLAYER_SQUARE_DIMENSIONS) + WORLD_BORDER_THICKNESS, extendViewport.getWorldHeight());


        renderer.rectLine(rightStartBoundary, rightEndBoundary, WORLD_BORDER_THICKNESS);

    }
}
