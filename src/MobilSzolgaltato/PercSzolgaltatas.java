package MobilSzolgaltato;

/**
 * Szolgáltatások interface-t valósítja meg, definiálja, hogy az egyes csomagokban a letelefonált percek által alkotott számla részösszeg hogyan számítandó.
 */
public class PercSzolgaltatas implements Szolgaltatasok{
    /**
     * Az Alapcsomagban a letelefonált percek által alkotott számla részösszeg számítása történik meg.
     * @param alap Adott Alapcsomag típusú objektum.
     * @return Visszaadja a számla perc által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolAlapCsomag(AlapCsomag alap) {
        return alap.getPercMennyiseg() * 30;
    }

    /**
     * A MobilNetcsomagban a letelefonált percek által alkotott számla részösszeg számítása történik meg.
     * @param mobilnet Adott MobilNetcsomag típusú objektum.
     * @return Visszaadja a számla perc által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolMobilNetCsomag(MobilNetCsomag mobilnet) {
        return mobilnet.getPercMennyiseg() * 32;
    }

    /**
     * Az SMSMaxcsomagban a letelefonált percek által alkotott számla részösszeg számítása történik meg.
     * @param sms Adott SMSMaxcsomag típusú objektum.
     * @return Visszaadja a számla perc által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolSMSMaxCsomag(SMSMaxCsomag sms) {
        return sms.getPercMennyiseg() * 23;
    }
}
