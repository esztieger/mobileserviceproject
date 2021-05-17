package MobilSzolgaltato;

/**
 * A Csomagok interface-t valósítja meg ez az osztály. Tárolja az sms-ek, percek és az internet fogyasztás mennyiségét.
 */
public class SMSMaxCsomag implements Csomagok{
    private int smsMennyiseg;
    private int percMennyiseg;
    private double internetMennyiseg;

    /**
     * Az osztály konstruktora. Paraméterként meg kell adni az sms, perc és internet mennyiséget.
     * @param smsMennyiseg Az előfizető által elküldött sms-ek száma.
     * @param percMennyiseg Az előfizető éltal letelefonált percek mennyisége.
     * @param internetMennyiseg Az előfizető által elhasznált mobilinternet mennyisége.
     */
    public SMSMaxCsomag(int smsMennyiseg, int percMennyiseg, double internetMennyiseg) {
        this.smsMennyiseg = smsMennyiseg;
        this.percMennyiseg = percMennyiseg;
        this.internetMennyiseg = internetMennyiseg;
    }

    /**
     * Az osztály konstruktora, nem vár paramétert, meghívásakor olyan SMSMax csomag példány jön létre, ahol nincs definiálva az sms, perc és internet mennyisége, ezek megadása később a setter-ek segítségével lehetséges.
     */
    public  SMSMaxCsomag() {}

    /**
     * @return Visszatér az elküldött sms-ek mennyiségével.
     */
    @Override
    public int getSmsMennyiseg() {
        return smsMennyiseg;
    }

    /**
     * @return Visszatér a letelefonált percek mennyiségével.
     */
    @Override
    public int getPercMennyiseg() {
        return percMennyiseg;
    }

    /**
     * @return Visszatér az elhasznált mobilinternet mennyiségével.
     */
    @Override
    public double getInternetMennyiseg() {
        return internetMennyiseg;
    }

    /**
     * @return Visszatér az smsmaxcsomag nevével.
     */
    @Override
    public String getName() {
        return "SMSMax csomag";
    }

    /**
     * Egy szolgáltatás típus kiszámítása ebben az osztályban adott mennyiségű fogyasztás alapján.
     * @param sz Szolgáltatás típusa, ez lehet: sms, perc, mobilinternet.
     * @return
    Egy lebegőpontos számmal tér vissza, amit az adott szolgáltatás csomag szerinti számítási metódusa ad vissza. (alapvetően: mennyiségszer * fajlagos_ár)
     */
    @Override
    public double szamol(Szolgaltatasok sz) {

        return sz.szamolSMSMaxCsomag(this);
    }

    /**
     * Megadható/megváltoztatható az előfizető által küldött sms-ek száma.
     * @param smsMennyiseg A küldött sms-ek száma.
     */
    @Override
    public void setSMSMennyiseg(int smsMennyiseg) {
        this.smsMennyiseg = smsMennyiseg;
    }

    /**
     * Megadható/megváltoztatható az előfizető által letelefonált percek száma.
     * @param percMennyiseg A letelefonált percek száma.
     */
    @Override
    public void setPercMennyiseg(int percMennyiseg) {
        this.percMennyiseg = percMennyiseg;
    }

    /**
     * Megadható/megváltoztatható az előfizető által elhasznált mobilinternet mennyisége.
     * @param internetMennyiseg Az elhasznált mobilinternet mennyisége.
     */
    @Override
    public void setInternetMennyiseg(double internetMennyiseg) {
        this.internetMennyiseg = internetMennyiseg;
    }

    /**
     * @return Visszaadja az SMSMax csomagban megadott mezők értékét String-ként.
     */
    @Override
    public String toString() {
        return "SMSMaxCsomag{" +
                "smsMennyiseg=" + smsMennyiseg +
                ", percMennyiseg=" + percMennyiseg +
                ", internetMennyiseg=" + internetMennyiseg +
                '}';
    }
}
