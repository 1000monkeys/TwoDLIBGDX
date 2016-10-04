package com.kjellvos.twod;

import com.badlogic.gdx.Game;
import com.kjellvos.twod.entities.Player;
import com.kjellvos.twod.inventory.Gear;
import com.kjellvos.twod.inventory.Inventory;
import com.kjellvos.twod.inventory.Items;
import com.kjellvos.twod.inventory.Shop;

public class TwoD extends Game {
	private static ShopMenu shopMenu;
	private static Menu menu;
	private static Main main;
	private static Splash splash;
	private static LevelManager level;
	private static Player player;
	private static Inventory inventory;
	private static Shop shop;
	private static Gear gear;
	public static Items items;
	public static final int PPM = 100;
	
	public void create() {
		splash = new Splash(this);
		this.setScreen(splash);
		items = new Items();
		main = new Main(this);
		menu = new Menu(this);
		shopMenu = new ShopMenu(this);
		level = new LevelManager("map.tmx", this);
		inventory = new Inventory();
		gear = new Gear();
		main.pause();
	}
	
	public void render(){
		super.render();
	}

	public void dispose() {
	}
	
	public static Main getMain(){
		return main;
	}

	public static Menu getMenu() {
		return menu;
	}
	
	public static LevelManager getLevel(){
		return level;
	}
	
	public static Player getPlayer(){
		return player;
	}
	
	public static void setPlayer(Player passedPlayer){
		player = passedPlayer;
	}
	
	public static Inventory getInventory(){
		return inventory;
	}
	
	public static Gear getGear(){
		return gear;
	}

	public static Items getItems(){
		return items;
	}
	
	public static ShopMenu getShopMenu(){
		return shopMenu;
	}
	
	public static Shop getShop(){
		return shop;
	}

	public void setScreenToShop(ShopMenu shopMenu) {
		setScreen(shopMenu);
	}
	
	public static void setShop(int[] itemsForSale){
		TwoD.shop = new Shop(itemsForSale);
	}
}

