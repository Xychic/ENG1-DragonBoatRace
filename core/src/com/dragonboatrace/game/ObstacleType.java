package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum ObstacleType {
    //      ID,             Weight, Size,                   speed   imageSrc,           mover
    BUOY(   "buoy",         3,      new Vector2(50, 50),    0,      "badlogic.jpg",     MovementCharacteristics.STATIC),
    ROCK(   "rock",         4,      new Vector2(50, 50),    0,      "badlogic.jpg",     MovementCharacteristics.STATIC),
    BRANCH( "branch" ,  (float)1.5, new Vector2(50, 50),    1,      "badlogic.jpg",     MovementCharacteristics.CONSTANT),
    DUCK(   "duck",         2,      new Vector2(50, 50),    2,      "badlogic.jpg",     MovementCharacteristics.WANDER),
    RUBBISH("rubbish" , (float)1.5, new Vector2(50, 50),    3,      "badlogic.jpg",     MovementCharacteristics.STATIC),
    LONGBOI("longboi",      2,      new Vector2(50, 50),    4,      "badlogic.jpg",     MovementCharacteristics.STATIC),
    BOAT(   "boat",         5,      new Vector2(50, 50),    5,      "badlogic.jpg",     MovementCharacteristics.STATIC);

    String ID;
    float weight;
    Vector2 size;
    float speed;
    Texture image;
    MovementCharacteristics mover;

    private ObstacleType(String ID, float weight, Vector2 size, float speed, String imageSrc, MovementCharacteristics mover){
        this.ID = ID;
        this.weight = weight;
        this.size = size;
        this.speed = speed;
        this.image = new Texture(imageSrc);
        this.mover = mover;
    }

    public String getID(){
        return this.ID;
    }

    public float getWeight(){
        return this.weight;
    }

    public Vector2 getSize(){
        return this.size;
    }

    public float getSpeed() {
        return this.speed;
    }

    public Texture getImage(){
        return this.image;
    }

    public MovementCharacteristics getMover() {
        return this.mover;
    }
}