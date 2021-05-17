package MobilSzolgaltato;

import java.util.*;
/**
 * Ennek az osztálynak a feladata, hogy tárolja a létező előfizetéseket. Lehet újat hozzáadni, egy létezőt kitörölni,
 * illetve többféleképpen listázni a felhasználókat.
 */
public class FelhasznalokNyilvantartas {
    /**
     * A nyilvántartás egy statikus listából áll, amelynek a hossza nincs előre definiálva, bármikor bővíthető plusz elemekkel.
     */
    protected static List<Felhasznalok> felhasznalokLista= new ArrayList<>();

    /**
     * Új előfizető és az előfizetés hozzáadása a nyilvántartáshoz, a telefonszám automatikusan generálódik, a többi
     * paraméter megadása szükséges csak.
     * @param nev A felhasználó neve.
     * @param lakcim A felhasználó lakcíme.
     * @param csomag A telefonszámához, azaz előfizetéséhez tartozó szolgáltatói csomag.
     */
    public void ujElofizetesHozzaadas (String nev, String lakcim, Csomagok csomag) {
        new Felhasznalok(nev, lakcim, csomag);
    }

    /**
     * Az előfizetés törlésére ad lehetőséget a Felhasznalo objektum referencia alapján.
     * @param f Felhasznalo típus szerint előfizetés törlése.
     */
    public void elofizetesTorlesFelhasznaloSzerint (Felhasznalok f) {
        felhasznalokLista.remove(f);
    }

    /**
     * Az előfizetés törlésére ad lehetőséget a felhasználó telefonszáma alapján.
     * @param t Telefonszám alapján előfizetés törlése.
     */
    public void elofizetesTorlesTelefonszamSzerint (String t) {
        for (Felhasznalok f : felhasznalokLista) {
            if (f.getTelefonszam().equals(t)) elofizetesTorlesFelhasznaloSzerint(f);
        }
    }

    /**
     * A nyilávntartás listájának rendezése lehetséges különféle szempontok szerint, a rendezés szabályát implementálja
     * a metódus. Visszatérési értéke nincs.
     * @param cmd E paraméter alapján történik a lista rendezése.
     * @throws Exception Kivételkezelés null paraméter esetére. A kivételkezelés során azokat az eseteket is kezeli,
     *                   amikor nem ismert paraméterértékkel hívják meg a metódust.
     */
    public static void sortArray(String cmd) throws Exception{
        try {
            if (cmd.equals("név")) {
                Collections.sort(felhasznalokLista, Comparator.comparing(Felhasznalok::getNev));
                return;
            } else if (cmd.equals("lakcím")) {
                Collections.sort(felhasznalokLista, Comparator.comparing(Felhasznalok::getLakcim));
                return;
            } else if (cmd.equals("telefonszám")) {
                Collections.sort(felhasznalokLista, Comparator.comparing(Felhasznalok::getTelefonszam));
                return;
            } else if (cmd.equals("csomag")) {
                Collections.sort(felhasznalokLista, Comparator.comparing(f -> f.getCsomag().getName()));
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

        ListIterator<Felhasznalok> iterator = felhasznalokLista.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}