import java.util.*;

/**
 * Przechowuje dane zadania.
 *
 * @author Pawel Kaczanowski 
 * @version 1.0
 */
public class Zadanie {

	/**
	 * Pole przechowuje opis.
	 */
	private String opis;

	/**
	 * Pole przechowuje termin wykonania.
	 */
	private Date terminWykonania;

	/**
	 * Konstruktor klasy zadanie Zadanie
	 */
	public Zadanie() {
	}

	/**
	 * Konstruktor parametrowy, przyjmuje opis i termin wykonania.
	 * @param opis
	 * @param terminWykonania
	 */
	public Zadanie(String opis, Date terminWykonania) {
		this.opis = opis;
		this.terminWykonania = terminWykonania;
	}

	/**
	 * Zwraca opis zadania.
	 * @return opis
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * Ustawia opis.
	 * @param opis
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}

	/**
	 * Zwraca termin wykonania zadania.
	 * @return
	 */
	public Date getTerminWykonania() {
		return terminWykonania;
	}

	/**
	 * Ustawia termin wykonania zadania.
	 * @param terminWykonania
	 */
	public void setTerminWykonania(Date terminWykonania) {
		this.terminWykonania = terminWykonania;
	}
}
