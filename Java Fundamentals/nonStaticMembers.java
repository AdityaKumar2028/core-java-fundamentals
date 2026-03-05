public class nonStaticMembers {
    int num;

    nonStaticMembers() {
        System.out.println("inside the constructor");
    }

    {
        System.out.println("Inside non-static block");
    }

    public static void main(String[] args) {
        System.out.println("inside main method");
        new nonStaticMembers();
    }

}