public class applyThrow {
    public static int divideNum(int a, int b) throws ArithmeticException {
        int div = a / b;
        return div;
    }

    public static void main(String[] args) {
        try {
            System.out.println(divideNum(1, 0));
        } catch (ArithmeticException e) {
            System.out.println("Number division by zero is not allowed");
        }
    }
}