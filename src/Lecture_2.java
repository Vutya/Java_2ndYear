import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;

public class Lecture_2 {
    /*
    В Java 2 библиотеки для IO:
        - пакет java.io, старый, (синхронная библиотека); простой, но медленный
        - пакет java.nio, новый, желательно пользоваться им (асинхронная библиотека); посложнее, но быстрее
    Старый:
        InputStream (входной поток байт)
        OutputStream (выходной поток байт)
        Reader (входной поток символов)
        Writer (выходной поток символов)
    Байт - универсально, символ - менее универсально; один символ занимает разное количество байт.
    Символ в Java - элемент таблицы Unicode.
    Кодировка - таблица соответствия символа и числового представления, кода.
    ASCII, cp 866 (DOS), windows-1251 (Win), Koi8-r, utf8.
        ASCII   7bit
        0
        10      перевод строки
        13      возврат каретки
        32      пробел
        48      "0"
        57      "9"
        65      "A"
        116     "z"

        cp 866 - это ASCII со второй половиной (128-255, русские буквы и псевдографика)

        windows-1251    -//- русские буквы и буквы кириллической писмьенности

        utf-8 - совместное представление Unicode

        InputStream <-> Reader
        OutputStream <-> Writer - в обоих случаях указываем кодировки
        Кодировки по умолчанию - не знаем, что там используется (utf-8, windows-1251)

        Стараться всё хранить в utf-8

        Есть 2 мира: байты и символы, заботимся о кодировках между ними.

        Что делает InputStream
            - int read() - читает 1 байт (-1 есть конец)
            - int read(byte[] buf) - читает несколько байтов в буфер
            - close() - не вызывайте сами, пользуйтесь try(){}

        Что делает OutputStream
            - write(byte b)
            - write(byte[] buf)
            - close()
            - flush() - протолкнуть, заставить записать данные на устройство
        Reader, Writer - аналогично

        FileInputStream - IS, связанный с файлом
        FileOutputStream - OS, связанный с файлом
        FileReader, FileWriter - не рекомендуются к использованию, кодировку указать нельзя
        OutputStreamWriter - OS в Writer
        InputStreamReader - IS в Reader

        Прочитать текст из файла
        try (Reader in = new InputStreamReader(new FileInputStream("a.txt"), "utf8"))

        Buffered -> InputStream, OutputStream, Reader, Writer
        Сохраняет данные в промежуточном буфере, обёртка для производительности + доп возможности.

        В BufferedWriter есть newLine() - перевод строки

        try (BufferedReader in = new BufferedReader(new InputStreamReader("a.txt"), "utf8"))
            in.readerLine();

        Немного о java.nio
        Класс File <-> путь на диске в java.io
        Класс Path - путь в java.nio

        Можно превращать друг в друга
            new File("a.txt").toPath()
            сразу Path  Paths.get("...")
                        Paths.get("c:/windows")
                        Paths.get("c:/windows", "a.txt")
            Paths - вспомогательный класс, чтобы получить Path

        Класс Files - вспомогательный класс для работы с файлами
        Примеры методов: Files.move() (удобнее, чем File.move())
        Files.move(source, dest, ...)
        StandardCopyOption.REPLACE_EXISTING - одна из опций в move,

        Files.readAllBites(Path) - byte[]
        Раньше читали в цикле побайтово, ещё и размер массива надо было угадать
        Files.readAllLines(Path) - String[]
        Files.write(Path, byte[], ...), ... - опции
        Files.write(Path, коллекции_строк, charset, опции)
        Хотим прочитать файл как 1 строку?
        allBytes = Files.readAllBytes(...)
        byte[] -> String - new String(byte[], кодировка)
        String -> byte[] - String.getBytes(кодировки)
     */
    private static String CURRENT_DIR = System.getProperty("user.dir");

    private static void fileErrors(List<String> l) {
        for (int i = 0; i < l.size(); i++)
            moveFile(l.get(i), i);
    }

    private static void moveFile(String file, int i) {
        try {
            Path old = new File(CURRENT_DIR + "\\out\\" + file).toPath();
            Path copy = new File(CURRENT_DIR + "\\out\\" + (i + 1) + file).toPath();
            Files.move(old, copy, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Renaming is successful.");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Renaming failed: moving to nonempty directory.");
        } catch (IOException e) {
            System.out.println("Renaming failed, file name: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a.txt", "b.txt", "c.txt");
        fileErrors(list);
    }
}
