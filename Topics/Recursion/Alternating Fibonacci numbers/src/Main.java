import java.util.Scanner;

public class Main {

    public static long recursive(long n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public static long fib(long n){
        // write your code here
        if (n % 2 == 0) {
            return -1 * recursive(n);
        } else {
            return recursive(n);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fib(n));
    }
}