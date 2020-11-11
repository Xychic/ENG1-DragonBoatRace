package com.dragonboatrace.game;

import com.badlogic.gdx.math.Vector2;
import java.util.concurrent.ThreadLocalRandom;

public enum MovementCharacteristics {
    STATIC("static", new Vector2(), new Tuple<Integer, Integer>(0, 0), new Tuple<Float, Float>((float)0, (float)0)),
    CONSTANT("constant", new Vector2(-3, 3), new Tuple<Integer, Integer>(0, 0), new Tuple<Float, Float>((float)0, (float)0)),
    WANDER("wander", new Vector2(-3, 3), new Tuple<Integer, Integer>(-45, 45), new Tuple<Float, Float>((float)0.5, (float)2));
    
    String ID;
    Vector2 vel;
    Tuple<Integer, Integer> angleDelta;
    Tuple<Float, Float> wanderDelayRange;
    float wanderDelay;

    private MovementCharacteristics(String ID, Vector2 vel, Tuple<Integer, Integer> angleDelta, Tuple<Float, Float> wanderDelayRange){
        this.ID = ID;
        this.vel = vel;
        this.angleDelta = angleDelta;
        this.wanderDelayRange = wanderDelayRange;
        this.wanderDelay = 0;
    }

    public void updateVel(float deltaTime) {
        this.wanderDelay -= deltaTime;
        if (this.wanderDelay <= 0) {
            this.wanderDelay = ThreadLocalRandom.current().nextFloat() * (wanderDelayRange.b - wanderDelayRange.a) + wanderDelayRange.a;
            this.vel.rotate(ThreadLocalRandom.current().nextInt(angleDelta.a, angleDelta.b+1));
        }
    }
    
    public Vector2 getVel(){
        return this.vel;
    }

    public Vector2 getAndUpdateVel(float timeDelta){
        this.updateVel(timeDelta);
        return this.getVel();
    }
}