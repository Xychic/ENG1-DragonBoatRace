package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

    public void move(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            this.vel.add(-(1 * this.boatType.getHandling() * deltaTime), 0);
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            this.vel.add((1 * this.boatType.getHandling() * deltaTime), 0);
        } if (Gdx.input.isKeyPressed(Keys.UP)) {
            this.vel.add(0, (1 * this.boatType.getHandling() * deltaTime));
        } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            this.vel.add(0, -(1 * this.boatType.getHandling() * deltaTime));
        }
    }

    public void render(SpriteBatch batch) {
        
    }

}