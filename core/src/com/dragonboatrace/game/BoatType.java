package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum BoatType {
    //TYPE( ID,          speed, accleration,    maxHealth,  weight, strength,   handling,   size,               imageSrc)
    FAST("fastBoat",     4,     3,              1,          1,      1,          1,          new Vector2(5, 5),  null),
    NORMAL("normalBoat", 3,     2,              2,          2,      2,          2,          new Vector2(5, 5),  null),
    HEAVY("heavyBoat",   3,     1,              4,          3,      2,          1,          new Vector2(5, 5),  null),
    LIGHT("lightBoat",   3,     3,              1,          1,      1,          3,          new Vector2(5, 5),  null),
    AGILE("agileBoat",   3,     2,              2,          2,      2,          3,          new Vector2(5, 5),  null),
    STRONG("strongBoat", 3,     2,              3,          3,      3,          1,          new Vector2(5, 5),  null);

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
        // this.image = new Texture(imageSrc);
    }

    public String getID() {return this.ID;}
    public float getSpeed() {return this.speed;}
    public float getAcceleration() {return this.acceleration;}
    public float getMaxHealth() {return this.maxHealth;}
    public float getWeight() {return this.weight;}
    public float getHandling() {return this.handling;}
    public Vector2 getSize() {return this.size;}
    public Texture getImage() {return this.image;}
}