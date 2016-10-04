package com.kjellvos.twod;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;

public class SeekablePoint implements Steerable<Vector2>{
	private Vector2 position;
	private Vector2 linearVelocity = new Vector2(0, 0);
	private float orientation;
	private float angularVelocity = 0;
	private float maxLinearSpeed = 0;
	private float maxLinearAcceleration = 0;
	private float maxAngularSpeed = 0;
	private float maxAngularAcceleration = 0;

	public SeekablePoint(float x, float y){
		this.position = new Vector2(x, y);
	}
	
	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

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
		return position;
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
		return null;
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
