package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.Hero;
import com.mygdx.game.Npc;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-06-04.
 */

public abstract class BaseMap extends BaseScreen {
    public final static int SPEED_MOVE = 50;

    public Rectangle leftRec;
    public Rectangle upRec;
    public Rectangle rightRec;
    public Rectangle bottomRec;

    protected Hero hero;
    protected ArrayList<Npc> npcList;
    protected int mapWidth;
    protected int mapHeight;

    private Texture bgTexture;
    private String bgSrc;

    public BaseMap(Game game, int mapWidth, int mapHeight, String bgSrc) {
        super(game);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.bgSrc = bgSrc;

        this.leftRec = new Rectangle(-1, -1, 2, mapHeight);
        this.upRec = new Rectangle(-1, mapHeight -1, mapWidth +1, 2);
        this.rightRec = new Rectangle(mapWidth -1, -1, 2, mapHeight);
        this.bottomRec = new Rectangle(-1, -1, mapWidth, 2);

        create();
    }

    public abstract void generateMap();
    public abstract void collisionEndMap();

    @Override
    public void create() {
        npcList = new ArrayList<Npc>();
        hero = new Hero(new Texture(Gdx.files.internal("badlogic.jpg")), 100);
        bgTexture = new Texture(Gdx.files.internal(bgSrc));

        generateMap();
    }

    @Override
    public void update(float dt) {
        collisionDetected();
        drawObject();
        cameraUpdate();
        hero.move(dt);
        hero.act(dt);
    }

    private void cameraUpdate() {
        camera.position.set(hero.getX() + hero.getOriginX(), hero.getY() + hero.getOriginY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, VIEW_WIDTH / 2, Map_01.MAP_WIDTH - VIEW_WIDTH / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, VIEW_HEIGHT / 2, Map_01.MAP_HEIGHT - VIEW_HEIGHT / 2);
        camera.update();
    }

    private void collisionDetected(){
        hero.collisionUpdate();
        npcList.get(0).collisionUpdate();

        if(hero.collision.overlaps(npcList.get(0).collision)){
            npcList.get(0).collisionDo();
        }

        if(hero.collision.overlaps(leftRec)
                || hero.collision.overlaps(upRec)
                || hero.collision.overlaps(rightRec)
                || hero.collision.overlaps(bottomRec)){
            collisionEndMap();
        }
        //TODO past code in for-each to npcList (0, 1 ... n-1, n)
    }

    private void drawObject() {
        spriteBatch.draw(bgTexture, 0, 0, mapWidth, mapHeight);
        spriteBatch.draw(npcList.get(0).getTexture(), npcList.get(0).getX(), npcList.get(0).getY());
        spriteBatch.draw(hero.getTexture(), hero.getX(), hero.getY());
    }

    private void setPositionHero(float screenX, float screenY){
        float x = ((screenX + camera.position.x) - VIEW_WIDTH /2 - hero.getTexture().getWidth() /2);
        float y = (((VIEW_HEIGHT + camera.position.y) - VIEW_HEIGHT /2) - screenY - hero.getTexture().getHeight() /2);
        float duration = timeSpeed(x, y);
        hero.addAction(Actions.moveTo(x, y, duration));
    }

    private float timeSpeed(float positionX, float positionY){
        float distanceX = positionX - hero.getX();
        float distanceY = positionY - hero.getY();
        double duration;

        if(distanceX < 0)
            distanceX = hero.getX() - positionX;
        if (distanceY < 0)
            distanceY = hero.getY() - positionY;

        if(distanceX < distanceY){
            duration = distanceY / SPEED_MOVE + (distanceX * 0.40) / SPEED_MOVE;
        }else{
            duration = distanceX / SPEED_MOVE + (distanceY * 0.40) / SPEED_MOVE;
        }

        return (float) duration;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setPositionHero(screenX, screenY);
        return false;
    }

    public void dispose(){
        for(Npc list: npcList){
            list.getTexture().dispose();
        }
        bgTexture.dispose();
        hero.getTexture().dispose();
    }
}
