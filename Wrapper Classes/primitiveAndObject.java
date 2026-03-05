public class primitiveAndObject {
    public static void main(String[] arg) {
        int x = 100;
        Integer y = Integer.valueOf(x);

        int result = y.intValue();
        System.out.println(result);
    }
}