package MobilSzolgaltato;

/**
 * Szolgáltatások interface-t valósítja meg, definiálja, hogy az egyes csomagokban az sms-ek által alkotott számla részösszeg hogyan számítandó.
 */
public class SMSSzolgaltatas implements Szolgaltatasok{

    /**
     * Az Alapcsomagban az sms-ek által alkotott számla részösszeg számítása történik meg.
     * @param alap Adott Alapcsomag típusú objektum.
     * @return Visszaadja a számla sms által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolAlapCsomag(AlapCsomag alap) {
        return alap.getSmsMennyiseg() * 20;
    }

    /**
     * A MobilNetc somagban az sms-ek által alkotott számla részösszeg számítása történik meg.
     * @param mobilnet Adott MobilNetcsomag típusú objektum.
     * @return Visszaadja a számla sms által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolMobilNetCsomag(MobilNetCsomag mobilnet) {
        return mobilnet.getSmsMennyiseg() * 25;
    }

    /**
     * Az SMSMaxcsomagban az sms-ek által alkotott számla részösszeg számítása történik meg.
     * @param sms Adott SMSMaxcsomag típusú objektum.
     * @return Visszaadja a számla sms által alkotott részösszegét, ami ehhez az objektumhoz tartozik.
     */
    @Override
    public double szamolSMSMaxCsomag(SMSMaxCsomag sms) {
        if (sms.getSmsMennyiseg() < 50) {
            return 0;
        } else {
            return ((sms.getSmsMennyiseg() -50) * 5);
        }
    }
}
