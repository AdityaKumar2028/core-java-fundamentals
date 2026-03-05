public class singleInheritance {
    // java implict inherit java.lang.Object
    void sayHi() {
        System.out.println("Hi!");
    }

    public static void main(String[] args) {
        singleInheritance obj = new singleInheritance();
        obj.sayHi();
        obj.hashCode();
    }
}