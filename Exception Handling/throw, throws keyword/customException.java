class customError extends Exception {
    customError(String message) {
        super(message);
    }
}

public class customException {
    static void checkAge(int age) throws customError {
        if (age < 18)
            throw new customError("Minor Not Allowed!");

        else
            System.out.println("Valid user");
    }

    public static void main(String[] args) {
        try {
            checkAge(20);
        } catch (customError e) {
            System.out.println("Error");
        }
    }
}
