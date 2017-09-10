package Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class BaseScreen implements Screen, InputProcessor{

    public final static int VIEW_WIDTH = 320;
    public final static int VIEW_HEIGHT = 530;
    public final static int VIEW_HEIGHT_NO_AD = 480;

    protected static Game game;
    public static OrthographicCamera camera;
    protected static Stage stage;

    private static int posX;
    private static int posY;

    private static boolean exception = false;
    private int fps = 0;
    private float ms = 0;

    public BaseScreen(Game g)
    {
            game = g;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

            stage = new Stage(new StretchViewport(VIEW_WIDTH, VIEW_HEIGHT, camera));

            InputMultiplexer im = new InputMultiplexer(this, stage);
            Gdx.input.setInputProcessor(im);

            posX = 0;
            posY = 0;
    }

    public abstract void create() throws CloneNotSupportedException;
    public abstract void update(float dt);

    public void render(float dt)
    {
       // try {
            stage.act(dt);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            stage.draw();
            update(dt);
    }

    public void resize(int width, int height) {
       stage.getViewport().update(width, height);
    }

    public void pause()   {

    }
    public void resume()  {  }
    public void dispose() {
        stage.dispose();
        game.dispose();
    }
    public void show()    {  }
    public void hide()    {  }

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode)
    {  return false;  }
    public boolean keyUp(int keycode)
    {  return false;  }
    public boolean keyTyped(char c)
    {  return false;  }
    public boolean mouseMoved(int screenX, int screenY)
    {return false;  }
    public boolean scrolled(int amount)
    {  return false;  }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;}
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {  return false;  }
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {  return false;  }

    public static boolean getException(){
        return exception;
    }

    public static Stage getStage(){
        return stage;
    }

    public static Game getGame(){
        return game;
    }

    public static void setPosX(int pos){
        posX = pos;
    }

    public static void setPosY(int pos){
        posY = pos;
    }

    public static int getPosX(){
        return posX;
    }

    public static int getPosY(){
        return posY;
    }
}
