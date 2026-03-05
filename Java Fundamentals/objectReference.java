public class objectReference {
    objectReference() {
        System.out.println("called by Object Reference");
    }

    public static void main(String[] args) {
        objectReference obj = new objectReference();
        System.out.println(obj);

        objectReference obj2 = new objectReference();
        System.out.println(obj2);

    }
}
