package com.dragonboatrace.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	PlayerBoat pb;
	Obstacle rock, branch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		pb = new PlayerBoat(BoatType.NORMAL, new Vector2(1920/2, 10));
		rock = new Obstacle(ObstacleType.ROCK, new Vector2(900, 700));
		branch = new Obstacle(ObstacleType.DUCK, new Vector2(800, 500));
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		pb.render(batch);
		pb.move(deltaTime);
		pb.update(deltaTime);

		rock.render(batch, pb.getInGamePos());
		rock.move(deltaTime);
		rock.update(deltaTime);

		branch.render(batch, pb.getInGamePos());
		branch.move(deltaTime);
		branch.update(deltaTime);

		pb.checkCollision(branch);
		pb.checkCollision(rock);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		pb.dispose();
		rock.dispose();
	}
}
