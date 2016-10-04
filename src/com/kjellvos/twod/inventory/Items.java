package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Items {
	public Sprite[] itemsSprites;
	public int[] itemsSellPrice, itemsBuyPrice;
	public Items(){
		itemsSprites = new Sprite[8];
		itemsSprites[0] = null;
		itemsSprites[1] = new Sprite(new Texture("GoldenSwordUp.png"));
		itemsSprites[2] = new Sprite(new Texture("IronGear.png"));
		itemsSprites[3] = new Sprite(new Texture("LeatherHelm.png"));
		itemsSprites[4] = new Sprite(new Texture("LeatherPants.png"));
		itemsSprites[5] = new Sprite(new Texture("LeftLeatherShoe.png"));
		itemsSprites[6] = new Sprite(new Texture("NormalSpellbook.png"));
		itemsSprites[7] = new Sprite(new Texture("RightLeatherShoe.png"));
		
		itemsSellPrice = new int[8];
		itemsSellPrice[0] = 0;
		itemsSellPrice[1] = 550;
		itemsSellPrice[2] = 350;
		itemsSellPrice[3] = 75;
		itemsSellPrice[4] = 75;
		itemsSellPrice[5] = 50;
		itemsSellPrice[6] = 125;
		itemsSellPrice[7] = 50;
		
		itemsBuyPrice = new int[8];
		itemsBuyPrice[0] = 0;
		itemsBuyPrice[1] = 500;
		itemsBuyPrice[2] = 300;
		itemsBuyPrice[3] = 50;
		itemsBuyPrice[4] = 50;
		itemsBuyPrice[5] = 25;
		itemsBuyPrice[6] = 100;
		itemsBuyPrice[7] = 25;
		
	}
}
