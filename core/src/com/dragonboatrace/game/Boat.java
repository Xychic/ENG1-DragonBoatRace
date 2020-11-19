package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public abstract class Boat extends Entity{

    protected BoatType boatType;
    protected float currentHealth, currentMaxSpeed;
    protected ArrayList<Obstacle> collided;
    protected float stamina, maxStamina;
    protected long finishTime;

    public Boat(BoatType boatType, Vector2 pos) {
        super(pos, boatType.getSize(), boatType.getWeight());
        this.boatType = boatType;
        this.currentHealth = this.boatType.getMaxHealth();
        this.currentMaxSpeed = this.boatType.getSpeed();
        this.collided = new ArrayList<Obstacle>();
        this.stamina = 1000;
        this.maxStamina = 1000;
    }

    public void collide(Obstacle o) {
        
    }

    public boolean checkCollision(Obstacle o) {
        boolean colliding = super.checkCollision(o);

        if (colliding) {
            if (!this.collided.contains(o)) {
                this.collided.add(o);
                this.currentHealth -= o.weight;
                this.currentMaxSpeed = this.boatType.getSpeed() / o.weight;
                this.stamina = Math.max(this.stamina - 100 * o.weight, 0);
            }
        } else if (this.collided.contains(o)) {
            this.collided.remove(o);
            this.currentMaxSpeed = this.boatType.getSpeed();
        }
        return colliding;
    }

    public void render(SpriteBatch batch, Vector2 relPos) {
        batch.begin();
        batch.draw(this.boatType.getImage(), 
        (this.pos.x-relPos.x), (this.pos.y-relPos.y),  
        this.boatType.getSize().x, this.boatType.getSize().y);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        this.currentMaxSpeed = this.boatType.getSpeed();
        if (this.collider != null) {this.currentMaxSpeed /= this.collider.weight;}
        super.update(deltaTime);
    }

    public void dispose() {
        this.boatType.getImage().dispose();
    }

    public void checkFinished(int finishLine, long startTime){
        if (this.isFinished(finishLine)){
            this.setFinishTime(startTime);
        }
    }
    
    public String getFinishTime() {
        if (this.finishTime == 0){
            //calculate an estimate dnf is just temporary?
            return "DNF";
        }
        else{
            //returns the finish time in minutes:seconds
            return String.valueOf((int) ((this.finishTime / 1000) / 60)) + ":" + String.valueOf((int) ((this.finishTime / 1000) % 60));
        }
    }

    public boolean isFinished(int finishLine){return this.pos.x > finishLine;}
    public void setFinishTime(long startTime) {this.finishTime = System.currentTimeMillis() - startTime;}

    public float getMaxSpeed() {return this.currentMaxSpeed;}
    public float getHealth() {return this.currentHealth;}
    public float getStamina() {return this.stamina;}
}