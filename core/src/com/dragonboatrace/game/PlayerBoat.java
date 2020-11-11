package com.dragonboatrace.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class PlayerBoat extends Boat {

    float currentMaxSpeed;
    float stamina;
    float maxStamina;

    public PlayerBoat(BoatType boatType, Vector2 pos) {
        super(boatType, pos);
        this.stamina = 1000;
        this.maxStamina = 1000;
    }

    @Override
    public void move(float deltaTime) {
        // TODO constrain to screen
        if (this.stamina > 0) {   
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                this.vel.add(-(1 * this.boatType.getHandling() / (deltaTime * 60)), 0);
                this.stamina -= 2 / (60 *deltaTime);
            } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                this.vel.add((1 * this.boatType.getHandling() / (deltaTime * 60)), 0);
                this.stamina -= 2 / (60 *deltaTime);
            } if (Gdx.input.isKeyPressed(Keys.UP) && (this.vel.y < this.currentMaxSpeed) || (this.vel.y < -this.currentMaxSpeed)) {
                this.vel.add(0, ((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
                this.stamina -= 2 / (60 *deltaTime);
            } else if (Gdx.input.isKeyPressed(Keys.DOWN) && (this.vel.y > -(this.currentMaxSpeed)) || this.vel.y > this.currentMaxSpeed) {
                this.vel.add(0, -((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
                this.stamina -= 2 / (60 *deltaTime);
            }
        } if (this.stamina < this.maxStamina) {
            this.stamina += 1 / (60 * deltaTime);
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

    public float getStamina() {return this.stamina;}
}