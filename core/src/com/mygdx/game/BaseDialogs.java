package com.mygdx.game;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class BaseDialogs {
    //[npc][text][nextTex]//
    private static final int COUNT_NPC = 17;

    //[npc][text][nextTex]//
    public static final String[][] TEXT = new String[COUNT_NPC][];
    public static final int[][][] INDEX_OPTIONS = new int[COUNT_NPC][][];
    public static final int[][] INDEX_LISTENER = new int[COUNT_NPC][];
    public static final int[][] STARTING_TEXT = new int[COUNT_NPC][5];

    public static final void loadNpcTextList(){
        //Npc #1
        //STARTING_TEXT = {TEXT_NPC, TEXT_ONE, TEXT_TWO, TEXT_THREE, COUNT_HERO_TEXT}
        STARTING_TEXT[0] = new int[]{18, 0, 1, -1, -1};
        TEXT[0] = new String[27];
        TEXT[0][0] = " Nie wiem ja nic nie pamiętam.";
        TEXT[0][1] = " Co ja tu robie?";
        TEXT[0][2] = " Gdzie ja jestem?";
        TEXT[0][3] = " Powiedz mi o co tu chodzi.";
        TEXT[0][4] = " Nie wiem dokąd wracać powiedz mi wszystko.";
        TEXT[0][5] = " To znaczy?";
        TEXT[0][6] = " Jestem wojownikiem niczego się nie boję mów dalej.";
        TEXT[0][7] = " To wszystko brzmi strasznie...";
        TEXT[0][8] = " Co się znajduję w tych krainach?";
        TEXT[0][9] = " Nie zrobie tego. Powiedz co ty tu robisz?";
        TEXT[0][10] = " Co robisz na tej wyspie?";
        TEXT[0][11] = " Mogę wam pomóc.";
        TEXT[0][12] = " Pomogę wam.";
        TEXT[0][13] = " Przyłączę się do was.";
        TEXT[0][14] = " Co to za zło?";
        TEXT[0][15] = " Jakie niebezpieczeństwo się tu kryje?";
        TEXT[0][16] = " Ruszam wytnę ich wszystkich.";
        TEXT[0][17] = " Ruszam pościnam te ścierwa.";

        TEXT[0][18] = " Kim jesteś?";
        TEXT[0][19] = " Nie wiem jak tu się znalazłeś i skąd wogóle wiesz o tym miejscu widziałem tylko jak samotnie dryfujesz nieprzytomny na łódce.";
        TEXT[0][20] = " Rozsądniej będzię gdy wrócisz skąd przybyłeś i zapomnisz o tym miejscu.";
        TEXT[0][21] = " Sam tego chciałeś. Jest to wyspa zwana sześcioma żywiołami...";
        TEXT[0][22] = " Są tu krainy Toksyczna zieleń, Ogniste Piaski i Lodowa odchłań";
        TEXT[0][23] = " Zło którego nie jesteś w stanie sobie wyobrazić dlatego będzie lepiej jak stąd odpłyniesz.";
        TEXT[0][24] = " Nie jestem tu sam staramy się walczyć ze złem ale jest coraz gorzej. Upadamy";
        TEXT[0][25] = " Widzę że mężny z ciebie człowiek lecz to za mało";
        TEXT[0][26] = " Ruszaj przed siebie na tej mapie znajdziesz jeszcze dwoje ludzi. Powiedzą ci oni dużo więcej aby do nich dotrzeć musisz pokonać kilka stworów. To tylko namiastka zła które czeka na ciebię";
        //
        //NPC #2
        //hero
        STARTING_TEXT[1] = new int[]{6, 0, 1, 2, -1};
        TEXT[1] = new String[9];
        TEXT[1][0] = " Nie pamiętam jak tu się znalazłem";
        TEXT[1][1] = " Rozmawiałem z Aldernem opowiedział mi o wyspie";
        TEXT[1][2] = " Pokaż mi co masz na sprzedarz";
        TEXT[1][3] = " Co to za lista?";
        TEXT[1][4] = " Rozumiem ruszam do niego";
        TEXT[1][5] = " Ruszam do niego";
        //npc
        TEXT[1][6] = " Witaj, widzę cię po raz pierwszy. Skąd przybyłeś? Chcesz coś kupić?";
        TEXT[1][7] = " Achius powie ci wszystko na temat wyspy i opowie o liście czempionów";
        TEXT[1][8] = "Dowiesz się wszystkiego od niego ale bądz czujny na czające się w pobliżu stwory";
        //
        //NPC #3
        //hero
        STARTING_TEXT[2] = new int[]{15, 0, 1, -1, -1};
        TEXT[2] = new String[25];
        TEXT[2][0] = " Co to za miejsce?";
        TEXT[2][1] = " Opowiedz mi o tym miejscu";
        TEXT[2][2] = " Od jak dawna jesteś na tej wyspie?";
        TEXT[2][3] = " Kogo zgładzić?";
        TEXT[2][4] = " Co masz na myśli?";
        TEXT[2][5] = " Skąd wiesz że jest ich pięć?";
        TEXT[2][6] = " Skąd masz te wszystkie informacje?";
        TEXT[2][7] = " Jaki jest wasz cel?";
        TEXT[2][8] = " Jakie masz dla mnie zadanie?";
        TEXT[2][9] = " Aldern mówił o pozostałych";
        TEXT[2][10] = " Aldern wspomniał że są tu jeszcze inni";
        TEXT[2][11] = " Co oni tam robią?";
        TEXT[2][12] = " Zrobię to! Zgładzę ich!";
        TEXT[2][13] = " Co jeszcze powinieniem wiedzieć?";
        TEXT[2][14] = " Zaprzysięgam się żę podołam zadaniu. To ja zgładzę wszystko co stanie mi na drodzę";
        //npc
        TEXT[2][15] = " Witaj czego potrzebujesz?";
        TEXT[2][16] = " Od dawna walczymy ze złem na tej wyspie. Niepozwalamy aby rozprzestrzeniło się poza nią. Walczymy ze stworami.";
        TEXT[2][17] = " Od samej młodości. Poległo tu wielu odważnych i mężnych ale jeszcze nikomu nie udało mu się zgładzić";
        TEXT[2][18] = " Aldern dał Ci mapę i listę pięciu mrocznych czempionów. Nikomu jeszcze nie udało się ich zgładzić";
        TEXT[2][19] = " Gdy tu przybyłem, był wśród nas Vendgar. Legenda to on przedarł się do ostatniego najpotężniejszego czempiona, lecz nie podołał";
        TEXT[2][20] = " Gdy zostanie pokonana cała piątka obejmiemy władzę nad wyspą i całym złem które się stąd wywodzi";
        TEXT[2][21] = " Tak znajdziesz ich na innych mapach, tu jest najniebezpieczniej więc uważaj gdzie zmierzasz";
        TEXT[2][22] = " Walczą a twoim zadaniem jest im pomóc zwalczać zło. Czekamy na tego który wykończy całą piątkę najpotężniejszych monstrów";
        TEXT[2][23] = " Pamiętaj że czeka cię bardzo długa cięzka droga i dużo przelanej krwi";
        TEXT[2][24] = " Na każdej mapie znajdziesz kogoś kto ma element ekwipunku na sprzedarz. Wykonuj zadania od naszych a będziesz miał pieniądze";
        //
        //NPC #4
        //hero
        STARTING_TEXT[3] = new int[]{3, 0, 1, -1, 2};
        TEXT[3] = new String[4];
        TEXT[3][0] = " Tak, zrobię to.";
        TEXT[3][1] = " Nie, nie mam na to czasu.";
        TEXT[3][2] = " Wykonałem zadanie.";
        //npc
        TEXT[3][3] = " Zaczekaj, pomóż mi zabić x x-name a wynagrodzę cię. Podejmujesz sie tego wyzwania?";
        //
        //NPC #5
        //hero
        STARTING_TEXT[4] = new int[]{5, 0, 1, -1, 4};
        TEXT[4] = new String[7];
        TEXT[4][0] = " A w czym rzecz?";
        TEXT[4][1] = " Nie, innym razem.";
        TEXT[4][2] = " Biorę to.";
        TEXT[4][3] = " Nie, innym razem.";
        TEXT[4][4] = " Wykonalem zadanie.";
        //npc
        TEXT[4][5] = " Rzucam ci wyzwanie podejmiesz się go?";
        TEXT[4][6] = " Chodzi o zabicie x x-name";
        //
        //NPC #6
        //hero
        STARTING_TEXT[5] = new int[]{5, 0, 1, -1, 4};
        TEXT[5] = new String[7];
        TEXT[5][0] = " Chciałbym coś kupić";
        TEXT[5][1] = " Chcę się zmierzyć z 'Valmorgiem'";
        TEXT[5][2] = " Zetnę mu głowę jestem tego pewien. Ruszajmy.";
        TEXT[5][3] = " Jednak zrezygnuję";
        TEXT[5][4] = " Wykonałem zlecenie";
        //npc
        TEXT[5][5] = " Witaj, chcesz coś kupić czy wybrać się na spotkanie z 'Valmorgiem', jednym z mrocznej piątki czempionów?";
        TEXT[5][6] = " Mogę cię tam zabrać łódką, ale pamiętaj gdy zginiesz będziesz musiał wybić jego sługusów od początku";
        //
        //NPC #7
        //hero
        STARTING_TEXT[6] = new int[]{5, 0, 1, -1, 4};
        TEXT[6] = new String[8];
        TEXT[6][0] = " Przybyłem tu aby walczyć.";
        TEXT[6][1] = " Mogę coś dla ciebie zrobić?";
        TEXT[6][2] = " Ok, zaraz wracam z trofeum.";
        TEXT[6][3] = " Nie zrobię tego, idę dalej.";
        TEXT[6][4] = " Wykonałem zadanie";
        //npc
        TEXT[6][5] = " Czego tu szukasz?";
        TEXT[6][6] = " Każdy tak mówi, a większośc upada.";
        TEXT[6][7] = " Tak, jeśli chcesz mi pomóc zabij x x-name.";
        //
        //NPC #8
        //hero
        STARTING_TEXT[7] = new int[]{5, 0, 1, -1, 4};
        TEXT[7] = new String[7];
        TEXT[7][0] = " Tak, pokaż swój asortyment.";
        TEXT[7][1] = " Masz dla mnie jakieś zadanie?";
        TEXT[7][2] = " Ok, już się robi.";
        TEXT[7][3] = " Nie, nie chcę.";
        TEXT[7][4] = " Wykonałem zadanie.";
        //npc
        TEXT[7][5] = " Mam asortyment na sprzedarz chcesz coś kupic?";
        TEXT[7][6] = " Jeśli chcesz zarobić zabij x x-name.";
        //
        //NPC #9
        //hero
        STARTING_TEXT[8] = new int[]{3, 0, 1, -1, 2};
        TEXT[8] = new String[4];
        TEXT[8][0] = " Upoluję go dla ciebie.";
        TEXT[8][1] = " To zbyt trudne dla mnie.";
        TEXT[8][2] = " Wykonałem zadanie.";
        //npc
        TEXT[8][3] = " Zatrzymaj się. Za moimi plecami jest jaskinia, znajduje się tam 'Quaregis' czwarty czempion z listy. Przynieś mi jego głowę a dostaniesz nagrodę";
        //
        //NPC #10
        //hero
        STARTING_TEXT[9] = new int[]{3, 0, 1, -1, 2};
        TEXT[9] = new String[4];
        TEXT[9][0] = " Pokaż sprzęt.";
        TEXT[9][1] = " Innym razem coś kupię.";
        TEXT[9][2] = " Wykonałem zadanie.";
        //npc
        TEXT[9][3] = " Mogę ci zaoferować nowy sprzęt.";
        //
        //NPC #11
        //hero
        STARTING_TEXT[10] = new int[]{4, 0, -1, -1, 3};
        TEXT[10] = new String[6];
        TEXT[10][0] = " Co dokładnie mam zrobić?";
        TEXT[10][1] = " Biorę to.";
        TEXT[10][2] = " Rezygnuję";
        TEXT[10][3] = " Wykonałem zadanie.";
        //npc
        TEXT[10][4] = " Mam dla Ciebie zadanie specjalne.";
        TEXT[10][5] = " Zabij x x-name.";
        //
        //NPC #12
        //hero
        STARTING_TEXT[11] = new int[]{4, 0, -1, -1, 3};
        TEXT[11] = new String[6];
        TEXT[11][0] = " Powiedz najpierw co mam zrobić.";
        TEXT[11][1] = " Podejmę się tego zadania.";
        TEXT[11][2] = " Masz rację to zbyt trudnę";
        TEXT[11][3] = " Wykonałem zadanie.";
        //npc
        TEXT[11][4] = " Jesteś gotowy aby podjąć się kolejnego wyzwania?";
        TEXT[11][5] = " Jest to trudne zadanie. Zabij x x-name.";
        //
        //NPC #13
        //hero
        STARTING_TEXT[12] = new int[]{4, 0, -1, -1, 3};
        TEXT[12] = new String[6];
        TEXT[12][0] = " Moim celem jest zgładzenie wszystkich czempionów.";
        TEXT[12][1] = " Tak, idę ich wyciąć.";
        TEXT[12][2] = " Nie, zrobię to innym razem.";
        TEXT[12][3] = " Wykonałem zadanie.";
        //npc
        TEXT[12][4] = " Witaj, słyszałem o tobie. Podobno bardzo dobrze sobie radzisz.";
        TEXT[12][5] = " Dlatego mam dla ciebie zadanie, Zabij x x-name.";
        //
        //NPC #14
        //hero
        STARTING_TEXT[13] = new int[]{5, 0, 1, -1, 4};
        TEXT[13] = new String[7];
        TEXT[13][0] = " Chcę coś kupić, pokąz co masz.";
        TEXT[13][1] = " Oczywiścię że, chcę zarobić.";
        TEXT[13][2] = " Ruszam w drogę.";
        TEXT[13][3] = " Nie, nie mam teraz na to czasu.";
        TEXT[13][4] = " Wykonałem zadanie";
        //npc
        TEXT[13][5] = " Witaj bohhaterze, chcesz coś kupić? A może chcesz zarobić?";
        TEXT[13][6] = " Musisz zabić x x-name, którzy kręcą się w pobliżu obozu.";
        //
        //NPC #15
        //hero
        STARTING_TEXT[14] = new int[]{5, 0, 1, -1, 4};
        TEXT[14] = new String[7];
        TEXT[14][0] = " W czym mogę ci pomóc?";
        TEXT[14][1] = " Czego odemnie chcesz?";
        TEXT[14][2] = " Zrobię to dla ciebie.";
        TEXT[14][3] = " Nie zrobię tego, nie zatrzymuj mnie.";
        TEXT[14][4] = " Wykonałem zadanie.";
        //npc
        TEXT[14][5] = " Zaczekaj, potrzebuje kogoś takiego jak ty.";
        TEXT[14][6] = " Oczywiście chcę krwi. Przelej krew x x-name. ";
        //
        //NPC #16
        //hero
        STARTING_TEXT[15] = new int[]{8, 0, 1, 2, 7};
        TEXT[15] = new String[12];
        TEXT[15][0] = " Kogo mam dla ciebie zabic?";
        TEXT[15][1] = " Niczego się nie boję.";
        TEXT[15][2] = " Nic mnie nie zatrzyma.";
        TEXT[15][3] = " Przybyłem tu aby ściąć głowę Morganie.";
        TEXT[15][4] = " Ruszam w drogę po głowę Morgany!";
        TEXT[15][5] = " Nie lękam się, przyjmuję to zadanie.";
        TEXT[15][6] = " Muszę odmówić wykonania tego zadania.";
        TEXT[15][7] = " Wykonałem zadanie.";
        //npc
        TEXT[15][8] = " Zatrzymaj się, nie idz dalej jeśli chcesz zachować życe.";
        TEXT[15][9] = " Zabij x x-name, będzie to dobry sprawdzian twoich umiejętności.";
        TEXT[15][10] = " Lodowa odchłań to kraina inna niż wszystkie, tu zło jest owiele potężniejsze i bardziej przebiegłe.";
        TEXT[15][11] = " Przygotuj się do tej walki, będzie ona wręcz najbardziej przerażająca, poleje się krew i bądz przygotowany na śmierć.";
        //
        //NPC #17
        //hero
        STARTING_TEXT[16] = new int[]{8, 0, 1, -1, 7};
        TEXT[16] = new String[11];
        TEXT[16][0] = " Czy masz coś na sprzedarz?";
        TEXT[16][1] = " Chcę przyjąć to zadanie bez względu na wszystko.";
        TEXT[16][2] = " Zrezygnuję, muszę stać się jeszcze silniejszy.";
        TEXT[16][3] = " Ruszam po ich głowy!";
        TEXT[16][4] = " Nie jestem jeszcze gotowy na to wyzwanie.";
        TEXT[16][5] = " Muszę je zobaczyć.";
        TEXT[16][6] = " Innym razem je obejrzę.";
        TEXT[16][7] = " Wykonałem zadanie.";
        //npc
        TEXT[16][8] = " Stój, Mam dla ciebie najtrudniejsze zadanie, któremu jeszcze nikt nie podołał";
        TEXT[16][9] = " Zabij x x-name";
        TEXT[16][10] = " Tak, mam kilka epickich przedmiotów na miarę ciebie.";
    }

    public static final void loadIndexOptions(){
        //Npc #1 to hero answer
        INDEX_OPTIONS[0] = new int[18][];
        INDEX_OPTIONS[0][0] = new int[]{19, 2};
        INDEX_OPTIONS[0][1] = new int[]{19, 2};
        INDEX_OPTIONS[0][2] = new int[]{20, 3, 4};
        INDEX_OPTIONS[0][3] = new int[]{21, 5, 6};
        INDEX_OPTIONS[0][4] = new int[]{21, 5, 6};
        INDEX_OPTIONS[0][5] = new int[]{22, 7, 8};
        INDEX_OPTIONS[0][6] = new int[]{22, 7, 8};
        INDEX_OPTIONS[0][7] = new int[]{23, 9, 10};
        INDEX_OPTIONS[0][8] = new int[]{23, 9, 10};
        INDEX_OPTIONS[0][9] = new int[]{24, 11, 12, 13};
        INDEX_OPTIONS[0][10] = new int[]{24, 11, 12, 13};
        INDEX_OPTIONS[0][11] = new int[]{25, 14, 15};
        INDEX_OPTIONS[0][12] = new int[]{25, 14, 15};
        INDEX_OPTIONS[0][13] = new int[]{25, 14, 15};
        INDEX_OPTIONS[0][14] = new int[]{26, 16, 17};
        INDEX_OPTIONS[0][15] = new int[]{26, 16, 17};
        INDEX_OPTIONS[0][16] = new int[]{};
        INDEX_OPTIONS[0][17] = new int[]{};
        //
        //NPC #2
        INDEX_OPTIONS[1] = new int[6][];
        INDEX_OPTIONS[1][0] = new int[]{7, 3, 4};
        INDEX_OPTIONS[1][1] = new int[]{7, 3, 4};
        INDEX_OPTIONS[1][2] = new int[]{7, 3, 4};
        INDEX_OPTIONS[1][3] = new int[]{8, 5};
        INDEX_OPTIONS[1][4] = new int[]{};
        INDEX_OPTIONS[1][5] = new int[]{};
        //
        //NPC #3
        INDEX_OPTIONS[2] = new int[15][];
        INDEX_OPTIONS[2][0] = new int[]{16, 2};
        INDEX_OPTIONS[2][1] = new int[]{16, 2};
        INDEX_OPTIONS[2][2] = new int[]{17, 3, 4};
        INDEX_OPTIONS[2][3] = new int[]{18, 5, 6};
        INDEX_OPTIONS[2][4] = new int[]{18, 5, 6};
        INDEX_OPTIONS[2][5] = new int[]{19, 7, 8};
        INDEX_OPTIONS[2][6] = new int[]{19, 7, 8};
        INDEX_OPTIONS[2][7] = new int[]{20, 9, 10};
        INDEX_OPTIONS[2][8] = new int[]{20, 9, 10};
        INDEX_OPTIONS[2][9] = new int[]{21, 11};
        INDEX_OPTIONS[2][10] = new int[]{21, 11};
        INDEX_OPTIONS[2][11] = new int[]{22, 12};
        INDEX_OPTIONS[2][12] = new int[]{23, 13};
        INDEX_OPTIONS[2][13] = new int[]{24, 14};
        INDEX_OPTIONS[2][14] = new int[]{};
        //
        //NPC #4
        INDEX_OPTIONS[3] = new int[3][];
        INDEX_OPTIONS[3][0] = new int[]{};
        INDEX_OPTIONS[3][1] = new int[]{};
        INDEX_OPTIONS[3][2] = new int[]{};
        //
        //npc #5
        INDEX_OPTIONS[4] = new int[5][];
        INDEX_OPTIONS[4][0] = new int[]{5, 2, 3};
        INDEX_OPTIONS[4][1] = new int[]{};
        INDEX_OPTIONS[4][2] = new int[]{};
        INDEX_OPTIONS[4][3] = new int[]{};
        INDEX_OPTIONS[4][4] = new int[]{};
        //
        //NPC #6
        INDEX_OPTIONS[5] = new int[5][];
        INDEX_OPTIONS[5][0] = new int[]{};
        INDEX_OPTIONS[5][1] = new int[]{6, 2, 3};
        INDEX_OPTIONS[5][2] = new int[]{};
        INDEX_OPTIONS[5][3] = new int[]{};
        INDEX_OPTIONS[5][4] = new int[]{};
        //
        //NPC #7
        INDEX_OPTIONS[6] = new int[5][];
        INDEX_OPTIONS[6][0] = new int[]{5, 1};
        INDEX_OPTIONS[6][1] = new int[]{6, 2, 3};
        INDEX_OPTIONS[6][2] = new int[]{};
        INDEX_OPTIONS[6][3] = new int[]{};
        INDEX_OPTIONS[6][4] = new int[]{};
        //
        //NPC #8
        INDEX_OPTIONS[7] = new int[5][];
        INDEX_OPTIONS[7][0] = new int[]{};
        INDEX_OPTIONS[7][1] = new int[]{6, 2, 3};
        INDEX_OPTIONS[7][2] = new int[]{};
        INDEX_OPTIONS[7][3] = new int[]{};
        INDEX_OPTIONS[7][4] = new int[]{};
        //
        //NPC #9
        INDEX_OPTIONS[8] = new int[3][];
        INDEX_OPTIONS[8][0] = new int[]{};
        INDEX_OPTIONS[8][1] = new int[]{};
        INDEX_OPTIONS[8][2] = new int[]{};
        //
        //NPC #10
        INDEX_OPTIONS[9] = new int[3][];
        INDEX_OPTIONS[9][0] = new int[]{};
        INDEX_OPTIONS[9][1] = new int[]{};
        INDEX_OPTIONS[9][2] = new int[]{};
        //
        //NPC #11
        INDEX_OPTIONS[10] = new int[4][];
        INDEX_OPTIONS[10][0] = new int[]{4, 1, 2};
        INDEX_OPTIONS[10][1] = new int[]{};
        INDEX_OPTIONS[10][2] = new int[]{};
        INDEX_OPTIONS[10][3] = new int[]{};
        //
        //NPC #12
        INDEX_OPTIONS[11] = new int[4][];
        INDEX_OPTIONS[11][0] = new int[]{5, 1, 2};
        INDEX_OPTIONS[11][1] = new int[]{};
        INDEX_OPTIONS[11][2] = new int[]{};
        INDEX_OPTIONS[11][3] = new int[]{};
        //
        //NPC #13
        INDEX_OPTIONS[12] = new int[4][];
        INDEX_OPTIONS[12][0] = new int[]{5, 1, 2};
        INDEX_OPTIONS[12][1] = new int[]{};
        INDEX_OPTIONS[12][2] = new int[]{};
        INDEX_OPTIONS[12][3] = new int[]{};
        //
        //NPC #14
        INDEX_OPTIONS[13] = new int[5][];
        INDEX_OPTIONS[13][0] = new int[]{};
        INDEX_OPTIONS[13][1] = new int[]{6, 2, 3};
        INDEX_OPTIONS[13][2] = new int[]{};
        INDEX_OPTIONS[13][3] = new int[]{};
        INDEX_OPTIONS[13][4] = new int[]{};
        //
        //npc #15
        INDEX_OPTIONS[14] = new int[5][];
        INDEX_OPTIONS[14][0] = new int[]{6, 2, 3};
        INDEX_OPTIONS[14][1] = new int[]{6, 2, 3};
        INDEX_OPTIONS[14][2] = new int[]{};
        INDEX_OPTIONS[14][3] = new int[]{};
        INDEX_OPTIONS[14][4] = new int[]{};
        //
        //NPC #16
        //hero
        INDEX_OPTIONS[15] = new int[8][];
        INDEX_OPTIONS[15][0] = new int[]{9, 5, 6};
        INDEX_OPTIONS[15][1] = new int[]{10, 3};
        INDEX_OPTIONS[15][2] = new int[]{10, 3};
        INDEX_OPTIONS[15][3] = new int[]{11, 4};
        INDEX_OPTIONS[15][4] = new int[]{};
        INDEX_OPTIONS[15][5] = new int[]{};
        INDEX_OPTIONS[15][6] = new int[]{};
        INDEX_OPTIONS[15][7] = new int[]{};
        //
        //NPO #17
        //hero
        INDEX_OPTIONS[16] = new int[8][];
        INDEX_OPTIONS[16][0] = new int[]{10, 5, 6};
        INDEX_OPTIONS[16][1] = new int[]{10, 3, 4};
        INDEX_OPTIONS[16][2] = new int[]{10, 3, 4};
        INDEX_OPTIONS[16][3] = new int[]{};
        INDEX_OPTIONS[16][4] = new int[]{};
        INDEX_OPTIONS[16][5] = new int[]{};
        INDEX_OPTIONS[16][6] = new int[]{};
        INDEX_OPTIONS[16][7] = new int[]{};
    }

    /**
     * info = 0
     * exit = 1
     * shop = 2
     * task = 3
     * replace = 4
     * rewardQuest = 5
     */
    public static final void loadIndexListener(){
        //Npc #1 to hero answer
        INDEX_LISTENER[0] = new int[18];
        INDEX_LISTENER[0][0] = 0;
        INDEX_LISTENER[0][1] = 0;
        INDEX_LISTENER[0][2] = 0;
        INDEX_LISTENER[0][3] = 0;
        INDEX_LISTENER[0][4] = 0;
        INDEX_LISTENER[0][5] = 0;
        INDEX_LISTENER[0][6] = 0;
        INDEX_LISTENER[0][7] = 0;
        INDEX_LISTENER[0][8] = 0;
        INDEX_LISTENER[0][9] = 0;
        INDEX_LISTENER[0][10] = 0;
        INDEX_LISTENER[0][11] = 0;
        INDEX_LISTENER[0][12] = 0;
        INDEX_LISTENER[0][13] = 0;
        INDEX_LISTENER[0][14] = 0;
        INDEX_LISTENER[0][15] = 0;
        INDEX_LISTENER[0][16] = 1;
        INDEX_LISTENER[0][17] = 1;
        //
        //NPC #2
        INDEX_LISTENER[1] = new int[6];
        INDEX_LISTENER[1][0] = 0;
        INDEX_LISTENER[1][1] = 0;
        INDEX_LISTENER[1][2] = 2;
        INDEX_LISTENER[1][3] = 0;
        INDEX_LISTENER[1][4] = 1;
        INDEX_LISTENER[1][5] = 1;
        //
        //NPC #3
        INDEX_LISTENER[2] = new int[15];
        INDEX_LISTENER[2][0] = 0;
        INDEX_LISTENER[2][1] = 0;
        INDEX_LISTENER[2][2] = 0;
        INDEX_LISTENER[2][3] = 0;
        INDEX_LISTENER[2][4] = 0;
        INDEX_LISTENER[2][5] = 0;
        INDEX_LISTENER[2][6] = 0;
        INDEX_LISTENER[2][7] = 0;
        INDEX_LISTENER[2][8] = 0;
        INDEX_LISTENER[2][9] = 0;
        INDEX_LISTENER[2][10] = 0;
        INDEX_LISTENER[2][11] = 0;
        INDEX_LISTENER[2][12] = 0;
        INDEX_LISTENER[2][13] = 0;
        INDEX_LISTENER[2][14] = 1;
        //
        //NPC #4
        INDEX_LISTENER[3] = new int[3];
        INDEX_LISTENER[3][0] = 3;
        INDEX_LISTENER[3][1] = 1;
        INDEX_LISTENER[3][2] = 5;
        //
        //NPC #5
        INDEX_LISTENER[4] = new int[5];
        INDEX_LISTENER[4][0] = 0;
        INDEX_LISTENER[4][1] = 1;
        INDEX_LISTENER[4][2] = 3;
        INDEX_LISTENER[4][3] = 1;
        INDEX_LISTENER[4][4] = 5;
        //
        //NPC #6
        INDEX_LISTENER[5] = new int[5];
        INDEX_LISTENER[5][0] = 2;
        INDEX_LISTENER[5][1] = 0;
        INDEX_LISTENER[5][2] = 4;
        INDEX_LISTENER[5][3] = 1;
        INDEX_LISTENER[5][4] = 5;
        //
        //NPC #7
        INDEX_LISTENER[6] = new int[5];
        INDEX_LISTENER[6][0] = 0;
        INDEX_LISTENER[6][1] = 0;
        INDEX_LISTENER[6][2] = 3;
        INDEX_LISTENER[6][3] = 1;
        INDEX_LISTENER[6][4] = 5;
        //
        //NPC #8
        INDEX_LISTENER[7] = new int[5];
        INDEX_LISTENER[7][0] = 2;
        INDEX_LISTENER[7][1] = 0;
        INDEX_LISTENER[7][2] = 3;
        INDEX_LISTENER[7][3] = 1;
        INDEX_LISTENER[7][4] = 5;
        //
        //NPC #9
        INDEX_LISTENER[8] = new int[3];
        INDEX_LISTENER[8][0] = 3;
        INDEX_LISTENER[8][1] = 1;
        INDEX_LISTENER[8][2] = 5;
        //
        //NPC #10
        INDEX_LISTENER[9] = new int[3];
        INDEX_LISTENER[9][0] = 3;
        INDEX_LISTENER[9][1] = 1;
        INDEX_LISTENER[9][2] = 5;
        //
        //NPC #11
        INDEX_LISTENER[10] = new int[4];
        INDEX_LISTENER[10][0] = 0;
        INDEX_LISTENER[10][1] = 3;
        INDEX_LISTENER[10][2] = 1;
        INDEX_LISTENER[10][3] = 5;
        //
        //NPC #12
        INDEX_LISTENER[11] = new int[4];
        INDEX_LISTENER[11][0] = 0;
        INDEX_LISTENER[11][1] = 3;
        INDEX_LISTENER[11][2] = 1;
        INDEX_LISTENER[11][3] = 5;
        //
        //NPC #13
        INDEX_LISTENER[12] = new int[4];
        INDEX_LISTENER[12][0] = 0;
        INDEX_LISTENER[12][1] = 3;
        INDEX_LISTENER[12][2] = 1;
        INDEX_LISTENER[12][3] = 5;
        //
        //NPC #14
        INDEX_LISTENER[13] = new int[5];
        INDEX_LISTENER[13][0] = 2;
        INDEX_LISTENER[13][1] = 0;
        INDEX_LISTENER[13][2] = 3;
        INDEX_LISTENER[13][3] = 1;
        INDEX_LISTENER[13][4] = 5;
        //
        //NPC #15
        INDEX_LISTENER[14] = new int[5];
        INDEX_LISTENER[14][0] = 0;
        INDEX_LISTENER[14][1] = 0;
        INDEX_LISTENER[14][2] = 3;
        INDEX_LISTENER[14][3] = 1;
        INDEX_LISTENER[14][4] = 5;
        //
        //NPC #16
        INDEX_LISTENER[15] = new int[8];
        INDEX_LISTENER[15][0] = 0;
        INDEX_LISTENER[15][1] = 0;
        INDEX_LISTENER[15][2] = 0;
        INDEX_LISTENER[15][3] = 0;
        INDEX_LISTENER[15][4] = 1;
        INDEX_LISTENER[15][5] = 3;
        INDEX_LISTENER[15][6] = 1;
        INDEX_LISTENER[15][7] = 5;
        //
        //NPC #17
        INDEX_LISTENER[16] = new int[8];
        INDEX_LISTENER[16][0] = 0;
        INDEX_LISTENER[16][1] = 0;
        INDEX_LISTENER[16][2] = 1;
        INDEX_LISTENER[16][3] = 3;
        INDEX_LISTENER[16][4] = 1;
        INDEX_LISTENER[16][5] = 2;
        INDEX_LISTENER[16][6] = 1;
        INDEX_LISTENER[16][7] = 5;
    }

    public static String getText(int indexFirst, int indexSecond){
        return TEXT[indexFirst][indexSecond];
    }

    public static int[] getIndexToNextText(int indexFirst, int indexSecond){
        return INDEX_OPTIONS[indexFirst][indexSecond];
    }

    public static int getIndexListener(int indexFirst, int indexSecond){
        return INDEX_LISTENER[indexFirst][indexSecond];
    }
}