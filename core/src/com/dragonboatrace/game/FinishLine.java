package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class FinishLine extends Entity {

    protected Texture img;

   

    public FinishLine(Vector2 pos, Vector2 size, float weight){
        super(pos, size, weight);
        this.img = new Texture("Obstacles/finishline.png");
    }
    
    public FinishLine(){
        FinishLine(new Vector2(0,0) , new Vector2(0,0), (float) 0 );
        this.img = new Texture("Obstacles/finishline.png");
    }

    @Override
    public void render(SpriteBatch batch, Vector2 relPos) {
        batch.begin();
        batch.draw(img, this.pos.x, this.pos.y);
        batch.end();
    }

    public void setNewPos(int yCoord){
        this.pos.y = yCoord;
        this.inGamePos.y = yCoord;
    }

    @Override
    public void move(float deltaTime) {
        // TODO Auto-generated method stub

    }

    @Override
    public void collide(Obstacle o) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        

    }
    
}
