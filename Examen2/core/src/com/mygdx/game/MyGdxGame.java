package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class MyGdxGame implements ApplicationListener {
	Texture dropImage;
	Texture bowserImage;

	Texture im;
	Texture img2;
	Texture im3;
	Texture im4;
	Texture  im5;
	Sound fireSound;
	Music fireMusic;

	OrthographicCamera camera;
	SpriteBatch batch;

	Rectangle fire;

	Array<Rectangle> fireDrops;
	long lastfireTime;

	@Override
	public void create() {

		dropImage = new Texture(Gdx.files.internal("fires.png"));
		bowserImage = new Texture(Gdx.files.internal("Pbowser.png"));

		im = new Texture("grass.png");
		img2= new Texture("background.png");
		im3= new Texture("cloud.png");
		im4= new Texture("littleshrooms_0.png");
		im5= new Texture("tree2-final.png");
		// load sound & music from assets
		fireSound = Gdx.audio.newSound(Gdx.files.internal("sfx.wav"));
		fireMusic = Gdx.audio.newMusic(Gdx.files.internal("fire.wav"));

		// start the background music now
		fireMusic.setLooping(true);
		fireMusic.play();

		// create orthographic camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a sprite batch
		batch = new SpriteBatch();


		fire = new Rectangle();
		fire.x = 800 / 2 - 48 / 2;
		fire.y = 20;
		fire.width = 48;
		fire.height = 48;




	}



	@Override
	public void render() {



		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

		// update the camera
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Gdx.app.log("Manpreet-gdx", "start drawing ...");
		batch.draw(img2, 0, 0);
		batch.draw(im,0,0);
		batch.draw(im3, 150,250);
		batch.draw(im4, 85,85 );
		batch.draw(im5, 300,500 );

		batch.draw(bowserImage, fire.x, fire.y);

		batch.end();

		// move
		if (Gdx.input.isTouched()) {

			Vector3 touchPos = new Vector3();
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		fire.x = touchPos.x - 48 / 2;
		if (fire.x < 0) {
			fire.x = 0;
			Gdx.input.vibrate(2000);
		}if (fire.x > 600){
			fire.x = 600;
				Gdx.input.vibrate(2000);
	}}





		}


	public void dispose(){
		// dispose all inactive resources
		bowserImage.dispose();
		dropImage.dispose();
		fireSound.dispose();
		fireMusic.dispose();
		batch.dispose();
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	@Override
	public void resume() {
	}
}
