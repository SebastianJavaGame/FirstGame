package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class BaseScreen implements Screen, InputProcessor{

    public final static int VIEW_WIDTH = 320;
    public final static int VIEW_HEIGHT = 480;

    protected Game game;
    protected SpriteBatch spriteBatch;
    protected OrthographicCamera camera;

    private Viewport viewport;

    public BaseScreen(Game g)
    {
        game = g;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
        camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

        InputMultiplexer im = new InputMultiplexer(this);
        Gdx.input.setInputProcessor(im);
    }

    public abstract void create();

    public abstract void update(float dt);

    // this is the gameloop. update, then render.
    public void render(float dt)
    {
        spriteBatch.setProjectionMatrix(camera.combined);

        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        update(dt);
        spriteBatch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void pause()   {  }
    public void resume()  {  }
    public void dispose() {
        game.dispose();
        spriteBatch.dispose();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {  return false;  }
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {  return false;  }
}
