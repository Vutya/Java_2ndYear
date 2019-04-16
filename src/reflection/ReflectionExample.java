package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, InvocationTargetException, NoSuchMethodException {
        String classname = "reflection.A";
        Class<?> aClass = Class.forName(classname);

        // ещё один способ получить ссылку на класс: String.class
        // это аналогично Class.forName("java.lang.String")
        Class<String> stringClass = String.class;
        // Т.е. тип Class параметризован тем классом, который он представляет
        // Но при использовании Class.forName мы, когда пишем программу,
        // не знаем, какой там будет тип, поэтому приходится писать
        // Class<?> или Class<? extends Object>

        // Итак, в переменной aClass мы храним информацию о класса А
        // Будет Object, потому что заранее не знаем, какой объект загрузится
        Object a1 = aClass.newInstance(); // outputs 'constructor 1'
        System.out.println(a1 instanceof A); // true

        // вызовем другой конструктор
        // способ не очень хороший, но сейчас сойдёт
        Constructor<?>[] aConstructors = aClass.getConstructors();
        Constructor<?> secondConstructor = aConstructors[1];
        Object a2 = secondConstructor.newInstance("Wow"); // outputs 'constructor 2'

        // System.out.println(((A) a2).getName()); // Здесь надо заранее знать класс А
        Method getName = aClass.getMethod("getName"); // Метод getName без параметров
        System.out.println(getName.invoke(a2));

        Method sayHello = aClass.getMethod("sayHello", int.class);
                                // метод sayHello с int-аргументом
        sayHello.invoke(a2, 42); // a2.sayHello(42);
    }
}
