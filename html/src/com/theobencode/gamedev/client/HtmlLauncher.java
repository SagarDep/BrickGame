package com.theobencode.gamedev.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.theobencode.gamedev.BrickGame;
import com.theobencode.gamedev.extras.Constants;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Constants.WORLD_SIZE1, Constants.WORLD_SIZE1);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new BrickGame();
        }
}