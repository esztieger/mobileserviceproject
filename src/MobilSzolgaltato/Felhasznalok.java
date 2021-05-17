package MobilSzolgaltato;

import java.util.concurrent.ThreadLocalRandom;
/**
 * Ez az osztály hívatott leírni a létező előfizetéseket és az előfizetések attribútumait.
 */
public class Felhasznalok {
    /**
     * Az előfizető neve, lakcíme és telefonszáma String-ként, az előfizetéshez tartozó szolgáltatói csomag Csomagok
     * típusként jelenik meg, az osztály mezői mind private-ek, így védve őket attól, hogy bárki módosíthassa az értéküket
     * kívülről. Az adott példány értékei getter-ekkel elkérehtők, illetve rendelkezésre állnak setter metódusok
     * is, ha a felhasználó csomagot szeretne váltani valamilyen oknál fogva vagy az adatai megváltoznának. A telefonszám
     * változtatására is lehetőség van, de ennek feltétele, hogy a kívánt telefonszám még ne legyen jelen a nyilvántartásban.
     */
    private String nev, lakcim, telefonszam;
    private Csomagok csomag;

    /**
     * Osztály konstruktor, ami létrehoz egy Felhasznalo példányt és azt beteszi a nyilvántartásban található
     * listába, paraméterként meg kell adni az előfizetéshez tartozó nevet, lakcímet, telefonszámot és szolgáltatói
     * csomagot.
     * @param nev A felhasználó neve.
     * @param lakcim A felhasználó lakcíme.
     * @param telefonszam A felhasználó telefonszáma, ami az egyedi azonosítója is egyben.
     * @param csomag A telefonszámához, azaz előfizetéséhez tartozó szolgáltatói csomag.
     * @throws Exception Akkor dobódik kivétel, ha az adott telefonszám már létezik a nyilvántartásban, ez szükséges,
     *                   mert minden felhasználót a telefonszám azonosít, egy telefonszámhoz nem tartozhat több
     *                   különböző felhasználó, illetve így elkerülhető a duplikáció. Akkor is kivétel dobódik, ha a
     *                   telefonszám formátuma hibás: nem 7jegyű, nem csak egész számmal azonos karakterekeből
     *                   állnak a számjegyek, vagy esetleg null értékű.
     */
    public Felhasznalok(String nev, String lakcim, String telefonszam, Csomagok csomag) {
        if (!hibásVagyLetezoTelefonszam(telefonszam)) {
            this.nev = nev;
            this.lakcim = lakcim;
            this.telefonszam = telefonszam;
            this.csomag = csomag;
            FelhasznalokNyilvantartas.felhasznalokLista.add(this);

        } else {
            System.out.println("Már létezik ilyen telefonszám vagy hibás formátumú, próbáld újra másikkal!");
        }
    }

    /**
     * Konstruktor, ami létrehoz egy Felhasznalo példányt és azt beteszi a nyilvántartásba.
     * Ebben a konsturktorban a telefonszámot nem kell előre megadni paraméterként, az automatikusan generálódik
     * a létrehozandó előfizetéshez.
     * @param nev A felhasználó neve.
     * @param lakcim A felhasználó lakcíme.
     * @param csomag A telefonszámához, azaz előfizetéséhez tartozó szolgáltatói csomag.
     */
    public Felhasznalok(String nev, String lakcim, Csomagok csomag) {
        this.nev = nev;
        this.lakcim = lakcim;
        this.telefonszam = telefonszamotSorsol();
        this.csomag = csomag;
        FelhasznalokNyilvantartas.felhasznalokLista.add(this);
    }

    /**
     * Visszaadja a felhasználó nevét, amin meghívták.
     * @return A felhasználó neve, amin meghívták a metódust.
     */
    public String getNev() {
        return nev;
    }

    /**
     * Visszaadja a felhasználó lakcímét, amin meghívták.
     * @return A felhasználó lakcíme, amin meghívták a metódust.
     */
    public String getLakcim() {
        return lakcim;
    }
    /**
     * Visszaadja a felhasználó telefonszámát, amin meghívták.
     * @return A felhasználó telefonszáma, amin meghívták a metódust.
     */
    public String getTelefonszam() {
        return telefonszam;
    }
    /**
     * Visszaadja a felhasználó szolgáltatói csomagját, amin meghívták.
     * @return A felhasználó szolgáltatói csomagja, amin meghívták a metódust.
     */
    public Csomagok getCsomag() {
        return csomag;
    }

