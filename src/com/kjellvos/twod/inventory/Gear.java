package com.kjellvos.twod.inventory;

import com.kjellvos.twod.TwoD;

public class Gear {
	private int[][] gearPositions;
	public GearItem[] gearItems;
	
	public Gear(){
		gearPositions = new int[7][2];
		gearPositions[0][0] = 745;
		gearPositions[0][1] = 570;
		gearPositions[1][0] = 645;
		gearPositions[1][1] = 440;
		gearPositions[2][0] = 745;
		gearPositions[2][1] = 440;
		gearPositions[3][0] = 835;
		gearPositions[3][1] = 440;
		gearPositions[4][0] = 745;
		gearPositions[4][1] = 305;
		gearPositions[5][0] = 690;
		gearPositions[5][1] = 155;
		gearPositions[6][0] = 790;
		gearPositions[6][1] = 155;
		
		gearItems = new GearItem[7];
		
		for (int i = 0; i < gearItems.length; i++) {
			gearItems[i] = new GearItem(0, null);
			GearItemActor actor = new GearItemActor(gearPositions[i][0], gearPositions[i][1], i);
			GearItemTarget target = new GearItemTarget(actor);
			GearItemSource source = new GearItemSource(actor);
			TwoD.getMenu().dnd.addTarget(target);
			TwoD.getMenu().dnd.addSource(source);
			TwoD.getMenu().stage.addActor(actor);
		}
	}
}
