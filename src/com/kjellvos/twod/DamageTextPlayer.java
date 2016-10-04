package com.kjellvos.twod;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DamageTextPlayer extends Actor{
	BitmapFont font;
	private float x, y, transparency;
	private CharSequence text;
	
	public DamageTextPlayer(CharSequence text, float x, float y){
		this.text = text;
		this.x = x;
		this.y = y;
		font = new BitmapFont();
	}
	
	public void act(float delta){
		this.x -= 2.25f;
		transparency += 0.007f;
		font.setColor(1, 1, 0, 1 - transparency);
		if (transparency >= 1) {
			TwoD.getMain().stage.getActors().removeValue(this, true);
		}
	}
	
	public void draw(Batch batch, float parentAlpha){
		font.draw(batch, text, x, y);
	}
}