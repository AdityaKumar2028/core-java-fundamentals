public class objectReference {
    int x;

    objectReference() {
        System.out.println(this);
        System.out.println(this.x);
    }

    public static void main(String[] args) {
        objectReference obj1 = new objectReference();
        objectReference obj2 = new objectReference();
    }
}
