package MobilSzolgaltato;

/**
 * Szolgáltatások interface, ezt az interface-t megvalósítő osztályok definiálják, hogy az egyes csomagokban az egyes szolgáltatási típusokat milyen módszerrel kell kiszámolni.
 */
public interface Szolgaltatasok {
    double szamolAlapCsomag(AlapCsomag alap);
    double szamolMobilNetCsomag(MobilNetCsomag mobilnet);
    double szamolSMSMaxCsomag(SMSMaxCsomag sms);
}
