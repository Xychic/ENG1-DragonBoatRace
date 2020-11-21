package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

public class BoatChoice extends ScreenAdapter{
    DragonBoatRace game;

    public BoatChoice(DragonBoatRace game){
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {

                    int laneCount = 7;

                    CPUBoat[] CPUs = new CPUBoat[laneCount-1];
		
                    for (int i = 0; i<laneCount-1; i++){
                        int xpos = i;
                        if(i >= (laneCount-1)/2){
                            xpos += 1;
                        }
                        CPUs[i] = new CPUBoat(BoatType.NORMAL, new Vector2( (int) (0.5 + xpos)*(Gdx.graphics.getWidth()/laneCount) ,10), 0 ,new Vector2(0,0));
                        CPUs[i].saveStartPos();
                    }

                    PlayerBoat pb = new PlayerBoat(BoatType.FAST, new Vector2(Gdx.graphics.getWidth()/2, 10));	// Creating the players boat
                    pb.saveStartPos();

                    game.setScreen(new GameScreen(game, 0, CPUs, pb));
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
        game.font.draw(game.batch, "Choose a boat!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