    /**
     * A felhasználóhoz tartozó szolgáltatói csomag megváltoztatására ad lehetőséget.
     * @param csomag Az a csomag, amire váltani szeretne a felhasználó.
     */
    public void setCsomag(Csomagok csomag) { if (!this.getCsomag().equals(csomag)) this.csomag = csomag; }

    /**
     * A felhasználóhoz tartozó név megváltoztatására ad lehetőséget.
     * @param nev Az a név, amire változtatni szeretné a felhasználó az előfizetéshez tartozó nevet.
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * A felhasználóhoz tartozó lakcím megváltoztatására ad lehetőséget.
     * @param lakcim Az a lakcím, amire változtatni szeretné a felhasználó az előfizetéshez tartozó lakcímet.
     */
    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    /**
     * Ha a felhasználó telefonszámot szeretne változtatni, akkor először ellenőrizni kell, hogy a kívánt telenfonszám
     * létezik-e már a nyilvántartásban. Telefonszám változatatás előtt azt is vizsgálja, hogy a kívánt
     * telefonszám megfelelő formátumú-e. Ha nem szerepel a nyilvántartásban és megfelelő a formátum, akkor
     * a telefonszám megváltoztatható, viszont ha már létezik vagy nem jó a formátum, akkor ez nem lehetséges.
     * Visszatérési érték nincs, de egy println-nal visszajelzést kap a felhasználó, hogy sikeres volt-e
     * a telefonszám változtatására tett kísérlet.
     * @param telefonszam Az új telefonszám, amire meg szeretné változtatni a felhasználó a jelenlegi számát.
     */
    public void setTelefonszam(String telefonszam) {
        if (!hibásVagyLetezoTelefonszam(telefonszam)) {
            this.telefonszam = telefonszam;
            System.out.println("A telefonszám sikeresen megváltozott. Az előfizetéshez tartozó új telefonszám: "
                    + telefonszam);
        } else {
            System.out.println("A telefonszám-változtatás nem sikerült. Már létezik vagy hibás formátumú a megadott telefonszám.");
        }
    }

    /**
     * A Felhasznalo példányt adja vissza formázott String-ként
     * @return Felhasznalo példány String-ként
     */
    @Override
    public String toString() {
        return "Felhasznalok{" +
                "nev='" + nev + '\'' +
                ", lakcim='" + lakcim + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                ", csomag=" + csomag +
                '}';
    }

    /**
     * Ha ezt a metódust meghívják, generál egy 7 számjegyű telefonszámot String formában, amelyet csak azután
     * ad vissza, miután leellenőrizte, hogy biztosan nem létezik ilyen a nyilvántartásban.
     * @return Generált 7jegyű telefonszám String-ként.
     */
    public String telefonszamotSorsol() {
        String telefonszam = new String();
        while (true) {
            for (int j=0; j < 7; j++) {
                Integer randomSzam = ThreadLocalRandom.current().nextInt(0, 10);
                telefonszam = telefonszam + randomSzam.toString();
            }
            if (!hibásVagyLetezoTelefonszam(telefonszam)) break;
        }
        return telefonszam;
    }

    /**
     * A paraméterként kapott telefonszámot ellenőrzi, hogy létezik-e a nyilvántarásban és hogy megfelelő formátumú-e.
     * @param telefonszam Telefonszám, amit ellenőrizni kell.
     * @return ha talál olyan előfizetést, ahol már létezik ez a telefonszám vagy nem megfelelő a telefonszám formátuma,
     *          akkor true-ként tér vissza, ha nem talál hibát, akkor false-ként.
     */
    public boolean hibásVagyLetezoTelefonszam(String telefonszam) {
        if (telefonszámJóFormatumu(telefonszam)) {
            for (Felhasznalok f : FelhasznalokNyilvantartas.felhasznalokLista) {
                if (f.getTelefonszam().equals(telefonszam)) {
                    return true;
                }
            } return false;
        }
        return true;
    }

    /**
     * Telefonszám formátumának ellenőrzése. Az ellenőrzés alatt a következőket vizsgálja: a paraméterként kapott String
     * null-e, 7 jegyű-e és hogy a számjegyek karakterei egész számok-e.
     * @param telefonszam Az ellenőrizendő telefonszám.
     * @return Ha minden rendben van a telefonszámmal, true-ként tér vissza, ha valamilyen formai hibát talál, akkor
     *         false-ként.
     */
    public boolean telefonszámJóFormatumu(String telefonszam) {
        try {
            if (telefonszam.length() != 7) {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
        int parseTelefonszam;
        try {
            parseTelefonszam = Integer.parseInt(telefonszam);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


