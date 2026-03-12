public class applyThrows {

    public static boolean checkAge(int age) {
        if (age < 18)
            throw new Exception("Minor not allowed!");

        return true;
    }

    public static void main(String[] args) {

        System.out.println(applyThrows.checkAge(10));
    }
}
