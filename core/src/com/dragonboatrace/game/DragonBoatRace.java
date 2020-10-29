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
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		pb = new PlayerBoat(BoatType.NORMAL, new Vector2(0, 0));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pb.render(batch);
		pb.move((float)10);
		pb.update((float) 1);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		pb.dispose();
	}
}
