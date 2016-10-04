package com.kjellvos.twod;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.ObjectMap;
import com.kjellvos.twod.components.AttackComponent;
import com.kjellvos.twod.components.BodyComponent;
import com.kjellvos.twod.components.HealthComponent;
import com.kjellvos.twod.components.LootComponent;
import com.kjellvos.twod.components.NonControllablePersonSteerableComponent;
import com.kjellvos.twod.components.PlayerHealthComponent;
import com.kjellvos.twod.components.PlayerSteerableComponent;
import com.kjellvos.twod.components.ShopKeeperComponent;
import com.kjellvos.twod.components.SpriteComponent;
import com.kjellvos.twod.components.SteerableComponent;
import com.kjellvos.twod.components.SwordSwingComponent;
import com.kjellvos.twod.components.TalkComponent;
import com.kjellvos.twod.entities.Monster;
import com.kjellvos.twod.entities.Player;
import com.kjellvos.twod.entities.Shopkeeper;

public class LevelManager {
	public static int lvlTileWidth, lvlTileHeight, lvlPixelWidth, lvlPixelHeight, tilePixelWidth, tilePixelHeight; 
	public static TiledMapTileSet tileSet;
	
	private static ObjectMap<String, FixtureDef> materials = new ObjectMap<String, FixtureDef>();
	
	TwoD game;
    static TiledMap map;
	static OrthogonalTiledMapRenderer tiledMapRenderer;
	static TiledMapTileLayer collisionLayer;
	
	public LevelManager(String fileName, TwoD game) {
		this.game = game;
		setupPlayer();
		loadLevel(fileName);
	}

	public static void setupPlayer(){
		Player entity = new Player();
		Sprite playerSprite = new Sprite(new Texture("Player.png"));
		
        playerSprite.setPosition(TwoD.getMain().camera.position.x - (playerSprite.getWidth() / 2), TwoD.getMain().camera.position.y - (playerSprite.getHeight() / 2));
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((playerSprite.getX() + playerSprite.getWidth()/2) / TwoD.PPM, (playerSprite.getY() + playerSprite.getHeight()/2) / TwoD.PPM);

        Body body = TwoD.getMain().world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(playerSprite.getWidth() / 2 / TwoD.PPM - 0.05f, playerSprite.getHeight() / 2 / TwoD.PPM - 0.05f);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("player");
        
        entity.add(new BodyComponent(body)).add(new SpriteComponent(playerSprite)).add(new PlayerHealthComponent()).add(new PlayerSteerableComponent(playerSprite.getX(), playerSprite.getY())).add(new SwordSwingComponent());
		TwoD.getMain().engine.addEntity(entity);
		TwoD.setPlayer(entity);
	}
	
	public static void loadLevel(String fileName){
		map = new TmxMapLoader().load(fileName);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        
        MapProperties properties = map.getProperties();
        lvlTileWidth = properties.get("width", Integer.class);
        lvlTileHeight = properties.get("height", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);
        lvlPixelWidth = lvlTileWidth * tilePixelWidth;
        lvlPixelHeight = lvlTileHeight * tilePixelHeight;
        tileSet = map.getTileSets().getTileSet(0);
        TiledMapTile empty = tileSet.getTile(0);
        
        collisionLayer = (TiledMapTileLayer) map.getLayers().get(1);
        createPhysics(map.getLayers().get(2));
        for (int i = 0; i < collisionLayer.getHeight(); i++) {
        	for (int j = 0; j < collisionLayer.getWidth(); j++) {
        		Cell cell = collisionLayer.getCell(i, j);
        		if (cell != null && cell.getTile() != null && cell.getTile().getId() == 25) { //normal monster
        			cell.setTile(empty);
        			Monster entity = new Monster();
        			Sprite sprite = new Sprite(new Texture("Monster.png"));
        			Sprite loot = new Sprite(new Texture("Loot.png"));
        			
        			BodyDef bodyDef = new BodyDef();
        		    FixtureDef fixtureDef = new FixtureDef();
        		    bodyDef.type = BodyDef.BodyType.DynamicBody;
        		    bodyDef.position.set((i*50 + sprite.getWidth()/2) / TwoD.PPM, (j*50 + sprite.getHeight()/2) / TwoD.PPM);
        		
        		    Body body = TwoD.getMain().world.createBody(bodyDef);
        		    PolygonShape shape = new PolygonShape();
        		    shape.setAsBox(sprite.getWidth() / 2 / TwoD.PPM, sprite.getHeight() / 2 / TwoD.PPM);
        		    fixtureDef.shape = shape;
        		    body.createFixture(fixtureDef).setUserData("monster");
        			
        			entity.add(new SteerableComponent(i*50, j*50)).add(new BodyComponent(body)).add(new SpriteComponent(sprite)).add(new AttackComponent(3)).add(new HealthComponent(100)).add(new LootComponent(loot)); 
        			entity.initHealthBar();
        			TwoD.getMain().engine.addEntity(entity);
        		}else if (cell != null && cell.getTile() != null && cell.getTile().getId() == 10) {
        			cell.setTile(empty);
        			Shopkeeper entity = new Shopkeeper();
        			Sprite sprite = new Sprite(new Texture("NCP.png"));
        			
        			BodyDef bodyDef = new BodyDef();
        		    FixtureDef fixtureDef = new FixtureDef();
        		    bodyDef.type = BodyDef.BodyType.DynamicBody;
        		    bodyDef.position.set((i*50 + sprite.getWidth()/2) / TwoD.PPM, (j*50 + sprite.getHeight()/2) / TwoD.PPM);
        		
        		    Body body = TwoD.getMain().world.createBody(bodyDef);
        		    PolygonShape shape = new PolygonShape();
        		    shape.setAsBox(sprite.getWidth() / 2 / TwoD.PPM, sprite.getHeight() / 2 / TwoD.PPM);
        		    fixtureDef.shape = shape;
        		    body.createFixture(fixtureDef).setUserData("monster");
        			
        		    int[] itemsForSale = new int[7];
        		    for (int k = 1; k < 8; k++) {
        		    	itemsForSale[k-1] = k;
        		    }
        		    
        			entity.add(new NonControllablePersonSteerableComponent(i*50, j*50)).add(new BodyComponent(body)).add(new SpriteComponent(sprite)).add(new ShopKeeperComponent(itemsForSale)).add(new TalkComponent("Press G to open shop!")); 
        			TwoD.getMain().engine.addEntity(entity);
        		}
        	}
        }
	}
	
