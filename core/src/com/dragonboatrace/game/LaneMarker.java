package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LaneMarker extends Entity {

    protected Texture img;

    public LaneMarker(Vector2 pos) {
        this(pos, new Texture("Obstacles/line.png"));
    }

    public LaneMarker(Vector2 pos, Texture img) {
        super(pos, new Vector2(img.getWidth(), img.getHeight()), 0);
        this.img = img;
    }

    public void render(SpriteBatch batch, Vector2 relPos) {
        batch.begin();
        batch.draw(this.img, 
            (this.pos.x), 
            (this.pos.y-relPos.y) % this.img.getHeight());
        batch.draw(this.img, 
            (this.pos.x), 
            ((this.pos.y-relPos.y) % this.img.getHeight()) + this.img.getHeight());
        batch.end();
    }

    public void dispose() {
        this.img.dispose();
    }

    public void collide(Obstacle o) {}

    public void move(float deltaTime) {}
    
}