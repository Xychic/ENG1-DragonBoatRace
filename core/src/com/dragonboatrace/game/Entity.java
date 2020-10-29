package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 pos, vel, acc, size;
    protected float weight, dampening;

    public Entity(Vector2 pos, Vector2 size, float weight) {
        this.pos = pos;
        this.vel = new Vector2();
        this.acc = new Vector2();
        this.size = size;
        this.weight = weight;
        this.dampening = (float) 0.2;
    }

    public void update(float deltaTime) {
        float deltaX = this.vel.x * this.dampening;
        float deltaY = this.vel.y * this.dampening;

        if (deltaX != 0) {
            this.pos.add(deltaX, 0);
            this.vel.add(-deltaX, 0);
        }
        if (deltaY != 0) {
            this.pos.add(0, deltaY/2);
            this.vel.add(0, -deltaY/2);
        }
    }

    public boolean checkCollision(Entity e) {
        float x1 = this.pos.x;
        float y1 = this.pos.y;
        float x2 = this.pos.x + this.size.x;
        float y2 = this.pos.y + this.size.y;

        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = 0; dy <= 1; dy++) {
                float checkX = e.pos.x + (dx * e.size.x);
                float checkY = e.pos.y + (dy * e.size.y);
                if ((x1 <= checkX && checkX <= x2) && 
                    (y1 <= checkY && checkY <= y2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void render(SpriteBatch batch);
    public abstract void move(float deltaTime);
    public abstract void collide(Entity e);

}