package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum ObstacleType {
    //      ID,             Weight, Size,                   imageSrc,   mover
    ROCK(   "rock",         4,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC),
    BRANCH( "branch" ,      1,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC),
    DUCK(   "duck",         2,      new Vector2(1,1) ,      null,       MovementCharacteristics.STATIC),
    LONGBOI("longboi",      2,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC),
    BOAT(   "boat",         5,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC),
    RUBBISH("rubbish" ,     1,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC),
    BUOY(   "buoy",         3,      new Vector2(1,1),       null,       MovementCharacteristics.STATIC);

    String ID;
    float weight;
    Vector2 size;
    Texture image;
    MovementCharacteristics mover; 

    private ObstacleType(String ID, float weight, Vector2 size, String imageSrc, MovementCharacteristics mover){
        this.ID = ID;
        this.weight = weight;
        this.size = size;
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

    public Texture getImage(){
        return this.image;
    }
}