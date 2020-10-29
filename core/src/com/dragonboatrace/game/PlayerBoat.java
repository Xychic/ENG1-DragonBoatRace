package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class PlayerBoat extends Boat {

    public PlayerBoat(BoatType boatType, Vector2 pos) {
        super(boatType, pos);
    }

    @Override
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

}