package Java12Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class NewFeatures {

    // private var field = 10;
    // нельзя var поля, только локальные переменные

    public static void main(String[] args) {
        int x = 10;
        var y = 20; //! ключевое слово var
        Map<String, List<Integer>> m = new HashMap<>();
        var m2 = new HashMap<String, List<Integer>>();

        // var z; // тип не вывести

        // доп возможности стандартной библиотеки
        System.out.println("abc".repeat(10));
        System.out.println("    abc     ".strip());
        System.out.println("    abc     ".stripLeading());
        System.out.println("    abc     ".stripTrailing());

        // Files вспомогательные функции для работы с файлами
        try {
            String aText = Files.readString( // С 11 версии
                    Path.of("a.txt"), // Path.of(File) с 11 версии
                    StandardCharsets.UTF_8
            );
            System.out.println(aText);
        } catch (Exception e) {
            System.out.println("Oh no");
        }

        // Files.writeString
        // Files.lines() - чтение строк файла, было ещё в 8 версии

        System.out.println("abc\nxyz\n111"
                .lines() // поток строк из String, нет в 8 Java
                .count()
        );

        "abc\nxyz\n111"
                .lines()
                .filter(
                        Pattern.compile("\\d+").asMatchPredicate() // since 11 Java
                )
                .forEach(System.out::println);

        // более простое создание коллекций
        var list1 = List.of("123", "444", "555");
        var list2 = List.of(12, 33, 44, 555);
        var set1 = Set.of("a", "v");
        // это неизменяемы коллекции
        // какие-то очень крутые свитчи

    }
}

/*
Мы всё время сидели на 8 версии
Что в ней сейчас происходит?
Большинство ещё остаётся на 8
Раньше между версиями было много времени, а после 8 они начали как все часто обновляться.
Сейчас они выходят два раза в год. Сейчас недавняя - 12.
Более устойчивая(с длительной поддержкой LTS) должна быть каждая третья.8, 11, 14…
Если для себя программируем, можно брать самую последнюю, если что-то для кого-то куда-то лучше lts.
Тут проблемы у фирм, обновляться или нет и когда, всякое сломаться может от перехода.
• Стало много непоняток между программистами по бесплатности джавы
До сих пор можно пользоваться бесплатно для коммерческих целей, но откуда её взять, какая позволяет?
Раньше скачивали с оракла, правильно и аптудейт, а сейчас не рекомендуется
оттуда брать, её только для некоммерческих целей можно.
Как устроена бесплатная джава - есть openJDK - исходный код джавы вообще.
Нам с вами не хочется компилировать исходный код, нужно брать штуку от фирмы, которая это для нас делает
 со своими исправлениями и дополнениями. Оракл вот, говорили. Есть проект Adopt OpenJDK, RedHet,
 какой-то азуль(Aesl?? - просит за помощь с штуками деньги, а так бесплатна).
• Между версиями добавляются языковые возможности, инфраструктура, стандартная библиотека
• Стали активно убирать всякие части
• Не могут что-то убрать, но рекомендует не пользоваться лол и со временем они удаляют вообще
 */
