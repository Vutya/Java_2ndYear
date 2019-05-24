package patterns.adapter;

public class Car {

    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String go() {
        return "brrrrrrrr, I'm " + name;
    }

    // Alt + Insert
    public String getName() {
        return name;
    }
}
