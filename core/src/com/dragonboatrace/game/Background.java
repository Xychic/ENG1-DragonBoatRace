package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background extends Entity {

    protected Texture[] allTextures;
    protected Texture img;

    public Background(Vector2 pos) {
        super(pos, new Vector2(0, 0), 0);
        loadTextures();
        updateTexture();
    }

    public void render(SpriteBatch batch, Vector2 relPos) {

        //if the background tile has gone off the screen move it to the top and give it a new texture
        if(relPos.y - this.pos.y > this.img.getHeight()){
            this.pos.y += Gdx.graphics.getHeight() + this.img.getHeight();
            updateTexture();
        }

        batch.begin();
        batch.draw(
            this.img, 
            (this.pos.x - this.size.x / 2), 
            (this.pos.y-relPos.y) 
            );
        batch.end();
    }

    private void updateTexture() {
        this.img = this.allTextures[(int)(Math.random() * allTextures.length)];
        this.size = new Vector2(this.img.getWidth(), this.img.getHeight());
    }

    private void loadTextures() {
        String[] fileNames = Gdx.files.internal("Backgrounds/catalog.txt").readString().split("\n");
        this.allTextures = new Texture[fileNames.length];
        for (int i=0; i<fileNames.length;i++) {
            this.allTextures[i] = new Texture(String.format("Backgrounds/%s", fileNames[i]));
        }
    }

    public void dispose() {
        for (Texture t : this.allTextures) {
            t.dispose();
        }
    }

    public void collide(Obstacle o) {}

    public void move(float deltaTime) {}
    
}