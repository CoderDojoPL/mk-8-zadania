import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa okna do wprowadzania zadania.
 *
 * @author Pawel Kaczanowski
 * @version 1.0
 */
public class OknoZadania extends Window {

    /**
     * Pola tekstowe opis i termin wykonania.
     */
    private TextBox opisBox, terminWykonaniaBox;

    /**
     * Przyciski dodaj i zamknij.
     */
    private Button przyciskDodaj, przyciskZamknij;

    /**
     * Przechowuje wprowadzone zadanie.
     */
    private Zadanie wprowadzoneZadanie;

    /**
     * Konstruktor klasy OknoZadania
     */
    public OknoZadania() {
        super("Nowe zadanie");

        // stworzenie kontenera glownego
        Container glownyKontener = new Container(new Point(1, 3));

        // stworzenie pola opisu
        opisBox = new TextBox(new Point(160, 60), "");
        // stworzenie etykiety opisu
        Label etykietaOpisu = new Label("Opis:");
        // stworzenie kontenera opisu
        Container kontenerOpis = new Container(new Point(2, 1));

        //dodanie do kontenera
        kontenerOpis.addComponent(etykietaOpisu);
        kontenerOpis.addComponent(opisBox);

        //dodanie kontenera opisu do kontenera glownego
        glownyKontener.addComponent(kontenerOpis);

        //stworzenie elementow GUI dla terminu wykonania
        terminWykonaniaBox = new TextBox(new Point(100, 20), "");
        terminWykonaniaBox.acceptOnly("0123456789-");
        terminWykonaniaBox.setMaxLength(10);

        Label etykietaTerminWykonania = new Label("Termin:");

        //stworzenie kontenera dla terminu wykonania i dodanie obiektow
        Container kontenerTermin = new Container(new Point(2, 1));
        kontenerTermin.addComponent(etykietaTerminWykonania);
        kontenerTermin.addComponent(terminWykonaniaBox);
        glownyKontener.addComponent(kontenerTermin);

        // stworzenie przyciskow
        przyciskDodaj = new Button("Dodaj", new Point(50, 23));
        przyciskZamknij = new Button("Zamknij", new Point(50, 23));

        // stworzenie konetenra przyciskow
        Container kontenerPrzyciskow = new Container(new Point(2, 1));
        // dodanie przyciskow do kontenera
        kontenerPrzyciskow.addComponent(przyciskDodaj);
        kontenerPrzyciskow.addComponent(przyciskZamknij);
        // dodanie kontenera przyciskow do glownego konenera
        glownyKontener.addComponent(kontenerPrzyciskow);

        // dodanie glownego kontenera do okna
        addContainer(glownyKontener);

    }

    @Override
    public void act() {
        super.act();

        if (przyciskZamknij.wasClicked()) {
            //ukrycie okna
            toggleShow();
        }

        if (przyciskDodaj.wasClicked()) {
            //pobranie opisu
            String opis = opisBox.getText();

            //pobranie terminu wykonania
            String terminWykonaniaNapis = terminWykonaniaBox.getText();
            Date terminWykonania = wczytajTermin(terminWykonaniaNapis);

            //przypisanie zadani
            wprowadzoneZadanie = new Zadanie(opis, terminWykonania);

            //ukrycie okna
            toggleShow();
        }
    }

    public Zadanie pobierzWprowadzoneZadanie() {
        Zadanie temp = wprowadzoneZadanie;
        wprowadzoneZadanie = null;
        return temp;
    }

    /**
     * Zamienia termin wykonania z napisu na date.
     */
    private Date wczytajTermin(String data) {

        //stworzenie parsera ze znanym juz nam formatem daty
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {

            //jesli niepoprawne to wyrzuci ParseException
            Date skonwertowanaData = parser.parse(data);
            return skonwertowanaData;

            //  zlapanie bledu
        } catch (Exception e) {
            //jestli blad to zwracam aktualna date
            return new Date();
        }
    }
}
