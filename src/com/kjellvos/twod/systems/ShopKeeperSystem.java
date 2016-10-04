package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.NonControllablePersonSteerableComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.ShopKeeperComponent;

public class ShopKeeperSystem extends EntitySystem{
	TwoD game;
	
	private ImmutableArray<Entity> shopKeepers, player;
	
	private ComponentMapper<NonControllablePersonSteerableComponent> cmncpsc = ComponentMapper.getFor(NonControllablePersonSteerableComponent.class);
	private ComponentMapper<ShopKeeperComponent> cmskc = ComponentMapper.getFor(ShopKeeperComponent.class);
	
	private ComponentMapper<PlayerSteerableComponent> cmpsc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	
	public ShopKeeperSystem(TwoD game) {
		this.game = game;
	}

	public void addedToEngine(Engine engine){
		player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class).get());
		shopKeepers = engine.getEntitiesFor(Family.all(NonControllablePersonSteerableComponent.class, ShopKeeperComponent.class).get());
	}
	
	public void update(float delta){
		Entity playerEntity = player.get(0);
		
		PlayerSteerableComponent pstc = cmpsc.get(playerEntity);
		
		if (TwoD.getMain().gPressed) {
			for (int i = 0; i < shopKeepers.size(); i++) {
				Entity shopKeeper = shopKeepers.get(i);
				
				NonControllablePersonSteerableComponent ncpstc = cmncpsc.get(shopKeeper);
				
				if (ncpstc.x - 160 < pstc.getPosition().x && pstc.getPosition().x < ncpstc.x + 160 && ncpstc.y - 210 < pstc.getPosition().y && pstc.getPosition().y < ncpstc.y + 210) {
					TwoD.setShop(shopKeeper.getComponent(ShopKeeperComponent.class).itemsForSale);
					game.setScreenToShop(TwoD.getShopMenu());
					TwoD.getMain().gPressed = false;
				}
			}
		}
	}
}
