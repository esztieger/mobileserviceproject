package MobilSzolgaltato;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Az osztály feladata összegyűjteni azokat a parancsokat, amik létrehozzák az új szolgáltatói csomagokat.
 */
public class CreateCsomagCommand {
    /**
     * A parancsok neveit és a hozzájuk tartozó új objektumot létrehozó metódusokat map-ben tárolja párban, így is elkerülve a switch-case alkalmazását. Private, hogy ne férhessen hozzá senki kívülről, final, amely azt jelenti, hogy csak egyszer lehet inicializálni és utána nem módosítható, statikus mert nem példányosítható többször, nem példányhoz kapcsolódik, hanem az osztályhoz. A map nem inicializálódik itt, hanem majd a konstruktorban.
     */
    private static final Map<String, Command> CSOMAGOK;


    static {
        final Map<String, Command> csomagok = new HashMap<>();

        csomagok.put("ALAP", AlapCsomag::new);

        csomagok.put("SMSMAX", SMSMaxCsomag::new);

        csomagok.put("MOBILNET", MobilNetCsomag::new);

        CSOMAGOK = Collections.unmodifiableMap(csomagok);
    }

    /**
     * Példányosít egy Command típust.
     * @param csomagTipus A csomagtípus, amit fájlból beolvas a program.
     * @return A megfelelő Csomagok típus létrehozása a new-val, és ezzel az új, specializált Csomagok üres objektummal tér vissza.
     */
    public Csomagok createCsomag(String csomagTipus) {
        Command command = CSOMAGOK.get(csomagTipus.toUpperCase());
        return command.create();
    }
}
