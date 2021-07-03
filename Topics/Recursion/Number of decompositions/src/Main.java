import java.util.*;

class Main {
    public static void printPartitions(int target, int maxValue, String suffix) {
        if (target == 0) {
            System.out.println(suffix);
        } else {
            if (maxValue > 1) {
                printPartitions(target, maxValue - 1, suffix);
            }
            if (maxValue <= target) {
                printPartitions(target - maxValue, maxValue, suffix + " " + maxValue);
            }
        }
    }
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printPartitions(n, n, "");
    }
}