package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 pos, vel, acc, size;
    protected float weight;

    public Entity(Vector2 pos, Vector2 size, float weight) {
        this.pos = pos;
        this.vel = new Vector2();
        this.acc = new Vector2();
        this.size = size;
        this.weight = weight;
    }

    public void update(float deltaTime) {

    }

    public boolean checkCollision(Entity e) {
        return false;
    }

    public abstract void render(SpriteBatch batch);
    public abstract void move();
    public abstract void collide(Entity e);

}