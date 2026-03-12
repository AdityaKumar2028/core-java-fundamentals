import java.util.Scanner;

public class basics {
    static public void main(String[] args) {
        // declare array
        int[] arr;
        int arr2[] = { 10, 20, 30 };

        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the size of array: ");
        int n = obj.nextInt();

        int arr3[] = new int[n];
        for (int elem : arr3) {
            System.out.println(elem);
        }

        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = obj.nextInt();
        }

        for (int elem : arr3) {
            System.out.println(elem);
        }

        System.out.println("Code ended");

    }
}