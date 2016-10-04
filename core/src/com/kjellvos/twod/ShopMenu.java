package com.kjellvos.twod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.twod.inventory.InventoryScreen;
import com.kjellvos.twod.inventory.ShopScreen;

public class ShopMenu implements Screen, InputProcessor{
	TwoD game;
	OrthographicCamera camera;
	SpriteBatch batch;
	BitmapFont font;
    Actor shopScreen, inventoryScreen;	
	FitViewport viewport;
	public DragAndDrop dnd;
	InputMultiplexer inputMultiplexer;
	public Stage stage;
	
	
	public ShopMenu(TwoD game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		camera = new OrthographicCamera();
		viewport = new FitViewport(1024, 786, camera);
        viewport.apply();
		stage = new Stage(viewport, batch);
		
		stage.setDebugAll(true);
		
		shopScreen = new ShopScreen();
		shopScreen.setBounds(512, 0, 512, 786);
		
		inventoryScreen = new InventoryScreen();
		inventoryScreen.setBounds(0, 0, 512, 786);
		
		stage.addActor(inventoryScreen);
		stage.addActor(shopScreen);
		dnd = new DragAndDrop();        
        
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);
	}

	public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
	}

	public void render(float delta) {
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();

		stage.draw();
		String moneyString = Integer.toString(TwoD.getPlayer().getMoney());
		batch.begin();
		font.draw(batch, moneyString, 110, 670);
		batch.end();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	public void pause() {
	}

	public void resume() {
		
	}

	public void hide() {
		
	}

	public void dispose() {
		
	}
	
	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.G){
    	   game.setScreen(TwoD.getMain());
       	}
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	public boolean scrolled(int amount) {
		return false;
	}
}
