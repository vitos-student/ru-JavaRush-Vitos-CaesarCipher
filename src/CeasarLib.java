import java.util.List;

public class CeasarLib {

    private static int NormalizerKeyCaesar(int keyCaesar, int size) {
        if (keyCaesar % size == 0) {
            return 1;
        } else {
            if (keyCaesar > size) {
                return keyCaesar % size;
            } else {
                return keyCaesar;
            }
        }
    }

    public static int getKeyCaesar(int keyCaesar, int size) {
        return NormalizerKeyCaesar(keyCaesar, size);
    }

    public static String DeСrypt(List<Character> listAlphabet, String str, int keyCaesar) {
        if (AlphabetEmpty(listAlphabet)) {
            Log_Lib.setLogCaesarApp("DeСrypt","Не задан алфавит");
           // System.out.println("Не задан алфавит");
            return null;
        }
        char[] newChar = new char[str.length()];
        int i = 0;
        for (char ch : str.toCharArray()) {
            int seek = keyCaesar;
            if ((listAlphabet.indexOf(ch) - keyCaesar) == 0) {
                seek = 0;
            } else if (listAlphabet.indexOf(ch) == 0) {
                seek = listAlphabet.size() + listAlphabet.indexOf(ch) - keyCaesar;
            } else if ((listAlphabet.indexOf(ch) - keyCaesar) < 0) {
                seek = listAlphabet.size() + listAlphabet.indexOf(ch) - keyCaesar + 1;
            } else {
                if ((listAlphabet.indexOf(ch) - keyCaesar) > 0) {
                    seek = listAlphabet.indexOf(ch) - keyCaesar;
                } else {
                    seek = listAlphabet.size() - keyCaesar - 1;
                }
            }
            if (seek >= listAlphabet.size()) {
                seek -= listAlphabet.size();
            }
            // System.out.println("listAlphabet.indexOf(ch)  :"+listAlphabet.indexOf(ch));
            // System.out.println("keyCaesar                 :"+keyCaesar);
            //  System.out.println(seek + "   " + (new String(newChar)));
            newChar[i] = listAlphabet.get(seek);
            i++;
        }
        return new String(newChar);
    }


    public static String Сrypt(List<Character> listAlphabet, String str, int keyCaesar) {
        if (AlphabetEmpty(listAlphabet)) {
            Log_Lib.setLogCaesarApp("Сrypt","Не задан алфавит");
          //  System.out.println("Не задан алфавит");
            return null;
        }
        char[] newChar = new char[str.length()];
        int i = 0;
        for (char ch : str.toCharArray()) {
            int seek = keyCaesar;

            if (!listAlphabet.contains(ch)) {
                Log_Lib.setLogCaesarApp("Сrypt","В тексте присутствуют иные символы от алфавита");
                break;
            }
            if ((listAlphabet.indexOf(ch) + keyCaesar) == listAlphabet.size()) {
                seek = 0;
            } else if ((listAlphabet.indexOf(ch) + keyCaesar) > listAlphabet.size()) {
                seek = listAlphabet.indexOf(ch) + keyCaesar - listAlphabet.size() - 1;
            } else {
                seek = listAlphabet.indexOf(ch) + keyCaesar;
            }
            newChar[i] = listAlphabet.get(seek);
            i++;
        }
        return new String(newChar);
    }

    public static boolean AlphabetEmpty(List<Character> listAlphabet) {
        if (listAlphabet.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
