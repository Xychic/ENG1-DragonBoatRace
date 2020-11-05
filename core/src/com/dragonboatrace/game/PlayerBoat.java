package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class PlayerBoat extends Boat {

    float currentMaxSpeed;

    public PlayerBoat(BoatType boatType, Vector2 pos) {
        super(boatType, pos);
    }

    @Override
    public void move(float deltaTime) {
        // TODO constrain to screen
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            this.vel.add(-(1 * this.boatType.getHandling() / (deltaTime * 60)), 0);
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            this.vel.add((1 * this.boatType.getHandling() / (deltaTime * 60)), 0);

        } if (Gdx.input.isKeyPressed(Keys.UP) && (this.vel.y < this.currentMaxSpeed) || (this.vel.y < -this.currentMaxSpeed)) {
            this.vel.add(0, ((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
        } else if (Gdx.input.isKeyPressed(Keys.DOWN) && (this.vel.y > -(this.currentMaxSpeed)) || this.vel.y > this.currentMaxSpeed) {
            this.vel.add(0, -((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
        }
    }

    @Override
    public void update(float deltaTime) {
        float deltaX = this.vel.x * this.dampening;
        float deltaY = this.vel.y * this.dampening;

        if (deltaX != 0) {
            this.pos.add(deltaX, 0);
            this.inGamePos.add(deltaX, 0);
            this.vel.add(-deltaX, 0);
        }
        if (deltaY != 0) {
            this.inGamePos.add(0, deltaY);
        }
        this.currentMaxSpeed = this.boatType.getSpeed();
        if (this.collider != null) {this.currentMaxSpeed /= this.collider.weight;}
    }
}