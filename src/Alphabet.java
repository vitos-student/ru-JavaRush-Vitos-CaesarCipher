import java.util.Arrays;
import java.util.List;

public class Alphabet {

    /**
     * тут задаю алфавит
     */
    private static final List<Character> ALPHABETRU = Arrays.asList('а', 'б','в','г', 'д', 'е', 'ж', 'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', ':', '!', '?', ' ','\n','\r');

    public static List<Character> getAlpabet() {
        return ALPHABETRU;
    }

}
