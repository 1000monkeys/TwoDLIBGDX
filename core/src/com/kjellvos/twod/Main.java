package com.kjellvos.twod;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.twod.systems.ControllableAttackSystem;
import com.kjellvos.twod.systems.ControllableMovementSystem;
import com.kjellvos.twod.systems.LootSystem;
import com.kjellvos.twod.systems.NonControllableAttackSystem;
import com.kjellvos.twod.systems.NonControllableMovementSystem;
import com.kjellvos.twod.systems.NonControllablePersonMovementSystem;
import com.kjellvos.twod.systems.NonControllablePersonTalkSystem;
import com.kjellvos.twod.systems.RenderSystem;
import com.kjellvos.twod.systems.ShopKeeperSystem;

public class Main implements Screen, InputProcessor {
	public boolean leftPressed, rightPressed, upPressed, downPressed, fPressed, gPressed;
	
	TwoD game;
    public OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture;
    Sprite gearScreen, inventoryScreen;	
	public Stage stage;
	FitViewport viewport;
	public World world;
	Engine engine;
	public boolean isDead = false;
	BitmapFont font;
	Sprite wastedSprite;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	
	public Main(TwoD game) {
		this.game = game;
		
		camera = new OrthographicCamera();
        camera.update();

		viewport = new FitViewport(1024, 786, camera);
        viewport.apply();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new Box2dContactListener());       
        
        batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
		
		engine = new Engine();
		engine.addSystem(new NonControllableMovementSystem());
		engine.addSystem(new NonControllableAttackSystem());
		engine.addSystem(new RenderSystem(batch));
		engine.addSystem(new ControllableMovementSystem());
		engine.addSystem(new ControllableAttackSystem());
		engine.addSystem(new LootSystem());
		engine.addSystem(new NonControllablePersonMovementSystem());
		engine.addSystem(new ShopKeeperSystem(game));
		engine.addSystem(new NonControllablePersonTalkSystem(batch));
		
		wastedSprite = new Sprite(new Texture("Wasted.png"));
	}
	
	public void show() {
        Gdx.input.setInputProcessor(this);
        debugRenderer = new Box2DDebugRenderer();
	}

	public void render(float delta) {
        if (isDead) {
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        batch.draw(wastedSprite, camera.position.x - (wastedSprite.getWidth() / 2), camera.position.y - (wastedSprite.getHeight() / 2));
	        batch.end();
		}else{
	        world.step(1f/30f, 6, 2);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		    LevelManager.tiledMapRenderer.setView(camera);
			LevelManager.tiledMapRenderer.render();
			stage.act(delta);
			stage.draw();
		    
			batch.begin();
			engine.update(delta);
			batch.end();
        }
	    debugMatrix = batch.getProjectionMatrix().cpy().scale(TwoD.PPM, TwoD.PPM, 0);
        debugRenderer.render(world, debugMatrix);
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
		if(keycode == Input.Keys.A){
			leftPressed = true;
		}
        if(keycode == Input.Keys.D){
        	rightPressed = true;
        }
        if(keycode == Input.Keys.W){
        	upPressed = true;
        }
        if(keycode == Input.Keys.S){
        	downPressed = true;
        }
        if (keycode == Input.Keys.ESCAPE){
        	TwoD.getMenu().resume();
        	game.setScreen(TwoD.getMenu());
        }
        if (keycode == Input.Keys.SPACE) {
        	fPressed = true;
        }
        return false;
	}

	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.A){
			leftPressed = false;
		}
        if(keycode == Input.Keys.D){
        	rightPressed = false;
        }
        if(keycode == Input.Keys.W){
        	upPressed = false;
        }
        if(keycode == Input.Keys.S){
        	downPressed = false;
        }
        if (keycode == Input.Keys.SPACE) {
        	fPressed = false;
        }
        if(keycode == Input.Keys.G){
        	gPressed = true;
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
