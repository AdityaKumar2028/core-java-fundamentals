import java.util.Scanner;

public class division {
    public static void main(String[] args) {
        int numerator, denominator, result;

        System.out.print("Enter the numerator: ");
        Scanner obj = new Scanner(System.in);
        numerator = obj.nextInt();
        System.out.print("Enter the denominator: ");
        denominator = obj.nextInt();
        try {
            result = numerator / denominator;
            System.out.println("Result is: " + result);
        } catch (Exception e) {
            System.out.println("Exception occured " + e);
        }

    }
}