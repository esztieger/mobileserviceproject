package MobilSzolgaltato;

/**
 * A Csomagok interface-t valósítja meg ez az osztály. Tárolja az sms-ek, percek és az internet fogyasztás mennyiségét.
 */
public class MobilNetCsomag implements Csomagok{
    private int smsMennyiseg;
    private int percMennyiseg;
    private double internetMennyiseg;

    /**
     * Az osztály konstruktora. Paraméterként meg kell adni az sms, perc és internet mennyiséget.
     * @param smsMennyiseg Az előfizető által elküldött sms-ek száma.
     * @param percMennyiseg Az előfizető éltal letelefonált percek mennyisége.
     * @param internetMennyiseg Az előfizető által elhasznált mobilinternet mennyisége.
     */
    public MobilNetCsomag(int smsMennyiseg, int percMennyiseg, double internetMennyiseg) {
        this.smsMennyiseg = smsMennyiseg;
        this.percMennyiseg = percMennyiseg;
        this.internetMennyiseg = internetMennyiseg;
    }

    /**
     * Az osztály konstruktora, nem vár paramétert, meghívásakor olyan MobilNet csomag példány jön létre, ahol nincs definiálva az sms, perc és internet mennyisége, ezek megadása később a setter-ek segítségével lehetséges.
     */
    public MobilNetCsomag() {}

    /**
     * @return Visszaadja a MobilNet csomagban megadott mezők értékét String-ként.
     */
    @Override
    public String toString() {
        return "MobilNetCsomag{" +
                "smsMennyiseg=" + smsMennyiseg +
                ", percMennyiseg=" + percMennyiseg +
                ", internetMennyiseg=" + internetMennyiseg +
                '}';
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setSMSMennyiseg(int smsMennyiseg) {

    }

    @Override
    public void setPercMennyiseg(int percMennyiseg) {

    }

    @Override
    public void setInternetMennyiseg(double internetMennyiseg) {

    }

    @Override
    public int getSmsMennyiseg() {
        return 0;
    }

    @Override
    public int getPercMennyiseg() {
        return 0;
    }

    @Override
    public double getInternetMennyiseg() {
        return 0;
    }

    @Override
    public double szamol(MobilSzolgaltato.Szolgaltatasok sz) {
        return 0;
    }
}
