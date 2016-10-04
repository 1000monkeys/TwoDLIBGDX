package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SwordSwingComponent implements Component {
	public Sprite 	upSprite = new Sprite(new Texture("SwordUp.png")),
					downSprite = new Sprite(new Texture("SwordDown.png")),
					leftSprite = new Sprite(new Texture("SwordLeft.png")),
					rightSprite = new Sprite(new Texture("SwordRight.png")),
					goldenUpSprite = new Sprite(new Texture("GoldenSwordUp.png")),
					goldenDownSprite = new Sprite(new Texture("GoldenSwordDown.png")),
					goldenLeftSprite = new Sprite(new Texture("GoldenSwordLeft.png")),
					goldenRightSprite = new Sprite(new Texture("GoldenSwordRight.png"));
	
	public int x = 0;
	public int y = 0;
}
