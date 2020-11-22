package com.dragonboatrace.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public abstract class Boat extends Entity{

    protected BoatType boatType;
    protected float currentHealth, currentMaxSpeed;
    protected ArrayList<Obstacle> collided;
    protected float stamina, maxStamina, timePenalties, penaltyResetDelay;
    protected long finishTime;
    protected boolean finished = false;
    protected float distanceTravelled; //distance travelled in one round
    protected long totalTime;
    protected Tuple<Float, Float> laneBounds;

    protected Vector2 startPos;

    public Boat(BoatType boatType, Vector2 pos, Tuple<Float, Float> laneBounds) {
        super(pos.cpy(), boatType.getSize().cpy(), boatType.getWeight());
        this.boatType = boatType;
        this.currentHealth = this.boatType.getMaxHealth();
        this.currentMaxSpeed = this.boatType.getSpeed();
        this.collided = new ArrayList<Obstacle>();
        this.stamina = 1000;
        this.maxStamina = 1000;
        this.distanceTravelled = 0;
        this.totalTime = (long) 0;
        this.laneBounds = laneBounds;
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
            (this.pos.x), (this.pos.y-relPos.y),  
            this.size.x, this.size.y);
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

    public boolean checkFinished(int finishLine , long startTime){
        //finish line is the pixels from the start that the boats have to travel
        //start time is the system time when the race started
        
        if (this.finished){
            return true;
        }
        else if (this.isFinished(finishLine)){
            this.setFinishTime(System.currentTimeMillis() - startTime + (long)this.timePenalties);
            this.finished = true;
        }
        return this.finished;
    
    }

    public String getFinishTimeString() {
        if (this.finishTime == 0){
            //calculate an estimate dnf is just temporary?
            return "DNF";
        }
        else{
            //returns the finish time in minutes:seconds
            return String.valueOf((int) ((this.finishTime / 1000) / 60)) + ":" + String.valueOf((int) ((this.finishTime / 1000) % 60));
        }
    }

    public void saveStartPos() {
        this.startPos = this.pos.cpy();
    }

    public void resetPos() {
        this.pos = this.startPos.cpy();
        this.inGamePos = this.startPos.cpy();
        this.vel = new Vector2();
    }

    public boolean isFinished(int finishLine){return this.distanceTravelled > finishLine;}
    public void setFinishTime(long finishTime) {this.finishTime = finishTime;}

    public long getFinishTimeLong(){return finishTime;}
    public float getMaxSpeed() {return this.currentMaxSpeed;}
    public float getHealth() {return this.currentHealth;}
    public float getStamina() {return this.stamina;}
    public float getDistanceTravelled() {return this.distanceTravelled;}
}