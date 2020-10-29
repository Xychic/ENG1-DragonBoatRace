package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Boat extends Entity{

    protected BoatType boatType;
    protected float currentHealth, tiredness;

    public Boat(BoatType boatType, Vector2 pos) {
        super(pos, boatType.getSize(), boatType.getWeight());
        this.boatType = boatType;
        this.currentHealth = this.boatType.getMaxHealth();
        this.tiredness = 100;
    }

    public void collide(Entity e) {

    }

    public void render(SpriteBatch batch, Vector2 relPos) {
        batch.begin();
        batch.draw(this.boatType.getImage(), 
        (this.pos.x-relPos.x), (this.pos.y-relPos.y),  
        this.boatType.getSize().x, this.boatType.getSize().y);
        batch.end();
    }

    public void dispose() {
        this.boatType.getImage().dispose();
    }
}