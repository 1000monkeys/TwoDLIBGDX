package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SteerableComponent implements Component, Steerable<Vector2>{
	public Vector2 linearVelocity = new Vector2(0, 0);
	public float orientation;
	public float angularVelocity;
	public float maxLinearSpeed = 1;
	public float maxLinearAcceleration = 3;
	public float maxAngularSpeed = 1;
	public float maxAngularAcceleration = 1;
	public boolean independentFacing = true;
    public float x = 0.0f;
    public float y = 0.0f;
    public float spawnX = 0.0f;
    public float spawnY = 0.0f;
    
    public SteerableComponent (float x, float y) {
    	this.x = x;
    	this.y = y;
    	this.spawnX = x;
    	this.spawnY = y;
    }
	
	public float getZeroLinearSpeedThreshold () {
		return 0.001f;
	}
	
    public static float calculateOrientationFromLinearVelocity (Steerable<Vector2> character) {
        // If we haven't got any velocity, then we can do nothing.
        if (character.getLinearVelocity().isZero(MathUtils.FLOAT_ROUNDING_ERROR))
            return character.getOrientation();

        return character.vectorToAngle(character.getLinearVelocity());
    }

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}

	@Override
	public float getOrientation() {
		return orientation;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}

	@Override
	public float getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public float getBoundingRadius() {
		return 0;
	}

	@Override
	public boolean isTagged() {
		return false;
	}

	@Override
	public void setTagged(boolean tagged) {
	}

	@Override
	public Vector2 newVector() {
		return new Vector2();
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}
}
