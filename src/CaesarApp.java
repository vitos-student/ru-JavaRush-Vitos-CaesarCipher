import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CaesarApp {
    /**
     * Предпологается, что файлы созданы. Не делал контроль на отсутвие и пустоту
     */

    /**
     * FILEINPATH - исходный файлик с правильным текстом
     * FILE_OUT_PATH - зашифрованный файлик
     * FILE_OUT_PATH_DE_CRYPT - файлик с декодированной информаций
     * FILE_OUT_BF - взлом. все пишу в один файлик, с разделитем Шаг №
     *
     * Внимание 1. пути к файлам надо указать свои
     * Внимание 2. вывожу на экран информацию о валидации. Например наличие символов вне алфавита.
     * для себя не хочется засорять экран, правильнее скидывать в лог
     * Декодирование не падает, при наличии символов вне алгоритма.
     * Сделал остановку на этом символе. В файлы информаця запишется от 1 символа до не существующего в алгоритме.
     *
     */
    private final static String FILEINPATH = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textIn.txt";
    private final static String FILE_OUT_PATH = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textOutCrypt.txt";
    private final static String FILE_OUT_PATH_DE_CRYPT = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\src\\textOutDeCrypt.txt";
    private final static String FILE_OUT_BF = "C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\out\\production\\ru JavaRush Vitos CaesarCipher\\BrudFors\\textBF.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа шифрования и дешифрования кодом Цезаря");
        System.out.println("Для выхода введите exit, для продолжения любую клавишу");
        while (!scanner.nextLine().equals("exit")) {
            System.out.println("Меню:");
            System.out.println("1. Зашифровать");
            System.out.println("2. Дешифровать");
            System.out.println("3. Взлом (Brute Force)");
            System.out.println("exit");
            System.out.println("Выберите пункт меню:");
            String menu = scanner.nextLine();
            if (menu.equals("exit")) {
                break;
            }
            if (!(menu.equals("1") || menu.equals("2") || menu.equals("3"))) {
                System.out.println("Вы ошиблись с выбором меню. Повторите, нажмите любую клавишу");
                continue;
            }
            switch (menu) {
                case "1": {
                    System.out.print("Введите секретный ключ, от 1 до 300:");
                    int keyCaesar = scanner.nextInt();
                    while (!controlKeyCaesar(keyCaesar)) {
                        System.out.println("Ошибка ввода.");
                        System.out.print("Введите секретный ключ, от 1 до 300:");
                        keyCaesar = scanner.nextInt();
                    }
                    ;
                    List<Character> listAlphabet = Alphabet.getAlpabet();
                    // System.out.println(listAlphabet);
                    keyCaesar = CeasarLib.getKeyCaesar(keyCaesar, listAlphabet.size());
//                    for (int i = 0; i < listAlphabet.size(); i++) {
//                        System.out.print(listAlphabet.get(i) + "   ");
//                    }
                    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILEINPATH));
                         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILE_OUT_PATH));
                    ) {
                        int bufferSize = 1024;
                        byte[] buffer = new byte[bufferSize];
                        int bytesRead;

                        while ((bytesRead = bis.read(buffer, 0, buffer.length)) != -1) {
                            String stt = new String(buffer, 0, bytesRead).toLowerCase(Locale.ROOT);
                            String cryptString2 = CeasarLib.Сrypt(listAlphabet, stt, keyCaesar);
                            bos.write(cryptString2.getBytes());
                            bos.flush();
                            bos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "2": {
                    System.out.print("Введите ключ дешефрования, от 1 до 300:");
                    int keyCaesar = scanner.nextInt();
                    while (!controlKeyCaesar(keyCaesar)) {
                        System.out.println("Ошибка ввода.");
                        System.out.print("Введите секретный ключ, от 1 до 300:");
                        keyCaesar = scanner.nextInt();
                    }
                    ;
                    List<Character> listAlphabet = Alphabet.getAlpabet();
                    // System.out.println(listAlphabet);
                    keyCaesar = CeasarLib.getKeyCaesar(keyCaesar, listAlphabet.size());
//                    for (int i = 0; i < listAlphabet.size(); i++) {
//                        System.out.print(listAlphabet.get(i) + "   ");
//                    }
                    try (BufferedInputStream bufferedInputStreamCrypt = new BufferedInputStream(new FileInputStream(FILE_OUT_PATH));
                         BufferedOutputStream bosDeCrypt = new BufferedOutputStream(new FileOutputStream(FILE_OUT_PATH_DE_CRYPT));
                    ) {
                        int bufferSize = 1024;
                        byte[] buffer = new byte[bufferSize];
                        int bytesRead;

                        while ((bytesRead = bufferedInputStreamCrypt.read(buffer, 0, buffer.length)) != -1) {
                            String stt = new String(buffer, 0, bytesRead).toLowerCase(Locale.ROOT);
                            String cryptString2 = CeasarLib.Сrypt(listAlphabet, stt, keyCaesar);
                            String cryptString3 = CeasarLib.DeСrypt(listAlphabet, stt, keyCaesar);
                            bosDeCrypt.write(cryptString3.getBytes());
                            bosDeCrypt.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                }
                case "3": {
                    List<Character> listAlphabet = Alphabet.getAlpabet();
//                    String[] fileBf = new String[listAlphabet.size()];
//                    идея со множеством файлов, попробовать сделать через цикл
//                   for (int i = 0; i < listAlphabet.size(); i++) {
//                       fileBf[i]=new String("C:\\Users\\фвьшт\\IdeaProjects\\ru JavaRush Vitos CaesarCipher\\out\\BrudFors\\textBF"+i+".txt");
//                        System.out.println(fileBf[i]);
//                   }
                    //буду писать в один файлик с разделителем и ключем
                    boolean tresh = true;
                    for (int j = 1; j < 300; j++) {
                        try (BufferedInputStream bufferedInputStreamCrypt = new BufferedInputStream(new FileInputStream(FILE_OUT_PATH));

                             BufferedOutputStream bosDeCrypt = tresh ? new BufferedOutputStream(new FileOutputStream(FILE_OUT_BF)) :
                                     new BufferedOutputStream(new FileOutputStream(FILE_OUT_BF, true));

                        ) {
                            int keyCaesar = j;
                            keyCaesar = CeasarLib.getKeyCaesar(keyCaesar, listAlphabet.size());
                            int bufferSize = 1024;
                            byte[] buffer = new byte[bufferSize];
                            int bytesRead;

                            String step = "\n\nШаг " + j + "*****************************************************\n\n";
                            bosDeCrypt.write(step.getBytes());
                            bosDeCrypt.flush();

                            while ((bytesRead = bufferedInputStreamCrypt.read(buffer, 0, buffer.length)) != -1) {
                                String stt = new String(buffer, 0, bytesRead).toLowerCase(Locale.ROOT);
                                String cryptString2 = CeasarLib.Сrypt(listAlphabet, stt, keyCaesar);
                                String cryptString3 = CeasarLib.DeСrypt(listAlphabet, stt, keyCaesar);
                                bosDeCrypt.write(cryptString3.getBytes());
                                bosDeCrypt.flush();
                            }
                            tresh = false;


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    break;
                }
                default: {
                    break;
                }
            }
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
