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

    public void move() {

    }

    public void render(SpriteBatch batch) {
        
    }

}