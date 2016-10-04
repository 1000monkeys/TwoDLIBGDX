package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.kjellvos.twod.DamageText;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.BodyComponent;
import com.kjellvos.twod.components.HealthComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.SpriteComponent;
import com.kjellvos.twod.components.SteerableComponent;
import com.kjellvos.twod.components.SwordSwingComponent;

public class ControllableAttackSystem extends EntitySystem {
	private ImmutableArray<Entity> player;
	private ImmutableArray<Entity> monsters;
	
	private ComponentMapper<PlayerSteerableComponent> cmpstc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	private ComponentMapper<SwordSwingComponent> cmssc = ComponentMapper.getFor(SwordSwingComponent.class); 
	private ComponentMapper<SpriteComponent> cmsc = ComponentMapper.getFor(SpriteComponent.class);
	
	private ComponentMapper<HealthComponent> cmhc = ComponentMapper.getFor(HealthComponent.class);
	private ComponentMapper<SteerableComponent> cmstc = ComponentMapper.getFor(SteerableComponent.class);
	private ComponentMapper<BodyComponent> cmbc = ComponentMapper.getFor(BodyComponent.class);
	
	public void addedToEngine(Engine engine){
		player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class, BodyComponent.class, SpriteComponent.class, SwordSwingComponent.class, SpriteComponent.class).get());
		monsters = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
	}
	
	public void update(float delta){
		if (TwoD.getMain().fPressed) {
			Entity playerEntity = player.get(0);
			PlayerSteerableComponent pstc = cmpstc.get(playerEntity);
			SwordSwingComponent pssc = cmssc.get(playerEntity);
			SpriteComponent psc = cmsc.get(playerEntity);
			
			Entity lowestHealthEntity = null;
			HealthComponent lowestHc = null;
			SteerableComponent lowestSc = null;
			
			long millis = TimeUtils.millis();
			if (TwoD.getPlayer().getTimeSinceLastAttack() + 250 < millis) {
				for (int i = 0; i < monsters.size(); i++) {
					Entity entity = monsters.get(i);
					
					SteerableComponent sc = cmstc.get(entity);
					HealthComponent hc = cmhc.get(entity);
					BodyComponent bc = cmbc.get(entity);
					if (sc.x > pstc.x - 100 && pstc.x + 100 > sc.x && sc.y > pstc.y - 100 && pstc.y + 100 > sc.y) {
						if (lowestHealthEntity != null && lowestHc != null) {
							if (hc.health < lowestHc.health) {
								lowestHealthEntity = entity;
							}
						}else{
							lowestHealthEntity = entity;
							lowestHc = cmhc.get(lowestHealthEntity);
							lowestSc = cmstc.get(lowestHealthEntity);
						}
					}
				}
				if (lowestSc != null && lowestSc.x > pstc.x - (psc.sprite.getWidth() / 2) && pstc.x + (psc.sprite.getWidth() / 2) > lowestSc.x && lowestSc.y > pstc.y - (psc.sprite.getHeight() / 2) && lowestSc.y > pstc.y + (psc.sprite.getHeight() / 2)) {
					pssc.x = 0;
					pssc.y = -1;
				}else if (lowestSc != null && lowestSc.x > pstc.x - (psc.sprite.getWidth() / 2) && pstc.x + (psc.sprite.getWidth() / 2) > lowestSc.x && lowestSc.y < pstc.y - (psc.sprite.getHeight() / 2) && lowestSc.y < pstc.y + (psc.sprite.getHeight() / 2)) {
					pssc.x = 0;
					pssc.y = 1;
				}else if (lowestSc != null && lowestSc.x > pstc.x - (psc.sprite.getWidth() / 2) && lowestSc.x > pstc.x + (psc.sprite.getWidth() / 2) && lowestSc.y > pstc.y - (psc.sprite.getHeight() / 2) && pstc.y + (psc.sprite.getHeight() / 2) > lowestSc.y) {
					pssc.x = 1;
					pssc.y = 0;
				}else if (lowestSc != null && lowestSc.x < pstc.x - (psc.sprite.getWidth() / 2) && lowestSc.x < pstc.x + (psc.sprite.getWidth() / 2) && lowestSc.y > pstc.y - (psc.sprite.getHeight() / 2) && pstc.y + (psc.sprite.getHeight() / 2) > lowestSc.y) {
					pssc.x = -1;
					pssc.y = 0;
				}
				if (lowestHealthEntity != null && lowestHc != null) {
					int attack = TwoD.getPlayer().getAttack();
					TwoD.getMain().stage.addActor(new DamageText(Integer.toString(attack), lowestSc.x, lowestSc.y));
					lowestHc.health -= attack;
					TwoD.getPlayer().setTimeSinceLastAttack(millis);
				}
			}
		}
	}
}
