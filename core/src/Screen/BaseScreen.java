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
    public final static int VIEW_HEIGHT = 480;

    protected static Game game;
    public static OrthographicCamera camera;
    protected static Stage stage;
    protected static Stage fontStage;

    private static int posX;
    private static int posY;

    private static boolean exception = false;

    public BaseScreen(Game g)
    {
        try {
            game = g;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

            stage = new Stage(new StretchViewport(VIEW_WIDTH, VIEW_HEIGHT, camera));
            fontStage = new Stage(new StretchViewport(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight()));

            InputMultiplexer im = new InputMultiplexer(this, stage, fontStage);
            Gdx.input.setInputProcessor(im);

            posX = 0;
            posY = 0;
        } catch (Exception e) {
            showException(e);
        }
    }

    public abstract void create() throws CloneNotSupportedException;
    public abstract void update(float dt);

    public void render(float dt)
    {
        try {
            stage.act(dt);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            stage.draw();
            update(dt);
        } catch (IllegalStateException e1){
            showException(e1);
        } catch (Exception e){
           showException(e);
        }
    }

    public static void showException(Exception e){
        int x = (int) -camera.position.x + BaseMap.VIEW_WIDTH / 2;
        int y = (int) -camera.position.y + BaseMap.VIEW_HEIGHT / 2;
        stage.clear();
        Image image = new Image(new Texture(Gdx.files.internal("shadow.png")));
        image.setBounds(x, y, BaseScreen.VIEW_WIDTH, BaseScreen.VIEW_HEIGHT);
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
            BitmapFont font = MyGdxGame.createDistanceFont();
            Label.LabelStyle style = new Label.LabelStyle();
            style.font = font;
            style.fontColor = new Color(Color.RED);

            TextButton.TextButtonStyle styleError = new TextButton.TextButtonStyle();
            styleError.font = font;
            styleError.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("itemButton.png"))));

            Label lTitle = new Label("Blad! 'Pokaz to tworcy'", style);
            lTitle.setPosition(x +VIEW_WIDTH /2 -lTitle.getWidth(), y +460);
            lTitle.setFontScale(2);

            Label lMessage = new Label(exceptionMessage, style);

            TextButton exit = new TextButton("Wyjdz", styleError);
            exit.setPosition(x +VIEW_WIDTH /2 -exit.getWidth() /2, y +5);
            exit.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.exit(0);
                    Gdx.app.exit();
                    return false;
                }
            });
            lMessage.setPosition(x +10, y +exit.getY() +exit.getHeight());

            stage.addActor(lTitle);
            stage.addActor(lMessage);
            stage.addActor(exit);

            exception = true;

        }catch(RuntimeException e1) {
            MyGdxGame.dialog(exceptionMessage);
        }

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            e.printStackTrace();
        }
    }

    public void resize(int width, int height) {
       stage.getViewport().update(width, height);
       fontStage.getViewport().update(width, height);
    }

    public void pause()   {

    }
    public void resume()  {  }
    public void dispose() {
        game.dispose();
        stage.dispose();
        fontStage.dispose();
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
        System.out.println(screenY + "y");
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

    public static Stage getFontStage(){
        return fontStage;
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
