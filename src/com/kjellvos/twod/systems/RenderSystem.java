package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.NonControllablePersonSteerableComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.PositionComponent;
import com.kjellvos.twod.components.SpriteComponent;
import com.kjellvos.twod.components.SteerableComponent;
import com.kjellvos.twod.components.SwordSwingComponent;

public class RenderSystem extends EntitySystem {
	private ImmutableArray<Entity> entities, player, loot, nonControllablePersons;
	
	private SpriteBatch batch;
	
	private ComponentMapper<SteerableComponent> cmstc = ComponentMapper.getFor(SteerableComponent.class);
	private ComponentMapper<SpriteComponent> cmsc = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<PlayerSteerableComponent> cmpstc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	private ComponentMapper<SwordSwingComponent> cmssc = ComponentMapper.getFor(SwordSwingComponent.class);
	private ComponentMapper<PositionComponent> cmpc = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<NonControllablePersonSteerableComponent> cmncpsc = ComponentMapper.getFor(NonControllablePersonSteerableComponent.class);
	
	public RenderSystem(SpriteBatch batch){
		this.batch = batch;
	}
	
	public void addedToEngine(Engine engine){
		nonControllablePersons = engine.getEntitiesFor(Family.all(SpriteComponent.class, NonControllablePersonSteerableComponent.class).get());
		player = engine.getEntitiesFor(Family.all(SpriteComponent.class, PlayerSteerableComponent.class).get());
		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, SteerableComponent.class).get());
		loot = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class).get());
	}
	
	public void update(float delta){
		for (int i = 0; i < loot.size(); i++) {
			Entity entity = loot.get(i);
			SpriteComponent sc = cmsc.get(entity);
			PositionComponent pc = cmpc.get(entity);
			
			batch.draw(sc.sprite, pc.x - sc.sprite.getWidth() / 2, pc.y - sc.sprite.getHeight() / 2);
		}
		for (int i = 0; i < nonControllablePersons.size(); i++) {
			Entity entity = nonControllablePersons.get(i);
			
			SpriteComponent sc = cmsc.get(entity);
			NonControllablePersonSteerableComponent stc = cmncpsc.get(entity);
			
			batch.draw(sc.sprite, stc.x - sc.sprite.getWidth() / 2, stc.y - sc.sprite.getHeight() / 2);
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			
			SpriteComponent sc = cmsc.get(entity);
			SteerableComponent stc = cmstc.get(entity);
			
			batch.draw(sc.sprite, stc.x - sc.sprite.getWidth() / 2, stc.y - sc.sprite.getHeight() / 2);
		}
		for (int i = 0; i < player.size(); i++) {
			Entity entity = player.get(i);
			
			SpriteComponent sc = cmsc.get(entity);
			PlayerSteerableComponent pstc = cmpstc.get(entity);
			SwordSwingComponent ssc = cmssc.get(entity);
			
			batch.draw(sc.sprite.getTexture(), pstc.x - sc.sprite.getWidth() / 2, pstc.y - sc.sprite.getHeight() / 2);
			if (TwoD.getPlayer().getTimeSinceLastAttack() + 125 > TimeUtils.millis()) {
				if (TwoD.getGear().gearItems[1].getItemId() == 0) {
					if (ssc.x == 0 && ssc.y < 0) {
						batch.draw(ssc.upSprite, pstc.x - ssc.upSprite.getWidth() / 2, pstc.y - ssc.upSprite.getHeight() / 2 + sc.sprite.getHeight() / 2 + ssc.upSprite.getHeight() / 2);
					}else if (ssc.x == 0 && ssc.y > 0) {
						batch.draw(ssc.downSprite, pstc.x - ssc.downSprite.getWidth() / 2, pstc.y - ssc.downSprite.getHeight() / 2 - sc.sprite.getHeight() / 2 - ssc.downSprite.getHeight() / 2);				
					}else if (ssc.x < 0 && ssc.y == 0) {
						batch.draw(ssc.leftSprite, pstc.x - ssc.leftSprite.getWidth() / 2 - sc.sprite.getWidth() / 2 - ssc.leftSprite.getWidth() / 2, pstc.y - ssc.leftSprite.getHeight() / 2);
					}else if (ssc.x > 0 && ssc.y == 0) {
						batch.draw(ssc.rightSprite, pstc.x - ssc.rightSprite.getWidth() / 2 + sc.sprite.getWidth() / 2 + ssc.downSprite.getWidth() / 2, pstc.y - ssc.rightSprite.getHeight() / 2);
					}
				}else if (TwoD.getGear().gearItems[1].getItemId() == 1) {
					if (ssc.x == 0 && ssc.y < 0) {
						batch.draw(ssc.goldenUpSprite, pstc.x - ssc.upSprite.getWidth() / 2, pstc.y - ssc.upSprite.getHeight() / 2 + sc.sprite.getHeight() / 2 + ssc.upSprite.getHeight() / 2);
					}else if (ssc.x == 0 && ssc.y > 0) {
						batch.draw(ssc.goldenDownSprite, pstc.x - ssc.downSprite.getWidth() / 2, pstc.y - ssc.downSprite.getHeight() / 2 - sc.sprite.getHeight() / 2 - ssc.downSprite.getHeight() / 2);				
					}else if (ssc.x < 0 && ssc.y == 0) {
						batch.draw(ssc.goldenLeftSprite, pstc.x - ssc.leftSprite.getWidth() / 2 - sc.sprite.getWidth() / 2 - ssc.leftSprite.getWidth() / 2, pstc.y - ssc.leftSprite.getHeight() / 2);
					}else if (ssc.x > 0 && ssc.y == 0) {
						batch.draw(ssc.goldenRightSprite, pstc.x - ssc.rightSprite.getWidth() / 2 + sc.sprite.getWidth() / 2 + ssc.downSprite.getWidth() / 2, pstc.y - ssc.rightSprite.getHeight() / 2);
					}
				}
			}
		}
	}
}
