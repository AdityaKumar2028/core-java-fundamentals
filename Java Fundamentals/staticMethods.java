public class staticMethods {
    public static void main(String[] args) {
        System.out.println("main block called");
        staticMethods.staticMethod();
    }

    static void staticMethod() {
        System.out.println("static method 1 called");
    }

    static {
        System.out.println("static block called");
        staticMethods.staticMethod();
    }
}