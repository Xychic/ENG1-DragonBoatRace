package com.dragonboatrace.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	
	DragonBoatRace game;

    public GameScreen(DragonBoatRace game, int round){
		this.game = game;
		this.create(round);
    }
	
	Texture img;
	PlayerBoat pb;
	ArrayList<Obstacle> obstacleList;
	ObstacleType[] obstacles;
	Obstacle collider;
	LaneMarker[] laneMarkers;
	Background[] backgrounds;
	int round, maxObstacles, laneCount;
	Texture tmp;
	CPUBoat[] CPUs;

	@Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ESCAPE) {
                    System.exit(0);;
                }
                return true;
            }
        });
    }

	public void create(int round) {
		obstacles = new ObstacleType[]{ObstacleType.BUOY, ObstacleType.ROCK, ObstacleType.BRANCH, ObstacleType.DUCK, ObstacleType.RUBBISH, ObstacleType.LONGBOI, ObstacleType.BOAT};	// The 
		laneCount = 7;
		laneMarkers = new LaneMarker[laneCount+1];
		for (int i=0; i<laneCount+1;i++) {
			laneMarkers[i] = new LaneMarker(new Vector2(i * Gdx.graphics.getWidth() / (laneCount), 0));
		}
		CPUs = new CPUBoat[laneCount-1];
		
		for (int i = 0; i<laneCount-1; i++){
			int xpos = i;
			if(i >= (laneCount-1)/2){
				xpos += 1;
			}
			CPUs[i] = new CPUBoat(BoatType.NORMAL, new Vector2( (int) (0.5 + xpos)*(Gdx.graphics.getWidth()/laneCount) ,10), round ,new Vector2(0,0));
		}

		int backgroundCount = 5;
		backgrounds = new Background[backgroundCount];
		for (int i=0; i<backgroundCount; i++){
			backgrounds[i] = new Background(new Vector2(Gdx.graphics.getWidth()/2 , i*270));
		}

		pb = new PlayerBoat(BoatType.FAST, new Vector2(Gdx.graphics.getWidth()/2, 10));	// Creating the players boat

		obstacleList = new ArrayList<Obstacle>();	// Creating the empty arrayList of obstacles

		this.round = round;		// temp hard coding, will be moved to a screen.
		switch (round) {	// The max number of obstacles changes from round to round
			case 0:
				maxObstacles = 30;
				break;
			case 1:
				maxObstacles = 45;
				break;
			case 2:
				maxObstacles = 60;
				break;
			case 3:
				maxObstacles = 90;
				break;
			default:
				maxObstacles = 0;
				break;
		}
	}


	public void render(float delta) {
		float deltaTime = Gdx.graphics.getDeltaTime();	// Getting time since last frame
		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (Background b : backgrounds){
			b.render(game.batch, pb.getInGamePos());
		}

		for (LaneMarker l : laneMarkers) {
			l.render(game.batch, pb.getInGamePos());
		}
		

		game.batch.begin();	// Start drawing HUD (For debugging)
		String debugString = String.format("stamina: %f\nhealth: %f\npos.x: %f\npos.y: %f\nvel.x: %f\nvel.y: %f\nmaxSpeed: %f\nhealth: %f\nobstacles: %d\ncolliding: %s", 
			pb.getStamina(),
			pb.getHealth(),
			pb.getPos().x, 
			pb.getPos().y, 
			pb.getVel().x,
			pb.getVel().y,
			pb.getMaxSpeed(),
			pb.getHealth(),
			obstacleList.size(),
			collider != null ? collider.getType().getID() : "null");
		game.font.draw(game.batch, debugString, 10, Gdx.graphics.getHeight() - 10);
		game.batch.end();

		pb.render(game.batch);	// Render the boat
		pb.move(deltaTime);	// Move the boat based on player inputs
		pb.update(deltaTime);	// Update the position of the boat 

		for (CPUBoat c : CPUs){
			c.render(game.batch, pb.getInGamePos());
			c.move(deltaTime);
			c.update(deltaTime);
		}

		Iterator<Obstacle> obstacleIterator = obstacleList.iterator(); 	// Create iterator for iterating over the obstacles
		
		collider = null;

		while (obstacleIterator.hasNext()) {	// While there is another obstacle, continute iterating
			Obstacle o = obstacleIterator.next();	// Get the next obstacle
			Vector2 renderPos = o.getRelPos(pb.getInGamePos());	// Get the position of the obstacle, relative to the players boat
			if (renderPos.x > Gdx.graphics.getWidth() +30||
				renderPos.x + o.size.x < -30 ||
				// renderPos.y > Gdx.graphics.getHeight() +10 ||
				renderPos.y + o.size.y < -100) {
					obstacleIterator.remove();	// If the obstacles is off the screen (apart from the top) delete it
			} else {
				o.render(game.batch, pb.getInGamePos());	// If the obstacle is not off the screen, render it
				o.move(deltaTime);	// Run the obstacles mover
				o.update(deltaTime);	// Update the position of the obstacle
				if (pb.checkCollision(o)) {	// See if the players boat is colliding with the obstacle
					collider = o;
				}

				for (CPUBoat c : CPUs) {c.checkCollision(o);}
			}
		}
		if (obstacleList.size() < maxObstacles) {
			obstacleList.add(spawnObstacle());
		}
	}

	private Obstacle spawnObstacle() {
		Vector2 spawnPos;	// position the obstacle will spawn at
		Vector2 dir;		// direction the obstacle will start travelling in
		ObstacleType obs;	// type of the obstacle that will be spawned
		int obstacleChoice;	// index of the type of obstacle that will be spawned 
		int side;			// the side of the screen the obstacle will spawn at
		Random rand = new Random();

		switch (round) {	// The types of obstacles that can spawn is dependant on the round
			case 0:
				obstacleChoice = rand.nextInt(2);	// The following can be spawned in round 0: {BUOY, ROCK} 
				break;
			case 1:
				obstacleChoice = rand.nextInt(4);	// The following can be spawned in round 1: {BUOY, ROCK, BRANCH, DUCK}
				break;
			case 2:
				obstacleChoice = rand.nextInt(6);	// The following can be spawned in round 2: {BUOY, ROCK, BRANCH, DUCK, RUBBISH, LONGBOI}
				break;
			case 3:
				obstacleChoice = rand.nextInt(7);	// The following can be spawned in round 3: {BUOY, ROCK, BRANCH, DUCK, RUBBISH, LONGBOI, BOAT}
				break;
			default:
				obstacleChoice = -1;	// Should never be the case, -1 will cause an error, as there is something wrong.
				break;
		}
		
		obs = obstacles[obstacleChoice];	// Select the obstacle type from the list
		if (obs.getMover().getID() == "static") {	// Static moving obstacles can only be spawned from the top
			side = 0;
		} else {
			side = rand.nextInt(3);		// Any other obstacles can spawn on any other side
		}

		switch (side) {		// Creating a spawning position along the chosen edge
			case 0:
				spawnPos = new Vector2(	// If spawning along the top edge, pick a random x coord and a random y within the bounds of the screen, will be translated off screen
					rand.nextFloat() * Gdx.graphics.getWidth(), 
					(rand.nextFloat()) * Gdx.graphics.getHeight()
				);
				break;
			case 1:
				spawnPos = new Vector2(	// For the edge spawning, spawn slightly off screen but not enough to be deleted, and in the top 2/3rds of the side
					-10, 
					rand.nextFloat() * Gdx.graphics.getHeight() * 2/3
				);
				break;
			case 2:
				spawnPos = new Vector2(	// Same as above
					Gdx.graphics.getWidth() + 10, 
					rand.nextFloat() * Gdx.graphics.getHeight() * 2/3
				);
				break;
			default:	// By default spawn at 0,0
				spawnPos = new Vector2();	
				break;
		}
		spawnPos.y = 2 * Gdx.graphics.getHeight() - spawnPos.y + pb.getInGamePos().y;		// Translate 2 screens up and relative to the player

		dir = spawnPos.cpy().sub(new Vector2(	// Create a vector pointing from the spawn pos to a random point on the screen
			rand.nextFloat() * Gdx.graphics.getWidth(), 
			rand.nextFloat() * Gdx.graphics.getHeight())
		);	
		dir.limit(obs.getSpeed());	// Limit the vector to the max speed of the obstacle

		return new Obstacle(obs, spawnPos, dir);	// Return the new obstacle
	}

	private Boat checkWinner() {
		return null;	// TODO will return the winning boat, null if not over yet. 
	}
	
	@Override
	public void dispose() {
		game.batch.dispose();
		pb.dispose();
	}
}
