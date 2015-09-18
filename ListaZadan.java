import greenfoot.Greenfoot;
import greenfoot.World;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Klasa swiata - Lista zadan.
 *
 * @author Pawel Kaczanowski
 * @version 1.0
 */
public class ListaZadan extends World {

	// pole tekstowe - opis zadania
	private TextBox opisBox;

	// pole tekstowe - termin wykonania
	private TextBox terminWykonaniaBox;

	/**
	 * Pole przechowujace aktualne zadanie.
	 */
	private Zadanie aktualneZadanie;

	/**
	 * Przechowuje wszystkie zadania.
	 */
	private ArrayList<Zadanie> zadania;

	/**
	 * Przechowuje indeks aktualnego zadania.
	 */
	private int indeksAktualnegoZadania;

	/**
	 * Przycisk poprzedniego zadania.
	 */
	private Button poprzednieZadanie;

	/**
	 * Przycisk nastpenego zadania.
	 */
	private Button nastepneZadanie;

	/**
	 * Okno zadania.
	 */
	private OknoZadania oknoZadania;

	/**
	 * Przycisk pokazujacy okno zadania.
	 */
	private Button dodajZadanie;

	/**
	 * Konstruktor klasy ListaZadan.
	 */
	public ListaZadan() {
		super(600, 400, 1);

		// stworzenie listy
		zadania = new ArrayList<Zadanie>();
		// dodanie kolejnych zadan do listy
		zadania.add(new Zadanie("Pierwsze zadanie", new Date()));
		zadania.add(new Zadanie("Drugie zadanie", new Date()));
		zadania.add(new Zadanie("Trzecie zadanie", new Date()));

		// inicjacja aktualnego zadania
		indeksAktualnegoZadania = 0;
		aktualneZadanie = zadania.get(indeksAktualnegoZadania);

		// inicjacja kontrolek zadan
		inicjujPodgladZadania();

		// inicjacja przyciskow
		inicujujPrzyciskiNawigacji();

		// inicjacja okna zadania
		oknoZadania = new OknoZadania();
		// stworzenie i umieszczenie na ekranie przycisku dodaj zadanie
		dodajZadanie = new Button("Dodaj zadanie", new Point(120, 18));
		addObject(dodajZadanie, 500, 350);

		odswiezEkran();

		// uruchomienie aplikacji od razu po kompilacji
		Greenfoot.start();

	}

	private void odswiezEkran() {
		opisBox.setText(aktualneZadanie.getOpis());

		SimpleDateFormat formaterDaty = new SimpleDateFormat("yyyy-MM-dd");
		String terminWykonaniaNapis = formaterDaty.format(aktualneZadanie.getTerminWykonania());
		terminWykonaniaBox.setText(terminWykonaniaNapis);

	}

	/*
	 * Metoda inicujaca kontrolki do podgladu zadania.
	 */
	private void inicjujPodgladZadania() {
		// stworzenie etykiety
		Label etykieta = new Label("Opis zadania");
		// stworzenie pola tekstowego o rozmiarze 160x60 pikseli i z napisem "Testowe  zadanie"
		opisBox = new TextBox(new Point(160, 60), "Testowe zadanie");
		// ustawienie pola tylko do odczyty, nie bedzie mozna go edytowac
		opisBox.setReadOnly(true);

		// dodanie etykiety i pola tekstowego na odpowiednich pozycjach na
		// ekranie
		addObject(etykieta, 200, 100);
		addObject(opisBox, 350, 100);

		//  termin wykonania - etykieta
		Label etykietaTerminu = new Label("Termin wykonania");
		//  pole tekstowe
		terminWykonaniaBox = new TextBox(new Point(120, 18), "2015-08-22");
		//  tylko do odczytu
		terminWykonaniaBox.setReadOnly(true);
		addObject(etykietaTerminu, 200, 200);
		addObject(terminWykonaniaBox, 330, 200);

	}

	/**
	 * Inicuje przyciski zadania.
	 */
	private void inicujujPrzyciskiNawigacji() {

		//Stworzenie obiektow przyciskow
		poprzednieZadanie = new Button("Poprzednie zadanie", new Point(120, 18));
		nastepneZadanie = new Button("Nastepne zadanie", new Point(120, 18));

		// Dodanie przyciskow na ekranie
		addObject(poprzednieZadanie, 100, 30);
		addObject(nastepneZadanie, 500, 30);

	}

	@Override
	public void act() {
		super.act();

		sluchajNaZmianieZadania();

		sluchajNaDodaniuZadania();
	}

	/**
	 * Nasluchiwanie przyciskow na zmiane wyswietlanego zadania.
	 */
	private void sluchajNaZmianieZadania() {

		if (nastepneZadanie.wasClicked()) {
			indeksAktualnegoZadania = indeksAktualnegoZadania + 1;

			if (indeksAktualnegoZadania >= zadania.size()) {
				indeksAktualnegoZadania = 0;
			}
			aktualneZadanie = zadania.get(indeksAktualnegoZadania);
			odswiezEkran();
		}

		if (poprzednieZadanie.wasClicked()) {
			indeksAktualnegoZadania = indeksAktualnegoZadania - 1;

			if (indeksAktualnegoZadania < 0) {
				indeksAktualnegoZadania = zadania.size() - 1;
			}
			aktualneZadanie = zadania.get(indeksAktualnegoZadania);
			odswiezEkran();
		}
	}

	/**
	 * Nasluchuje na akcjach dodawania zadania.
	 */
	private void sluchajNaDodaniuZadania() {

		// jesli kliknieto przycisk dodawania zadania
		if (dodajZadanie.wasClicked()) {
		// pokazanie okna
			oknoZadania.toggleShow();
		}

		//pobranie wprowadzonego zadania
		Zadanie noweZadanie = oknoZadania.pobierzWprowadzoneZadanie();
		if (noweZadanie != null) {
			//dodaje na liste
			zadania.add(noweZadanie);
			//ustawiam indeks i aktualne zadanie
			indeksAktualnegoZadania = zadania.size() - 1;
			aktualneZadanie = noweZadanie;
			//odswiezam ekran
			odswiezEkran();
		}

	}
}
