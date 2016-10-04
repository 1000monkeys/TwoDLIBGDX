package com.kjellvos.twod.inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.kjellvos.twod.TwoD;

public class InventoryItemSource extends Source{
	
	public InventoryItemSource(InventoryItemActor actor){
		super(actor);
	}

	public Payload dragStart(InputEvent event, float x, float y, int pointer) {
		Sprite item = TwoD.getInventory().items[((InventoryItemActor)super.getActor()).positionId].getSprite();
		if (item != null) {
			Payload payload = new Payload();
			payload.setDragActor(new DragActor(item));
			payload.setObject(new DragObject(TwoD.getInventory().items[((InventoryItemActor)super.getActor()).positionId].getItemId(), ((InventoryItemActor)super.getActor()).positionId, "InventoryItemSource"));
			TwoD.getInventory().items[((InventoryItemActor)super.getActor()).positionId].setSprite(null);
			TwoD.getInventory().items[((InventoryItemActor)super.getActor()).positionId].setItemId(0);
			return payload;
		}
		return null;
	}
	
	public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
		DragActor dragActor = (DragActor) payload.getDragActor();
		DragObject dragObject = (DragObject) payload.getObject();
		if (target != null && target.getClass() == InventoryItemTarget.class) {
			InventoryItemActor targetActor = (InventoryItemActor) target.getActor();
			if (TwoD.getInventory().items[targetActor.positionId].getItemId() == 0 && TwoD.getInventory().items[targetActor.positionId].getSprite() == null) {
				TwoD.getInventory().items[targetActor.positionId].setItemId(dragObject.itemId);
				TwoD.getInventory().items[targetActor.positionId].setSprite(dragActor.sprite);
			}else{
				if (dragObject.caller.equals("InventoryItemSource")) {
					TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("GearItemSource")) {
					TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("ShopItemSource")) {
					TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
				}
			}
		}else if (target != null && target.getClass() == GearItemTarget.class){
			GearItemActor targetActor = (GearItemActor) target.getActor();
			if (TwoD.getGear().gearItems[targetActor.positionId].getItemId() == 0 && TwoD.getGear().gearItems[targetActor.positionId].getSprite() == null) {
				if (targetActor.positionId == 0) {
					if (dragObject.itemId == 3) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("ShopItemSource")) {
							TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 1) {
					if (dragObject.itemId == 1) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 2) {
					if (dragObject.itemId == 2) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("ShopItemSource")) {
							TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 3) {
					if (dragObject.itemId == 6) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("ShopItemSource")) {
							TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 4) {
					if (dragObject.itemId == 4) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("ShopItemSource")) {
							TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 5) {
					if (dragObject.itemId == 5) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else if (targetActor.positionId == 6) {
					if (dragObject.itemId == 7) { //TODO add ors
						TwoD.getGear().gearItems[targetActor.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[targetActor.positionId].setSprite(dragActor.sprite);
					}else{
						if (dragObject.caller.equals("InventoryItemSource")) {
							TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("GearItemSource")) {
							TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
						}else if (dragObject.caller.equals("ShopItemSource")) {
							TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
							TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
						}
					}
				}else{
					if (dragObject.caller.equals("InventoryItemSource")) {
						TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
						TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
					}else if (dragObject.caller.equals("GearItemSource")) {
						TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
						TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
					}else if (dragObject.caller.equals("ShopItemSource")) {
						TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
						TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
					}
				}
			}else{
				if (dragObject.caller.equals("InventoryItemSource")) {
					TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("GearItemSource")) {
					TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("ShopItemSource")) {
					TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
				}
			}
		}else if (target != null && target.getClass() == ShopItemTarget.class){
			ShopItemActor targetActor = (ShopItemActor) target.getActor();
			if (TwoD.getShop().shopItems[targetActor.positionId].getItemId() == 0 && TwoD.getShop().shopItems[targetActor.positionId].getSprite() == null) {
				TwoD.getShop().shopItems[targetActor.positionId].setItemId(dragObject.itemId);
				TwoD.getShop().shopItems[targetActor.positionId].setSprite(dragActor.sprite);
				TwoD.getPlayer().setMoney(TwoD.getPlayer().getMoney() + TwoD.getItems().itemsBuyPrice[dragObject.itemId]);
			}else{
				if (dragObject.caller.equals("InventoryItemSource")) {
					TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("GearItemSource")) {
					TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
				}else if (dragObject.caller.equals("ShopItemSource")) {
					TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
					TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
				}
			}
		}else{
			if (dragObject.caller.equals("InventoryItemSource")) {
				TwoD.getInventory().items[dragObject.positionId].setItemId(dragObject.itemId);
				TwoD.getInventory().items[dragObject.positionId].setSprite(dragActor.sprite);
			}else if (dragObject.caller.equals("GearItemSource")) {
				TwoD.getGear().gearItems[dragObject.positionId].setItemId(dragObject.itemId);
				TwoD.getGear().gearItems[dragObject.positionId].setSprite(dragActor.sprite);
			}else if (dragObject.caller.equals("ShopItemSource")) {
				TwoD.getShop().shopItems[dragObject.positionId].setItemId(dragObject.itemId);
				TwoD.getShop().shopItems[dragObject.positionId].setSprite(dragActor.sprite);
			}
		}
	}
}
