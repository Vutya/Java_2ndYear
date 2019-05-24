package patterns.adapter;

import patterns.factory.Animal;

public class AdpaterExample {

    public static void main(String[] args) {
        Car c = new Car("Lada");
        System.out.println(c.go());

        Animal a = new CarAdapter(c);
        System.out.println(a.greet());
    }
}
