package MobilSzolgaltato;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ez a Main osztály egy olyan alkalmazás futtatására ad lehetőséget, ami egy telefonszolgáltató által használt
 * nyilvántartást valósít meg 3 különböző szolgáltatói csomaggal. Ezen a nyilvántartáson lehet különböző műveleteket
 * végezni, új előfizetőket hozzáadni, meglévőt törölni, adatokat módosítani, fogyasztást beállítani az egyes felhasználókhoz,
 * fogyasztás alapján számlázni. A szolgáltatói csomagok típuskészlete bővíthető. A felhasználók és a számlák többféle
 * szempont alapján rendezetők és listázhatók.
 *
 * @author Farkas Eszter
 * @since   2021-05-03
 */
public class Main {
    /**
     * A felhasználók adatainak beolvasásakor azt is megkapja a program, hogy az adott felhasználónak milyen szolgáltatói csomagja van regisztrálva a telefonszámához. Ennek beolvasása Stringként történik. Ez a metódus a String típusú paraméter alapján meghatároz a felhasználóhoz egy Csomagok típusú objektumot.
     * @param csomagNev String típusú paraméter, ami a felhasználó által választott szolgáltatói csomag neve.
     * @return A visszatérési érték Csomagok típusú.
     */
    public static Csomagok melyikCsomag(String csomagNev) {
        CreateCsomagCommand createCommand = new CreateCsomagCommand();
        Csomagok csomag = createCommand.createCsomag (csomagNev);
        return csomag;
    }

    /**
     * Beolvassa az előfizetők adatait az adott nevű fájlból és eltárolja őket a felhasználók nyilvántartásban.
     * @throws IOException Ha nem találja a fájlt, IOException keletkezik. Ez egy kötelezően kezelendő kivétel.
     */
    public static void felhasznalokBeolvasasa() throws IOException {
        Stream<String> input1 = Files.lines(Paths.get("Felhasznalok_adatai.txt"));
        input1
                .map(line -> {
                    String[] columns = line.split(";");
                    try {
                        return new Felhasznalok(columns[0],columns[1],columns[2],melyikCsomag(columns[3]));
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    /**
     * Beolvassa az előfizetők havi fogyaztását az adott nevű fájlból.
     * @throws IOException Ha nem találja a fájlt, IOException keletkezik. Ez egy kötelezően kezelendő kivétel.
     */
    public static void forgalomBeolvasasa() throws IOException {
        Stream<String> input2 = Files.lines(Paths.get("Havi_forgalom.txt"));
        input2
                .skip(1)
                .map(line -> {
                    String[] columns = line.split(";");
                    for (Felhasznalok f : FelhasznalokNyilvantartas.felhasznalokLista) {
                        if (f.getTelefonszam().equals(columns[0])) {
                            fogyasztasMegadas(f, columns[1], columns[2], columns[3]);
                        }
                    }
                    return columns;
                })
                .collect(Collectors.toList());;
    }

    /**
     * Miután beolvasásra került a felhasználók havi fogyasztása, utána érdemes ezt a metódust meghívni. Ez a metódus kiszámolja minden elődizető havi fizetendő számlaösszegét a fogyasztása alapján és ezt felveszi a számlák nyilvántartásba.
     */
    public static void szamlakGeneral() {
        for (Felhasznalok f : FelhasznalokNyilvantartas.felhasznalokLista) {
            SzamlakNyilvantartas.szamlakLista.add(new Szamla(f));
        }
    }

    /**
     * Kiírja a legenerált számlákat .txt típusú fájlba. Azokat a számlákat írja ki, amelyek megtalálhatók a SzamlaNyilvantartas osztály statikus listájában.
     * @throws FileNotFoundException Ha nem találja a fájlt, FileNotFoundException keletkezik. Ez egy kötelezően kezelendő kivétel.
     */
    public static void szamlakKiiratas() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("Havi_számlák.txt");
        pw.println(String.format("%-20s \t %-10s \t %-20s", "Név", "Telefonszám","Havi számlaösszeg [HUF]"));
        for (Szamla sz : SzamlakNyilvantartas.szamlakLista) {
            pw.println(String.format("%-20s \t %-10s \t %-20s", sz.getFelhasznalo().getNev(),
                    sz.getFelhasznalo().getTelefonszam(), sz.getSzamlaOsszeg()));
        }
        pw.close();
    }

    /**
     * A fogyasztás adatainak beolvasásában segítő metódus. Feladata, hogy ellenőrizze, hogy megfelelő formátumban legyen megadva a fogyasztás és ezek értékét hozzá is adja a felhasználókhoz. Ha valamelyik mennyiség formátuma nem megfelelő, akkor default értékként 0 vagy 0.0 kerül a helyére.
     * @param f Az adott felhasználó, akinek a havi fogyasztását szeretnénk megadni.
     * @param sms A felhasználó által küldött sms-ek száma a hónapban.
     * @param perc A felhasználó által letelefonált percek mennyisége a hónapban.
     * @param internet A felhasználó által elhasznált mobilinetrnet mennyisége.
     */
    public static void fogyasztasMegadas(Felhasznalok f, String sms, String perc, String internet) {
        int parseSms, parsePerc;
        double parseInternet;

        try {
            parseSms = Integer.parseInt(sms);
        } catch (NumberFormatException n) {
            parseSms = 0;
        }
        try {
            parsePerc = Integer.parseInt(perc);
        } catch (NumberFormatException n) {
            parsePerc = 0;
        }
        try {
            parseInternet = Double.parseDouble(internet);
        } catch (NumberFormatException n) {
            parseInternet = 0.0;
        }
        f.getCsomag().setSMSMennyiseg(parseSms);
        f.getCsomag().setPercMennyiseg(parsePerc);
        f.getCsomag().setInternetMennyiseg(parseInternet);
    }

    /**
     * Main metódus, innen indítható az alkalmazás és innen érhető el az összes elvégezhető művelet.
     * @throws Exception IOException dobása, ha nem találja az adott fájlt.
     */
    public static void main(String[] args) throws IOException {
        felhasznalokBeolvasasa();
        forgalomBeolvasasa();
        szamlakGeneral();
        szamlakKiiratas();

    }

}


