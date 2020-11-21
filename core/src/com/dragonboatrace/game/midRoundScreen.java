package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class midRoundScreen extends ScreenAdapter{
    
    DragonBoatRace game;
    CPUBoat[] CPUs;
    int round;
    PlayerBoat pb;

    public midRoundScreen(DragonBoatRace game, int round, CPUBoat[] CPUs, PlayerBoat playerBoat){
        this.game = game;
        this.CPUs = CPUs;
        this.round = round;
        this.pb = playerBoat;

        for (CPUBoat c : this.CPUs) {
            c.resetPos();
        }
        this.pb.resetPos();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {

                    for(CPUBoat CPU : CPUs){
                        CPU.moveToStart(pb.inGamePos.y);
                    }

                    pb.moveToStart();
                    game.setScreen(new GameScreen(game, round + 1, CPUs, pb));
                    
                }
                return true;
            }
        });
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "You came x place!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "You can progress to the next round!.", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}
