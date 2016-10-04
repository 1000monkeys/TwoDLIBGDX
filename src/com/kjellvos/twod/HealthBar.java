package com.kjellvos.twod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kjellvos.twod.entities.Monster;

public class HealthBar extends Actor{
	private Sprite healthBarBg, healthBarFg;
	private Monster owner;
	
	public HealthBar(Monster owner){
		this.owner = owner;
		this.healthBarBg = new Sprite(new Texture("HealthBarBg.png"));
		this.healthBarFg = new Sprite(new Texture("HealthBarFg.png"));
	}
	
	public void draw(Batch batch, float parentAlpha){
		healthBarBg.draw(batch);
		healthBarFg.draw(batch);
	}
	
	public void act(float delta){
		Vector2 xyPos = owner.getXY();
		if (xyPos != null) {
			healthBarBg.setX(xyPos.x - (healthBarBg.getWidth() / 2));
			healthBarBg.setY(xyPos.y + (healthBarBg.getHeight()));
			healthBarFg.setX(xyPos.x - (healthBarBg.getWidth() / 2));
			healthBarFg.setY(xyPos.y + (healthBarBg.getHeight()));
		}
		if (1 > owner.getHealth()) {
			TwoD.getMain().stage.getActors().removeValue(this, true);
		}
		healthBarFg.setScale(owner.getHealth() / (float)owner.getMaxHealth(), 1f);
	}
}
