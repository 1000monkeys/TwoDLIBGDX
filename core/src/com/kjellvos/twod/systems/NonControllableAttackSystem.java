package com.kjellvos.twod.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.kjellvos.twod.DamageTextPlayer;
import com.kjellvos.twod.TwoD;
import com.kjellvos.twod.components.AttackComponent;
import com.kjellvos.twod.components.PlayerHealthComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.SteerableComponent;

public class NonControllableAttackSystem extends EntitySystem {
	private ImmutableArray<Entity> entities, player;	
	
	private ComponentMapper<AttackComponent> cmac = ComponentMapper.getFor(AttackComponent.class);
	private ComponentMapper<SteerableComponent> cmstc = ComponentMapper.getFor(SteerableComponent.class);
	
	private ComponentMapper<PlayerHealthComponent> cmphc = ComponentMapper.getFor(PlayerHealthComponent.class);
	private ComponentMapper<PlayerSteerableComponent> cmpsc = ComponentMapper.getFor(PlayerSteerableComponent.class);
	
	public void addedToEngine(Engine engine){
		player = engine.getEntitiesFor(Family.all(PlayerHealthComponent.class, PlayerSteerableComponent.class).get());
		entities = engine.getEntitiesFor(Family.all(AttackComponent.class).get());
	}
	
	public void update(float delta){
		Entity playerEntity = player.get(0);
		PlayerHealthComponent phc = cmphc.get(playerEntity);
		PlayerSteerableComponent psc = cmpsc.get(playerEntity);
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);

			SteerableComponent sc = cmstc.get(entity);
			AttackComponent ac = cmac.get(entity);
			
			if (sc.x - 50 < psc.x && psc.x < sc.x + 50 && sc.y - 100 < psc.y && psc.y < sc.y + 100) {
				long millis = TimeUtils.millis();
				if (ac.timeSinceLastAttack + 250 < millis) {
					ac.timeSinceLastAttack = millis;
					int attack = ac.attack - TwoD.getPlayer().getDefense();
					if (0 > attack) {
						attack = 0;
					}
					TwoD.getMain().stage.addActor(new DamageTextPlayer(Integer.toString(attack), psc.x, psc.y));
					phc.health -= attack;
					if (phc.health < 1) {
						TwoD.getMain().isDead = true;
					}
				}
			}
		}
	}
}
