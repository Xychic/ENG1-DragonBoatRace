package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum BoatType {
    fastBoat("fastBoat", 1, 1, 1, 1, 1, 1, new Vector2(5, 5), null);

    String ID;
    float speed, acceleration, maxHealth, weight, strength, handling;
    Vector2 size;
    Texture image;

    private BoatType(String ID, float speed, float acceleration, float maxHealth, float weight, float strength, float handling, Vector2 size, String imageSrc) {
        this.ID = ID;
        this.speed = speed;
        this.acceleration = acceleration;
        this.maxHealth = maxHealth;
        this.weight = weight;
        this.strength = strength;
        this.handling = handling;
        this.size = size;
        this.image = new Texture(imageSrc);
    }

}