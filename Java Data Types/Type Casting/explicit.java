public class explicit {
    public static void main(String[] args) {
        int i = 100;
        byte b = (byte) i;
        System.out.println(b);

        char ch = (char) i;
        System.out.println(ch);

        int num1 = -11;
        byte num2 = 101;

        System.out.println((byte) num1 + num2);

        int num3 = 200;
        byte num3B = (byte) num3;
        System.out.println(num3B);
        System.out.println((char) num3B * -1);
    }

}
