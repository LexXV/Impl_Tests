import java.util.*;

public class Main{
    /* Make an array named "vector" to store
        multiplying factorial values */
    public static ArrayList<Integer> vector = new ArrayList<Integer>();
    /* Make the function to multiply stored values */
    public static void multiply(int x) {
        int collector = 0; // Initialize collector for previous values
        int size = vector.size();
        for (int i = 0; i < size; i++) {
            int res = collector + vector.get(i) * x;
            /* Reduce values to avoid overfill in array */
            vector.set(i, res % 10);
            collector = res / 10;
        }
        while (collector != 0) {
            vector.add(collector % 10);
            collector /= 10;
        }
    }
    /* Make the function to find the sum of digits
        from n-value 100! (i.e. 100 factorial) */
    public static int SumOfDigits(int n) {
        vector.add(1); // Initialize "vector" array
        for (int i = 1; i <= n; i++) {
            multiply(i);
        }
        int sum = 0; // Initialize sum value
        int size = vector.size();
        for (int i = 0; i < size; i++) {
            sum += vector.get(i);
        }
        return sum;
    }
    /* Head method */
    public static void main(String[] args) {
        int n = 100;
        System.out.println(SumOfDigits(n));
    }
}
