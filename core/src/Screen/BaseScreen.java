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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;

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
            stage.clear();
            Image image = new Image(new Texture(Gdx.files.internal("shadow.png")));
            image.setBounds(0, 0, BaseScreen.VIEW_WIDTH, BaseScreen.VIEW_HEIGHT);
            stage.addActor(image);

            String describe = e.toString();
            int describeLength = describe.length();

            String exceptionMessage = "Class: " + e.getStackTrace()[0].getClassName() +
                    "\n Method: " + e.getStackTrace()[0].getMethodName() + "\n Line: " +
                    e.getStackTrace()[0].getLineNumber() + "\n Describe: ";

            int i = 0;
            int lineLength = 40;
            while(describeLength > lineLength){
                describeLength -=lineLength;
                exceptionMessage += "\n" + describe.substring(lineLength *i, lineLength + lineLength *i);
                i++;
            }

            if(describeLength < lineLength)
                exceptionMessage += "\n" + describe.substring(0, describeLength);

            try{
                BitmapFont font = new BitmapFont();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = font;
                style.fontColor = new Color(Color.RED);

                TextButton.TextButtonStyle styleError = new TextButton.TextButtonStyle();
                styleError.font = font;
                styleError.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("itemButton.png"))));

                Label lTitle = new Label("Blad! 'Pokaz to tworcy'", style);
                lTitle.setPosition(VIEW_WIDTH /2 -lTitle.getWidth(), 460);
                lTitle.setFontScale(2);

                Label lMessage = new Label(exceptionMessage, style);
                lMessage.setPosition(10, 200);

                TextButton exit = new TextButton("Wyjdz", styleError);
                exit.setPosition(VIEW_WIDTH /2 -exit.getWidth() /2, 5);
                exit.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        System.exit(0);
                        Gdx.app.exit();
                        return false;
                    }
                });
                stage.addActor(lTitle);
                stage.addActor(lMessage);
                stage.addActor(exit);

            }catch(RuntimeException e1) {
                MyGdxGame.dialog(exceptionMessage);
            }

            if(Gdx.app.getType() == Application.ApplicationType.Desktop){
                System.out.println("Przyczyna bledu: " + e.toString());
                System.out.println("Blad w klasie: " + e.getStackTrace()[0].getClassName());
                System.out.println("Blad w metodzie: " + e.getStackTrace()[0].getMethodName());
                System.out.println("Blad w lini: " + e.getStackTrace()[0].getLineNumber());
            }
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
