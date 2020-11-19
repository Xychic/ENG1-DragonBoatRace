package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background extends Entity {

    protected Texture img;

    public Background(Vector2 pos) {
        this(pos, new Texture("Backgrounds/background" + String.valueOf((int)(Math.random() * 4)) +".png"));
    }

    public Background(Vector2 pos, Texture img) {
        super(pos, new Vector2(img.getWidth(), img.getHeight()), 0);
        this.img = img;
    }

    public void render(SpriteBatch batch, Vector2 relPos) {

        //if the background tile has gone off the screen move it to the top and give it a new texture
        if(relPos.y - this.pos.y > 270){
            this.pos.y += Gdx.graphics.getHeight() + 270;
            this.img = new Texture("Backgrounds/background" + String.valueOf((int)(Math.random() * 4)) +".png");
        }

        batch.begin();
        batch.draw(
            this.img, 
            (this.pos.x - this.size.x / 2), 
            (this.pos.y-relPos.y) 
            );
        batch.end();
    }

    public void dispose() {
        this.img.dispose();
    }

    public void collide(Obstacle o) {}

    public void move(float deltaTime) {}
    
}