package com.dragonboatrace.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DragonBoatRace extends Game{

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    ArrayList<ScreenAdapter> toDispose;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
        this.toDispose = new ArrayList<ScreenAdapter>();
        setScreen(new TitleScreen(this));
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.shapeRenderer.dispose();
        this.font.dispose();
        for (ScreenAdapter s : this.toDispose) {
            s.dispose();
        }
    }
    
}
