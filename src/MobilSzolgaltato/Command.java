package MobilSzolgaltato;

/**
 * Command interface implementálása, ezt valósítja majd meg a CreateCsomagCommand, melynek feladata, hogy a beolvasott String csomagtípus alapján visszaadjon egy Csomagok típusú objektumot.
 */
public interface Command {
    /**
     * Megvalósítandó metódus, feladata az új Csomagok típusú objektum létrehozása.
     * @return Csomagok típusú objektummal tér vissza.
     */
    Csomagok create();
}
