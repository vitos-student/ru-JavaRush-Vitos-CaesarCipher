import java.util.List;
import java.util.Scanner;

public class CaesarApp {
    public static void main(String[] args) {
        String str = "пываа";
        char[] chars = str.toCharArray();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите секретный ключ, от 1 до 300:");
        int keyCaesar = scanner.nextInt();
        while (!controlKeyCaesar(keyCaesar)) {
            System.out.println("Ошибка ввода.");
            System.out.print("Введите секретный ключ, от 1 до 300:");
            keyCaesar = scanner.nextInt();
        };
        List<Character> listAlphabet = Alphabet.getAlpabet();
        System.out.println(listAlphabet);

        listAlphabet.size();

        for (int i = 0; i < listAlphabet.size(); i++) {
            System.out.print(listAlphabet.get(i) + "   ");
        }
        System.out.println();

        String cryptString=Сrypt(listAlphabet,str,keyCaesar) ;
        System.out.println("Было    :" + str);
        System.out.println("Стало   :" + cryptString);

        String newDecryptString = Сrypt(listAlphabet,cryptString,-keyCaesar);
        System.out.println();
        System.out.println();
        System.out.println("Было    :" + cryptString);
        System.out.println("Стало   :" + newDecryptString);

    }

    public static String Сrypt(List<Character> listAlphabet,String str,int keyCaesar) {
        char[] newChar = new char[str.length()];
        int i =0;
        for(char ch:str.toCharArray()){
            newChar[i]=listAlphabet.get(listAlphabet.indexOf(ch) + keyCaesar);
            i++;
        }
        return new String(newChar);
    }

    private static boolean controlKeyCaesar(int keyCaesar) {

        if (keyCaesar > 0 && keyCaesar <= 300) {
            return true;
        } else {
            return false;
        }
    }
}
