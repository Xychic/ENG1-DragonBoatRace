package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class BoatDeathScreen extends ScreenAdapter{

    DragonBoatRace game;

    public BoatDeathScreen(DragonBoatRace game){
        this.game = game;
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new TitleScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 1, 0);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(new Texture("menus/boat broke.png"), 0 , 0);     
        game.font.draw(game.batch, "Press SPACE to restart :(" , Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .5f);
        game.batch.end();
    } 
}
