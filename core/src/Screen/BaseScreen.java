package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class BaseScreen implements Screen, InputProcessor{

    public final static int VIEW_WIDTH = 320;
    public final static int VIEW_HEIGHT = 480;

    protected Game game;
    protected OrthographicCamera camera;
    protected Stage stage;

    public BaseScreen(Game g)
    {
        game = g;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

        stage = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera));

        InputMultiplexer im = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(im);
    }

    public abstract void create();
    public abstract void update(float dt);

    public void render(float dt)
    {
        try {
            stage.act(dt);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            stage.draw();
            update(dt);
        }catch (Exception e){
            //new InfoAndroid("a");
        }
    }

    public void resize(int width, int height) {
       stage.getViewport().update(width, height);
    }

    public void pause()   {  }
    public void resume()  {  }
    public void dispose() {
        game.dispose();
       stage.dispose();
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
