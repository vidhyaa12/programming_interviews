package basic;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World !");

        Map<Integer,String> vilians = new HashMap<>();
        vilians.put(1, "Kajko");
        System.out.println("Original map: " + vilians);

        // pre Java 8 version:
        String name;
        if (vilians.containsKey(2)) {
            name = vilians.get(2);
        } else {
            name = "Kokosz";
        }
        System.out.println("Name: " + name);

        // Java 8 version:
        name = vilians.getOrDefault(2, "Kokosz");
        System.out.println("Name: " + name);
    }
}
