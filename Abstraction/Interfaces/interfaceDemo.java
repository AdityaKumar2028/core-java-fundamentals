interface Area {
    void area();

    float pi = 3.14f;
}

class Rectangle implements Area {
    @Override
    public void area() {
        System.out.println("area of rectangle");
        System.out.println(pi * 2);
    }
}

public class interfaceDemo {
    public static void main(String[] args) {
        Area a = new Rectangle();
        a.area();

    }
}
