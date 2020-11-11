package com.dragonboatrace.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	PlayerBoat pb;
	ArrayList<Obstacle> obstacleList;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		pb = new PlayerBoat(BoatType.NORMAL, new Vector2(1920/2, 10));

		obstacleList = new ArrayList<Obstacle>();
		obstacleList.add(new Obstacle(ObstacleType.ROCK, new Vector2(900, 700)));
		obstacleList.add(new Obstacle(ObstacleType.DUCK, new Vector2(800, 500)));

	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
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
			pb.getCollider() != null ? pb.getCollider().getType().getID() : "null");
		font.draw(batch, debugString, 10, Gdx.graphics.getHeight() - 10);
		batch.end();

		pb.render(batch);
		pb.move(deltaTime);
		pb.update(deltaTime);

		Iterator<Obstacle> obstacleIterator = obstacleList.iterator(); 

		while (obstacleIterator.hasNext()) {
			Obstacle o = obstacleIterator.next();
			if (o.getInGamePos().x > Gdx.graphics.getWidth() ||
				o.getInGamePos().x + o.size.x < 0 ||
				o.getInGamePos().y > Gdx.graphics.getHeight() ||
				o.getInGamePos().y + o.size.y < 0) {
					// obstacleIterator.remove();	// TODO remove when so far from player
			} else {
				o.render(batch, pb.getInGamePos());
				o.move(deltaTime);
				o.update(deltaTime);
				pb.checkCollision(o);
			}
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		pb.dispose();
	}
}
