package basic;

/**
 * Created by vidhyaa on 08/12/17.
 */
public class InheritanceChecker {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.walk();
    }
}

class Animal {
    public void walk() {
        System.out.println("walk");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println("bark");
    }
}