	public static void createPhysics(MapLayer layer) {
		MapObjects objects = layer.getObjects();
		Iterator<MapObject> objectIt = objects.iterator();
		
		FixtureDef defaultFixture = new FixtureDef();
		defaultFixture.density = 1.0f;
		defaultFixture.friction = 0.8f;
		defaultFixture.restitution = 0.0f;
		
		materials.put("default", defaultFixture);
		
		while(objectIt.hasNext()) {
			MapObject object = objectIt.next();
			
			if (object instanceof TextureMapObject){
				continue;
			}
			 
			Shape shape;
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			
			if (object instanceof RectangleMapObject) {
				RectangleMapObject rectangle = (RectangleMapObject)object;
				shape = getRectangle(rectangle);
			}
			else if (object instanceof PolygonMapObject) {
				shape = getPolygon((PolygonMapObject)object);
			}
			else if (object instanceof PolylineMapObject) {
				shape = getPolyline((PolylineMapObject)object);
			}
			else if (object instanceof CircleMapObject) {
				shape = getCircle((CircleMapObject)object);
			}else{
				continue;
			}
			
			MapProperties properties = object.getProperties();
			String material = properties.get("material", "default", String.class);
			FixtureDef fixtureDef = materials.get(material);
			fixtureDef = materials.get("default");
			
			fixtureDef.shape = shape;
			fixtureDef.filter.categoryBits = 1;
			
			
			TwoD.getMain().world.createBody(bodyDef).createFixture(fixtureDef).setUserData("wall");
			
			fixtureDef.shape = null;
			shape.dispose();
		}
	}
	
	private static Shape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / TwoD.PPM, (rectangle.y + rectangle.height * 0.5f ) / TwoD.PPM);
		polygon.setAsBox(rectangle.width * 0.5f / TwoD.PPM, rectangle.height * 0.5f / TwoD.PPM, size, 0.0f);
		return polygon;
	}
	
	private static Shape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(circle.radius / TwoD.PPM);
		circleShape.setPosition(new Vector2(circle.x / TwoD.PPM, circle.y / TwoD.PPM));
		return circleShape;
	}
	
	private static Shape getPolygon(PolygonMapObject polygonObject) {
		PolygonShape polygon = new PolygonShape();
		float[] vertices = polygonObject.getPolygon().getTransformedVertices();
		
		float[] worldVertices = new float[vertices.length];
		
		for (int i = 0; i < vertices.length; ++i) {
		    worldVertices[i] = vertices[i] / TwoD.PPM;
		}
		
		polygon.set(worldVertices);
		return polygon;
	}
	
	private static Shape getPolyline(PolylineMapObject polylineObject) {
		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];
		
		for (int i = 0; i < vertices.length / 2; ++i) {
		    worldVertices[i] = new Vector2();
		    worldVertices[i].x = vertices[i * 2] / TwoD.PPM;
		    worldVertices[i].y = vertices[i * 2 + 1] / TwoD.PPM;
		}
		
		ChainShape chain = new ChainShape(); 
		chain.createChain(worldVertices);
		return chain;
	}
	
	public boolean isCellBLocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int)(x / LevelManager.tilePixelWidth), (int)(y / LevelManager.tilePixelHeight));
	 
	    return (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked"));
	}
	
	public boolean checkCollisionMap(int offsetX, int offsetY){
        float xWorld = TwoD.getMain().camera.position.x + offsetX;
        float yWorld = TwoD.getMain().camera.position.y + offsetY; 
                                                                                                     
        boolean collisionWithMap;
        collisionWithMap = isCellBLocked(xWorld, yWorld);
 
        if (collisionWithMap) {             
            return false;
        }
        return true;
	}
}
