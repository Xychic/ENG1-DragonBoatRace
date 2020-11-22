package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class Finale extends ScreenAdapter{
    
    DragonBoatRace game;
    CPUBoat[] CPUs;
    PlayerBoat pb;
    int[] playerPositions;

    public Finale(DragonBoatRace game, CPUBoat[] CPUs, PlayerBoat pb){
        this.game = game;
        this.CPUs = CPUs;
        this.pb = pb;
        this.playerPositions = getPlayerPositions();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new TitleScreen(new DragonBoatRace()));
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
        game.font.draw(game.batch, "You came #"+playerPositions[1]+" in the dragon boat race!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Congratulations!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .4f);
        game.font.draw(game.batch, "Press Space to restart!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .3f);
        game.batch.end();
    }

    public int[] getPlayerPositions() {
        //element 0 of the output is the players position in the last race
        //element 1 of the output is the players position in all races

        int[] output = {1,1};
        for (CPUBoat CPU : CPUs){
            if(CPU.finishTime < pb.finishTime){
                output[0] += 1;
            }
            if(CPU.totalTime < pb.totalTime){
                output[1] += 1;
            }
        }
        return output;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}
