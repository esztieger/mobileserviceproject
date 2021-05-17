package MobilSzolgaltato;

import java.util.*;

/**
 * Ennek az osztálynak a feladata, hogy tárolja a létező számlákat. Lehet újat hozzáadni, egy létezőt kitörölni, illetve többféleképpen listázni a számlákat.
 */
public class SzamlakNyilvantartas {
    /**
     * A nyilvántartás egy statikus listából áll, amelynek a hossza nincs előre definiálva, bármikor bővíthető plusz elemekkel.
     */
    protected static List<Szamla> szamlakLista= new ArrayList<>();

    /**
     * Új számla hozzáadása a nyilvántartáshoz, a felhasználó megadása szükséges.
     * @param f Az a felhasználó, akinek készül a számla.
     */
    public void ujSzamlaHozzaadas (Felhasznalok f) {
        new Szamla(f);
    }

    /**
     * A számla törlésére ad lehetőséget a Szamla objektum referencia alapján.
     * @param sz Az a Szamla típusú objektumra mutató referencia, amelyik objektumot törölni kell.
     */
    public void szamlaTorles (Szamla sz) {
        szamlakLista.remove(sz);
    }

    /**
     * A nyilávntartás listájának rendezése lehetséges különféle szempontok szerint, a rendezés szabályát implementálja a metódus. Visszatérési értéke nincs.
     * @param cmd E paraméter alapján történik a lista rendezése.
     * @throws Exception Kivételkezelés null paraméter esetére. A kivételkezelés során azokat az eseteket is kezeli, amikor nem ismert paraméterértékkel hívják meg a metódust.
     */
    public static void sortArray(String cmd) throws Exception{
        try {
            if (cmd.equals("számlaösszeg")) {
                Collections.sort(szamlakLista, Comparator.comparing(Szamla::getSzamlaOsszeg));
                return;
            } else if (cmd.equals("név")) {
                Collections.sort(szamlakLista, Comparator.comparing(sz -> sz.getFelhasznalo().getNev()));
                return;
            } else if (cmd.equals("telefonszám")) {
                Collections.sort(szamlakLista, Comparator.comparing(sz -> sz.getFelhasznalo().getTelefonszam()));
                return;
            } else if (cmd.equals("csomag")) {
                Collections.sort(szamlakLista, Comparator.comparing(sz -> sz.getFelhasznalo().getCsomag().getName()));
                return;
            }
        } catch (NullPointerException ex) {
            System.err.println("Nem adtál meg rendezési feltételt!");
        }
        throw new Exception("Nincs ilyen rendezési mód, kérlek adj meg egy másikat!");
    }

    /**
     * Valamilyen, a metódushívó által adott szempont szerint kiírja rendezve a nyilvántartás listáját.
     * Visszatérési értéke nincs.
     * @param cmd  E paraméter alapján történik a lista rendezése.
     * @throws Exception Kivételkezelés null paraméter esetére. A kivételkezelés során azokat az eseteket is kezeli, amikor nem ismert paraméterértékkel hívják meg a metódust.
     */
    public static void rendezveListaz(String cmd) throws Exception {
        sortArray(cmd);

        ListIterator<Szamla> iterator = szamlakLista.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}