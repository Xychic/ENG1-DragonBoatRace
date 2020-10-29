package com.dragonboatrace.game;

import com.badlogic.gdx.math.Vector2;

public enum MovementCharacteristics {
    STATIC("static", new Vector2(), new Tuple<Float, Float>((float)0, (float)0), new Tuple<Float, Float>((float)0, (float)0)),
    CONSTANT("constant", new Vector2(-3, 3), new Tuple<Float, Float>((float)0, (float)0), new Tuple<Float, Float>((float)0, (float)0));
    
    String ID;
    Vector2 vel;
    Tuple<Float, Float> angleDelta;
    Tuple<Float, Float> wanderDelayRange;

    private MovementCharacteristics(String ID, Vector2 vel, Tuple<Float, Float> angleDelta, Tuple<Float, Float> wanderDelayRange){
        this.ID = ID;
        this.vel = vel;
        this.angleDelta = angleDelta;
        this.wanderDelayRange = wanderDelayRange;
    }

    public Vector2 getVel(){
        return this.vel;
    }
}