package patterns.factory;

public class FactoryExample {

    public static void main(String[] args) {
        // фабрика создаёт объекты, когда не хочется или
        // неудобно предлагать создавать объекты с помощью конструктора

        AnimalFactory factory = new AnimalFactory();
        Animal cat = factory.createCat("Tom");
        Animal dog = factory.createDog("Bob");

        System.out.println(cat.greet());
        System.out.println(dog.greet());

        for (Cat c: factory.getAllCats())
            System.out.println(c.greet());
    }
}
