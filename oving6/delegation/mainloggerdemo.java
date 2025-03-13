package oving6.delegation;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class MainLoggerDemo {

    public static void main(String[] args) {

        OutputStream stream = new ByteArrayOutputStream();
        StreamLogger streamLogger = new StreamLogger(stream);
        

        streamLogger.log(ILogger.INFO, "Info-melding: Alt fungerer som det skal.", null);
        streamLogger.log(ILogger.WARNING, "Advarsel: Sjekk nettverkstilkobling.", new IllegalStateException("Nettverksfeil"));
        streamLogger.log(ILogger.ERROR, "Kritisk feil: Tjenesten har stoppet.", new RuntimeException("Systemkrasj"));
        

        streamLogger.setFormatString("Severity: %s | Message: %s | Exception: %s");
        streamLogger.log(ILogger.ERROR, "Ny kritisk feil oppdaget.", new NullPointerException("Nullverdi funnet"));


        System.out.println("Logg-utdata:\n" + stream.toString());
    }
}