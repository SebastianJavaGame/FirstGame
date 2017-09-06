package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
 * Created by Sebastian on 2017-09-04.
 */

public class Autors extends BaseScreen {
    private final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private final Label.LabelStyle STYLE = new Label.LabelStyle();

    public Autors(Game g) {
        super(g);
        STYLE.font = FONT;
        try {
            create();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create() throws CloneNotSupportedException {
        Image image = new Image(new Texture(Gdx.files.internal("itemButton.png")));
        Label label = new Label("Menu", STYLE);

        final Table scrollTable = new Table();

        Label[] labels = new Label[126];
        labels[0] = new Label("Zasoby są na licencji creative commons modyfikowane w conajmniej minimalnym stopniu. Autorzy i odnośniki do zasobu:\nClint Bellanger, Blarumyrran, crowline, Justin Nichol", STYLE);
        labels[1] = new Label("\nhttp://www.supergameasset.com  yinakoSGA", STYLE);
        labels[2] = new Label("\nClint Bellanger and Justin Nichol", STYLE);
        labels[3] = new Label("Misha https://opengameart.org/content/bastardsword001-concept", STYLE);
        labels[4] = new Label("\n" +
                "Clint Bellanger https://opengameart.org/content/stone-axe", STYLE);
        labels[5] = new Label("http://flarerpg.org lub http://clintbellanger.net", STYLE);
        labels[6] = new Label("https://opengameart.org/content/medieval-building-tiles", STYLE);
        labels[7] = new Label("https://opengameart.org/content/cave-tileset", STYLE);
        labels[8] = new Label("https://opengameart.org/content/dungeon-stairs", STYLE);
        labels[9] = new Label("Justin Nichol https://opengameart.org/content/fantasy-items-set", STYLE);
        labels[10] = new Label("https://opengameart.org/content/quarterstaff", STYLE);
        labels[11] = new Label("Jordan Trudgett | http://jordan.trudgett.com/", STYLE);
        labels[12] = new Label("fktt https://opengameart.org/content/elven-dagger", STYLE);
        labels[13] = new Label("Ravenmore http://dycha.net", STYLE);
        labels[14] = new Label("StumpyStrust", STYLE);
        labels[15] = new Label("http://devassets.com/", STYLE);
        labels[16] = new Label("Tamara Ramsay http://vectorgurl.com/", STYLE);
        labels[17] = new Label("Clint Bellanger   Lamoot    Blender Foundation | apricot.blender.org   lattice  p0ss  Bart K.   OpenGameArt.org  Sindwiller", STYLE);
        labels[18] = new Label("Seth Galbraith https://opengameart.org/content/isometric-64x64-medieval-building-tileset", STYLE);
        labels[19] = new Label("Yar  https://opengameart.org/content/isometric-64x64-outside-tileset", STYLE);
        labels[20] = new Label("rubberduck", STYLE);
        labels[21] = new Label("Daniel Stephens  Scribe   http://www.unknown-horizons.org/", STYLE);
        labels[22] = new Label("extradave https://opengameart.org/content/castle-walls-isometric-64-x-128", STYLE);
        labels[23] = new Label("Kenney.nl", STYLE);
        labels[24] = new Label("Bleed -http://remusprites.carbonmade.com/", STYLE);
        labels[25] = new Label("Amstrad  https://opengameart.org/content/desert-tileset", STYLE);
        labels[26] = new Label("Unknown Horizons  https://opengameart.org/content/real-time-strategy-gui-hud-elements-unknown-horizons", STYLE);
        labels[27] = new Label("https://opengameart.org/content/tents-hunter-lumberjack-pavilion-storage-unknown-horizons-2d", STYLE);
        labels[28] = new Label("https://opengameart.org/content/coastal-tent-buildings-and-animated-signal-fire-unknown-horizons-3d", STYLE);
        labels[29] = new Label("davidbv http://www.exiledkingdoms.com", STYLE);
        labels[30] = new Label("http://www.supergameasset.com/", STYLE);
        labels[31] = new Label("http://remusprites.carbonmade.com/", STYLE);
        labels[32] = new Label("Tuomo Untinen\thttps://opengameart.org/content/castle-door", STYLE);
        labels[33] = new Label("Apostrophic Labs https://www.1001freefonts.com/endor.font", STYLE);
        labels[34] = new Label("https://craftpix.net/file-licenses/", STYLE);
        labels[35] = new Label("Jayr Kalugin (www.hourglass3d.com)", STYLE);
        labels[36] = new Label("Alexandr Zhelanov https://soundcloud.com/alexandr-zhelanov", STYLE);
        labels[37] = new Label("Cadnav.com", STYLE);
        labels[38] = new Label("http://www.cadnav.com/3d-models/model-41183.html", STYLE);
        labels[39] = new Label("http://www.cadnav.com/3d-models/model-41142.html", STYLE);
        labels[40] = new Label("http://www.cadnav.com/3d-models/model-41140.html", STYLE);
        labels[41] = new Label("http://www.cadnav.com/3d-models/model-41208.html", STYLE);
        labels[42] = new Label("http://www.cadnav.com/3d-models/model-41041.html", STYLE);
        labels[43] = new Label("http://www.cadnav.com/3d-models/model-40466.html", STYLE);
        labels[44] = new Label("http://www.cadnav.com/3d-models/model-40471.html", STYLE);
        labels[45] = new Label("http://www.cadnav.com/3d-models/model-40381.html", STYLE);
        labels[46] = new Label("http://www.cadnav.com/3d-models/model-40385.html", STYLE);
        labels[47] = new Label("http://www.cadnav.com/3d-models/model-40351.html", STYLE);
        labels[48] = new Label("http://www.cadnav.com/3d-models/model-40339.html", STYLE);
        labels[49] = new Label("http://www.cadnav.com/3d-models/model-40198.html", STYLE);
        labels[50] = new Label("http://www.cadnav.com/3d-models/model-39998.html", STYLE);
        labels[51] = new Label("http://www.cadnav.com/3d-models/model-39997.html", STYLE);
        labels[52] = new Label("http://www.cadnav.com/3d-models/model-39996.html", STYLE);
        labels[53] = new Label("http://www.cadnav.com/3d-models/model-39989.html", STYLE);
        labels[54] = new Label("http://www.cadnav.com/3d-models/model-39889.html", STYLE);
        labels[55] = new Label("http://www.cadnav.com/3d-models/model-39848.html", STYLE);
        labels[56] = new Label("http://www.cadnav.com/3d-models/model-39825.html", STYLE);
        labels[57] = new Label("http://www.cadnav.com/3d-models/model-39805.html", STYLE);
        labels[58] = new Label("http://www.cadnav.com/3d-models/model-39697.html", STYLE);
        labels[59] = new Label("http://www.cadnav.com/3d-models/model-19", STYLE);
        labels[60] = new Label("http://www.cadnav.com/3d-models/model-41148.html082.html", STYLE);
        labels[61] = new Label("http://www.cadnav.com/3d-models/model-41100.html", STYLE);
        labels[62] = new Label("http://www.cadnav.com/3d-models/model-41076.html", STYLE);
        labels[63] = new Label("http://www.cadnav.com/3d-models/model-40788.html", STYLE);
        labels[64] = new Label("http://www.cadnav.com/3d-models/model-40787.html", STYLE);
        labels[65] = new Label("http://www.cadnav.com/3d-models/model-40505.html", STYLE);
        labels[66] = new Label("http://www.cadnav.com/3d-models/model-40507.html", STYLE);
        labels[67] = new Label("http://www.cadnav.com/3d-models/model-40183.html", STYLE);
        labels[68] = new Label("http://www.cadnav.com/3d-models/model-40166.html", STYLE);
        labels[69] = new Label("http://www.cadnav.com/3d-models/model-40141.html", STYLE);
        labels[70] = new Label("http://www.cadnav.com/3d-models/model-36527.html", STYLE);
        labels[71] = new Label("http://www.cadnav.com/3d-models/model-40097.html", STYLE);
        labels[72] = new Label("http://www.cadnav.com/3d-models/model-39965.html", STYLE);
        labels[73] = new Label("http://www.cadnav.com/3d-models/model-39962.html", STYLE);
        labels[74] = new Label("http://www.cadnav.com/3d-models/model-39960.html", STYLE);
        labels[75] = new Label("http://www.cadnav.com/3d-models/model-39724.html", STYLE);
        labels[76] = new Label("http://www.cadnav.com/3d-models/model-39723.html", STYLE);
        labels[77] = new Label("http://www.cadnav.com/3d-models/model-39267.html", STYLE);
        labels[78] = new Label("http://www.cadnav.com/3d-models/model-39253.html", STYLE);
        labels[79] = new Label("http://www.cadnav.com/3d-models/model-39445.html", STYLE);
        labels[80] = new Label("http://www.cadnav.com/3d-models/model-40882.html", STYLE);
        labels[81] = new Label("http://www.cadnav.com/3d-models/model-40786.html", STYLE);
        labels[82] = new Label("http://www.cadnav.com/3d-models/model-38396.html", STYLE);
        labels[83] = new Label("http://www.cadnav.com/3d-models/model-38873.html", STYLE);
        labels[84] = new Label("http://www.cadnav.com/3d-models/model-38207.html", STYLE);
        labels[85] = new Label("http://www.cadnav.com/3d-models/model-38143.html", STYLE);
        labels[86] = new Label("http://www.cadnav.com/3d-models/model-38460.html", STYLE);
        labels[87] = new Label("http://www.cadnav.com/3d-models/model-37946.html", STYLE);
        labels[88] = new Label("http://www.cadnav.com/3d-models/model-38117.html", STYLE);
        labels[89] = new Label("http://www.cadnav.com/3d-models/model-37981.html", STYLE);
        labels[90] = new Label("http://www.cadnav.com/3d-models/model-37934.html", STYLE);
        labels[91] = new Label("http://www.cadnav.com/3d-models/model-37935.html", STYLE);
        labels[92] = new Label("http://www.cadnav.com/3d-models/model-37669.html", STYLE);
        labels[93] = new Label("http://www.cadnav.com/3d-models/model-37589.html", STYLE);
        labels[94] = new Label("http://www.cadnav.com/3d-models/model-36625.html", STYLE);
        labels[95] = new Label("http://www.cadnav.com/3d-models/model-36932.html", STYLE);
        labels[96] = new Label("http://www.cadnav.com/3d-models/model-35266.html", STYLE);
        labels[97] = new Label("http://www.cadnav.com/3d-models/model-35226.html", STYLE);
        labels[98] = new Label("http://www.cadnav.com/3d-models/model-23475.html", STYLE);
        labels[99] = new Label("http://www.cadnav.com/3d-models/model-37926.html", STYLE);
        labels[100] = new Label("http://www.cadnav.com/3d-models/model-37930.html", STYLE);
        labels[101] = new Label("http://www.cadnav.com/3d-models/model-37584.html", STYLE);
        labels[102] = new Label("http://www.cadnav.com/3d-models/model-18842.html", STYLE);
        labels[103] = new Label("http://www.cadnav.com/3d-models/model-22659.html", STYLE);
        labels[104] = new Label("http://www.cadnav.com/3d-models/model-22278.html", STYLE);
        labels[105] = new Label("http://www.cadnav.com/3d-models/model-22711.html", STYLE);
        labels[106] = new Label("http://www.cadnav.com/3d-models/model-38870.html", STYLE);
        labels[107] = new Label("http://www.cadnav.com/3d-models/model-38868.html", STYLE);
        labels[108] = new Label("http://www.cadnav.com/3d-models/model-38871.html", STYLE);
        labels[109] = new Label("http://www.cadnav.com/3d-models/model-38878.html", STYLE);
        labels[110] = new Label("http://www.cadnav.com/3d-models/model-39259.html", STYLE);
        labels[111] = new Label("http://www.cadnav.com/3d-models/model-39968.html", STYLE);
        labels[112] = new Label("http://www.cadnav.com/3d-models/model-40120.html", STYLE);
        labels[113] = new Label("http://www.cadnav.com/3d-models/model-40593.html", STYLE);
        labels[114] = new Label("http://www.cadnav.com/3d-models/model-40263.html", STYLE);
        labels[115] = new Label("http://www.cadnav.com/3d-models/model-40214.html", STYLE);
        labels[116] = new Label("http://www.cadnav.com/3d-models/model-38463.html", STYLE);
        labels[117] = new Label("http://www.cadnav.com/3d-models/model-37985.html", STYLE);
        labels[118] = new Label("http://www.cadnav.com/3d-models/model-38134.html", STYLE);
        labels[119] = new Label("http://www.cadnav.com/3d-models/model-37592.html", STYLE);
        labels[120] = new Label("http://www.cadnav.com/3d-models/model-37917.html", STYLE);
        labels[121] = new Label("http://www.cadnav.com/3d-models/model-37916.html", STYLE);
        labels[122] = new Label("http://www.cadnav.com/3d-models/model-40347.html", STYLE);
        labels[123] = new Label("http://www.cadnav.com/3d-models/model-40587.html", STYLE);
        labels[124] = new Label("J. W. Bjerk (eleazzaar) -- www.jwbjerk.com/art -- find this and other open art at: http://opengameart.org", STYLE);
        labels[125] = new Label("HorrorPen https://opengameart.org/content/ice-spell-icons", STYLE);

        for(int i = 0; i < 126; i++)
            labels[i].setFontScale(0.5f);

        for(int i = 0; i < 126; i++){
            scrollTable.add(labels[i]);
            scrollTable.row();
        }

        final ScrollPane scroll = new ScrollPane(scrollTable);
        scroll.setScrollingDisabled(false, false);

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
