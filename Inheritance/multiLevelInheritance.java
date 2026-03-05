
// parent inherits from java.lang.Object -> child inherits parent and child (multilevel)

public class multiLevelInheritance {
    public static void main(String[] args) {
        Child obj = new Child();
        obj.func1();
        obj.func2();
        System.out.println(obj.hashCode());
    }
}

class Parent {
    void func1() {
        System.out.println("I am parent");
    }
}

class Child extends Parent {
    void func2() {
        System.out.println("I am child inherit from my parent");
    }
}