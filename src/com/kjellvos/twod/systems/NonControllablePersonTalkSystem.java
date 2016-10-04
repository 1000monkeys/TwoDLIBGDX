package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.NonControllablePersonSteerableComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.TalkComponent;

public class NonControllablePersonTalkSystem extends EntitySystem{
	SpriteBatch batch;
	BitmapFont font;
	GlyphLayout layout;
	ShapeRenderer shapeRenderer;

	private ImmutableArray<Entity> entities, player;

	private ComponentMapper<TalkComponent> cmtc = ComponentMapper.getFor(TalkComponent.class);
	private ComponentMapper<NonControllablePersonSteerableComponent> cmstc = ComponentMapper.getFor(NonControllablePersonSteerableComponent.class);
	
	private ComponentMapper<PlayerSteerableComponent> cmpsc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	
	public NonControllablePersonTalkSystem(SpriteBatch batch) {
		this.batch = batch;
		font = new BitmapFont();
		layout = new GlyphLayout();
		shapeRenderer = new ShapeRenderer();
	}

	public void addedToEngine(Engine engine){
		player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class).get());
		entities = engine.getEntitiesFor(Family.all(TalkComponent.class).get());
	}
	
	public void update(float delta){
		Entity playerEntity = player.get(0);
		
		PlayerSteerableComponent psc = cmpsc.get(playerEntity);
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			
			TalkComponent tc = cmtc.get(entity);
			NonControllablePersonSteerableComponent ncpstc = cmstc.get(entity);
			if (ncpstc.x - 160 < psc.getPosition().x && psc.getPosition().x < ncpstc.x + 160 && ncpstc.y - 210 < psc.getPosition().y && psc.getPosition().y < ncpstc.y + 210) {
				layout.setText(font, tc.textToSay);
				float x = ncpstc.x - (layout.width / 2);
				float y = ncpstc.y + layout.height + 15;
				batch.end();
				shapeRenderer.setProjectionMatrix(TwoD.getMain().camera.combined);
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.WHITE);
			    shapeRenderer.rect(x - 13, y + 2, 26 + layout.width, 26 + layout.height);
			    shapeRenderer.setColor(Color.BLACK);
			    shapeRenderer.rect(x - 10, y + 5, 20 + layout.width, 20 + layout.height);
			    shapeRenderer.end();
			    batch.begin();
				font.draw(batch, layout, x, y + 25);
			}
		}
	}
}
