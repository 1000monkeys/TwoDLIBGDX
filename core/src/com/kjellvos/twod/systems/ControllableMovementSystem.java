package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.BodyComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.SpriteComponent;

public class ControllableMovementSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<PlayerSteerableComponent> cmpsc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	private ComponentMapper<BodyComponent> cmbc = ComponentMapper.getFor(BodyComponent.class);
	private ComponentMapper<SpriteComponent> cmsc = ComponentMapper.getFor(SpriteComponent.class);
	
	public void addedToEngine(Engine engine){
		entities = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class, BodyComponent.class, SpriteComponent.class).get());
	}
	
	public void update(float delta){
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			
			PlayerSteerableComponent psc = cmpsc.get(entity);
			BodyComponent bc = cmbc.get(entity);
			SpriteComponent sc = cmsc.get(entity);
			
			int horizontalForce = 0, verticalForce = 0;
			if (TwoD.getMain().upPressed) {
				verticalForce += 1;
			}
			if (TwoD.getMain().downPressed) {
				verticalForce -= 1;		
			}
			if (TwoD.getMain().rightPressed) {
				horizontalForce += 1;
			}
			if (TwoD.getMain().leftPressed) {
				horizontalForce -= 1;
			}
	        bc.body.setLinearVelocity(horizontalForce, verticalForce);
	        psc.x = bc.body.getPosition().x * TwoD.PPM;
	        psc.y = bc.body.getPosition().y * TwoD.PPM;
	        sc.sprite.setX(bc.body.getPosition().x * TwoD.PPM);
	        sc.sprite.setY(bc.body.getPosition().y * TwoD.PPM);
	        
			Vector3 cameraPosition = TwoD.getMain().camera.position;
			cameraPosition.x = bc.body.getPosition().x * TwoD.PPM;
			cameraPosition.y = bc.body.getPosition().y * TwoD.PPM;
			TwoD.getMain().camera.position.set(cameraPosition);
		}
	}
}
