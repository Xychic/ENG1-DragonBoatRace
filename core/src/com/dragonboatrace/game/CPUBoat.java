package com.dragonboatrace.game;

import com.badlogic.gdx.math.Vector2;

public class CPUBoat extends Boat{
    
    int difficulty;
    Vector2 laneBarriers;

    public CPUBoat(BoatType boatType, Vector2 pos, int difficulty, Vector2 laneBarriers) {
        super(boatType, pos);
        this.difficulty = difficulty;
        this.laneBarriers = laneBarriers;
    }

    @Override
    public void move(float deltaTime) {
        this.vel.add(0, ((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
        this.stamina -= 2 / (60 *deltaTime);
        if (this.stamina < this.maxStamina) {
            this.stamina += 1 / (60 * deltaTime);
        }
    }

    //this method isnt used yet bc it sucks, I was going to use it to decide where to move the cpus intelligently but that's too hard so might just make them move randomly 
    public void decideMovement(float deltaTime, Obstacle[] obstacles){
        
        Obstacle closestObstacle = obstacles[0];

        for(Obstacle i : obstacles){
            Vector2 obstaclePos = i.getRelPos(pos);
            if(obstaclePos.y >0){
                if (obstaclePos.y < closestObstacle.getRelPos(pos).y){
                    closestObstacle = i;
                }
            }       
        }
    }

    @Override
    public void update(float deltaTime) {
        float deltaX = this.vel.x * this.dampening;
        float deltaY = this.vel.y * this.dampening;

        if (deltaX != 0) {
            this.pos.add(deltaX, 0);
            this.inGamePos.add(deltaX, 0);
            this.vel.add(-deltaX, 0);
        }
        if (deltaY != 0) {
            this.inGamePos.add(0, deltaY);
        }
    }

    public String getCurrentPos(){
        return this.pos.toString() + " , " + this.inGamePos.toString();
    }
 
}
