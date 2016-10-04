package com.kjellvos.twod.inventory;

public class DragObject {
	public int itemId;
	public int positionId;
	public String caller;
	
	public DragObject(int itemId, int positionId, String caller) {
		this.itemId = itemId;
		this.positionId = positionId;
		this.caller = caller;
	}
}
