package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.BodyComponent;
import com.kjellvos.twod.components.HealthComponent;
import com.kjellvos.twod.components.ItemComponent;
import com.kjellvos.twod.components.LootComponent;
import com.kjellvos.twod.components.MoneyComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.PositionComponent;
import com.kjellvos.twod.components.SpriteComponent;
import com.kjellvos.twod.components.SteerableComponent;
import com.kjellvos.twod.entities.Loot;

public class LootSystem extends EntitySystem {
	Engine engine;
	
	private ImmutableArray<Entity> lootToDrop, moneyToPickUp, player, itemsToPickUp;	
	
	private ComponentMapper<LootComponent> cmlc = ComponentMapper.getFor(LootComponent.class);
	private ComponentMapper<HealthComponent> cmhc = ComponentMapper.getFor(HealthComponent.class);
	private ComponentMapper<BodyComponent> cmbc = ComponentMapper.getFor(BodyComponent.class);
	private ComponentMapper<SteerableComponent> cmstc = ComponentMapper.getFor(SteerableComponent.class);
	private ComponentMapper<SpriteComponent> cmsc = ComponentMapper.getFor(SpriteComponent.class);
	private ComponentMapper<PositionComponent> cmpc = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<MoneyComponent> cmmc = ComponentMapper.getFor(MoneyComponent.class);
	private ComponentMapper<ItemComponent> cmic = ComponentMapper.getFor(ItemComponent.class);
	
	private ComponentMapper<PlayerSteerableComponent> cmpsc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	
	public void addedToEngine(Engine engine){
		this.engine = engine;
		player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class).get());
		lootToDrop = engine.getEntitiesFor(Family.all(LootComponent.class, HealthComponent.class, BodyComponent.class, SteerableComponent.class, SpriteComponent.class).get());
		moneyToPickUp = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class, MoneyComponent.class).get());
		itemsToPickUp = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class, ItemComponent.class).get());
	}
	
	public void update(float delta){
		for (int i = 0; i < lootToDrop.size(); i++) {
			Entity entity = lootToDrop.get(i);
			
			HealthComponent hc = cmhc.get(entity);
			BodyComponent bc = cmbc.get(entity);
			LootComponent lc = cmlc.get(entity);
			SpriteComponent sc = cmsc.get(entity);
			SteerableComponent stc = cmstc.get(entity);
			
			if (1 > hc.health) {
				TwoD.getMain().world.destroyBody(bc.body);
				entity.removeAll();
				engine.removeEntity(entity);
						
				Loot lootEntity = new Loot();
				int random = MathUtils.random(0, 8);
				System.out.println(random);
				if (random == 0) {
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new MoneyComponent(lc.amountOfMoney));
				}else if (random == 1) {
					//item id 1 == golden sword
					lc.sprite = new Sprite(new Texture("GoldenSwordUp.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(1));
				}else if (random == 2) {
					//item id 2 == armor
					lc.sprite = new Sprite(new Texture("IronGear.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(2));
				}else if (random == 3) {
					lc.sprite = new Sprite(new Texture("LeatherHelm.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(3));
				}else if (random == 4) {
					lc.sprite = new Sprite(new Texture("LeatherPants.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(4));
				}else if (random == 5) {
					lc.sprite = new Sprite(new Texture("LeftLeatherShoe.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(5));
				}else if (random == 6) {
					lc.sprite = new Sprite(new Texture("NormalSpellbook.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(6));
				}else if (random == 7) {
					lc.sprite = new Sprite(new Texture("RightLeatherShoe.png"));
					lootEntity.add(new SpriteComponent(lc.sprite)).add(new PositionComponent(stc.x, stc.y)).add(new ItemComponent(7));
				}
				engine.addEntity(lootEntity);
			}
		}
		Entity playerEntity = player.get(0);
		PlayerSteerableComponent pstc = cmpsc.get(playerEntity);
		for (int i = 0; i < moneyToPickUp.size(); i++) {
			Entity entity = moneyToPickUp.get(i);
			
			PositionComponent pc = cmpc.get(entity);
			SpriteComponent sc = cmsc.get(entity);
			MoneyComponent mc = cmmc.get(entity); 
			
			if (pstc.x > pc.x - sc.sprite.getWidth() / 2 && pstc.x < pc.x + sc.sprite.getWidth() / 2 && pstc.y > pc.y - sc.sprite.getHeight() / 2 && pstc.y < pc.y + sc.sprite.getHeight() / 2) {
				TwoD.getPlayer().setMoney(TwoD.getPlayer().getMoney() + mc.amountOfMoney);
				engine.removeEntity(entity);
			}
		}
		for (int i = 0; i < itemsToPickUp.size(); i++) {
			Entity entity = itemsToPickUp.get(i);
			
			PositionComponent pc = cmpc.get(entity);
			SpriteComponent sc = cmsc.get(entity);
			ItemComponent ic = cmic.get(entity); 
			
			if (pstc.x > pc.x - sc.sprite.getWidth()  / 2 && pstc.x < pc.x + sc.sprite.getWidth() / 2 && pstc.y > pc.y - sc.sprite.getHeight() / 2 && pstc.y < pc.y + sc.sprite.getHeight() / 2) {
				if (TwoD.getInventory().addToInventory(ic.itemId)){
					engine.removeEntity(entity);	
				}
			}
		}
	}
}
