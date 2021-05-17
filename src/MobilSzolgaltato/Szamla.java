package MobilSzolgaltato;

/**
 * Ez az osztály hívatott leírni a létező számlákat és a számlák attribútumait.
 */
public class Szamla {
    /**
     * A számla összege double-ként, az előfizetéshez tartozó felhasználó Felhasznalok típusként jelenik meg, az osztály mezői mind private-ek. A mezők értékei getter-ekkel elkérehtők.
     */
    private double szamlaOsszeg;
    private Felhasznalok felhasznalo;

    /**
     * Osztály konstruktor, ami létrehoz egy Szamla példányt és azt beteszi a nyilvántartásban található listába, paraméterként meg kell adni egy Felhasznalok típusú objektumra mutató referenciát.
     * @param f Felhasznalok típusú objektumra mutató referencia.
     */
    public Szamla(Felhasznalok f) {
        this.szamlaOsszeg = f.getCsomag().szamol(new SMSSzolgaltatas()) + f.getCsomag().szamol(new PercSzolgaltatas())
                + f.getCsomag().szamol(new MobilNetSzolagltatas());
        this.felhasznalo = f;
        SzamlakNyilvantartas.szamlakLista.add(this);
    }

    /**
     * Visszaadja az adott Szamla objektumhoz tartozó Felhasznalok típusú objektumra mutató referenciát.
     * @return Felhasznalok típusú objektumra mutató referncia.
     */
    public Felhasznalok getFelhasznalo() {
        return felhasznalo;
    }

    /**
     * Visszatér az adott felhasználó havi fizetendő számlaösszegével.
     * @return Az addott Szamla objektumhoz tartozó számlaösszeg értékét, ami double típusú.
     */
    public double getSzamlaOsszeg() {
        return szamlaOsszeg;
    }

    /**
     * A Szamla példányt adja vissza formázott String-ként.
     * @return Szamla példány String-ként
     */
    @Override
    public String toString() {
        return "Szamla{" +
                "szamlaOsszeg=" + szamlaOsszeg +
                ", felhasznalo=" + felhasznalo +
                '}';
    }
}

