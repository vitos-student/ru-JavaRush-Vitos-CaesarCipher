import java.util.Arrays;
import java.util.List;

public class Alphabet {

    private static final List ALPHABET = Arrays.asList('а', 'б','в','г', 'д', 'е', 'ж', 'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', ':', '!', '?', ' ');

    public Alphabet(){
       // this.ALPHABET
    }

    public static List<Character> getAlpabet() {
        return ALPHABET;
    }

}
