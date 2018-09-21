import java.util.Scanner;

public class Lecture_1 {
    // Exceptions
    /*
    Пример:
    f = open('f.txt')
    if (lat.error != 0)
        print(last.error.log(last.error()))
        return
    txt = read(f)
    if как выше, ещё и close добавить на проверку
    close(f)

    f = open("f.txt")
    txt = read(f)
    close(f)
    Если тут возникнет исключение, то дальше код не выполняется

    Метод в Java
    public void f(...) {
        появилось исключение:
            -обработка (public void f(...) throws Exception{})
            -явно отказываемся обрабатывать ошибку (тот, кто вызывает f, опять решает, если в main(String[] args)
            throws Exception, т.е. Java обработает: e.printStackTrace())
    }

    Как обработать:
    void f(){
        try (
        exception
        ) catch (Exception e) {
            если внутри try был exception, то выполняется этот блок
            e.printStackTrace() выводит информацию об ошибке
        }
    }

    Можно выбросить исключение throwable, их несколько видов: есть error, есть exception
    Error мб OutOfMemoryError, смысла рыпаться и пытаться исправить особо нет
    Exception всё остальное, их имеет смысл обработать и проверить (RuntimeException[DivisionByZeroException,
    ArrayOutOfBoundException, NullPointerException, ...], IOException, FileNotFoundException)
    Обработка RuntimeException - плохой стиль

    Полная форма try-catch
    try {

    } catch (FileNotFoundException e) {
    } catch (IOException e) {
    } catch (...Exception e) {
    } finally {
        выполняется всегда после try и мб catch, выполняется даже после return в try и catch
        желательно не делать return и в try/catch, и в finally одновременно, мб непредсказуемо сработает
    }
    С Java 7 можно написать try (...), в скобках заводим переменную, они точно будут close()

    Оператор throw e;
    throw new Exception ("error")
    throw new Excpetion()
    public class MyException extends Exception {
    }
    throw new MyException()
    catch (MyException e) {
    }

    Читать с клавиатуры
    s = new Scanner(System.in)
    s.nextLine()
     */

    /*
    Задача 1.
    void f(int x) throws Exception {
    функция чётная - исключение, нечётная - ничего
    }
    В main for i=1:10
        f(i)
    Вывести: 1 - нет исключения, 2 - есть исключение
     */

    private static void f(int x) throws Exception{
        if (x % 2 == 0)
            throw new Exception("NumberIsEvenException");
    }

    /*
    Задача 2.
    Метод Integer.parseInt("42") - строка в число
    Надо функцию readInt(String message)
    Использование:
    int x = readInt("Введите число")
    int y = тоже самое
    sout ("x+y=" + (x+y))
     */

    private static int readInt(String message) {
        Scanner s = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++)
            try {
                f(i);
                System.out.println(i + " - нет исключения");
            } catch (Exception e) {
                System.out.println(i + " - есть исключение");
            }

        int x = readInt("Введите x");
        int y = readInt("Введите y");
        System.out.println("x + y = " + (x+y));
    }
}
