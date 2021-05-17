package MobilSzolgaltato;

/**
 * Szolgáltatások interface-t valósítja meg, definiálja, hogy az egyes csomagokban a mobilinternet-fogyasztás által alkotott számla részösszeg hogyan számítandó.
 */
public class MobilNetSzolagltatas implements Szolgaltatasok{

    /**
     * Az Alapcsomagban az elhasznált mobilinternet mennyiség által alkotott számla részösszeg számítása történik meg.
     * @param alap Adott Alapcsomag típusú objektum.
     * @return Visszaadja a számla mobilinternet által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolAlapCsomag(AlapCsomag alap) {
        return alap.getInternetMennyiseg() * 500;
    }

    /**
     * A MobilNetcsomagban az elhasznált mobilinternet mennyiség által alkotott számla részösszeg számítása történik meg.
     * @param mobilnet Adott MobilNetcsomag típusú objektum.
     * @return Visszaadja a számla mobilinternet által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolMobilNetCsomag(MobilNetCsomag mobilnet) {
        if (mobilnet.getInternetMennyiseg() < 2) {
            return 0;
        } else {
            return ((mobilnet.getInternetMennyiseg()-2) * 100);
        }
    }

    /**
     * Az SMSMaxcsomagban az elhasznált mobilinternet mennyiség által alkotott számla részösszeg számítása történik meg.
     * @param sms Adott SMSMaxcsomag típusú objektum.
     * @return Visszaadja a számla mobilinternet által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolSMSMaxCsomag(SMSMaxCsomag sms) {
        return sms.getInternetMennyiseg() * 600;
    }
}
