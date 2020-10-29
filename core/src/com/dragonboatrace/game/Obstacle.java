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

    public void move(float deltaTime) {
        Vector2 moverVel = this.obstacleType.getMover().getVel();
        this.vel = new Vector2().add(this.obstacleType.getMover().getVel());
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
}
