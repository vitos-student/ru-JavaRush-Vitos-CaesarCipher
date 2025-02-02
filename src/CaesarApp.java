import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CaesarApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите секретный ключ, от 1 до 300:");
        int keyCaesar = scanner.nextInt();
        while (!controlKeyCaesar(keyCaesar)) {
            System.out.println("Ошибка ввода.");
            System.out.print("Введите секретный ключ, от 1 до 300:");
            keyCaesar = scanner.nextInt();
        }
        ;

        List<Character> listAlphabet = Alphabet.getAlpabet();
        System.out.println(listAlphabet);

        keyCaesar = CeasarLib.getKeyCaesar(keyCaesar, listAlphabet.size());


        for (int i = 0; i < listAlphabet.size(); i++) {
            System.out.print(listAlphabet.get(i) + "   ");
        }

        String filePath = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textIn.txt";
        String fileOutPath = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textOutCrypt.txt";
        String fileOutPathDeCrypt = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textOutDeCrypt.txt";
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOutPath));
             BufferedOutputStream bosDeCrypt = new BufferedOutputStream(new FileOutputStream(fileOutPathDeCrypt));
        ) {
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;

            while ((bytesRead = bis.read(buffer, 0, buffer.length)) != -1) {
                String stt = new String(buffer, 0, bytesRead).toLowerCase(Locale.ROOT);
                String cryptString2 = CeasarLib.Сrypt(listAlphabet, stt, keyCaesar);
                String cryptString3 = CeasarLib.DeСrypt(listAlphabet, cryptString2, keyCaesar);
                bos.write(cryptString2.getBytes());
                bos.flush();
                bosDeCrypt.write(cryptString3.getBytes());
                bosDeCrypt.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static boolean controlKeyCaesar(int keyCaesar) {
        if (keyCaesar > 0 && keyCaesar <= 300) {
            return true;
        } else {
            return false;
        }
    }
}
