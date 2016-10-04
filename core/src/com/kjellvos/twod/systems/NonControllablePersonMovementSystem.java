package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kjellvos.twod.SeekablePoint;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.BodyComponent;
import com.kjellvos.twod.components.NonControllablePersonSteerableComponent;

public class NonControllablePersonMovementSystem extends EntitySystem{
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<NonControllablePersonSteerableComponent> cmstc = ComponentMapper.getFor(NonControllablePersonSteerableComponent.class);
	private ComponentMapper<BodyComponent> cmbc = ComponentMapper.getFor(BodyComponent.class);
	
	
	SteeringBehavior<Vector2> steeringBehavior;
	private static SteeringAcceleration<Vector2> steeringOutput;
	
	public void addedToEngine(Engine engine){
		entities = engine.getEntitiesFor(Family.all(NonControllablePersonSteerableComponent.class, BodyComponent.class).get());
	}
	
	public void update(float delta){
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			
			NonControllablePersonSteerableComponent sc = cmstc.get(entity);
			BodyComponent bc = cmbc.get(entity);
			steeringOutput = new SteeringAcceleration<Vector2>(sc.linearVelocity);
			if (sc.spawnX - 160 < sc.x && sc.spawnX + 160 > sc.x && sc.spawnY - 160 < sc.y && sc.spawnY + 160 > sc.y) {
				steeringBehavior = new Wander<Vector2>(sc).setWanderRadius(1f).setWanderRate(MathUtils.PI2 * 4);			
			}else{
				SeekablePoint target = new SeekablePoint(sc.spawnX, sc.spawnY);
				steeringBehavior = new Seek<Vector2>(sc, target);
			}
			steeringBehavior.calculateSteering(steeringOutput);
	
			boolean anyAccelerations = false;
			if (!steeringOutput.linear.isZero()) {
				bc.body.applyForceToCenter(steeringOutput.linear, true);
				anyAccelerations = true;
			}

			if (sc.independentFacing) {
				if (steeringOutput.angular != 0) {
					bc.body.applyTorque(steeringOutput.angular, true);
					anyAccelerations = true;
				}
			} else {
				Vector2 linVel = sc.getLinearVelocity();
				if (!linVel.isZero(sc.getZeroLinearSpeedThreshold())) {
					float newOrientation = sc.vectorToAngle(linVel);
					bc.body.setAngularVelocity((newOrientation - sc.getAngularVelocity()) * delta); // this is superfluous if independentFacing is always true
					bc.body.setTransform(bc.body.getPosition(), newOrientation);
				}
			}

			if (anyAccelerations) {
				Vector2 velocity = bc.body.getLinearVelocity();
				float currentSpeedSquare = velocity.len2();
				float maxLinearSpeed = sc.getMaxLinearSpeed();
				if (currentSpeedSquare > maxLinearSpeed * maxLinearSpeed) {
					bc.body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float)Math.sqrt(currentSpeedSquare)));
				}

				float maxAngVelocity = sc.getMaxAngularSpeed();
				if (bc.body.getAngularVelocity() > maxAngVelocity) {
					bc.body.setAngularVelocity(maxAngVelocity);
				}
			}
			sc.x = bc.body.getPosition().x * TwoD.PPM;
			sc.y = bc.body.getPosition().y * TwoD.PPM;
		}
	}
}
