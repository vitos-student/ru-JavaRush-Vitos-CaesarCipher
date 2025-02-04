import java.io.*;
import java.time.LocalDateTime;
import java.util.Locale;

public class Log_Lib {
    private final static String FILE_OUT_LOG = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\LOG\\log.txt";

    private static void Log_Lib(String module, String str) {
        try (BufferedOutputStream bosDeCrypt = new BufferedOutputStream(new FileOutputStream(FILE_OUT_LOG, true));

        ) {

            String step = "*****************************************************\n"+ LocalDateTime.now()+"\n";
            bosDeCrypt.write(step.getBytes());
            bosDeCrypt.flush();
            bosDeCrypt.write(new String(module + "  " + str +"\n").getBytes());
            bosDeCrypt.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLogCaesarApp(String module, String str) {
        Log_Lib(module, str);
    }
}
