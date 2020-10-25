package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Obstacle extends Entity{
    protected ObstacleType obstacleType;

    public Obstacle(ObstacleType obstacleType, Vector2 pos){
        super(pos, obstacleType.getSize(), obstacleType.getWeight());
        this.obstacleType = obstacleType;
    }

    public void collide(Entity e){

    }

    public void move(){

    }

    public void render(SpriteBatch batch){

    }
}
