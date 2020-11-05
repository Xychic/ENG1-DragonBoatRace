package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class Obstacle extends Entity{
    protected ObstacleType obstacleType;

    public Obstacle(ObstacleType obstacleType, Vector2 pos){
        super(pos, obstacleType.getSize(), obstacleType.getWeight());
        this.obstacleType = obstacleType;
    }

    public void collide(Entity e){

    }

    public void move(float deltaTime) {
        this.vel = new Vector2().add(this.obstacleType.getMover().getAndUpdateVel(deltaTime));
    }

    public void render(SpriteBatch batch, Vector2 relPos) {
        batch.begin();
        batch.draw(this.obstacleType.getImage(), 
            (this.pos.x), (this.pos.y-relPos.y), 
            this.obstacleType.getSize().x, this.obstacleType.getSize().y);
        batch.end();
    }

    public void dispose() {
        this.obstacleType.getImage().dispose();
    }

    public Vector2 getRelPos(Vector2 relPos) {
        return new Vector2((this.pos.x), (this.pos.y-relPos.y));
    }
}
