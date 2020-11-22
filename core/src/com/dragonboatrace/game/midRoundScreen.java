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
    int[] playerPositions;
    
    public midRoundScreen(DragonBoatRace game, int round, CPUBoat[] CPUs, PlayerBoat playerBoat){
        this.game = game;
        this.CPUs = CPUs;
        this.round = round;
        this.pb = playerBoat;
        playerPositions = getPlayerPositions();

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


                    if(round != 3 || playerPositions[1] < 4){
                        for(CPUBoat CPU : CPUs){
                            CPU.moveToStart(pb.inGamePos.y);
                        }
    
                        pb.moveToStart();
                        game.setScreen(new GameScreen(game, round + 1, CPUs, pb));
                    }
                    else{
                        game.setScreen(new Finale(game, CPUs, pb));
                    }
                    //this will move every cpu boat to the 'start'
                    //really it just moves them to be at the same position as the player
                    
                    
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
        game.font.draw(game.batch, "You came #"+ playerPositions[0] +" in that race!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Overall you are #"+ playerPositions[1] +" in the dragon boat race!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .4f);

        if(round != 3 || playerPositions[1] < 4){
            if(round != 3){
                game.font.draw(game.batch, "You can progress to the next round!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .3f);
            }
            else{
                game.font.draw(game.batch, "You can progress to the finale!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .3f);
            }
            
            game.font.draw(game.batch, "Press space to continue!", Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .2f);
        }
        
        game.batch.end();
    }
    
    public int[] getPlayerPositions() {
        //element 0 of the output is the players position in the last race
        //element 1 of the output is the players position in all races

        int[] output = {1,1};
        System.out.println("pb Total Time" + pb.totalTime + " pb Finish Time" + pb.finishTime );
        for (CPUBoat CPU : CPUs){
            System.out.println("CPU Total Time" + CPU.totalTime + " CPU Finish Time" + CPU.finishTime );
            if(CPU.finishTime < pb.finishTime){
                output[0] += 1;
            }
            if(CPU.totalTime + CPU.finishTime < pb.totalTime + pb.finishTime){
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
