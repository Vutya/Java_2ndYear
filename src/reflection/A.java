package Reflection;

public class A {
    private String name;

    public A() {
        System.out.println("Constructor 1");
    }

    public A(String name) {
        System.out.println("Constructor 2");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sayHello(int x) {
        System.out.println("Hello, " + x);
    }
}
