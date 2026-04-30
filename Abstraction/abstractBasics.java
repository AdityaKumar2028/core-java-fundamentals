abstract class shape {
    abstract void area();

    void sayHi() {
        System.out.println("shape says hi!");
    }
}

class Circle extends shape {
    @Override
    void area() {
        System.out.println("display circle area");
    }
}

public class abstractBasics {
    public static void main(String[] args) {
        shape s = new Circle();
        s.area();
        s.sayHi();
    }
}