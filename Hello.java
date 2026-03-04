public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    static {
        System.out.println("Static Block Runs 1");
    }

    static {
        System.out.println("Static Block runs 2");
    }
}