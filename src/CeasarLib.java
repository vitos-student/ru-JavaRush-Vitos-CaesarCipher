import java.util.List;

public class CeasarLib {

    private static int NormalizerKeyCaesar(int keyCaesar, int size) {
        if (keyCaesar%size==0)
        {
            return 1;
        }
        else {
            if (keyCaesar > size) {
                return keyCaesar % size;
            } else {
                return keyCaesar;
            }
        }
    }

    public static int getKeyCaesar(int keyCaesar, int size){
        return NormalizerKeyCaesar(keyCaesar,size);
    }

    public static String DeСrypt(List<Character> listAlphabet, String str, int keyCaesar) {
        char[] newChar = new char[str.length()];
        int i = 0;
        for (char ch : str.toCharArray()) {
            int seek = keyCaesar;
            System.out.println("listAlphabet.indexOf(ch):"+listAlphabet.indexOf(ch));
            System.out.println("keyCaesar:"+keyCaesar);
            if ((listAlphabet.indexOf(ch) - keyCaesar)==0) {
                seek=0;
            }else
            if ((listAlphabet.indexOf(ch) - keyCaesar)<0) {
                seek = listAlphabet.size() +listAlphabet.indexOf(ch)- keyCaesar+1;
                System.out.println("ДОлжен быть тут  :" +seek);
            } else{
                if ((listAlphabet.indexOf(ch) - keyCaesar) > 0) {
                    seek = listAlphabet.indexOf(ch) - keyCaesar;
                } else {
                    seek = listAlphabet.size() - keyCaesar - 1;
                }
            }

            System.out.println(seek+"   "+ (new String(newChar)));
            newChar[i] = listAlphabet.get(seek);
            i++;
        }
        return new String(newChar);
    }


    public static String Сrypt(List<Character> listAlphabet, String str, int keyCaesar) {
        char[] newChar = new char[str.length()];
        int i = 0;
        for (char ch : str.toCharArray()) {
            int seek = keyCaesar;
            if ((listAlphabet.indexOf(ch) + keyCaesar) >= listAlphabet.size()) {
                seek = listAlphabet.indexOf(ch) + keyCaesar-listAlphabet.size()-1;
            } else {
                seek = listAlphabet.indexOf(ch) + keyCaesar;
            }
            newChar[i] = listAlphabet.get(seek);
            i++;
        }
        return new String(newChar);
    }

}
