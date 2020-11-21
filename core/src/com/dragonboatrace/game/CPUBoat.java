package com.dragonboatrace.game;

import com.badlogic.gdx.math.Vector2;

public class CPUBoat extends Boat{
    
    int difficulty;
    Vector2 laneBarriers, startPos;

    public CPUBoat(BoatType boatType, Vector2 pos, int difficulty, Vector2 laneBarriers) {
        super(boatType, pos);
        this.difficulty = difficulty;
        this.laneBarriers = laneBarriers;
        this.startPos = pos;
    }

    @Override
    public void move(float deltaTime) {
        if (this.vel.y < this.currentMaxSpeed) {
            this.vel.add(0, ((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
        } else if (this.vel.y > this.currentMaxSpeed) {
            this.vel.add(0, -((this.boatType.getAcceleration() / 100) / (deltaTime * 60)));
        }

        if (this.currentHealth <= 0) {
            this.vel = new Vector2();
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
            this.pos.add(0, deltaY);
            this.inGamePos.add(0, deltaY);
            this.distanceTravelled += deltaY;
        }
    }

    public String getCurrentPos(){
        return this.pos.toString() + " , " + this.inGamePos.toString();
    }
    
    public void moveToStart(float y){
        //float y is the position of the player boat
        //this will move all the cpu boats up to the finish line and reset all of their round specific stats

        pos = startPos;
        inGamePos.y = y;
        vel = new Vector2();
        stamina = maxStamina; 
        distanceTravelled = 0;
        totalTime += finishTime;
        finishTime = 0;
        finished = false;
    }

}
