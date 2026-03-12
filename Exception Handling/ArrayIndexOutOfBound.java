public class ArrayIndexOutOfBound {
    public static void main(String[] args) {
        int arr[] = { 10, 20, 30 };

        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception found ! " + e);
        } finally {
            System.out.println("I got executed");
        }

    }
}
