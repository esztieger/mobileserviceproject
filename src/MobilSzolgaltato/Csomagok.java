package MobilSzolgaltato;

/**
 *  Csomagok interface
 */
public interface Csomagok {
    String getName();
    double szamol(Szolgaltatasok sz);
    void setSMSMennyiseg(int smsMennyiseg);
    void setPercMennyiseg(int percMennyiseg);
    void setInternetMennyiseg(double internetMennyiseg);
    int getSmsMennyiseg();
    int getPercMennyiseg();
    double getInternetMennyiseg();

}
