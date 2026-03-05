public class objectAndString {
    public static void main(String[] a) {
        long x = 1000;
        Long y = Long.valueOf(x);
        String s = y.toString();
        Long z = Long.valueOf(s);
        System.out.println(z);

    }
}
