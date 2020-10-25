package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public enum ObstacleType {
    //ID, Weight, Size, imageSrc, mover
    branch("branch", 1, new Vector2(1,1), null, new MovementCharacteristics());

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