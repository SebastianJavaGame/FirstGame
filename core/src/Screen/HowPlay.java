package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Sebastian on 2017-08-06.
 */

public class HowPlay extends BaseScreen{
    private final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private final Label.LabelStyle STYLE = new Label.LabelStyle();

    public HowPlay(Game g) {
        super(g);
        STYLE.font = FONT;
        create();
    }

    @Override
    public void create() {
        Image image = new Image(new Texture(Gdx.files.internal("itemButton.png")));
        Label label = new Label("Menu", STYLE);

        Label lTitleTarget = new Label("Twój cel", STYLE);
        Label lTitleCompass = new Label("Mapa", STYLE);
        Label lTitleNpc = new Label("Dialogi", STYLE);
        Label lTitleHero = new Label("Statystyki bohatera", STYLE);
        Label lTitleFight = new Label("Walka", STYLE);
        Label lTitleAbord = new Label("Ucieczka z walki", STYLE);

        lTitleTarget.setColor(Color.OLIVE);
        lTitleCompass.setColor(Color.OLIVE);
        lTitleNpc.setColor(Color.OLIVE);
        lTitleHero.setColor(Color.OLIVE);
        lTitleFight.setColor(Color.OLIVE);
        lTitleAbord.setColor(Color.OLIVE);

        lTitleTarget.setFontScale(0.8f);
        lTitleCompass.setFontScale(0.8f);
        lTitleNpc.setFontScale(0.8f);
        lTitleHero.setFontScale(0.8f);
        lTitleFight.setFontScale(0.8f);
        lTitleAbord.setFontScale(0.8f);

        Label lTarget = new Label("Twoim celem jest poskromić wielką 5 z\nmrocznej listy czempionów. Wbijaj\npoziomy walcz z monstrami i wygrywaj\nlegendarne przedmioty przez nich\nskrywane", STYLE);
        Label lCompass = new Label("Kliknij kompas aby wyświetlić mapę\nkrainy i mroczną listę czempionów", STYLE);
        Label lNpc = new Label("Napotkani sprzymierzeńcy mają dla\nciebie zadania za które otrzymasz\nnagrodę. Na każdej mapie jeden ze\nsprzymierzeńców ma sklep z\nekwipunkiem", STYLE);
        Label lHero = new Label("SIŁA odpowiada za\nwielkość obrażeń fizycznych\nWIEDZA odpowiada za\nwielkość obrażeń magicznych\nPANCERZ\nzmniejsza obrażenia o wartość pancerza\nZRĘCZNOŚĆ odpowiada za celność\nataków i skuteczność obrony fizycznej\nMAGIA odpowiada za celnośc\nataków i skutecznośc obrony magicznej", STYLE);
        Label lFight = new Label("W każdej rundzie zadajemy 2 ataki\nfizyczne i 1 atak magiczny. W walce masz\ndo dyspozycji 10 punktów akcji, które\nmożesz przeznaczyć na celność ataku\nfizycznego i magicznego oraz obrony\nfizycznej i magicznej. Większa ilość\npunktów zapewnia większą skuteczność\nataku i obrony. Każdy zadany atak zużywa\nod 10 do 20 punktów energi, gdy energia\nspadnie do 0 zostaje odjęty\n1 punkt akcji", STYLE);
        Label lAbord = new Label("W prawym dolnym rogu walki znajduje się\nikona ucieczki. Jeśli ją klikniemy w\ntrakcie walki stracimy 20% punktów życia\ni uciekniemy z walki. W przypadku kiedy\nnie rozpoczniemy walki ucieczka nie\nspowoduje straty punktów życia", STYLE);

        lTarget.setFontScale(0.5f);
        lCompass.setFontScale(0.5f);
        lNpc.setFontScale(0.5f);
        lHero.setFontScale(0.5f);
        lFight.setFontScale(0.5f);
        lAbord.setFontScale(0.5f);

        final Table scrollTable = new Table();

        scrollTable.add(lTitleTarget);
        scrollTable.row();
        scrollTable.add(lTarget);
        scrollTable.row();
        scrollTable.add(lTitleCompass).expandX();
        scrollTable.row();
        scrollTable.add(lCompass);
        scrollTable.row();
        scrollTable.add(lTitleNpc);
        scrollTable.row();
        scrollTable.add(lNpc);
        scrollTable.row();
        scrollTable.add(lTitleHero).expandX();
        scrollTable.row();
        scrollTable.add(lHero);
        scrollTable.row();
        scrollTable.add(lTitleFight);
        scrollTable.row();
        scrollTable.add(lFight);
        scrollTable.row();
        scrollTable.add(lTitleAbord);
        scrollTable.row();
        scrollTable.add(lAbord);
        scrollTable.row();

        final ScrollPane scroll = new ScrollPane(scrollTable);
        scroll.setScrollingDisabled(true, false);

        final Table table = new Table();
        table.setBounds(0, 55, 320, 430);
        table.add(scroll);

        image.setSize(image.getWidth(), 60);
        image.setPosition(BaseScreen.VIEW_WIDTH /2 -image.getWidth() /2, 0);
        label.setPosition(BaseScreen.VIEW_WIDTH /2 -label.getWidth() /2, 5);
        label.setTouchable(Touchable.disabled);

        image.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Menu(game));
                return false;
            }
        });

        stage.addActor(table);
        stage.addActor(image);
        stage.addActor(label);
    }

    @Override
    public void update(float dt) {

    }
}
