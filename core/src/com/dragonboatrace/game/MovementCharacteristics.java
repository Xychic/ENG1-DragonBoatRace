package com.dragonboatrace.game;

import com.badlogic.gdx.math.Vector2;

public class MovementCharacteristics{
    Vector2 vel;
    Tuple angleDelta;
    Tuple wanderDelayRange;

    public MovementCharacteristics(Vector2 vel, Tuple angleDelta, Tuple wanderDelayRange){
        this.vel = vel;
        this.angleDelta = angleDelta;
        this.wanderDelayRange = wanderDelayRange;
    }

    public Vector2 getVel(){
        return this.vel;
    }
}